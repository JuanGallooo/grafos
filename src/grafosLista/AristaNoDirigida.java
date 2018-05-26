package grafosLista;

public class AristaNoDirigida<T extends Comparable<?>> extends Arista<T>{
	private Vertice<T> referencia;

	public AristaNoDirigida(Vertice<T> referencia,Vertice<T> destino, int peso) {
		super(destino, peso);
		this.referencia= referencia;
	}
	public Vertice<T> getReferencia() {
		return referencia;
	}
	public void setReferencia(Vertice<T> referencia) {
		this.referencia = referencia;
	}
	@Override
	public String toString() {
		return "AristaNoDirigida [referencia=" +", destino=" + getDestino().toString() + ", peso=" +getPeso() + "]";
	}
}
