package grafos;

public class Arista<T> {
	private T destino;
	private double peso;
	public Arista(T destino, double peso) {
		this.destino= destino;
		this.peso= peso;
	}
	@Override
	public String toString() {
		return "Puntero [destino=" + destino.toString() + ", peso=" + peso + "]";
	}
}
