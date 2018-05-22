package nuevoGrafos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GrafoDirigido<T> {

	private HashMap<Object, Vertice<T>> vertices;
	private HashMap<Object, Arista<T>> aristas;

	public GrafoDirigido(){
		this.vertices = new HashMap<Object, Vertice<T>>();
		this.aristas = new HashMap<Object, Arista<T>>();
	}
    public GrafoDirigido(ArrayList<Vertice<T>> vertices){
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
		vertices.forEach((k, v) -> {
			if(vertices.get(k).toString().equals(referencia) && vertices.get(k).getAristas().containsKey(arista.hashCode())){
				vertices.get(k).getAristas().remove(arista.hashCode());
			}
	    });
		return this.aristas.remove(arista.hashCode());
	}
	public Vertice<T> eliminarVertice(String vertice) {
		aristas.forEach((k, v) -> {
           if(aristas.get(k).getDestino().getElemento().toString().equals(vertice)) {
        	   aristas.remove(k);
           }
        });
		return this.vertices.remove(vertices.get(vertice));
	}
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
			Vertice<T> node = (Vertice<T>)queue.remove();
			Vertice<T> child=null;		
			Iterator it = node.getAristas().entrySet().iterator();
			while (it.hasNext()) {			
			Map.Entry e = (Map.Entry)it.next();
			retorno.add(child);
			child= node.getAristas().get(e.getKey()).getDestino();
			child.setVisitado(true);
			queue.add(child);
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
			Iterator it = node.getAristas().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				if (child != null) {
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
    public Arista<T> prim(String start,String end){
//        PriorityQueue<Arista<T>,Double> queue = new PriorityQueue<>();
//        
//        List<Vertice<T>> colored = new ArrayList<Vertice<T>>();
//        
//        queue.add(new Arista<T>(vertices.get(start),0));
//        
//        while(!queue.isEmpty()) {
//        	Arista<T> local = queue.remove();
//            colored.add(local.getDestino());
//
//            if(local.getDestino().compareTo(vertices.get(end)) == 0 ){
//                return local;
//            }
//            aristas.forEach((k, v) -> {
//            	if ( aristas.get(k).getDestino().compareTo(local.getDestino())==0 
//            			&& !colored.contains(item.next(local.getEnd()))) {
//                    Road<V> next = new Road<V>(local.getStart(), item.next(local.getEnd()),
//                            local.getDistance() + item.getDistance(),
//                            local.getTime() + item.getTime(),
//                            local.getCost() + item.getCost());
//                    //queue.add(next, next.getDistance()); // shortest path
//                    queue.add(next, next.getTime());//fastest path
//                }
//            	
//            });
//            for (Road<V> item : roads) {
//                
//            }
//        }
        return null;
    }
	
}