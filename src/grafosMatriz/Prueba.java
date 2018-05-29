package grafosMatriz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Prueba {
	public static void main(String[] args) throws IOException {	
		BufferedReader lector =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(System.out));
		String mensaje = lector.readLine();
		String[] valores = null;
		int casos = Integer.parseInt(mensaje);
		int n,m,s,t;
		int v1,v2,distancia;
		GrafoMatrizNoDirigido<Integer> grafo =  null;
		
		for(int x = 1; x <= casos; x++){
			mensaje =lector.readLine();
			if(mensaje.equals("")){
				mensaje = lector.readLine();
			}
			valores = mensaje.split(" ");
			n = Integer.parseInt(valores[0]);	
			m = Integer.parseInt(valores[1]);
			s = Integer.parseInt(valores[2]);
			t = Integer.parseInt(valores[3]);
			
			grafo = new GrafoMatrizNoDirigido(n);
			
			for(int i = 0; i < m; i++){
				mensaje = lector.readLine();
				valores = mensaje.split(" ");
				v1 = Integer.parseInt(valores[0]);
				v2 = Integer.parseInt(valores[1]);
				distancia = Integer.parseInt(valores[2]);
				grafo.insertaAristaUVA(v1, v2, distancia);
			}
			
			String out = ""; 
			int[] caminoMinimo = grafo.dijkstra(grafo.getMatrizPeso(), s);
			if(caminoMinimo == null || caminoMinimo[t] == grafo.INF){
				out = "Case #" + x + ":" + " unreachable";
			}
			else{
				out = "Case #" + x + ":" + " " + caminoMinimo[t];
			}
			escritor.write(out + "\n");	
		}
		
		lector.close();
		escritor.close();
	
	}
}
	


