import java.util.*;
import java.io.*;
/**
 * Classe que cria e gerencia os vertices do Grafo
 */
class Vertice{
	private int degree, element;
	private List<Edge> edges;
	private Edge e;
	/**
	 * Classe que cria e gerencia as arestas do Grafo
	 */
	class Edge {
	
		private int weight;	
		private Vertice next;
	
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
			resp |= (e.next.getElement() == i); 
			
		}
		return resp;
	}

	public void printEdges (){
		int i = 0;
		for (Edge e : edges) {
				System.out.println(element + ","+e.next.getElement()+","+e.getWeigth());
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

class Grafo{
	public Vertice[] vList;
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
		}
		else{
			System.out.println("ERRO: Pelo menos um vertice selecionado é invalido ou não existe!");
		}
			
	}
	/**
	 * [Verifica se existe o vertice precurado no Grafo]
	 * @param  element [Identificação do vertice buscado]
	 * @return         [Retorna o endereço do vertice buscado[ ou null caso o vertice não exista no grafo]
	 */
	public Vertice getVertice(int element){
		Vertice resp = null;
		if (vList.length == 0) {
			System.out.println("O Grafo não possui Vertices.");
			return resp;
		}
		else{
			for (Vertice v : this.vList) {
				if (v.getElement() == element) {
					resp = v;
					return resp;
				}
			}
			System.out.println("O Vertice ["+element+"] não existe.");
			return resp;
		}
	}

	/**
	 * [Percorre o gafo contado quantas arestas existem no grafo]
	 * @return [description]
	 */
	public int allEdges(){
		int resp = 0;
		for (int i = 0; i < getSize(); i++) {
			resp += vList[i].getAllEdges();
		}
		return resp/2; //divite por 2 para ignorar as ligações de ida e volta, considerando apenas uma delas
	}
	/**
	 * [Mostra o tamanho da lista, deixando claro quantos vertices existem no grafo]
	 * @return [description]
	 */
	public int getSize(){
		return vList.length;
	}

	public boolean isCompleted(){
		boolean resp = false;
		for (int i = 0; i < getSize(); i++) {
			if (vList[i].getDegree() == getSize()-1) {
				resp = true;
			}
			else {
				resp = false;
				return resp;
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
		String spli[] = new String[3];

		line = s.nextLine();
		if (Integer.parseInt(line) == 0) {
			line = s.nextLine();
			g = new Grafo(Integer.parseInt(line));
			while (!(line = s.nextLine()).equals( "FIM")) {
				spli = line.split(",");
				g.addEdge(Integer.parseInt(spli[0]),Integer.parseInt(spli[1]),Integer.parseInt(spli[2]));
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
			System.out.println(g.allEdges());
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

		

		

			
		
		//System.out.println(g.getSize());

	}
}