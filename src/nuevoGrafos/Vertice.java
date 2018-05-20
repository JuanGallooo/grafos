package nuevoGrafos;
import java.util.HashMap;
public class Vertice<T> {
	private boolean visitado;
	private T elemento;
    private HashMap<Object, Arista<T>> aristas;
	
	public Vertice(T elemento, int numAristas) {
		super();
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
}
