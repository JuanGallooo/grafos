package grafosMatriz;

import java.util.ArrayList;
import java.util.PriorityQueue;

import auxiliares.ColaPrioridad;

public class GrafoMatrizNoDirigido<T> {
	
	public final static int INF = 9999999;
	
	private int matriz[][];
	private int numeroVertices;
	private ArrayList<Vertice<T>>listaVertices;
	
	
	public GrafoMatrizNoDirigido(int numVertices){
		// TODO Auto-generated constructor stub
		listaVertices = new ArrayList<Vertice<T>>(); 
		numeroVertices = numVertices;
		matriz =  new int[numVertices][numVertices];
        for(int i=0; i< numeroVertices; i++){
            for(int j=0; j< numeroVertices; j++){
            	if(i==j){
            		matriz[i][j] =0;
            	}
            	else
                matriz[i][j] = INF;
            }            
        }
    }
	
	
	
	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	public void insertaArista(int v1, int v2, int dist) throws ArrayIndexOutOfBoundsException {
	if(listaVertices.size()!=0){
		if (v1 > numeroVertices -1 || v2 > numeroVertices-1) {
			throw new ArrayIndexOutOfBoundsException();
			}
		    else {
		    
			matriz[v1][v2] = dist;
			matriz[v2][v1] = dist;
			}
	   }
	}
	
	public void insertarVertice(T elemento){
		
	}
	public void insertaAristaUVA(int v1, int v2, int dist) throws ArrayIndexOutOfBoundsException {
		if (v1 > numeroVertices -1 || v2 > numeroVertices-1) {
		throw new ArrayIndexOutOfBoundsException();
		}
	    else if(dist < matriz[v1][v2]) {
		matriz[v1][v2] = dist;
		matriz[v2][v1] = dist;
		}
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
	
	public ArrayList<Vertice<T>> prim(Vertice<T> n){
		return null;
	}
	public static int [][] prim(int [][] matrizSistema){
		ArrayList<Boolean> verticesVerificados = new ArrayList<Boolean>();
		ArrayList<Integer> distanciaRelativa = new ArrayList<Integer>();
		ArrayList<Integer> nodosVecinos = new ArrayList<Integer>();
		
		for(Integer contador = 0; contador < matrizSistema[0].length; contador++){
			verticesVerificados.add(false);
			nodosVecinos.add(0);
			distanciaRelativa.add(Integer.MAX_VALUE);
		}
		
		distanciaRelativa.set(0, new Integer(0));
		Integer raizResultante= 0;
		for(int i = 0; i< matrizSistema[0].length; i++){
			for(int j= 0; j< matrizSistema[0].length; j++){
				if((verticesVerificados.get(j)) || (j== raizResultante)){
					continue;
				}
				if((matrizSistema[raizResultante][j] > 0) &&((matrizSistema[raizResultante][j]<distanciaRelativa.get(j)))){
					distanciaRelativa.set(j, matrizSistema[raizResultante][j]);
					nodosVecinos.set(j, raizResultante);
				}
			}
			verticesVerificados.set(raizResultante, true);
			raizResultante= new Integer(0);
			int distanciaComparada = new Integer(Integer.MAX_VALUE);
			
			for(int contador = 1; contador < verticesVerificados.size(); contador++){
				if(verticesVerificados.get(contador)){
					continue;
				}
				if(distanciaRelativa.get(contador) < distanciaComparada){
					distanciaComparada = distanciaRelativa.get(contador);
					raizResultante = contador;
				}
			}
		}
		int [][] matrizRespuesta = new int [matrizSistema[0].length][matrizSistema[0].length];
		for(int contador = 1; contador < nodosVecinos.size(); contador++){
			matrizRespuesta[contador][nodosVecinos.get(contador)] = matrizSistema[contador][nodosVecinos.get(contador)];
			matrizRespuesta[nodosVecinos.get(contador)][contador] = matrizRespuesta[contador][nodosVecinos.get(contador)];
		}
		return matrizRespuesta;
	}
	
	public ArrayList<Vertice<T>> kruskal(Arista<T>[] aristas){
		return null;
	}
		
	public int[] dijkstra(int[][] peso, int origen){
		int temporal,temporal2;
		int numVertices = peso.length;		
		boolean visto[] =  new boolean[numVertices];
		int[] distancias = new int[numVertices];
		int[] aux = new int[numVertices];
		
		for(int i = 0; i < numVertices; i++){
			visto[i] = false;
			aux[i] = -1;
			distancias[i] = INF;
		}
		distancias[origen] = 0;
		PriorityQueue<Integer> colaVisitados =  new PriorityQueue<Integer>();
		colaVisitados.add(distancias[origen]+origen);
		while(!colaVisitados.isEmpty()){
			int actual = colaVisitados.poll();
			visto[actual] = true;
			for(int v = 0; v < numVertices; v++){
			int visualizar =peso[actual][v];
			visualizar = distancias[v];
			if(peso[origen][v] != 0){
				temporal = distancias[v];
				temporal2 = distancias[actual] + peso[actual][v];
				if( temporal > temporal2 ){
					distancias[v] = distancias[actual] + peso[actual][v];
					aux[v] = actual;
					colaVisitados.add(v);
				}	
			 }
			}
			
		}
		return distancias;
	   }
	
	public int[] transformacionArrayList(ArrayList<Integer> aristasMultiples){
		int[] retorno = new int[aristasMultiples.size()];
		for (int i = 0; i < aristasMultiples.size(); i++) {
			retorno[i] = aristasMultiples.get(i); 
		}
		return retorno;
	}

	
	public int buscarMinimoR(int[] distancias, int i, int j ){
		if(i==j || j == i+1){
			return Math.min(distancias[i], distancias[j]);
		}
		else{
			int m = (i+j)/2;
			int izq = buscarMinimoR(distancias, i, m);
			int der =buscarMinimoR(distancias, m+1, j);
			return Math.min(izq, der);	
		}
		
		
	    
	}
	public int buscarMinimo(int[] distancias){
	    int minimo = buscarMinimoR(distancias, 0, distancias.length-1);
	    return minimo;
	}

	
	
	public int[][] floydWarshall (int [][] matrizPeso){
		int vertices = matrizPeso.length;
		int matrizAdyacencia[][] =  matrizPeso;
		int i,j,k;
		float temporal1,temporal2,temporal3,temporal4,minimo;
		for(k=0;k<vertices;k++){
			for(i =0; i < vertices;i++){
				for(j=0;j< vertices;j++){
					temporal1=matrizAdyacencia[i][j];
					temporal2=matrizAdyacencia[i][k];
					temporal3=matrizAdyacencia[k][j];
					temporal4 = temporal2+temporal3;
					
					minimo = Math.min(temporal1, temporal4);
					matrizAdyacencia[i][j] =  (int)minimo;
				}
			}
		}
			
		return matrizAdyacencia;
	}

	
}
