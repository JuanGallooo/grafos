package solucionesInversas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class SendingConListas {

			public static class Vertice implements Comparable<Vertice>{
				
				public int id;
				public ArrayList<Arista> adjList;
				public int distOrigen;
				public boolean visitado;
				
				public Vertice(int id){
					this.id = id;
					this.adjList = new ArrayList<Arista>();
					distOrigen = Integer.MAX_VALUE;
					visitado = false;
				}
				
				

				@Override
				public int compareTo(Vertice v) {
					return this.distOrigen- v.distOrigen;
				}
			}
			
			public static class Arista{
				public int v;
				public int w;
				
				public Arista(int v,int w){
					this.v = v;
					this.w = w;
				}
			}
			
		   private static void imprimirSalida(int caso, boolean camino,int distancia){
				System.out.printf("Case #%d: ", caso);
			if(camino){
				System.out.printf("%d%n", distancia);
			  }
			else{
				System.out.println("unreachable");
			}
		   }
		   
		   public static void main(String[] args) {
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			Scanner sc = new Scanner(br);
			
			int casosTest = sc.nextInt();
			int n,m,s,t;
			Vertice[] vertices;
			
			for (int x = 1; x <= casosTest; x++) {
			  n = sc.nextInt();
			  m = sc.nextInt();
			  s = sc.nextInt();
			  t = sc.nextInt();
			  
			  if(m == 0){
				  imprimirSalida(x, false,0);
				  continue;
			  }
			  vertices = new Vertice[n+1];
			  for (int i = 0; i < n; i++) {
				vertices[i] =new Vertice(i);
			  }
			  for (int i = 0; i <m; i++) {
				int u,v,dist;
				u = sc.nextInt();
				v = sc.nextInt();
				dist = sc.nextInt();
		        vertices[u].adjList.add(new Arista(v,dist));
				vertices[v].adjList.add(new Arista(u,dist));	
			  }
			  PriorityQueue<Vertice> cola = new PriorityQueue<Vertice>();
			  vertices[s].distOrigen = 0;
			  cola.add(vertices[s]);
			  Vertice u,v;
			 while(!cola.isEmpty()){
			  u = cola.poll();
			  u.visitado = true;
			  if(u.id == t) break;
			  
			  for(int j = 0; j < u.adjList.size();j++){
				Arista actual = (Arista) u.adjList.get(j);
				  v = vertices[actual.v];
				  if(u.distOrigen + actual.w < v.distOrigen){
					  v.distOrigen = u.distOrigen + actual.w;
					  cola.add(v);
				  }
			    }
		      }
			 
			 imprimirSalida(x, vertices[t].visitado, vertices[t].distOrigen);
		    }
			sc.close();
		   } 

}
