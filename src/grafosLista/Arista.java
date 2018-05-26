package grafosLista;

public class Arista<T extends Comparable<?>> implements Comparable<Arista<T>>{
	private Vertice<T> destino;
	private int peso;
	public Arista(Vertice<T> destino, int peso) {
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
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
    public boolean tiene(Vertice<T> vertex){
        return destino.compareTo(vertex) == 0;
    }
    public Vertice<T> getRefencia(Arista<T> arista){
    	return ((AristaNoDirigida<T>) arista).getReferencia();
    }
	@Override
	public int compareTo(Arista<T> o) {
		return (int)(getPeso() - o.getPeso());
	}
}
