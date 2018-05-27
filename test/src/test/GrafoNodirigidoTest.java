package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import grafosLista.AristaNoDirigida;
import grafosLista.DijkstraListaAdyacencia;
import grafosLista.GrafoListasNoDirigido;
import grafosLista.Vertice;

public class GrafoNodirigidoTest {
	private ArrayList<Vertice<String>> vertices;
	private ArrayList<AristaNoDirigida<String>> aristas;
	private GrafoListasNoDirigido<String> grafo;
	
	public void escenario1() {
		vertices=new ArrayList<Vertice<String>>();
		aristas= new ArrayList<AristaNoDirigida<String>>();
		grafo= new GrafoListasNoDirigido<String>();
		
		Vertice<String> a= new Vertice<String>("a");
		Vertice<String> b= new Vertice<String>("b");
		Vertice<String> c= new Vertice<String>("c");
		Vertice<String> d= new Vertice<String>("d");
		
		AristaNoDirigida<String> uno= new AristaNoDirigida<String>(a,b, 2);
		AristaNoDirigida<String> dos= new AristaNoDirigida<String>(b,c, 2);
		AristaNoDirigida<String> tres= new AristaNoDirigida<String>(c,d, 1);
		AristaNoDirigida<String> cuatro= new AristaNoDirigida<String>(d,a, 4);
		AristaNoDirigida<String> cinco= new AristaNoDirigida<String>(d,b, 3);
		
		vertices.add(a);
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		
		aristas.add(uno);
		aristas.add(dos);
		aristas.add(tres);
		aristas.add(cuatro);
		aristas.add(cinco);
	}
	public void escenario2() {
		escenario1();
		grafo.insertarVertice(vertices.get(0));
		grafo.insertarVertice(vertices.get(1));
		grafo.insertarVertice(vertices.get(2));
		grafo.insertarVertice(vertices.get(3));
		grafo.insertarVertice(vertices.get(0));
	}
	public void escenario3() {
		escenario2();
		grafo.insertarArista("a", "b", 2);
		grafo.insertarArista("b", "c", 2);
		grafo.insertarArista("c", "d", 1);
		grafo.insertarArista("d", "a", 4);
		grafo.insertarArista("d", "b", 3);
	}
	public void escenario4() {
		grafo= new GrafoListasNoDirigido<>();
		vertices=new ArrayList<Vertice<String>>();
		Vertice<String> a= new Vertice<String>("a");
		Vertice<String> b= new Vertice<String>("b");
		Vertice<String> c= new Vertice<String>("c");
		Vertice<String> d= new Vertice<String>("d");
		Vertice<String> e= new Vertice<String>("e");
		Vertice<String> f= new Vertice<String>("f");
		vertices.add(a);
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		vertices.add(e);
		vertices.add(f);
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
	}

