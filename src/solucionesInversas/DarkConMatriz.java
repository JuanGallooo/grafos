package solucionesInversas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;



public class DarkConMatriz {

	public static void main(String[] args) throws IOException {
		BufferedReader io =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(System.out));
		String mensaje= io.readLine();
		String[] split= mensaje.split(" ");
		GrafoMatrizNoDirigido<Integer> grafo =  null;
		
		while (!split[0].equals("0") && !split[1].equals("0")) {
			int vertices= Integer.parseInt(split[0]);
			grafo= new GrafoMatrizNoDirigido<Integer>(vertices);
			int roads = Integer.parseInt(split[1]);
			int sumaTotal= 0;
			for (int i = 0; i < roads; i++) {
				mensaje= io.readLine();
				split= mensaje.split(" ");
				String referencia= split[0];
				String destino= split[1];
				int peso= Integer.parseInt(split[2]);
				sumaTotal+=peso;
				grafo.insertaAristaUVA(Integer.parseInt(referencia), Integer.parseInt(destino), peso);
			}
			mensaje= io.readLine();
			split= mensaje.split(" ");
			int ahorrado= prim(grafo.getMatrizPeso(), vertices);
			escritor.write(sumaTotal-ahorrado+ "\n");
		}
		escritor.close();
		io.close();
	}
	public static int prim(int[][] grafo, int v) {
		int pesoMinimo;
        List<Integer> nodosVisitados = new ArrayList<Integer>();
        nodosVisitados.add(0);
        int w=0;
        int retorno=0;
        while(nodosVisitados.size() != v) {
            pesoMinimo = Integer.MAX_VALUE;
            for(int i : nodosVisitados) {
                for(int j = 0 ; j < v; ++j ){
                    if(pesoMinimo > grafo[i][j] && !nodosVisitados.contains(j)){
                    	pesoMinimo = grafo[i][j];
                        w = j;
                    }
                }
            }
            nodosVisitados.add(w);
            retorno+= pesoMinimo;
        }
        return retorno;
	}
	public static class  GrafoMatrizNoDirigido<T> {
		
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
		public  int [][] prim(int [][] matrizSistema){
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
	public static class Vertice<T> {
		private boolean visitado;
		private T elemento;
	    private HashMap<Object, Arista<T>> aristas;
	    private int posicion;
		
		public Vertice(T elemento) {
			super();
			visitado= false;
			this.elemento = elemento;
		}
		public T getElemento() {
			return elemento;
		}
		public void setElemento(T elemento) {
			this.elemento = elemento;
		}
		public int getPosicion() {
			return posicion;
		}
		public void setPosicion(int posicion) {
			this.posicion = posicion;
		}
		public boolean insertarArista(Arista<T> arista) {
			Arista<T> actual = aristas.get(arista.getPeso());
			if (actual != null){ // Ya existe la arista
				return false;
			}
			aristas.put(arista.hashCode(), arista);
			return true;
		}
		public Arista<T> eliminarArista(Arista<T> arista){
			return aristas.remove(arista.hashCode());
		}
		public HashMap<Object, Arista<T>> getAristas() {
			return aristas;
		}
		public void setAristas(HashMap<Object, Arista<T>> aristas) {
			this.aristas = aristas;
		}
		public boolean isVisitado() {
			return visitado;
		}
		public void setVisitado(boolean visitado) {
			this.visitado = visitado;
		}
		@Override
		public String toString() {
			return elemento.toString();
		}
		public int compareTo(Vertice<T> vertice) {
			return 0;
		}
	}

public static class Arista<T> {
	private Vertice<T> destino;
	private double peso;
	public Arista(Vertice<T> destino, double peso) {
		this.destino= destino;
		this.peso= peso;
	}
	@Override
	public String toString() {
		return "Puntero [destino=" + destino.toString() + ", peso=" + peso + "]";
	}
	public Vertice<T> getDestino() {
		return destino;
	}
	public void setDestino(Vertice<T> destino) {
		this.destino = destino;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
    public boolean tiene(Vertice<T> vertex){
        return destino.compareTo(vertex) == 0;
    }
}



}
