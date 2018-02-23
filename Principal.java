/**
 * PUC Minas - Ciência da Computação 
 * Algoritmos em Grafos 2018/1
 * Frederico - 5415	07
 */
import java.util.*;
import java.io.*;
/**
* Classe que cria e gerencia as arestas do Grafo
*/
class Edge {
	
	private int weight;	
	private Vertice next;

	public Edge (){
		this.weight = 0;
		this.next = null;
	}
	/**
	* [Cria a aresta com seus atributos]
	* @param  weight [peso da aresta]
	* @param  next   [apontador para o proximo vertice]
	* @return        [Cria a aresta]
	*/
	public Edge (int weight, Vertice next){
		this.weight = weight;
		this.next = next;
	}
	public void setWeigth (int weight){
		this.weight = weight;
	}
	public int getWeigth(){
		return this.weight;
	}
	public void setNext(Vertice next){
		this.next = next;
	}
	public Vertice getNext(){
		return this.next;
	}
}

/**
* Classe que cria e gerencia os vertices do Grafo
*/
class Vertice{
	private int degree, element;
	private List<Edge> edges;
	
	/**
	 * Construtor do vertice com o nome peso e uma lista de aresta que apontara para os vertices
	 * @param  element [Nome ou numero do vertice]
	 * @return         [Retorna o vertice criado]
	 */

	public Vertice( int element ){	
		this.element = element;
		this.degree = 0;
		edges = new LinkedList<Edge>();
	}
	public void setDegree(int degree){
		this.degree = degree;
	}
	public int getDegree(){
		return this.degree;
	}
	public void setElement(int element){
		this.element = element;
	}
	public int getElement(){
		return this.element;
	}

	/**
	 * [Verifica se existe uma aresta do Vertice atual para outro vertice qualquer]
	 * @param  i [identificador do vertice de destino da busca]
	 * @return   [True se existir aresta entre os vertices, false caso contrario]
	 */
	public boolean exist(int i){
		boolean resp = false;
		for (Edge e : edges) {
			resp |= (e.getNext().getElement() == i); 
		}
		return resp;
	}
	/**
	 * [Printa todas as arestas não repetidas do grafos]
	 */
	public void printEdges (){
		for (Edge e : edges) {
			if (element < e.getNext().getElement()) {
				System.out.println(element + ","+e.getNext().getElement()+","+e.getWeigth());
			}
		}
	}

	/**
	 * [Conta o numero de arestas que saem do Vertice]
	 * @return [retorna o grau do vertice]
	 */
	public int getAllEdges(){
		return edges.size();
	}
	
	/**
	 * [Adiciona aresta entre dois vertices, o atual e o passado como parametro; v1.addEdge(peso,v2)]
	 * @param weight [peso da aresta entre os vertices]
	 * @param v2     [vertice para qual o vertice atual vai criar uma aresta]
	 */
	public void addEdge(int weight, Vertice v2){
		Edge e = new Edge(weight, v2);
		edges.add(e);
		this.degree ++;
	}
}

/**
 * Classe de grafo em matriz
 */
class GrafoMatriz {
	private Edge graph[][];
	private int nVertices, qntEdges;

	public GrafoMatriz(int nVertices){
		this.nVertices = nVertices;
		for (int i = 0; i < this.nVertices; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				graph[i][j] = new Edge();
			}
		}
	}

	public void addEdge(int v1, int v2, int weight){
		graph[v1][v2].setWeigth(weight);
		graph[v2][v1].setWeigth(weight);
		this.qntEdges ++;
	}
	
	public int qntEdges(){
		return this.qntEdges;
	}

	public int getNumVertices (){
		return this.nVertices;
	}
	public boolean isCompleted(){
	 	boolean resp = true;
	 	for (int i = 0; i < this.nVertices; i++) {
	 		for (int j = 0; j < this.nVertices; j++) {
	 			if (i != j) {
	 				resp &= (this.graph[i][j].getWeigth() != 0); 
	 			}
	 		}
	 	}
		return resp;
	}

	public GrafoMatriz complementar(){
		GrafoMatriz compl = new GrafoMatriz(this.nVertices);
		for (int i = 0; i < this.nVertices; i++) {
	 		for (int j = 0; j < this.nVertices; j++) {
	 			if (this.graph[i][j].getWeigth() != 0 && i != j) {
	 				compl.graph[i][j].setWeigth(0);
	 			}
	 			else if(this.graph[i][j].getWeigth() == 0 && i != j){
	 				compl.graph[i][j].setWeigth(1);
	 			}
	 		}
	 	}
	 	return compl;
	}

}
/**
 * Classe de grafo em Lista
 */
