package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import grafosMatriz.*;

import org.junit.Test;

public class GrafoMatrizNoDirigidoTest {
	private GrafoMatrizNoDirigido<String> grafo;
	
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
	public void escenarioPrim(){
		String uno  = "a";
		String dos =  "b";
		String tres =  "c";
		String cuatro = "d";
		String cinco = "e";
		grafo.insertarVertice(uno);
		grafo.insertarVertice(dos);
		grafo.insertarVertice(tres);
		grafo.insertarVertice(cuatro);
		grafo.insertarVertice(cinco);
		
		
		//Chicago = a
		//Atlanta = b
		//SanFrancisco = c
		//Denver = d
		//New York = e
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(dos), 700);
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(tres), 1200);
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(cuatro), 1300);
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(cinco), 1000);
		grafo.insertaArista(grafo.buscarIndiceMatriz(dos), grafo.buscarIndiceMatriz(tres), 2200);
		grafo.insertaArista(grafo.buscarIndiceMatriz(dos), grafo.buscarIndiceMatriz(cuatro), 1400);
		grafo.insertaArista(grafo.buscarIndiceMatriz(dos), grafo.buscarIndiceMatriz(cinco), 800);
		grafo.insertaArista(grafo.buscarIndiceMatriz(tres), grafo.buscarIndiceMatriz(cuatro), 900);
		grafo.insertaArista(grafo.buscarIndiceMatriz(tres), grafo.buscarIndiceMatriz(cinco), 2000);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(cinco), 1600);

		
	}
	
	public void escenarioDijkstra(){
		String uno  = "a";
		String dos =  "b";
		String tres =  "c";
		String cuatro = "d";
		String cinco = "e";
		String seis = "z";
		grafo.insertarVertice(uno);
		grafo.insertarVertice(dos);
		grafo.insertarVertice(tres);
		grafo.insertarVertice(cuatro);
		grafo.insertarVertice(cinco);
		grafo.insertarVertice(seis);
		
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(dos), 4);
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(tres), 2);
		grafo.insertaArista(grafo.buscarIndiceMatriz(dos), grafo.buscarIndiceMatriz(tres), 1);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(tres), 8);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(dos), 5);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(seis), 6);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(cinco), 2);
		grafo.insertaArista(grafo.buscarIndiceMatriz(tres), grafo.buscarIndiceMatriz(cinco), 10);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cinco), grafo.buscarIndiceMatriz(seis), 3);
		
	}
	public void escenarioFloydWarshall(){
		String uno  = "0";
		String dos =  "1";
		String tres =  "2";
		String cuatro = "3";
		String cinco = "4";
		grafo.insertarVertice(uno);
		grafo.insertarVertice(dos);
		grafo.insertarVertice(tres);
		grafo.insertarVertice(cuatro);
		grafo.insertarVertice(cinco);
		
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(dos), 3);
		grafo.insertaArista(grafo.buscarIndiceMatriz(uno), grafo.buscarIndiceMatriz(tres), 7);
		grafo.insertaArista(grafo.buscarIndiceMatriz(dos), grafo.buscarIndiceMatriz(cuatro), 5);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(tres), 4);
		grafo.insertaArista(grafo.buscarIndiceMatriz(cuatro), grafo.buscarIndiceMatriz(cinco), 6);
		grafo.insertaArista(grafo.buscarIndiceMatriz(tres), grafo.buscarIndiceMatriz(cinco), 8);
		
	}
	
	
	@Test
	public void insertarVertice(){
		inicializar();
		String uno = "1";
		String dos = "2";
		int numVerticesActuales = grafo.getNumeroVertices();		
		assertTrue(numVerticesActuales == 0 && grafo.getMatrizPeso().length == 0 && grafo.getMatrizAdy().length == 0);
		
		grafo.insertarVertice(uno);
		grafo.insertarVertice(dos);
		
		numVerticesActuales = grafo.getNumeroVertices();
		assertTrue(numVerticesActuales == 2 && grafo.getMatrizPeso().length == 2 && grafo.getMatrizAdy().length == 2);
		
		//Tratando de reinsertar uno cuando ya existe
		grafo.insertarVertice(uno);
		numVerticesActuales = grafo.getNumeroVertices();
		assertTrue(numVerticesActuales == 2 && grafo.getMatrizPeso().length == 2 && grafo.getMatrizAdy().length == 2);
		
		String tres = "3";
		String cuatro  = "4";
		String cinco = "5";
		String seis = "6";
		String siete = "7";
		
		grafo.insertarVertice(tres);
		grafo.insertarVertice(cuatro);
		grafo.insertarVertice(cinco);
		grafo.insertarVertice(seis);
		grafo.insertarVertice(siete);
		
		numVerticesActuales = grafo.getNumeroVertices();
		assertTrue(numVerticesActuales == 7 && grafo.getMatrizPeso().length == 7 && grafo.getMatrizAdy().length == 7);
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
		escenario1();
		//uno, pos = 0, dos, pos=1, tres,=pos=2, cuatro, pos=3
		int actualAdy = grafo.getMatrizAdy()[0][1];
		int actualPeso = grafo.getMatrizPeso()[0][1];
		
		grafo.insertaArista(0, 1, 300);
		assertTrue((actualAdy != grafo.getMatrizAdy()[0][1] && 1== grafo.getMatrizAdy()[0][1]) && (actualAdy != grafo.getMatrizAdy()[1][0] && 1 == grafo.getMatrizAdy()[1][0]));
		assertTrue((actualPeso != grafo.getMatrizPeso()[0][1] && 300 == grafo.getMatrizPeso()[0][1])  && (actualPeso != grafo.getMatrizPeso()[1][0]) && 300 == grafo.getMatrizPeso()[0][1]);
		
		actualAdy = grafo.getMatrizAdy()[3][1];
		actualPeso = grafo.getMatrizPeso()[1][3];
		
		grafo.insertaArista(3, 1, 1000);
		
		assertTrue((actualAdy != grafo.getMatrizAdy()[1][3] && 1== grafo.getMatrizAdy()[1][3]) && (actualAdy != grafo.getMatrizAdy()[3][1] && 1 == grafo.getMatrizAdy()[3][1]));
		assertTrue((actualPeso != grafo.getMatrizPeso()[1][3] && 1000 == grafo.getMatrizPeso()[1][3])  && (actualPeso != grafo.getMatrizPeso()[3][1]) && 1000 == grafo.getMatrizPeso()[3][1]);
		//Posicion de Vertice inexistente
		try{
			grafo.insertaArista(5, 1, 300);	
			fail("Se esperaba la excepcion");
		}
		catch(ArrayIndexOutOfBoundsException e){
			assertTrue(true);
		}
		//Agregando una Arista a si mismo
		actualAdy = grafo.getMatrizAdy()[0][0];
		actualPeso = grafo.getMatrizPeso()[0][0];
		
        grafo.insertaArista(0, 0, 100);
		
		assertTrue((actualAdy == grafo.getMatrizAdy()[0][0] && 1 != grafo.getMatrizAdy()[0][0]));
		assertTrue((actualPeso == grafo.getMatrizPeso()[0][0] && 1000 != grafo.getMatrizPeso()[0][0]));
	}
	
	@Test
	public void eliminarVerticeTest(){
		inicializar();
		//Eliminando Vacio
       String cinco = "4";
		
		int actualAdy = grafo.getMatrizAdy().length;
		int actualPeso = grafo.getMatrizPeso().length;
		
		grafo.eliminarVertice(cinco);
		
		assertTrue(actualAdy == grafo.getMatrizAdy().length && actualPeso ==grafo.getMatrizPeso().length);
		
		escenario1();
		//uno, pos = 0, dos, pos=1, tres,pos=2, cuatro, pos=3
		
		//Eliminando un elemento que no existe
		
		cinco = "4";
		
		actualAdy = grafo.getMatrizAdy().length;
		actualPeso = grafo.getMatrizPeso().length;
		
		grafo.eliminarVertice(cinco);
		
		assertTrue(actualAdy == grafo.getMatrizAdy().length && actualPeso ==grafo.getMatrizPeso().length);
		
		
		//Eliminando dos,cuatro, uno, tres
		
		String actual = "b";
		
		actualAdy = grafo.getMatrizAdy().length;
		actualPeso = grafo.getMatrizPeso().length;
		
		grafo.eliminarVertice(actual);
		
		assertTrue(actualAdy -1  == grafo.getMatrizAdy().length && actualPeso -1 ==grafo.getMatrizPeso().length);
		
		actual = "d";
		
		actualAdy = grafo.getMatrizAdy().length;
		actualPeso = grafo.getMatrizPeso().length;
		
		grafo.eliminarVertice(actual);
		
		assertTrue(actualAdy -1  == grafo.getMatrizAdy().length && actualPeso -1 ==grafo.getMatrizPeso().length);
       
		actual = "a";
		
		actualAdy = grafo.getMatrizAdy().length;
		actualPeso = grafo.getMatrizPeso().length;
		
		grafo.eliminarVertice(actual);
		
		assertTrue(actualAdy -1  == grafo.getMatrizAdy().length && actualPeso -1 ==grafo.getMatrizPeso().length);
	
        actual = "c";
		
		actualAdy = grafo.getMatrizAdy().length;
		actualPeso = grafo.getMatrizPeso().length;
		
		grafo.eliminarVertice(actual);
		
		assertTrue(actualAdy -1  == grafo.getMatrizAdy().length && actualPeso -1 ==grafo.getMatrizPeso().length);
	}
	
	@Test
	public void eliminarAristaTest(){
		inicializar();
		
		// Grafo con aristas incluidas y su respectivo peso
		escenario2();
		
		//uno, pos = 0, dos, pos=1, tres,pos=2, cuatro, pos=3
		
		int valorActualPeso = grafo.getMatrizPeso()[3][0];
		//ValorActualPeso = 4
		int valorActualAdy  = grafo.getMatrizAdy()[3][0];
		//ValorActualAdy = 1
		
		grafo.eliminarArista("a", "d");
		
		assertTrue((valorActualPeso !=grafo.getMatrizPeso()[3][0] && GrafoMatrizNoDirigido.INF==grafo.getMatrizPeso()[3][0]) && (valorActualAdy != grafo.getMatrizAdy()[3][0] && 0== grafo.getMatrizAdy()[3][0]));
	    assertTrue((valorActualPeso !=grafo.getMatrizPeso()[0][3] && GrafoMatrizNoDirigido.INF==grafo.getMatrizPeso()[0][3]) && (valorActualAdy != grafo.getMatrizAdy()[0][3] && 0== grafo.getMatrizAdy()[0][3]));
	    
	    
	    valorActualPeso = grafo.getMatrizPeso()[1][2];
		//ValorActualPeso = 2
		valorActualAdy  = grafo.getMatrizAdy()[1][2];
		//ValorActualAdy = 1
		
		grafo.eliminarArista("b", "c");
		
		assertTrue((valorActualPeso !=grafo.getMatrizPeso()[1][2] && GrafoMatrizNoDirigido.INF==grafo.getMatrizPeso()[1][2]) && (valorActualAdy != grafo.getMatrizAdy()[1][2] && 0== grafo.getMatrizAdy()[1][2]));
	    assertTrue((valorActualPeso !=grafo.getMatrizPeso()[2][1] && GrafoMatrizNoDirigido.INF==grafo.getMatrizPeso()[2][1]) && (valorActualAdy != grafo.getMatrizAdy()[2][1] && 0== grafo.getMatrizAdy()[2][1]));
	  
	    valorActualPeso = grafo.getMatrizPeso()[0][1];
		//ValorActualPeso = 2
		valorActualAdy  = grafo.getMatrizAdy()[0][1];
		//ValorActualAdy = 1
		
		grafo.eliminarArista("a", "b");
		
		assertTrue((valorActualPeso !=grafo.getMatrizPeso()[0][1] && GrafoMatrizNoDirigido.INF==grafo.getMatrizPeso()[0][1]) && (valorActualAdy != grafo.getMatrizAdy()[0][1] && 0== grafo.getMatrizAdy()[0][1]));
	    assertTrue((valorActualPeso !=grafo.getMatrizPeso()[1][0] && GrafoMatrizNoDirigido.INF==grafo.getMatrizPeso()[1][0]) && (valorActualAdy != grafo.getMatrizAdy()[1][0] && 0== grafo.getMatrizAdy()[1][0]));
	   
	    
	    //Eliminar Valor de si mismo
	    
	    valorActualPeso = grafo.getMatrizPeso()[0][0];
		//ValorActualPeso = 0
		valorActualAdy  = grafo.getMatrizAdy()[0][0];
		//ValorActualAdy = 0
		
		grafo.eliminarArista("a", "a");
		
		assertTrue((valorActualPeso ==grafo.getMatrizPeso()[0][0] && 0==grafo.getMatrizPeso()[0][0]) && (valorActualAdy == grafo.getMatrizAdy()[0][0] && 0 == grafo.getMatrizAdy()[0][0]));
	}
	
	@Test
	public void primTest(){
		inicializar();
		escenarioPrim();
		int[][] matrizEsperada ={{0,700,1200,0, 0},{700,0,0,0,800},{1200,0,0,900,0},{0,0,900,0,0},{0,800,0,0,0}};
		int[][] matrizPrim = grafo.prim(grafo.getMatrizPeso());
		boolean iguales = true;
		for (int i = 0; i < matrizPrim.length; i++) {
			for (int j = 0; j < matrizPrim.length; j++) {
				if(matrizEsperada[i][j] != matrizPrim[i][j]){
					iguales = false;
				}
			}
		}
		assertTrue(iguales);
	}
	
	
	@Test
	public void dijkstraTest(){
		inicializar();
		escenarioDijkstra();
		
		//Yendo de a-z
		//Valor esperado 13
		int[] caminosMinimos = grafo.dijkstra(grafo.getMatrizPeso(), grafo.buscarIndiceMatriz("a"));
		
		int caminoMinimo = caminosMinimos[grafo.buscarIndiceMatriz("z")];

		assertTrue(caminoMinimo == 13);
		
		//Yendo de a-b
		// Valor esperado 3
		caminoMinimo = caminosMinimos[grafo.buscarIndiceMatriz("b")];
		assertTrue(caminoMinimo == 3);
		
		//Yendo de a-d
		// Valor esperado 8
		caminoMinimo = caminosMinimos[grafo.buscarIndiceMatriz("d")];
		assertTrue(caminoMinimo == 8);
		
		//Yendo de a-a
		//Valor esperado 0
		caminoMinimo = caminosMinimos[grafo.buscarIndiceMatriz("a")];
		assertTrue(caminoMinimo == 0);
		
	}
	@Test
	public void floydWarshallTest(){
		inicializar();
		escenarioFloydWarshall();
		//MatrizEsperada
		int[][] matrizEsperada = {{0,3,7,8,14},{3,0,9,5,11},{7,9,0,4,8},{8,5,4,0,6},{14,11,8,6,0}};
		
		int[][] caminosMinimos = grafo.floydWarshall(grafo.getMatrizPeso());
		
		boolean iguales = true;
		for (int i = 0; i < caminosMinimos.length; i++) {
			for (int j = 0; j < caminosMinimos.length; j++) {
				if(matrizEsperada[i][j] != caminosMinimos[i][j]){
					iguales = false;
				}
			}
		}
		assertTrue(iguales);	
	}
	

}
