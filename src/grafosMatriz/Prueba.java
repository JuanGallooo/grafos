package grafosMatriz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prueba {
	public static void main(String[] args) {
		GrafoMatrizNoDirigido<String> grafo = new GrafoMatrizNoDirigido<>(5, null);
		grafo.insertaArista(0, 1, 1000);
		grafo.insertaArista(0, 2,700);
		grafo.insertaArista(0, 3, 1300);
		grafo.insertaArista(0, 4, 1200);
		grafo.insertaArista(1, 2, 800);
		grafo.insertaArista(1, 3, 1600);
		grafo.insertaArista(1, 4, 2000);
		grafo.insertaArista(2, 3, 1400);
		grafo.insertaArista(2, 4, 2200);
		grafo.insertaArista(3, 4, 900);
		
		
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		
	     int[][] matrizNueva = grafo.getMatriz();
	     
	     int matrizPeso[][] = grafo.floydWarshall(grafo.getMatriz());
	     
	    String mensaje = "";
	     for (int i = 0; i < matrizNueva.length; i++) {
	    	 for (int j = 0; j < matrizNueva.length; j++) {    		 
	    		 
	    		if(j==matrizNueva.length-1){
	    		mensaje+=matrizNueva[i][j] + "\n";
	    		}
	    		else{
	    		mensaje+= matrizNueva[i][j] + " ";
	    		}
			}
			
		}
	     
	     System.out.println(mensaje);
	     
	     
		    String mensaje21 = "";
		     for (int i = 0; i < matrizNueva.length; i++) {
		    	 for (int j = 0; j < matrizNueva.length; j++) {    		 
		    		 
		    		if(j==matrizNueva.length-1){
		    		mensaje21+=matrizNueva[i][j] + "\n";
		    		}
		    		else{
		    		mensaje21+= matrizNueva[i][j] + " ";
		    		}
				}
				
			}
		     
		     System.out.println(mensaje21);
		
		  }
	     
	
	  }
	
	


