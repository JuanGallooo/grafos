package grafos;

public class Vertice<T> {
	private T elemento;
	private Puntero<T>[] aristas;
	
	public Vertice(T elemento, int numAristas) {
		super();
		this.elemento = elemento;
		this.aristas = new Puntero[numAristas];
	}
	public T getElemento() {
		return elemento;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	public Puntero<T>[] getAristas() {
		return aristas;
	}
	public void setAristas(Puntero<T>[] aristas) {
		this.aristas = aristas;
	}
	public void agregarArista(T elemento,double peso) {
		
	}
	public int getNumeroAristas() {
		return aristas.length;
	}
}
