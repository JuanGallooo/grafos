package grafosMatriz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.print.attribute.standard.NumberOfDocuments;

import auxiliares.ColaPrioridad;

public class GrafoMatrizNoDirigido<T> {
	
	public final static int INF = 9999999;
	
	private int matrizPeso[][];
	private int matrizAdy[][];
	private int numeroVertices;
	private int posFinal;
	private HashMap<T, Integer> map;
	private ArrayList<Vertice<T>>listaVertices;
	
	
	public GrafoMatrizNoDirigido(int numVertices){
		// TODO Auto-generated constructor stub
		listaVertices = new ArrayList<Vertice<T>>(); 
		numeroVertices = numVertices;
		posFinal = 0;
	    map = new HashMap<T,Integer>();
		matrizPeso =  new int[numVertices][numVertices];
		matrizAdy = new int[numVertices][numVertices];
        for(int i=0; i< numeroVertices; i++){
            for(int j=0; j< numeroVertices; j++){
            	if(i==j){
            		matrizPeso[i][j] =0;
            	}
            	else{
            		matrizAdy[i][j] = 0;
            		matrizPeso[i][j] = INF;		
            	}
            }            
        }
    }
	
	
	
	public int getNumeroVertices() {
		return numeroVertices;
	}



	public void setNumeroVertices(int numeroVertices) {
		this.numeroVertices = numeroVertices;
	}



	public HashMap<T, Integer> getMap() {
		return map;
	}



	public void setMap(HashMap<T, Integer> map) {
		this.map = map;
	}



	public ArrayList<Vertice<T>> getListaVertices() {
		return listaVertices;
	}



	public void setListaVertices(ArrayList<Vertice<T>> listaVertices) {
		this.listaVertices = listaVertices;
	}



	public int getPosFinal() {
		return posFinal;
	}



	public void setPosFinal(int posFinal) {
		this.posFinal = posFinal;
	}



	public int[][] getMatrizPeso() {
		return matrizPeso;
	}

	public void setMatrizPeso(int[][] matriz) {
		this.matrizPeso = matriz;
	}
	
	
	
	public int[][] getMatrizAdy() {
		return matrizAdy;
	}



	public void setMatrizAdy(int[][] matrizAdy) {
		this.matrizAdy = matrizAdy;
	}



	public void insertaArista(int v1, int v2, int dist) throws ArrayIndexOutOfBoundsException {
	if(listaVertices.size()!=0){
		if (v1 > numeroVertices -1 || v2 > numeroVertices-1) {
			throw new ArrayIndexOutOfBoundsException();
			}
		    else {
		    
			matrizPeso[v1][v2] = dist;
			matrizPeso[v2][v1] = dist;
			matrizAdy[v1][v2] = 1;
			matrizAdy[v2][v1] = 1;
			
			}
	   }
	}
	
	public void insertarVertice(T elemento){
		Integer verificar = map.get(elemento);
		if(verificar == null){
			Vertice<T> nuevo =  new Vertice<T>(elemento);
			listaVertices.add(nuevo);
			nuevo.setPosicion(posFinal);
			map.put(elemento, posFinal);
			posFinal++;
		}	
	}
	public void insertaAristaUVA(int v1, int v2, int dist) throws ArrayIndexOutOfBoundsException {
		if (v1 > numeroVertices -1 || v2 > numeroVertices-1) {
		throw new ArrayIndexOutOfBoundsException();
		}
	    else if(dist < matrizPeso[v1][v2]) {
		matrizPeso[v1][v2] = dist;
		matrizPeso[v2][v1] = dist;
		}
}
	
	public Integer buscarIndiceMatriz(T elemento){
		Integer retorno = map.get(elemento);
		return retorno;
	}
	public void eliminarArista(T elemento1, T elemento2){
	    Integer temp = map.get(elemento1);
	    if(temp != null){
	    	Integer temp2 = map.get(elemento2);
	    	if(temp2 != null){
	    		matrizPeso[temp][temp2] = INF;
	    		matrizPeso[temp2][temp] = INF;
	    		matrizAdy[temp][temp2] = 0;
	    		matrizAdy[temp2][temp] = 0;		
	    	}
	    }
	}
	public void eliminarVertice(T elemento){
	    Integer temp = map.get(elemento);
	    if(temp != null){
	     int[][] nueva = new int[numeroVertices-1][numeroVertices-1];
	     int[][] nuevaAdy = new int[numeroVertices-1][numeroVertices-1];
	     map.remove(elemento);
	     for (int i = 0; i <numeroVertices; i++) {
	    	 if(i > temp.intValue()){
	    		 T elementoTemp = listaVertices.get(i).getElemento();
	    		 int posNueva = i-1;
	    		 listaVertices.get(i).setPosicion(posNueva);
	    		 map.remove(elementoTemp);
	    		 map.put(elementoTemp, posNueva);    		 
	    	 }
			for (int j = 0; j < numeroVertices; j++) {
				if(i == temp.intValue() || j == temp.intValue()){
				
				}
				else{
					if(i< temp.intValue()){
						if(j < temp.intValue()){
							nueva[i][j] = matrizPeso[i][j];
							nuevaAdy[i][j] = matrizAdy[i][j];
						}
						else{
							nueva[i][j-1] = matrizPeso[i][j];
							nuevaAdy[i][j-1] = matrizAdy[i][j];
						}
					}
					else{
						if(j < temp.intValue()){
							nueva[i-1][j] = matrizPeso[i][j];
							nuevaAdy[i-1][j] = matrizAdy[i][j];
						}
						else{
						nueva[i-1][j-1] = matrizPeso[i][j];
						nuevaAdy[i-1][j-1] = matrizAdy[i][j];
						}
					}
			    }
		   }
	  }
	    listaVertices.remove(temp.intValue());
	    matrizPeso = nueva;
	    matrizAdy = nuevaAdy;
	    posFinal--;
	    numeroVertices--;
	  }
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
	public int[] dijkstraCamino(int[][] peso, int origen){
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
		return aux;
}
	public Vertice<T> buscarElemento (T elemento){
		Integer buscado = map.get(elemento);
	     if(buscado!=null){
	    	 return listaVertices.get(buscado.intValue());
	     }
		return null;
	}
	public Vertice<T> buscarElemento (int i){
		if(i < numeroVertices){
			return listaVertices.get(i);
		}
		return null;
		
	}
	public int[] transformacionArrayList(ArrayList<Integer> aristasMultiples){
		int[] retorno = new int[aristasMultiples.size()];
		for (int i = 0; i < aristasMultiples.size(); i++) {
			retorno[i] = aristasMultiples.get(i); 
		}
		return retorno;
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
