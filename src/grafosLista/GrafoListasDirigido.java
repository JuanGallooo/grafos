package grafosLista;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GrafoListasDirigido<T> {

	private HashMap<Object, Vertice<T>> vertices;
	private HashMap<Object, Arista<T>> aristas;
	private Iterator<Object> iterador;
	public GrafoListasDirigido(){
		this.vertices = new HashMap<Object, Vertice<T>>();
		this.aristas = new HashMap<Object, Arista<T>>();
	}
    public GrafoListasDirigido(ArrayList<Vertice<T>> vertices){
	this.vertices = new HashMap<Object, Vertice<T>>();
	this.aristas = new HashMap<Object, Arista<T>>();
	for(Vertice<T> v : vertices )
	    {
		this.vertices.put(v.getElemento().toString(), v);
	    }
    }
	public boolean insertarArista(String v1, String v2, int peso) {
		if (v1.equals(v2)) {
			// No hay bucles entre vertices
			return false;
		}
		if (!vertices.containsKey(v1) || !vertices.containsKey(v2)) {
			// No existe alguno de los dos vertices
			return false;
		}
		Arista<T> nuevaArista = new Arista<T>(vertices.get(v2), peso);
		if (!vertices.get(v1).insertarArista(nuevaArista)) {
			return false;
		}
		aristas.put(nuevaArista.hashCode(), nuevaArista);
		return true;
	}
	public boolean insertarVertice(Vertice<T> vertice) {
		Vertice<T> actual = vertices.get(vertice.getElemento().toString());
		if (actual != null) { // Ya existe el vertice
			return false;
		}
		vertices.put(vertice.getElemento().toString(), vertice);
		return true;
	}
	
	public boolean contieneLaArista(Arista<T> arista) {
		if (arista.getDestino() == null || arista.getDestino() == null) {
			return false;
		}
		return aristas.containsKey(arista.hashCode());
	}
	public Arista<T> eliminarArista(String referencia,Arista<T> arista) {
		iterador= vertices.keySet().iterator();
		while (iterador.hasNext()) {
			String k= (String) iterador.next();
			if(vertices.get(k).toString().equals(referencia) && vertices.get(k).getAristas().containsKey(arista.hashCode())){
				vertices.get(k).getAristas().remove(arista.hashCode());
			}
		}
		return this.aristas.remove(arista.hashCode());
	}
	public Vertice<T> eliminarVertice(String vertice) {
		iterador= aristas.keySet().iterator();
		while (iterador.hasNext()) {
			Object k=iterador.next();
			if(aristas.get(k).getDestino().getElemento().toString().equals(vertice)) {
	        	   aristas.remove(k);
	        	   iterador= aristas.keySet().iterator();
	           }
		}
		iterador= vertices.keySet().iterator();
		while (iterador.hasNext()) {
			String k= (String) iterador.next();
			vertices.get(k).eliminarDestino(vertice);
		}
		return this.vertices.remove(vertices.get(vertice).toString());
	}
	//Consultar
    public Vertice<T> getVertice(String etiqueta){
	    return this.vertices.get(etiqueta);
    }
	public HashMap<Object, Vertice<T>> getVertices() {
		return vertices;
	}
	public void setVertices(HashMap<Object, Vertice<T>> vertices) {
		this.vertices = vertices;
	}
	public HashMap<Object, Arista<T>> getAristas() {
		return aristas;
	}
	public void setAristas(HashMap<Object, Arista<T>> aristas) {
		this.aristas = aristas;
	}
	//BFS
	public ArrayList<Vertice<T>> bfs(String s){
		ArrayList<Vertice<T>> retorno= new ArrayList<Vertice<T>>();
		Queue<Vertice<T>> queue = new LinkedList<Vertice<T>>();
		queue.add(vertices.get(s));
		retorno.add(vertices.get(s));
		vertices.get(s).setVisitado(true);
		while(!queue.isEmpty()){
			Vertice<T> node = (Vertice<T>)queue.poll();
			Vertice<T> child=null;		
			Iterator it = node.getAristas().keySet().iterator();
			while (it.hasNext()) {	
			Object e = it.next();
			child= node.getAristas().get(e).getDestino();
			if(!child.isVisitado()) {
			retorno.add(child);
			child.setVisitado(true);
			queue.add(child);
			}
			}
		}
		limpiarCadaVertice();
		return retorno;
	}
	//DFS
	public ArrayList<Vertice<T>> dfs(String s) {
		ArrayList<Vertice<T>> retorno = new ArrayList<Vertice<T>>();
		Stack<Vertice<T>> stack = new Stack<Vertice<T>>();
		stack.push(vertices.get(s));
		vertices.get(s).setVisitado(true);
		retorno.add(vertices.get(s));
		while (!stack.isEmpty()) {
			Vertice<T> node = (Vertice<T>) stack.peek();
			Vertice<T> child = null;
			Iterator it = node.getAristas().keySet().iterator();
			while (it.hasNext()) {
				Object e = it.next();
				child= node.getAristas().get(e).getDestino();
				if (!child.isVisitado()) {
					child.setVisitado(true);
					retorno.add(child);
					stack.push(child);
				} else {
					stack.pop();
				}
			}
		}
		limpiarCadaVertice();
		return retorno;
	}
	public void limpiarCadaVertice() {
		vertices.forEach((k, v) -> {
	        	   vertices.get(k).setVisitado(false);;
	    });
	}
    public void minimumSpaningTree(){
        PriorityQueue<Arista<T>,T> all = new PriorityQueue<>();
        aristas.forEach((k, v) -> {
        	all.add(aristas.get(k));
        });
    }
    public Vertice<T> getRefencia(Arista<T> arista){
    	return ((AristaNoDirigida<T>) arista).getReferencia();
    }
}