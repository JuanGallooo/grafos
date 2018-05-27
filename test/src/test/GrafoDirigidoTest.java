package test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import grafosLista.Arista;
import grafosLista.GrafoListasDirigido;
import grafosLista.Vertice;

public class GrafoDirigidoTest {
	private ArrayList<Vertice<String>> vertices;
	private ArrayList<Arista<String>> aristas;
	private GrafoListasDirigido<String> grafo;
	
	public void escenario1() {
		vertices=new ArrayList<Vertice<String>>();
		aristas= new ArrayList<Arista<String>>();
		grafo= new GrafoListasDirigido<String>();
		
		Vertice<String> a= new Vertice<String>("a");
		Vertice<String> b= new Vertice<String>("b");
		Vertice<String> c= new Vertice<String>("c");
		Vertice<String> d= new Vertice<String>("d");
		Arista<String> uno= new Arista<String>(b, 2);
		Arista<String> dos= new Arista<String>(c, 2);
		Arista<String> tres= new Arista<String>(d, 1);
		Arista<String> cuatro= new Arista<String>(a, 4);
		Arista<String> cinco= new Arista<String>(b, 3);
		
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
	@Test
	public void insertarVerticeTest() {
		escenario1();
		boolean agrego= false;
		agrego= grafo.insertarVertice(vertices.get(0));
		assertTrue(agrego);
		assertTrue(grafo.getVertices().get("a").equals(vertices.get(0)));
		agrego= grafo.insertarVertice(vertices.get(1));
		assertTrue(agrego);
		assertTrue(grafo.getVertices().get("b").equals(vertices.get(1)));
		agrego= grafo.insertarVertice(vertices.get(2));
		assertTrue(agrego);
		assertTrue(grafo.getVertices().get("c").equals(vertices.get(2)));
		agrego= grafo.insertarVertice(vertices.get(3));
		assertTrue(agrego);
		assertTrue(grafo.getVertices().get("d").equals(vertices.get(3)));
		agrego= grafo.insertarVertice(vertices.get(0));
		assertFalse(agrego);
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
		Arista<String> nueva= new Arista<String>(vertices.get(1), 2);
		Object hash= nueva.hashCode();
		grafo.getVertice("a").insertarArista(nueva);
		assertTrue(grafo.getVertice("a").getAristas().containsKey(hash));
		grafo.eliminarArista("a", nueva);
		assertTrue(!grafo.getVertice("a").getAristas().containsKey(hash));
		assertNull(grafo.getVertice("a").getAristas().get(hash));
	}
	@Test
	public void contieneLaAristaTest() {
		escenario2();
		Arista<String> ejemplo= new Arista<String>(vertices.get(0), 2);
		grafo.getAristas().put(ejemplo.hashCode(), ejemplo);
		boolean encontro= grafo.contieneLaArista(ejemplo);
		assertTrue(encontro);
		ejemplo=new Arista<String>(null, 2);
		encontro= grafo.contieneLaArista(ejemplo);
		assertFalse(encontro);
	}
	@Test
	public void eliminarVerticeTest(){
			escenario2();
			Vertice<String> aux= grafo.getVertice("a");
			assertTrue(aux.equals(grafo.eliminarVertice("a")));
			assertNull(grafo.getVertice("a"));
			
			escenario2();
			grafo.insertarArista("b", "a", 2);
			grafo.insertarArista("c", "a", 2);
			grafo.insertarArista("d", "a", 2);
			assertTrue(aux.toString().equals(grafo.eliminarVertice("a").toString()));
			assertNull(grafo.getVertice("a"));
			grafo.getVertices().get("b").getAristas().forEach((k, v) -> {
				assertTrue(!grafo.getVertices().get("b").getAristas().get(k).getDestino().toString().equals(aux.toString()));
		    });	
	}
	@Test
	public void bfsTest() {
		escenario3();
		ArrayList<Vertice<String>> prueba= grafo.bfs("a");
		String[] confirmacion= {"a","b","c","d"};
		for (int i = 0; i < prueba.size(); i++) {
			assertTrue(confirmacion[i].equals(prueba.get(i).toString()));
		}
	}
	@Test
	public void dfsTest() {
		escenario3();
		ArrayList<Vertice<String>> prueba= grafo.dfs("a");
		String[] confirmacion= {"a","b","c","d"};
		for (int i = 0; i < prueba.size(); i++) {
			assertTrue(confirmacion[i].equals(prueba.get(i).toString()));
		}
	}

	@Test
	public void limpiarCadaVerticeTest() {
		escenario2();
		grafo.getVertice("a").setVisitado(true);
		grafo.getVertice("b").setVisitado(true);
		grafo.limpiarCadaVertice();
		assertTrue(!grafo.getVertice("a").isVisitado());
		assertTrue(!grafo.getVertice("b").isVisitado());
	}
}
