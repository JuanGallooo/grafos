package solucionUVA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MainSending {
		public static void main(String[] args) throws IOException {
			BufferedReader lector =  new BufferedReader(new InputStreamReader(System.in));
			Scanner sc= new Scanner(lector);
			BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(System.out));
			int casos = sc.nextInt();
			int n,m,s,t;
			int v1,v2,distancia;
			GrafoMatrizNoDirigido grafo =  null;
			
			for(int x = 1; x <= casos; x++){
				n = sc.nextInt();	
				m = sc.nextInt();
				s = sc.nextInt();
				t =sc.nextInt();
				String out = ""; 
				if(m==0) {
					out = "Case #" + x + ":" + " unreachable";
				}
				else {
					try {
						grafo = new GrafoMatrizNoDirigido(n);
						for(int i = 0; i < m; i++){
							v1 = sc.nextInt();
							v2 = sc.nextInt();
							distancia = sc.nextInt();
							grafo.insertaAristaUVA(v1, v2, distancia);
						}
						int[] caminoMinimo = grafo.dijkstra(grafo.getMatriz(), s);
						if(caminoMinimo == null || (caminoMinimo[t] == 9999999)){
							out = "Case #" + x + ":" + " unreachable";
						   }
						else{
							out = "Case #" + x + ":" + " " + caminoMinimo[t];
						  }
					} catch (Exception e) {
						out = "Case #" + x + ":" + " unreachable";
					}
				  }
				escritor.write(out + "\n");	
			}
			lector.close();
			escritor.close();
			sc.close();
			System.exit(0);
		}
		
		public static class GrafoMatrizNoDirigido{
//			public final static int INF = 9999999;
			
			private int matriz[][];
			
			public GrafoMatrizNoDirigido(int numVertices){
				matriz =  new int[numVertices][numVertices];
		        for(int i=0; i< numVertices; i++){
		            for(int j=0; j< numVertices; j++){
		            	if(i==j){
		            		matriz[i][j] =0;
		            	}
		            	else
		                matriz[i][j] = 9999999;
		            }            
		        }
		    }
			
			public int[][] getMatriz() {
				return matriz;
			}
			
			public void insertaAristaUVA(int v1, int v2, int dist) throws ArrayIndexOutOfBoundsException {
				if (v1 > matriz.length -1 || v2 > matriz.length-1) {
				throw new ArrayIndexOutOfBoundsException();
				}
			    else if(dist < matriz[v1][v2]) {
				matriz[v1][v2] = dist;
				matriz[v2][v1] = dist;
				}
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
					distancias[i] = 9999999;
				}
				distancias[origen] = 0;
				PriorityQueue<Integer> colaVisitados =  new PriorityQueue<Integer>();
				colaVisitados.add(distancias[origen]+origen);
				while(!colaVisitados.isEmpty()){
					int actual = colaVisitados.poll();
					visto[actual] = true;
					for(int v = 0; v < numVertices; v++){
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
			
		}
	}