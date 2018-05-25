package grafosMatriz;

public class Prueba {
	public static void main(String[] args) {
		GrafoMatrizNoDirigido<String> grafo = new GrafoMatrizNoDirigido<>(4, null);
		int matrizPeso[][] = {{0,1000,700,1300,1200},{1000,0,800,1600,2000},
				                {700,800,0,1400,2200},{1300,1600,1400,0,900},{1200,2000,2200,900,0}};
		
	     int[][] matrizNueva = grafo.prim(matrizPeso);
	     
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
	
	  }
	}


