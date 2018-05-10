package grafos;

public class GrafoDirigido<T> implements InterfazGrafo<T>{

	private int numVertices;
	private int numAristas;
	private Vertice<T>[] vertices;
	private int num;

	@SuppressWarnings("unchecked")
	public GrafoDirigido(int numVertices) {
		super();
		this.numVertices = numVertices;
		this.numAristas = -1;
		vertices= new Vertice[numVertices];
		num=0;
	}

	@Override
	public int numVertices() {
		return numVertices;
	}

	@Override
	public int numAristas() {
		return numAristas;
	}

	@Override
	public boolean existeArista() {
		return false;
	}

	@Override
	public double pesoArista(T i, T j) {
		return 0;
	}

	@Override
	public void insertarArista(T i, T j) {
		
	}

	@Override
	public void insertarArista(T i, T j, double peso) {
		if(!existeVertice(i)) {
			
		}
	}

	@Override
	public void insertarVertice(T i, int numAristas) {
		if( vertices!= null) {
			Vertice<T> agregar = new Vertice<T>(i, numAristas);
			vertices[num]= agregar;
			num++;
			if(num>= numVertices);// No hay posibilidad de agregar mas vertices, lista llena  
		}
	}
  
	public Vertice<T>[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertice<T>[] vertices) {
		this.vertices = vertices;
	}
	public boolean existeVertice(T i) {
		return true;
	}
}
