package nuevoGrafos;

public class GrafoNoDirigido<T> extends GrafoDirigido<T>{
	public GrafoNoDirigido(){
		super();
	}
	@Override
	public boolean insertarArista(String v1, String v2, int peso) {
		if (v1.equals(v2)) {
			// No hay bucles entre vertices
			return false;
		}
		if (!getVertices().containsKey(v1) || !getVertices().containsKey(v2)) {
			// No existe alguno de los dos vertices
			return false;
		}
		AristaNoDirigida<T> nuevaArista= new AristaNoDirigida<>(getVertices().get(v1), getVertices().get(v2),peso);
		AristaNoDirigida<T> nuevaArista2= new AristaNoDirigida<>(getVertices().get(v2), getVertices().get(v1),peso);
		if (!getVertices().get(v1).insertarArista(nuevaArista)||!getVertices().get(v2).insertarArista(nuevaArista2)) {
			//ya existe dicha arista
			return false;
		}
		getVertices().get(v1).insertarArista(nuevaArista);
		getVertices().get(v2).insertarArista(nuevaArista2);
		getAristas().put(nuevaArista.hashCode(), nuevaArista);
		return true;
	}
}