	@Test
	public void insertarAristaTest() {
		escenario2();
		boolean agrego=false;
		agrego= grafo.insertarArista("a", "b", 2);
		assertTrue(agrego);
		assertFalse(grafo.getVertice("a").getAristas().isEmpty());
		agrego= grafo.insertarArista("a", "a", 2);
		assertFalse(agrego);// No existen bucles
		agrego= grafo.insertarArista("b", "c", 2);
		assertTrue(agrego);
		agrego= grafo.insertarArista("c", "d", 1);
		agrego= grafo.insertarArista("d", "a", 4);
		assertTrue(agrego);
		agrego= grafo.insertarArista("d", "b", 3);
		assertTrue(agrego);
		agrego= grafo.insertarArista("e", "b", 3);
		assertFalse(agrego);// No existe el vertice dado
		assertFalse(grafo.getAristas().isEmpty());
	}
	@Test
	public void eliminarAristaTest() {
		escenario2();
		AristaNoDirigida<String> nueva= new AristaNoDirigida<String>(vertices.get(0),vertices.get(1), 2);
		Object hash= nueva.hashCode();
		grafo.getVertice("a").insertarArista(nueva);
		assertTrue(grafo.getVertice("a").getAristas().containsKey(hash));
		grafo.eliminarArista("a", nueva);
		assertTrue(!grafo.getVertice("a").getAristas().containsKey(hash));
		assertNull(grafo.getVertice("a").getAristas().get(hash));
	}
	@Test
	public void getMatrizPesoTest(){
		escenario4();
		int[][] pesoDado = grafo.getMatrizPeso();
		int[][] resultado= {
				{0,3,5,9,Integer.MAX_VALUE,Integer.MAX_VALUE},
				{3,0,3,4,7,Integer.MAX_VALUE},
				{5,3,0,2,6,8},
				{9,4,2,0,2,2},
				{Integer.MAX_VALUE,7,6,2,0,5},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,8,2,5,0}
		};
		for (int i = 0; i < pesoDado.length; i++) {
			for (int j = 0; j < pesoDado.length; j++) {
				assertTrue(pesoDado[i][j]==resultado[i][j]);
			}
		}
	}
	@Test
	public void primTest() {
		escenario4();
		GrafoListasNoDirigido<String> solucionPrim= grafo.prim(grafo.getVertices().get("a"));
		ArrayList<AristaNoDirigida<String>> aristasSolucion= solucionPrim.getAristasList();
		String[] solucion={"d e 2",	"d f 2","a b 3","b c 3","c d 2"};
		for (int i = 0; i < aristasSolucion.size(); i++) {
			boolean encontro= false;
			String sol=aristasSolucion.get(i).getReferencia()+" "+aristasSolucion.get(i).getDestino()+" "+ aristasSolucion.get(i).getPeso();
			for (int j = 0; j < solucion.length && !encontro; j++) {
				if(solucion[j].equals(sol))encontro=true;
			}
			assertTrue(encontro);
		}
	}
	@Test
	public void KruskalTest() {
		escenario4();
		GrafoListasNoDirigido<String> solucionKruskal= grafo.Kruskal();
		ArrayList<AristaNoDirigida<String>> aristasSolucion= solucionKruskal.getAristasList();
		String[] solucion={"c d 2","d f 2","d e 2",	"b c 3","a b 3"};
		for (int i = 0; i < aristasSolucion.size(); i++) {
			aristasSolucion.get(i);
			String sol=aristasSolucion.get(i).getReferencia()+" "+aristasSolucion.get(i).getDestino()+" "+ aristasSolucion.get(i).getPeso();
			boolean encontro=false;
			for (int j = 0; j < solucion.length && !encontro; j++) {
				if(solucion[j].equals(sol))encontro=true;
			}
			assertTrue(encontro);
		}
	}
	@Test
	public void dijkstraTest() {
		escenario4();
		DijkstraListaAdyacencia<String> dijkstra= new DijkstraListaAdyacencia<>(grafo);
		dijkstra.inicio(grafo.getVertice("a"));
		LinkedList<Vertice<String>> vertices= dijkstra.destinoFinal(grafo.getVertice("f"));
		String[] soluciones= {"a","b","d","f"};
		for (int i = 0; i < vertices.size(); i++) {
			assertTrue(soluciones[i].equals(vertices.get(i).toString()));
		}
		dijkstra.inicio(grafo.getVertice("c"));
		vertices= dijkstra.destinoFinal(grafo.getVertice("f"));
		String[] soluciones2= {"c","d","f"};
		for (int i = 0; i < vertices.size(); i++) {
			assertTrue(soluciones2[i].equals(vertices.get(i).toString()));
		}
	}
	@Test
	public void floydWarshallTest(){
		escenario4();
		int[][] pesoDado = grafo.getMatrizPeso();
		int[][] floyd = grafo.floydWarshall();
		int[][] resultado= {{0,3,5,7,9,9},{3,0,3,4,6,6},{5,3,0,2,4,4},{7,4,2,0,2,2},{9,6,4,2,0,4},{9,6,4,2,4,0}};
		for (int i = 0; i < pesoDado.length; i++) {
			for (int j = 0; j < pesoDado.length; j++) {
				assertTrue(floyd[i][j]==resultado[i][j]);
			}
		}
	}
}
