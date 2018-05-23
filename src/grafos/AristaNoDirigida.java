package grafos;

public class AristaNoDirigida<T> extends Arista<T>{
	private Vertice<T> referencia;

	public AristaNoDirigida(Vertice<T> referencia,Vertice<T> destino, double peso) {
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
