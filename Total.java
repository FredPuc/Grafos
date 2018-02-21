import java.io.*;

class Grafo{
   public int matriz[][];
   public int nVertices;
   
   Grafo(){
      
   }
   
   Grafo(int nVertices){
      this.nVertices = nVertices;
      this.matriz = new int[nVertices][nVertices];
   }
      
   public void valor(){
      for(int i = 0;i<nVertices;i++){
         for(int j = 0;j<nVertices;j++){
            System.out.print(matriz[i][j] + " ");
         }
         System.out.println();
      }      
   }
   
   public boolean verAresta(int v1, int v2){
      boolean tof = false;
      if(matriz[v1][v2] > 0){
         tof = true;
      }
      return tof;
   }
   
   public int totalArestas(){
      int cont = 0;
      for(int i = 0;i<nVertices;i++){
         for(int j = 0;j<nVertices;j++){
            if(matriz[i][j] > 0 && matriz[j][i] > 0){
               cont++;
            }
         }
      }
      return cont/2;      
   }
   
   public boolean verGrafoCompleto(){
      boolean tof = true;
      for(int i = 0;i<nVertices;i++){
         for(int j = 0;j<nVertices;j++){
            if(matriz[i][j] == 0){
               return tof = false;
            }
         }
      }
      return tof;      
   }


   public void addAresta(int v1, int v2, int peso){
      matriz[v1][v2] = peso; 
      matriz[v2][v1] = peso;
   }
   
   public int grauVertice(int v1){
      int cont = 0;
      for(int i = 0;i<nVertices;i++){
         if(matriz[v1][i] > 0){
            cont++;
         }
      }
      return cont; 
   }
   
   public int[][] matrizComplemento(){
      int complemento[][];
      complemento = new int [nVertices][nVertices]; 
      for(int i = 0;i<nVertices;i++){
         for(int j = 0;j<nVertices;j++){
            if(matriz[i][j] == 0){
               complemento[i][j] = 1;
            }
            
         }
      }
      return complemento;      
   }
   
   public void caminhoComplemento(){
      int[][] mat = matrizComplemento();
            
      
   }

   
}

/*class Aresta{
   //atributos
   public int peso = 0;
   public int v1;
   public int v2;
	
   //construtores da class
   public Aresta(){
   
   }
	
   public Aresta(int peso, int v1, int v2){
      this.peso = peso;
      this.v1 = v1;
      this.v2 = v2;
   }
   
}*/


public class Total{
   public static void main(String[]args){
      Grafo g = new Grafo(6);
      g.addAresta(0,5,1);
      g.addAresta(0,4,1);
      g.addAresta(0,1,4);
      g.addAresta(1,4,1);
      g.addAresta(1,3,1);
      g.addAresta(4,5,4);
      g.addAresta(4,3,8);
      g.addAresta(5,3,1);
      g.addAresta(3,2,3);
      g.addAresta(5,2,4);
      g.valor();
      System.out.println("\n" + g.totalArestas());
      System.out.println("\n" + g.verAresta(0,1));
      System.out.println("\n" + g.verAresta(4,5));
      System.out.println("\n" + g.verGrafoCompleto());
      System.out.println("\n" + "grau:v1: "+g.grauVertice(0));
      System.out.println("\n" + "grau:v4: "+g.grauVertice(3));
      
   }
}