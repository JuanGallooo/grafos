package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import grafosLista.AristaNoDirigida;
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
}
