package grafosLista;

import java.util.LinkedList;

import mundo.Controlador;

public class main {

	public static void main(String[] args) throws Exception {
		GrafoListasNoDirigido<String> grafo= new GrafoListasNoDirigido<>();
		Vertice<String> a= new Vertice<String>("a");
		Vertice<String> b= new Vertice<String>("b");
		Vertice<String> c= new Vertice<String>("c");
		Vertice<String> d= new Vertice<String>("d");
		Vertice<String> e= new Vertice<String>("e");
		Vertice<String> f= new Vertice<String>("f");
		grafo.insertarVertice(a);
		grafo.insertarVertice(b);
		grafo.insertarVertice(c);
		grafo.insertarVertice(d);
		grafo.insertarVertice(e);
		grafo.insertarVertice(f);
		grafo.insertarArista("a", "b", 3);
		grafo.insertarArista("a", "c", 5);
		grafo.insertarArista("a", "d", 9);
		grafo.insertarArista("b", "c", 3);
		grafo.insertarArista("b", "e", 7);
		grafo.insertarArista("b", "d", 4);
		grafo.insertarArista("c", "d", 2);
		grafo.insertarArista("c", "f", 8);
		grafo.insertarArista("c", "e", 6);
		grafo.insertarArista("d", "f", 2);
		grafo.insertarArista("d", "e", 2);
		grafo.insertarArista("e", "f", 5);
		DijkstraListaAdyacencia<String> dijkstra= new DijkstraListaAdyacencia<>(grafo);
		dijkstra.inicio(grafo.getVertice("a"));
		LinkedList<Vertice<String>> vertices= dijkstra.destinoFinal(grafo.getVertice("f"));
		for (int i = 0; i < vertices.size(); i++) {
			System.out.println(vertices.get(i));
		}
		
	}
}