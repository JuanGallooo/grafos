package grafosMatriz;

public interface InterfazGrafo<T> {
	int numVertices();
	int numAristas();
	boolean existeArista();
	double pesoArista(T i, T j);
	void insertarArista(T i, T j);
	void insertarArista(T i, T j, double peso);
	void insertarVertice(T i, int numAristas);
}
