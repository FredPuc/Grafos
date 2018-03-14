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
	public Vertice next;

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
	private int degree, color; //color 0 = branco, 1 cinza, 2 preto.
	private String element, label;
	private int cont; 
	public List<Edge> edges;
	public Vertice dad; // pai = o indece do vertice pai na lista.
	public int initial, end;

	
	/**
	 * Construtor do vertice com o nome peso e uma lista de aresta que apontara para os vertices
	 * @param  element [Nome ou numero do vertice]
	 * @return         [Retorna o vertice criado]
	 */

	public Vertice( String element ){
		this.element = element;
		this.degree = 0;
		this.label = "";
		this.color = 0;
		this.dad = null;
		edges = new LinkedList<Edge>();
	}

	public void setDegree(int degree){
		this.degree = degree;
	}
	public int getDegree(){
		return this.degree;
	}
	public void setElement(String element){
		this.element = element;
	}
	public String getElement(){
		return this.element;
	}
	public void setLabel(String label){
		this.label = label;
	}
	public String getLabel(){
		return this.label;
	}
	public void setColor(int color){
		this.color = color;
	}
	public int getColor(){
		return this.color;
	}
	public void setDad(Vertice dad){
		this.dad = dad;
	}
	public Vertice getDad(){
		return this.dad;
	}

	/**
	 * [Verifica se existe uma aresta do Vertice atual para outro vertice qualquer]
	 * @param  i [identificador do vertice de destino da busca]
	 * @return   [True se existir aresta entre os vertices, false caso contrario]
	 */
	public boolean exist(String i){
		boolean resp = false;
		for (Edge e : edges) {
			resp |= ((e.getNext().getElement()).equals(i)); 
		}
		return resp;
	}
	/**
	 * [Printa todas as arestas não repetidas do grafos]
	 */
	public void printEdges (){
		for (Edge e : edges) {
			if (cont % 2 == 0) {
				System.out.println(element + ","+e.getNext().getElement()+","+e.getWeigth());
			}
			cont ++;
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
 * Classe de grafo em Lista
 */
class Grafo{
	public Vertice[] vList;
	private int qntEdges;
	public int timeStamp;
	/**
	 * [constutor do grafo]
	 * @return [retorna uma lista de vertices]
	 */
	public Grafo(int tamanho){
		this.vList = new Vertice[tamanho];
		for (int i = 0; i < tamanho; i++) {
			this.vList[i] = new Vertice(i+"");
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
	 * Mostra todos os vertices do grafo
	 */
	public void mostarVertices(){
		for (int i = 0; i < getSize(); i++) {
			System.out.println("Vertice ["+i+"]: "+vList[i].getElement());
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
				if (!vList[i].exist(j+"") && i != j) {
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
class Labirinto{
	public static Scanner s = new Scanner(System.in);
	public static Grafo g;
	public static List<Vertice> vl;
	/**
	 * Faz a leitura do labirinto e, com ajuda de alguns metodos auxiliares, 
	 * monta um grafo com as informaçoes do labirinto
	 */
	public static void getGraph (){
		Vertice v;
		vl = new LinkedList <Vertice>();
		String dim[] = new String[2];
		String line = s.nextLine();
		dim = line.split(",");
		//percorre a entrada e identifica todos os vertices que não são paredes
		//e adiciona em uma lista apenas os vertices que sao caminhos.
		for (int i = 0; i < Integer.parseInt(dim[0]); i++) {
			line = s.nextLine();
			for (int j = 0; j < Integer.parseInt(dim[1]); j++) {
				if (line.charAt(j) != '0') {
					v = new Vertice(i+","+j);
					v.setLabel(line.charAt(j)+"");
					vl.add(v);

				}
			}
		}
		//Pega a lista construido anteriiormente e insere ela no grafo
		//e faz a ligação das arestas para montar os caminhos possiveis  
		g = new Grafo(vl.size());
		String aux[] = new String[2];
		for (int i = 0; i < g.getSize(); i++) {
			g.vList[i] = vl.get(i);
			aux = g.vList[i].getElement().split(",");
			int auxi = Integer.parseInt(aux[0]);
			int auxj = Integer.parseInt(aux[1]);

			if (existSup(auxi, auxj) != -1) {
				g.addEdge(i, existSup(auxi, auxj), 1);
				g.addEdge(existSup(auxi, auxj), i, 1);
			}

			if (existAnt(auxi, auxj) != -1) {
				g.addEdge(i, existAnt(auxi, auxj),1);
				g.addEdge(existAnt(auxi, auxj), i,1);	
			}
		}
	}


	/**
	 * Pega as coordenadas do vertice atual e verifica se existe um vertice caminho na posição superior
	 * a ele do labirinto
	 * @param  i [coordenada x]
	 * @param  j [coordenada y]
	 * @return   [retorna o indice do vertice superior caso exista]
	 */
	public static int existSup (int i , int j){
		String aux1 = i+","+j;
		int resp = -1; 
		i = i-1;
		String aux = i+","+j;

		for (int x = 0; x < g.getSize(); x++) {
			if (g.vList[x].getElement().equals(aux) && g.vList[x].getElement() != aux1) {
				resp = x;
			}
		}
		return resp;
	}

	/**
	 * Pega as coordenadas do vertice atual e verifica se existe um vertice caminho na posição anterior
	 * a ele do labirinto
	 * @param  i [coordenada x]
	 * @param  j [coordenada y]
	 * @return   [retorna o indice do vertice superior caso exista]
	 */
	public static int existAnt (int i , int j){
		String aux1 = i+","+j;
		int resp = -1; 
		j = j-1;
		String aux = i+","+j;
		for (int x = 0; x < g.getSize(); x++) {
			if (g.vList[x].getElement().equals(aux) && g.vList[x].getElement() != aux1) {
				resp = x;
				x = g.getSize();
			}
		}
		return resp;
	}
	/**
	 * Inicializa a busca em profundidade
	 * @param initial [Ponto de partida do labirinto]
	 * @param end     [Ponto de chegada do labirinto]
	 */
	public static void dfs (int initial, String end){
		g.timeStamp = 0;
		dfs(g.vList[initial], end);
	}

	/**
	 * Aplica a busca em profundidade
	 * @param initial [Ponto de partida do labirinto]
	 * @param end     [Ponto de chegada do labirinto]
	 */
	public static boolean dfs(Vertice v, String end){
		boolean flag = true;
		g.timeStamp ++;
		v.initial = g.timeStamp;
		v.setColor(1);
		for (int i = 0;i < v.getAllEdges() && flag; i++) {
			if (v.edges.get(i).next.getColor() == 0) {
				v.edges.get(i).next.setDad(v);
				if (!v.getLabel().equals(end)) {
					flag &= dfs(v.edges.get(i).next, end);					
				}
				else {
					flag = false;
					flag &= dfs(v.edges.get(i).next, end);
				}
			}
		}
		v.setColor(2);
		g.timeStamp++;
		v.end = g.timeStamp;
		return flag;
	}

	/**
	 * Roda o progama =D
	 * @param args \o/
	 */
	public static void main(String[] args) {
		getGraph();
		int initial = 0, end = 0;
		for (int i = 0; i < g.getSize(); i++) {
			if (g.vList[i].getLabel().equals("I")) {
				initial = i;
			}
			if (g.vList[i].getLabel().equals("F")) {
				end = i;
			}
		}
		dfs(initial, "F");
		String resp = g.vList[end].getElement();
		int index = 0;
		Grafo aux = g;
		while (aux.vList[end].getDad() != null) {
			resp += " "+aux.vList[end].getDad().getElement();
			aux.vList[end] = aux.vList[end].getDad();
			index++;
		}
		String out[] = new String[index]; 
		out = resp.split(" ");
		for (int i = 0; i < out.length ; i++) {
			System.out.println(out[out.length - i - 1]);
		}
		s.close();
	}
}