package grafos;

public class Vertice<T> {
	private T elemento;
	private Arista<T>[] aristas;
	private int posicion;
	public Vertice(T elemento, int numAristas) {
		super();
		this.elemento = elemento;
		this.aristas = new Arista[numAristas];
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public T getElemento() {
		return elemento;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	public Arista<T>[] getAristas() {
		return aristas;
	}
	public void setAristas(Arista<T>[] aristas) {
		this.aristas = aristas;
	}
	public void agregarArista(T elemento,double peso) {
		
	}
	public int getNumeroAristas() {
		return aristas.length;
	}
}
