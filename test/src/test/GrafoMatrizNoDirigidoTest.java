package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import grafosMatriz.*;

import org.junit.Test;

public class GrafoMatrizNoDirigidoTest {
	private GrafoMatrizNoDirigido<String> grafo;
	private String[] elementos;
	
	public void inicializar(){
		grafo = new GrafoMatrizNoDirigido<String>(0);
	}
	
	public void escenario1(){
		String uno  = "a";
		String dos =  "b";
		String tres =  "c";
		String cuatro = "d";
		grafo.insertarVertice(uno);
		grafo.insertarVertice(dos);
		grafo.insertarVertice(tres);
		grafo.insertarVertice(cuatro);
	}
	public void escenario2(){
		escenario1();
		String uno  = "a";
		String dos =  "b";
		String tres =  "c";
		String cuatro = "d";
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(dos), 2);
		grafo.insertaArista(grafo.buscarIndiceMatriz(dos), grafo.buscarIndiceMatriz(tres), 2);
		grafo.insertaArista(grafo.buscarIndiceMatriz(tres), grafo.buscarIndiceMatriz(cuatro), 1);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(uno), 4);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(dos), 3);
	}
	

	@Test
	public void insertarAristaTest() {
		inicializar();
		//grafoVacio
		Vertice<String> uno =  new Vertice<String>("0");
		uno.setPosicion(0);
		Vertice<String> dos =  new Vertice<String>("1");
		dos.setPosicion(1);
		
		int tamanhoMatrizActual = grafo.getMatrizAdy().length;
		grafo.insertaArista(uno.getPosicion(), dos.getPosicion(), 300);
		
		assertTrue(tamanhoMatrizActual == grafo.getMatrizAdy().length);
		
		//Agregando los vertices al grafo
		String u  = "a";
		String d =  "b";
		String tres =  "c";
		String cuatro = "d";
		grafo.insertarVertice(u);
		grafo.insertarVertice(d);
		grafo.insertarVertice(tres);
		grafo.insertarVertice(cuatro);
		System.out.println(grafo.getMatrizAdy().length);
		//u, pos = 0, d, pos=1, tres,=pos=2, cuatro, pos=3
		int actualAdy = grafo.getMatrizAdy()[0][1];
		int actualPeso = grafo.getMatrizPeso()[0][1];
		
		grafo.insertaArista(0, 1, 300);
		
		assertTrue(actualAdy == grafo.getMatrizAdy()[0][1] && actualAdy == grafo.getMatrizAdy()[1][0]);
		assertTrue(actualPeso == grafo.getMatrizPeso()[0][1] && actualPeso == grafo.getMatrizPeso()[1][0]);
		
		
		
	}

}
