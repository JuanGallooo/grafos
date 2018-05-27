package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import grafosLista.GrafoListasNoDirigido;
import grafosLista.Vertice;

public class DarkRoads {
	private GrafoListasNoDirigido<String> grafo;
	private ArrayList<String> soluciones;
	
	public DarkRoads() {
		grafo= new GrafoListasNoDirigido<String>();
		soluciones= new ArrayList<String>();
	}
	
	public void crearGrafo(String archivo) throws Exception{
		try {
			BufferedReader io= new BufferedReader(new FileReader(new File(archivo)));
			String mensaje= io.readLine();
			String[] split= mensaje.split(" ");
			while (!split[0].equals("0") && !split[1].equals("0")) {
				grafo= new GrafoListasNoDirigido<String>();
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
				soluciones.add(solucionProblema());
				
				mensaje= io.readLine();
				split= mensaje.split(" ");
			}
			io.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al leer el archivo");
		}
	}
	public String solucionProblema() {
		GrafoListasNoDirigido<String> solucion= grafo.Kruskal();
		if(solucion.getAristas().isEmpty()) return 0+"";
//		GrafoListasNoDirigido<String> solucion= grafo.prim(grafo.getVertices().get("0"));
		List<grafosLista.AristaNoDirigida<String>> aristasSolucion= solucion.getAristasList();
		List<grafosLista.AristaNoDirigida<String>> aristasTotales= grafo.getAristasList();
		int pesoSolucion= 0;
		int pesoTotal=0;
		for (int i = 0; i < aristasTotales.size(); i++) {
			pesoTotal+= aristasTotales.get(i).getPeso();
		}
		
		for (int i = 0; i < aristasSolucion.size(); i++) {
			pesoSolucion+= aristasSolucion.get(i).getPeso();
		}
		int dineroAhorrado= pesoTotal-pesoSolucion;
		return dineroAhorrado+"";
	}

	public GrafoListasNoDirigido<String> getGrafo() {
		return grafo;
	}

	public void setGrafo(GrafoListasNoDirigido<String> grafo) {
		this.grafo = grafo;
	}

	public ArrayList<String> getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(ArrayList<String> soluciones) {
		this.soluciones = soluciones;
	}
	
}
