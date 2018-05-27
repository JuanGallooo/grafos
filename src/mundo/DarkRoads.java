package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import grafosLista.AristaNoDirigida;
import grafosLista.GrafoListasNoDirigido;
import grafosLista.Vertice;

public class DarkRoads {
	private ArrayList<GrafoListasNoDirigido<String>> grafos;
	private ArrayList<String> soluciones;
	private ArrayList<GrafoListasNoDirigido<String>> grafosSolucion;
	private ArrayList<ArrayList<AristaNoDirigida<String>>> aristasSoluciones;
	
	public DarkRoads() {
		grafos= new ArrayList<GrafoListasNoDirigido<String>>();
		soluciones= new ArrayList<String>();
		grafosSolucion= new ArrayList<GrafoListasNoDirigido<String>>();
		aristasSoluciones= new ArrayList<ArrayList<AristaNoDirigida<String>>>();
	}
	
	public void crearGrafo(String archivo) throws Exception{
		try {
			BufferedReader io= new BufferedReader(new FileReader(new File(archivo)));
			String mensaje= io.readLine();
			String[] split= mensaje.split(" ");
			int contador=0;
			while (!split[0].equals("0") && !split[1].equals("0")) {
				GrafoListasNoDirigido<String> grafo= new GrafoListasNoDirigido<String>();
				Integer.parseInt(split[0]);// numVertices
				int roads = Integer.parseInt(split[1]);
				for (int i = 0; i < roads; i++) {
					mensaje= io.readLine();
					split= mensaje.split(" ");
					String referencia= split[0];
					String destino= split[1];
					int peso= Integer.parseInt(split[2]);
					if( grafo.getVertice(referencia)==null) grafo.insertarVertice(new Vertice<String>(referencia));
					if( grafo.getVertice(destino)==null) grafo.insertarVertice(new Vertice<String>(destino));
					grafo.insertarArista(referencia, destino, peso);
				}
				grafos.add(grafo);
				soluciones.add(solucionProblema(contador));
				contador++;
				mensaje= io.readLine();
				split= mensaje.split(" ");
			}
			io.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al leer el archivo");
		}
	}
	public String solucionProblema(int indice) {
		GrafoListasNoDirigido<String> solucion= grafos.get(indice).Kruskal();
//		GrafoListasNoDirigido<String> solucion= grafos.get(indice).prim(grafos.get(indice).getVertices().get("0"));
		grafosSolucion.add(solucion);
		ArrayList<AristaNoDirigida<String>> aristasSolucion= solucion.getAristasList();
		aristasSoluciones.add(aristasSolucion);
		if(solucion.getAristas().isEmpty()) return 0+"";
		List<grafosLista.AristaNoDirigida<String>> aristasSoluciona= solucion.getAristasList();
		List<grafosLista.AristaNoDirigida<String>> aristasTotales= grafos.get(indice).getAristasList();
		int pesoSolucion= 0;
		int pesoTotal=0;
		for (int i = 0; i < aristasTotales.size(); i++) {
			pesoTotal+= aristasTotales.get(i).getPeso();
		}
		
		for (int i = 0; i < aristasSoluciona.size(); i++) {
			pesoSolucion+= aristasSoluciona.get(i).getPeso();
		}
		int dineroAhorrado= pesoTotal-pesoSolucion;
		return "Con un costo total antes del ahorro de $"+pesoTotal +" y un nuevo costo con minimos camino de $"+ pesoSolucion+"\n"+
				" Encontramos un dinero ahorrado de $"+ dineroAhorrado;
	}
	public ArrayList<GrafoListasNoDirigido<String>> getGrafos() {
		return grafos;
	}
	public void setGrafos(ArrayList<GrafoListasNoDirigido<String>> grafos) {
		this.grafos = grafos;
	}
	public ArrayList<GrafoListasNoDirigido<String>> getGrafosSolucion() {
		return grafosSolucion;
	}
	public void setGrafosSolucion(ArrayList<GrafoListasNoDirigido<String>> grafosSolucion) {
		this.grafosSolucion = grafosSolucion;
	}
	public ArrayList<ArrayList<AristaNoDirigida<String>>> getAristasSoluciones() {
		return aristasSoluciones;
	}
	public void setAristasSoluciones(ArrayList<ArrayList<AristaNoDirigida<String>>> aristasSoluciones) {
		this.aristasSoluciones = aristasSoluciones;
	}
	public ArrayList<String> getSoluciones() {
		return soluciones;
	}
	public void setSoluciones(ArrayList<String> soluciones) {
		this.soluciones = soluciones;
	}
}
