package grafosMatriz;

import java.util.ArrayList;

public class GrafoMatrizNoDirigido<T> {
	
	private long matriz[][];
	private int numeroVertices;
	private String[][] monda;
	private Vertice<T>[] listaVertices;
	
	
	public GrafoMatrizNoDirigido(int numVertices, Vertice<T>[] lista){
		// TODO Auto-generated constructor stub
		listaVertices = lista;
		numeroVertices = numVertices;
		matriz =  new long[numVertices][numVertices];
        for(int i=0; i< numeroVertices; i++){
            for(int j=0; j< numeroVertices; j++){
                matriz[i][j] = 0;
            }            
        }
    }
	
	public void insertaArista(int v1, int v2, int dist) throws ArrayIndexOutOfBoundsException {
			if (v1 > numeroVertices -1 || v2 > numeroVertices-1) {
			throw new ArrayIndexOutOfBoundsException();
			}
		    else {
			matriz[v1][v2] = dist;
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
	
	public ArrayList<Vertice<T>> dijkstra(Vertice<T> inicio, Vertice<T> fin){
		return null;
	}
	
	public long[][] floydWarshall (long [][] matrizPeso){
		int vertices = matrizPeso.length;
		long matrizAdyacencia[][] =  matrizPeso;
		String caminos[][] = new String [vertices][vertices];
		String caminosAuxiliares[][] =  new String [vertices][vertices];
		String caminoRecorrido = "",cadena = "", caminitos = "";
		int i,j,k;
		float temporal1,temporal2,temporal3,temporal4,minimo;
		for(i=0; i< vertices;i++){
			for(j=0;j<vertices;j++){
				caminos[i][j] = "";
				caminosAuxiliares[i][j] = "";
				
			}
		}
		
		for(k=0;k<vertices;k++){
			for(i =0; i < vertices;i++){
				for(j=0;j< vertices;j++){
					temporal1=matrizAdyacencia[i][j];
					temporal2=matrizAdyacencia[i][k];
					temporal3=matrizAdyacencia[k][j];
					temporal4 = temporal2+temporal3;
					
					minimo = Math.min(temporal1, temporal4);
					
					if(temporal1 != temporal4){
						if(minimo == temporal4){
							caminoRecorrido = "";
							caminosAuxiliares[i][j] = k +"";
							caminos[i][j] = caminosR(i,k,caminosAuxiliares,caminoRecorrido) + (k+1);
						}
					}
					matrizAdyacencia[i][j] =  (long) minimo;
				}
			}
		}
		
		monda = caminos;
			
		return matrizAdyacencia;
	}

	public String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido) {
		// TODO Auto-generated method stub
		if(caminosAuxiliares[i][k].equals("")){
			return "";
		}
		else{
			caminoRecorrido += caminosR(i,Integer.parseInt(caminosAuxiliares[i][k].toString()),caminosAuxiliares,caminoRecorrido) + (Integer.parseInt(caminosAuxiliares[i][k].toString())+1);
		}
		
		return caminoRecorrido;
	}
	
}
