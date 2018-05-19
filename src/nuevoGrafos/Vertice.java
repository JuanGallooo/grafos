package nuevoGrafos;
import java.util.HashMap;
public class Vertice<T> {
	private T elemento;
    private HashMap<Object, Arista<T>> aristas;
	
	public Vertice(T elemento, int numAristas) {
		super();
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
}
