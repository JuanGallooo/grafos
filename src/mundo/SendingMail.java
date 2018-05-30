package mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import grafosLista.GrafoListasNoDirigido;
import grafosMatriz.GrafoMatrizNoDirigido;

public class SendingMail {
	private ArrayList<GrafoMatrizNoDirigido<Integer>> grafos;
	private ArrayList<String> soluciones;
	private ArrayList<String> sYt;
	public SendingMail() {
		grafos= new ArrayList<GrafoMatrizNoDirigido<Integer>>();
		soluciones= new ArrayList<String>();
		sYt= new ArrayList<String>();
	}
	public void crearGrafo(String ruta) throws Exception {
		try {
			BufferedReader lector =  new BufferedReader(new FileReader(new File(ruta)));
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
				
				grafo = new GrafoMatrizNoDirigido<Integer>(n);
				
				for(int i = 0; i < m; i++){
					mensaje = lector.readLine();
					valores = mensaje.split(" ");
					v1 = Integer.parseInt(valores[0]);
					v2 = Integer.parseInt(valores[1]);
					distancia = Integer.parseInt(valores[2]);
					grafo.insertaAristaUVA(v1, v2, distancia);
				}
				grafos.add(grafo);
				String out = "La solución del "; 
				int[] caminoMinimo = grafo.dijkstra(grafo.getMatrizPeso(), s);
				if(caminoMinimo == null || caminoMinimo[t] == GrafoMatrizNoDirigido.INF){
					out += "Caso #" + x + ":" + " unreachable";
				}
				else{
					out += "Caso #" + x + ":" + " " + caminoMinimo[t];
				}
				sYt.add(s+" "+t);
				soluciones.add(out+ " siendo este el numero minimo para llegar desde de s hasta t");
			}
			
			lector.close();
		} catch (Exception e) {
			throw new Exception("Error al leer el archivo");
		}
	}
	public ArrayList<GrafoMatrizNoDirigido<Integer>> getGrafos() {
		return grafos;
	}
	public void setGrafos(ArrayList<GrafoMatrizNoDirigido<Integer>> grafos) {
		this.grafos = grafos;
	}
	public ArrayList<String> getSoluciones() {
		return soluciones;
	}
	public void setSoluciones(ArrayList<String> soluciones) {
		this.soluciones = soluciones;
	}
	public ArrayList<String> getsYt() {
		return sYt;
	}
	public void setsYt(ArrayList<String> sYt) {
		this.sYt = sYt;
	}
	
}
