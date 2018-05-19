package nuevoGrafos;
import java.util.ArrayList;
import java.util.HashMap;

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
		this.vertices.put(v.getElemento().toString().hashCode(), v);
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
		Vertice<T> actual = vertices.get(vertice.getElemento().toString().hashCode());
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
	public Arista<T> eliminarArista(Arista<T> arista) {
		arista.getDestino().eliminarArista(arista);
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
}