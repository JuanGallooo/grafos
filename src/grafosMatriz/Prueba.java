package grafosMatriz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prueba {
	public static void main(String[] args) {
		GrafoMatrizNoDirigido<String> grafo = new GrafoMatrizNoDirigido<>(6, null);
		grafo.insertaArista(0, 1, 4);
		grafo.insertaArista(0, 2,2);
		grafo.insertaArista(0, 3, 99999);
		grafo.insertaArista(0, 4, 99999);
		grafo.insertaArista(0, 5, 99999);
		grafo.insertaArista(1, 2, 1);
		grafo.insertaArista(1, 3, 5);
		grafo.insertaArista(1, 4, 99999);
		grafo.insertaArista(1, 5, 99999);
		grafo.insertaArista(2, 3, 8);
		grafo.insertaArista(2, 4, 10);
		grafo.insertaArista(2, 5, 99999);
		grafo.insertaArista(3, 4, 2);
		grafo.insertaArista(3, 5, 6);
		grafo.insertaArista(4, 5, 3);


		
	     int[] dijkstra = grafo.dijkstra(grafo.getMatriz(), 0);
	     int[][] floyd = grafo.floydWarshall(grafo.getMatriz());
	     
	     
	    String mensaje = "";
	    	 for (int j = 0; j < dijkstra.length; j++) {    		 
	    		 
	    		if(j==dijkstra.length-1){
	    		mensaje+=dijkstra[j] + "\n";
	    		}
	    		else{
	    		mensaje+= dijkstra[j] + " ";
	    		}
			}
			
	     
	     System.out.println("Dijkstra" + "\n" + mensaje);
	     
	     
	     String mensaje2 = "";
	     for (int i = 0; i < floyd.length; i++) {
    	 for (int j = 0; j < floyd.length; j++) {    		 
    		 
    		if(j==floyd.length-1){
    		mensaje2+=floyd[i][j] + "\n";
    		}
    		else{
    		mensaje2+= floyd[i][j] + " ";
    		}
		}
	     
	}
	    System.out.println("Floyd" + "\n"+ mensaje2);
   }
}
	


