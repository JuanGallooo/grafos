package grafos;

public class GrafoMatriz<T> {
	
	private int matriz[][];
	private int numeroVertices;
	private Vertice<T>[] listaVertices;
	
	
	public GrafoMatriz(int numVertices, Vertice<T>[] lista){
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
	    		 numEncontro = i;
	    	 }
	     }
	     
	     
	}
}
