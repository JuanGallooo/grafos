package solucionUVA;
//Dark Roads

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class MainDark {

	public static void main(String[] args) throws Exception {
		BufferedReader io= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter rc= new BufferedWriter(new OutputStreamWriter(System.out));
		String mensaje= io.readLine();
		String[] split= mensaje.split(" ");
		while (!split[0].equals("0") && !split[1].equals("0")) {
			int roads = Integer.parseInt(split[1]);
			//Creacion del grafo y la solucion
			Set<Vertice<String>> visited = new HashSet<Vertice<String>>();
			HashMap<String, Vertice<String>> vertices= new HashMap<>();
			int pesoTotal=0;
			for (int i = 0; i < roads; i++) {
				mensaje= io.readLine();
				split= mensaje.split(" ");
				String referencia= split[0];
				String destino= split[1];
				int peso= Integer.parseInt(split[2]);
				if(vertices.get(referencia)==null) {
					Vertice<String> nuevo= new Vertice<String>(referencia);
					vertices.put(referencia, nuevo);
				}
				if(vertices.get(destino)==null) {
					Vertice<String> nuevo= new Vertice<String>(destino);
					vertices.put(destino, nuevo);
				}
				AristaNoDirigida<String> nueva= new AristaNoDirigida<String>(vertices.get(referencia),
						vertices.get(destino), peso);
				AristaNoDirigida<String> nueva2= new AristaNoDirigida<String>(vertices.get(destino),
						vertices.get(referencia), peso);
				vertices.get(referencia).insertarArista(nueva);
				vertices.get(destino).insertarArista(nueva2);
				pesoTotal+=peso;
			}
			//Prim desde el primer nodo 0
			int pesoSolucion= 0;
			PriorityQueue<Arista<String>> queue = new PriorityQueue<Arista<String>>(
					vertices.get("0").getAristas().values());
			visited.add(vertices.get("0"));
			while (!queue.isEmpty()) {
				AristaNoDirigida<String> min =(AristaNoDirigida<String>) queue.poll();
				if (!(visited.contains(min.getReferencia()) && visited.contains(min.getDestino()))) {
					visited.add(min.getDestino());
					pesoSolucion+=min.getPeso();
					queue.addAll(min.getDestino().getAristas().values());
				}
			}
			rc.write(pesoTotal-pesoSolucion+"\n");
			mensaje= io.readLine();
			split= mensaje.split(" ");
	}
		io.close();
		rc.close();
	}
	public static class Vertice<T extends Comparable<?>> {
		private boolean visitado;
		private T elemento;
	    private HashMap<Object, Arista<T>> aristas;
		
		public Vertice(T elemento) {
			super();
			aristas=new HashMap<Object, Arista<T>>();
			visitado= false;
			this.elemento = elemento;
		}
		public T getElemento() {
			return elemento;
		}
		public void setElemento(T elemento) {
			this.elemento = elemento;
		}
		public boolean insertarArista(Arista<T> arista) {
			Arista<T> actual = aristas.get(arista.getPeso());
			if (actual != null){ // Ya existe la arista
				return false;
			}
			aristas.put(arista.hashCode(), arista);
			return true;
		}
		public Arista<T> eliminarArista(Arista<T> arista){
			return aristas.remove(arista.hashCode());
		}
		public HashMap<Object, Arista<T>> getAristas() {
			return aristas;
		}
		public void setAristas(HashMap<Object, Arista<T>> aristas) {
			this.aristas = aristas;
		}
		public boolean isVisitado() {
			return visitado;
		}
		public void setVisitado(boolean visitado) {
			this.visitado = visitado;
		}
		@Override
		public String toString() {
			return elemento.toString();
		}
		public int compareTo(Vertice<T> vertice) {
			return 0;
		}
		public void eliminarDestino(String elemento) {
			Iterator<Object> iterador= aristas.keySet().iterator();
			while (iterador.hasNext()) {
				Object k=iterador.next();
				if(aristas.get(k).getDestino().getElemento().toString().equals(elemento)) {
		        	   aristas.remove(k);
		        	   iterador= aristas.keySet().iterator();
		         }
			}
			
		}
	}
	public static class Arista<T extends Comparable<?>> implements Comparable<Arista<T>>{
		private Vertice<T> destino;
		private int peso;
		public Arista(Vertice<T> destino, int peso) {
			this.destino= destino;
			this.peso= peso;
		}
		@Override
		public String toString() {
			return "Puntero [destino=" + destino.toString() + ", peso=" + peso + "]";
		}
		public Vertice<T> getDestino() {
			return destino;
		}
		public void setDestino(Vertice<T> destino) {
			this.destino = destino;
		}
		public int getPeso() {
			return peso;
		}
		public void setPeso(int peso) {
			this.peso = peso;
		}
	    public boolean tiene(Vertice<T> vertex){
	        return destino.compareTo(vertex) == 0;
	    }
	    public Vertice<T> getRefencia(Arista<T> arista){
	    	return ((AristaNoDirigida<T>) arista).getReferencia();
	    }
		@Override
		public int compareTo(Arista<T> o) {
			return (int)(getPeso() - o.getPeso());
		}
	}
	public static class AristaNoDirigida<T extends Comparable<?>> extends Arista<T>{
		private Vertice<T> referencia;

		public AristaNoDirigida(Vertice<T> referencia,Vertice<T> destino, int peso) {
			super(destino, peso);
			this.referencia= referencia;
		}
		public Vertice<T> getReferencia() {
			return referencia;
		}
		public void setReferencia(Vertice<T> referencia) {
			this.referencia = referencia;
		}
		@Override
		public String toString() {
			return "AristaNoDirigida [referencia=" +", destino=" + getDestino().toString() + ", peso=" +getPeso() + "]";
		}
	}
	
}
