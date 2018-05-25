package grafosMatriz;

import java.util.ArrayList;

public class GrafoMatrizDirigido<T> {
	private int matriz[][];
	private int numeroVertices;
	private Vertice<T>[] listaVertices;
	
	
	public GrafoMatrizDirigido(int numVertices, Vertice<T>[] lista){
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
	
	public int[][] floydWarshall (int [][] matrizPeso){
		int vertices = matrizPeso.length;
		int matrizAdyacencia[][] =  matrizPeso;
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
					matrizAdyacencia[i][j] =  (int)minimo;
				}
			}
		}
		
			
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
