package solucionesInversas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Scanner;

import grafosLista.GrafoListasNoDirigido;
import grafosLista.Vertice;
import solucionUVA.MainSending.GrafoMatrizNoDirigido;

public class SendingConListas {

	public static void main(String[] args) throws IOException {
		BufferedReader lector =  new BufferedReader(new InputStreamReader(System.in));
		Scanner sc= new Scanner(lector);
		BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(System.out));
		int casos = sc.nextInt();
		int n,m,s,t;
		int v1,v2,distancia;
		GrafoListasNoDirigido<Integer> grafo =null; 
		
		for(int x = 1; x <= casos; x++){
			n = sc.nextInt();	
			m = sc.nextInt();
			s = sc.nextInt();
			t =sc.nextInt();
			String out = ""; 
			if(m==0) {
				out = "Case #" + x + ":" + " unreachable";
			}
			else {
				try {
					grafo = new GrafoListasNoDirigido<Integer>();
					for(int i = 0; i < m; i++){
						v1 = sc.nextInt();
						v2 = sc.nextInt();
						distancia = sc.nextInt();
						if(grafo.getVertice(v1+"")== null) grafo.insertarVertice(new Vertice<Integer>(v1));
						if(grafo.getVertice(v2+"")== null) grafo.insertarVertice(new Vertice<Integer>(v2));
						grafo.insertarArista(v1+"", v2+"", distancia);
					}
					int caminoMinimo=grafo.dijkstraDistancia(s+"", t+"");
					if(caminoMinimo==Integer.MAX_VALUE){
						out = "Case #" + x + ":" + " unreachable";
					   }
					else{
						out = "Case #" + x + ":" + " " + caminoMinimo;
					  }
				} catch (Exception e) {
					out = "Case #" + x + ":" + " unreachable";
				}
			  }
			escritor.write(out + "\n");	
		}
		lector.close();
		escritor.close();
		sc.close();
		System.exit(0);
	}

}
