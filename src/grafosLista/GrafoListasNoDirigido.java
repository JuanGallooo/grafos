package grafosLista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import auxiliares.ConjuntoDisjunto;

public class GrafoListasNoDirigido<T extends Comparable<?>> extends GrafoListasDirigido<T> {
	public GrafoListasNoDirigido() {
		super();
	}
	public GrafoListasNoDirigido(List<Vertice<T>> nodes) {
		super();
		for(Vertice<T> v : nodes ){
			this.getVertices().put(v.getElemento().toString(), v);
		}
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
		AristaNoDirigida<T> nuevaArista = new AristaNoDirigida<>(getVertices().get(v1), getVertices().get(v2), peso);
		AristaNoDirigida<T> nuevaArista2 = new AristaNoDirigida<>(getVertices().get(v2), getVertices().get(v1), peso);
		if (!getVertices().get(v1).insertarArista(nuevaArista) || !getVertices().get(v2).insertarArista(nuevaArista2)) {
			// ya existe dicha arista
			return false;
		}
		getVertices().get(v1).insertarArista(nuevaArista);
		getVertices().get(v2).insertarArista(nuevaArista2);
		getAristas().put(nuevaArista.hashCode(), nuevaArista);
		return true;
	}
	@Override
	public Arista<T> eliminarArista(String referencia, Arista<T> arista) {
		((AristaNoDirigida<T>) arista).getReferencia().getAristas().remove(arista.hashCode());
		return this.getAristas().remove(arista.hashCode());
	}
	public List<AristaNoDirigida<T>> getAristasDeVerticeList(String vertice) {
		ArrayList<AristaNoDirigida<T>> list = new ArrayList<AristaNoDirigida<T>>();
		setIterador(getVertices().get(vertice).getAristas().keySet().iterator());
		while (getIterador().hasNext()) {
			Object k = (Object) getIterador().next();
			list.add((AristaNoDirigida<T>) getVertices().get(vertice).getAristas().get(k));
		}
		return list;
	}
	public ArrayList<AristaNoDirigida<T>> getAristasList() {
		ArrayList<AristaNoDirigida<T>> list = new ArrayList<AristaNoDirigida<T>>();
		setIterador(getAristas().keySet().iterator());
		while (getIterador().hasNext()) {
			Object k = (Object) getIterador().next();
			list.add((AristaNoDirigida<T>)getAristas().get(k));
		}
		return list;
	}
	
	public int[][] getMatrizPeso(){
		int[][] matrizPeso= new int[getVertices().size()][getVertices().size()];
		ArrayList<Vertice<T>> asignados= getVerticesList();
		setIterador(getAristas().keySet().iterator());
		for (int i = 0; i < matrizPeso.length; i++) {
			for (int j = 0; j < matrizPeso.length; j++) {
				if(i==j)matrizPeso[i][j]=0;
				else matrizPeso[i][j]= Integer.MAX_VALUE;
			}
		}
		while (getIterador().hasNext()) {
			Object k = (Object) getIterador().next();
			AristaNoDirigida<T> aux= (AristaNoDirigida<T>)getAristas().get(k); 
			int i= asignados.indexOf(aux.getReferencia());
			int j= asignados.indexOf(aux.getDestino());
			matrizPeso[i][j]=aux.getPeso();
			matrizPeso[j][i]=aux.getPeso();
		}
		return matrizPeso;
	}
	public GrafoListasNoDirigido<T> prim( Vertice<T> start) {
		Set<Vertice<T>> visited = new HashSet<Vertice<T>>();
		GrafoListasNoDirigido<T> g = new GrafoListasNoDirigido<T>();
		g.insertarVertice(start);
		PriorityQueue<AristaNoDirigida<T>> queue = new PriorityQueue<AristaNoDirigida<T>>(
				getAristasDeVerticeList(start.toString()));
		visited.add(start);
		while (!queue.isEmpty()) {
			AristaNoDirigida<T> min = queue.poll();
			if (!(visited.contains(min.getReferencia()) && visited.contains(min.getDestino()))) {
				visited.add(min.getDestino());
				if(g.getVertice(min.getReferencia().toString())== null)g.insertarVertice(min.getReferencia());
				if(g.getVertice(min.getDestino().toString())==null)g.insertarVertice(min.getDestino());
				g.insertarArista(min.getReferencia().toString(), min.getDestino().toString(), (int) min.getPeso());
				queue.addAll(getAristasDeVerticeList(min.getDestino().toString()));
			}
		}
		return g;
	}
	public GrafoListasNoDirigido<T> Kruskal() {
		List<Vertice<T>> nodes= getVerticesList();
		List<AristaNoDirigida<T>> edges= getAristasList();
		Collections.sort(edges);
		GrafoListasNoDirigido<T> grafo= new GrafoListasNoDirigido<T>(nodes);
		ArrayList<AristaNoDirigida<T>> MST = new ArrayList<AristaNoDirigida<T>>(edges.size() / 2);
		ConjuntoDisjunto<Vertice<T>> nodeset = new ConjuntoDisjunto<Vertice<T>>();
		nodeset.crearSubConjuntos(nodes);
		for (AristaNoDirigida<T> e : edges) {
			if (nodeset.buscar(e.getReferencia()) != nodeset.buscar(e.getDestino())) {
				nodeset.unir(nodeset.buscar(e.getReferencia()), nodeset.buscar(e.getDestino()));
				grafo.insertarArista(e.getReferencia().toString(), e.getDestino().toString(),e.getPeso());
				MST.add(e);
			}
		}
		return grafo;
	}
	public LinkedList<Vertice<T>> dijkstra(String inicio, String finalidad) {
		DijkstraListaAdyacencia<T> dijkstra = new DijkstraListaAdyacencia<T>(this);
		dijkstra.inicio(getVertices().get(inicio));
		LinkedList<Vertice<T>> vertices = dijkstra.destinoFinal(getVertices().get(finalidad));
		return vertices;
	}
	public int[][] floydWarshall (){
		int [][] matrizPeso= getMatrizPeso();
		int vertices = matrizPeso.length;
		int matrizAdyacencia[][] =  matrizPeso;
		int i,j,k;
		float temporal1,temporal2,temporal3,temporal4,minimo;
		for(k=0;k<vertices;k++){
			for(i =0; i < vertices;i++){
				for(j=0;j< vertices;j++){
					temporal1=matrizAdyacencia[i][j];
					temporal2=matrizAdyacencia[i][k];
					temporal3=matrizAdyacencia[k][j];
					temporal4 = temporal2+temporal3;
					minimo = Math.min(temporal1, temporal4);
					matrizAdyacencia[i][j] =  (int)minimo;
				}
			}
		}
		return matrizAdyacencia;
	}
}
