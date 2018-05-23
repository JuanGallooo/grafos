package grafos;

import java.util.ArrayList;

public class GrafoMatrizNoDirigido<T> {
	
	private int matriz[][];
	private int numeroVertices;
	private Vertice<T>[] listaVertices;
	
	
	public GrafoMatrizNoDirigido(int numVertices, Vertice<T>[] lista){
		// TODO Auto-generated constructor stub
		listaVertices = lista;
		numeroVertices = numVertices;
		matriz =  new int[numVertices][numVertices];
        for(int i=0; i< numeroVertices; i++){
            for(int j=0; j< numeroVertices; j++){
                matriz[i][j] = 0;
            }            
        }
    }
	
	public void insertarArista(T elemento1, T elemento){
		
 
	}
	
	public int buscarIndiceMatriz(T elemento){
		boolean encontro = false;
		int numEncontrado = 0;
	     for(int i=0; i< numeroVertices && !encontro; i++){
	    	 if(elemento.equals(listaVertices[i])){
	    		 encontro = true;
	    		 numEncontrado = i;
	    	 }
	     }
	     if(encontro){
	    	 return numEncontrado;
	     }
	     else{
	    	 return -1;
	     }   
	}
	
	public void insertarVertice(Vertice<T> n){
		
	}
	
	public boolean contieneLaArtista(AristaNoDirigida<T> n){
		return true;
	}
	public AristaNoDirigida<T> eliminarArista(T elemento1, T elemento2){
		return null;
	}
	public Vertice<T> eliminarVertice(T elemento){
		return null;
	}
	public ArrayList<Vertice<T>> bfs(T elemento){
		return null;
	}
	
	public ArrayList<Vertice<T>> dfs(T elemento){
		return null;
	}
	
	public ArrayList<Vertice<T>> kruskal(Arista<T>[] aristas){
		return null;
	}
	
	public ArrayList<Vertice<T>> dijkstra(Vertice<T> inicio, Vertice<T> fin){
		return null;
	}
	
	public int[][] floydWarshall (int[][] matrizPeso){
		return null;
	}
	
}
