package grafos;

public class Puntero<T> {
	private T destino;
	private double peso;
	public Puntero(T destino, double peso) {
		this.destino= destino;
		this.peso= peso;
	}
	@Override
	public String toString() {
		return "Puntero [destino=" + destino.toString() + ", peso=" + peso + "]";
	}
}