class Grafo{
	public Vertice[] vList;
	private int qntEdges;
	/**
	 * [constutor do grafo]
	 * @return [retorna uma lista de vertices]
	 */
	public Grafo(int tamanho){
		this.vList = new Vertice[tamanho];
		for (int i = 0; i < tamanho; i++) {
			this.vList[i] = new Vertice(i);
		}
	}

	/**
	 * [Verifica se os vertices passado como parametro sao validos e adiciona uma Aresta entre os dois]
	 * @param v1     [Vertice de origem]
	 * @param v2     [Vertice de Destino]
	 * @param weight [Peso da aresta]
	 */
	public void addEdge (int v1, int v2, int weight){
		if (v1 < this.vList.length && v2 < this.vList.length) {
			vList[v1].addEdge(weight, vList[v2]);
			vList[v2].addEdge(weight, vList[v1]);
			this.qntEdges++;
		}
		else{
			System.out.println("ERRO: Pelo menos um vertice selecionado é invalido ou não existe!");
		}
			
	}

	/**
	 * [Retorna o valor todal te arestas adicionadas no gravo]
	 * @return [O atributo de que soma as arestasdo grafo]
	 */
	public int qntEdges(){
		return this.qntEdges;
	}
	/**
	 * [Mostra o tamanho da lista, deixando claro quantos vertices existem no grafo]
	 * @return [retorna a quantidade de Vertices]
	 */
	public int getSize(){
		return vList.length;
	}

	/**
	 * [Compara o tamanho do grafo com o o grau de cada vertice para saber se o grafo é completo]
	 * @return [true caso o grafo seja completo, false caso contrario]
	 */
	public boolean isCompleted(){
		boolean resp = true;
		for (int i = 0; i < getSize(); i++) {
			resp = (vList[i].getDegree()/2 == getSize()-1) ? true : false;
			if (!resp) {
				i = getSize();
			}
		}
		return resp;
	}

	/**
	 * [Percorre o grafo e mostra os valores de cada vertice]
	 */
	public void mostarGrafo(){
		for (int i = 0; i < getSize(); i++) {
			vList[i].printEdges();
				
		}
	}

	/**
	 * [Metodo que verifica quias arestas faltam em um grafo e cria ela em um novo grafo]
	 * @return [Gera um grafo complementar, com as arestas necessarias para o grafo original ser completo]
	 */
	public Grafo complementar (){
		Grafo comp = new Grafo (getSize());
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (!vList[i].exist(j) && i != j) {
					comp.vList[i].addEdge(1, comp.vList[j]);
				}
			}
		}
		return comp;
	}
}
/**
 * Classe principal para execultar os metodos das classes anteriores
 */
class Principal{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		Grafo g;
		String line;
		String splt[] = new String[3];

		line = s.nextLine();
		if (Integer.parseInt(line) == 0) {
			line = s.nextLine();
			g = new Grafo(Integer.parseInt(line));
			while (!(line = s.nextLine()).equals( "FIM")) {
				splt = line.split(",");
				g.addEdge(Integer.parseInt(splt[0]),Integer.parseInt(splt[1]),Integer.parseInt(splt[2]));
			}
			System.out.println(g.vList[Integer.parseInt(line = s.nextLine())].getDegree());
			line = s.nextLine();
			int v1 = Integer.parseInt(line.charAt(0) + "");
			int v2 = Integer.parseInt(line.charAt(2) + "");
			if (g.vList[v1].exist(v2)) {
				System.out.println("SIM");				
			}
			else {
				System.out.println("NAO");				
			}
			System.out.println(g.qntEdges());
			if (g.isCompleted()) {
				System.out.println("SIM");
			}
			else{
				System.out.println("NAO");
			}
			Grafo g2 = g.complementar();
			g2.mostarGrafo();

		}
		else if (Integer.parseInt(line) == 1) {
			
		}
		s.close();
	}
}