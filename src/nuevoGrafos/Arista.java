package nuevoGrafos;

public class Arista<T> {
	private Vertice<T> destino;
	private double peso;
	public Arista(Vertice<T> destino, double peso) {
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
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
}
