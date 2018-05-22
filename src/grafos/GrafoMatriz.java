package grafos;

public class GrafoMatriz {
	
	private int matriz[][];
	private int numeroVertices;
	
	public GrafoMatriz(int numVertices) {
		// TODO Auto-generated constructor stub
		numeroVertices = numVertices;
		matriz =  new int[numVertices][numVertices];
        for(int i=0; i< numeroVertices; i++){
            for(int j=0; j< numeroVertices; j++){
                matriz[i][j] = 0;
            }            
        }
    }
	}
	
	public void insertar

}
