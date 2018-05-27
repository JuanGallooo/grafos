package solucionUVA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws Exception {
		Controlador mundo= new Controlador();
	}
	public static class Vertice<T extends Comparable<?>> {
		private boolean visitado;
		private T elemento;
	    private HashMap<Object, Arista<T>> aristas;
		
		public Vertice(T elemento) {
			super();
			aristas=new HashMap<Object, Arista<T>>();
			visitado= false;
			this.elemento = elemento;
		}
		public T getElemento() {
			return elemento;
		}
		public void setElemento(T elemento) {
			this.elemento = elemento;
		}
		public boolean insertarArista(Arista<T> arista) {
			Arista<T> actual = aristas.get(arista.getPeso());
			if (actual != null){ // Ya existe la arista
				return false;
			}
			aristas.put(arista.hashCode(), arista);
			return true;
		}
		public Arista<T> eliminarArista(Arista<T> arista){
			return aristas.remove(arista.hashCode());
		}
		public HashMap<Object, Arista<T>> getAristas() {
			return aristas;
		}
		public void setAristas(HashMap<Object, Arista<T>> aristas) {
			this.aristas = aristas;
		}
		public boolean isVisitado() {
			return visitado;
		}
		public void setVisitado(boolean visitado) {
			this.visitado = visitado;
		}
		@Override
		public String toString() {
			return elemento.toString();
		}
		public int compareTo(Vertice<T> vertice) {
			return 0;
		}
		public void eliminarDestino(String elemento) {
			Iterator<Object> iterador= aristas.keySet().iterator();
			while (iterador.hasNext()) {
				Object k=iterador.next();
				if(aristas.get(k).getDestino().getElemento().toString().equals(elemento)) {
		        	   aristas.remove(k);
		        	   iterador= aristas.keySet().iterator();
		         }
			}
			
		}
	}
	public static class GrafoListasDirigido<T extends Comparable<?>> {

		private HashMap<Object, Vertice<T>> vertices;
		private HashMap<Object, Arista<T>> aristas;
		private Iterator<Object> iterador;
		
		public GrafoListasDirigido(){
			this.vertices = new HashMap<Object, Vertice<T>>();
			this.aristas = new HashMap<Object, Arista<T>>();
		}
	    public GrafoListasDirigido(ArrayList<Vertice<T>> vertices){
		this.vertices = new HashMap<Object, Vertice<T>>();
		this.aristas = new HashMap<Object, Arista<T>>();
		for(Vertice<T> v : vertices )
		    {
			this.vertices.put(v.getElemento().toString(), v);
		    }
	    }
		public boolean insertarArista(String v1, String v2, int peso) {
			if (v1.equals(v2)) {
				// No hay bucles entre vertices
				return false;
			}
			if (!vertices.containsKey(v1) || !vertices.containsKey(v2)) {
				// No existe alguno de los dos vertices
				return false;
			}
			Arista<T> nuevaArista = new Arista<T>(vertices.get(v2), peso);
			if (!vertices.get(v1).insertarArista(nuevaArista)) {
				return false;
			}
			aristas.put(nuevaArista.hashCode(), nuevaArista);
			return true;
		}
		public boolean insertarVertice(Vertice<T> vertice) {
			Vertice<T> actual = vertices.get(vertice.getElemento().toString());
			if (actual != null) { // Ya existe el vertice
				return false;
			}
			vertices.put(vertice.getElemento().toString(), vertice);
			return true;
		}
		
		public boolean contieneLaArista(Arista<T> arista) {
			if (arista.getDestino() == null || arista.getDestino() == null) {
				return false;
			}
			return aristas.containsKey(arista.hashCode());
		}
		public Arista<T> eliminarArista(String referencia,Arista<T> arista) {
			iterador= vertices.keySet().iterator();
			while (iterador.hasNext()) {
				String k= (String) iterador.next();
				if(vertices.get(k).toString().equals(referencia) && vertices.get(k).getAristas().containsKey(arista.hashCode())){
					vertices.get(k).getAristas().remove(arista.hashCode());
				}
			}
			return this.aristas.remove(arista.hashCode());
		}
		public Vertice<T> eliminarVertice(String vertice) {
			iterador= aristas.keySet().iterator();
			while (iterador.hasNext()) {
				Object k=iterador.next();
				if(aristas.get(k).getDestino().getElemento().toString().equals(vertice)) {
		        	   aristas.remove(k);
		        	   iterador= aristas.keySet().iterator();
		           }
			}
			iterador= vertices.keySet().iterator();
			while (iterador.hasNext()) {
				String k= (String) iterador.next();
				vertices.get(k).eliminarDestino(vertice);
			}
			return this.vertices.remove(vertices.get(vertice).toString());
		}
		//Consultar
	    public Vertice<T> getVertice(String etiqueta){
		    return this.vertices.get(etiqueta);
	    }
		public HashMap<Object, Vertice<T>> getVertices() {
			return vertices;
		}
		public void setVertices(HashMap<Object, Vertice<T>> vertices) {
			this.vertices = vertices;
		}
		public HashMap<Object, Arista<T>> getAristas() {
			return aristas;
		}
		public void setAristas(HashMap<Object, Arista<T>> aristas) {
			this.aristas = aristas;
		}
		public void limpiarCadaVertice() {
			vertices.forEach((k, v) -> {
		        	   vertices.get(k).setVisitado(false);;
		    });
		}
	    public void minimumSpaningTree(){
	        ColaPrioridad<Arista<T>,T> all = new ColaPrioridad<>();
	        aristas.forEach((k, v) -> {
	        	all.add(aristas.get(k));
	        });
	    }
	    public Vertice<T> getRefencia(Arista<T> arista){
	    	return ((AristaNoDirigida<T>) arista).getReferencia();
	    }
		public Iterator<Object> getIterador() {
			return iterador;
		}
		public void setIterador(Iterator<Object> iterador) {
			this.iterador = iterador;
		}
		public int buscarEnLista(String buscar,String[] asignados) {
			for (int i = 0; i < asignados.length; i++) {
				if(asignados[i].equals(buscar)) return i; 
			}
			return -1;
		}
		public ArrayList<Vertice<T>> getVerticesList() {
			ArrayList<Vertice<T>> list = new ArrayList<Vertice<T>>();
			setIterador(getVertices().keySet().iterator());
			while (getIterador().hasNext()) {
				Object k = (Object) getIterador().next();
				list.add(getVertices().get(k));
			}
			return list;
		}
		//BFS
			public ArrayList<Vertice<T>> bfs(String s){
				ArrayList<Vertice<T>> retorno= new ArrayList<Vertice<T>>();
				Queue<Vertice<T>> queue = new LinkedList<Vertice<T>>();
				queue.add(vertices.get(s));
				retorno.add(vertices.get(s));
				vertices.get(s).setVisitado(true);
				while(!queue.isEmpty()){
					Vertice<T> node = (Vertice<T>)queue.poll();
					Vertice<T> child=null;		
					Iterator it = node.getAristas().keySet().iterator();
					while (it.hasNext()) {	
					Object e = it.next();
					child= node.getAristas().get(e).getDestino();
					if(!child.isVisitado()) {
					retorno.add(child);
					child.setVisitado(true);
					queue.add(child);
					}
					}
				}
				limpiarCadaVertice();
				return retorno;
			}
			//DFS
			public ArrayList<Vertice<T>> dfs(String s) {
				ArrayList<Vertice<T>> retorno = new ArrayList<Vertice<T>>();
				Stack<Vertice<T>> stack = new Stack<Vertice<T>>();
				stack.push(vertices.get(s));
				vertices.get(s).setVisitado(true);
				retorno.add(vertices.get(s));
				while (!stack.isEmpty()) {
					Vertice<T> node = (Vertice<T>) stack.peek();
					Vertice<T> child = null;
					Iterator it = node.getAristas().keySet().iterator();
					while (it.hasNext()) {
						Object e = it.next();
						child= node.getAristas().get(e).getDestino();
						if (!child.isVisitado()) {
							child.setVisitado(true);
							retorno.add(child);
							stack.push(child);
						} else {
							stack.pop();
						}
					}
				}
				limpiarCadaVertice();
				return retorno;
			}
	}
	public static class GrafoListasNoDirigido<T extends Comparable<?>> extends GrafoListasDirigido<T> {
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
		public List<AristaNoDirigida<T>> getAristasList() {
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
	public static class Arista<T extends Comparable<?>> implements Comparable<Arista<T>>{
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
	public static class AristaNoDirigida<T extends Comparable<?>> extends Arista<T>{
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
	public static class DijkstraListaAdyacencia<T extends Comparable<?>> {
	    private HashMap<Object, Arista<T>> aristas;
	    private Set<Vertice<T>> verticesVisitados;
	    private Set<Vertice<T>> verticesNoVisitados;
	    private Map<Vertice<T>, Vertice<T>> predecesores;
	    private Map<Vertice<T>, Integer> distancias;
	    public DijkstraListaAdyacencia(GrafoListasNoDirigido<T> graph) {
	        this.aristas =  graph.getAristas();
	    }
	    public void inicio(Vertice<T> inicio) {
	        verticesVisitados= new HashSet<Vertice<T>>();
	        verticesNoVisitados= new HashSet<Vertice<T>>();
	        distancias = new HashMap<Vertice<T>, Integer>();
	        predecesores = new HashMap<Vertice<T>, Vertice<T>>();
	        distancias.put(inicio, 0);
	        verticesNoVisitados.add(inicio);
	        while (verticesNoVisitados.size() > 0) {
	        	Vertice<T> nodo = getMinimo(verticesNoVisitados);
	        	verticesVisitados.add(nodo);
	        	verticesNoVisitados.remove(nodo);
	        	distanciasMinimas(nodo);
	        }
	    }
	    private void distanciasMinimas(Vertice<T> node) {
	        List<Vertice<T>> nodoAdyacentes = getVecinos(node);
	        for (Vertice<T> destino:nodoAdyacentes) {
	        	int distanciaMinima= getDistanciaMinima(destino);
	        	int suma= getDistanciaMinima(node)+getDistancia(node, destino);
	            if (distanciaMinima>suma) {
	                distancias.put(destino, suma);
	                predecesores.put(destino, node);
	                verticesNoVisitados.add(destino);
	            }
	        }
	    }
	    private int getDistancia(Vertice<T> node, Vertice<T> target) {
			Iterator iterador= aristas.keySet().iterator();
			while (iterador.hasNext()) {
				Object k= (Object) iterador.next();
	            if (aristas.get(k).getRefencia(aristas.get(k)).equals(node)
	                    && aristas.get(k).getDestino().equals(target)) {
	                return (int) aristas.get(k).getPeso();
	            }
			}
	        throw new RuntimeException("Should not happen");
	    }
	    private List<Vertice<T>> getVecinos(Vertice<T> node) {
	        List<Vertice<T>> vecinos = new ArrayList<Vertice<T>>();
			Iterator iterador= aristas.keySet().iterator();
			while (iterador.hasNext()) {
				Object k= (Object) iterador.next();
				if (aristas.get(k).getRefencia(aristas.get(k)).equals(node) && !esVisitado(aristas.get(k).getDestino())) {
					vecinos.add(aristas.get(k).getDestino());
	            }
			}
	        return vecinos;
	    }
	    private Vertice<T> getMinimo(Set<Vertice<T>> vertices) {
	    	Vertice<T> minimum = null;
	        for (Vertice<T> vertice : vertices) {
	            if (minimum == null) {
	                minimum = vertice;
	            } else {
	                if (getDistanciaMinima(vertice) < getDistanciaMinima(minimum)) {
	                    minimum = vertice;
	                }
	            }
	        }
	        return minimum;
	    }
	    public boolean esVisitado(Vertice<T> vertice) {
	        return verticesVisitados.contains(vertice);
	    }
	    public int getDistanciaMinima(Vertice<T> destination) {
	        Integer d = distancias.get(destination);
	        if (d == null) {
	            return Integer.MAX_VALUE;
	        } else {
	            return d;
	        }
	    }
	    public LinkedList<Vertice<T>> destinoFinal(Vertice<T> target) {
	        LinkedList<Vertice<T>> path = new LinkedList<Vertice<T>>();
	        Vertice<T> paso = target;
	        if (predecesores.get(paso) == null) {
	            return null;
	        }
	        path.add(paso);
	        while (predecesores.get(paso) != null) {
	        	paso = predecesores.get(paso);
	            path.add(paso);
	        }
	        Collections.reverse(path);
	        return path;
	    }
}
	public static class ColaPrioridad<T extends Comparable<?>,P extends Comparable<?>>
	{
	    private Object[] data;
	    private int size;
	    private static final int capacidad = 10;

	    public ColaPrioridad () {
	        data = new Object[capacidad];
	        size = 0;
	    }
	    public void add(T arista) {
	        if (size >= data.length - 1) {
	            data = this.agrandar();
	        }
	        size++;
	        int index = size;
	        data[index] = arista;
	        upNode();
	    }
	    public boolean isEmpty() {
	        return size == 0;
	    }
		public T peek() {
	        if (this.isEmpty()) {
	            return null;
	        }
	        return (T) data[1];
	    }
	    public T remove() {
	        T  result = peek();
	        data[1] = data[size];
	        data[size] = null;
	        size--;
	        downNode();
	        return result;
	    }

		protected void downNode() {
	        int index = 1;
	        while (izquierdaIndex(index) <= size) {
	            int smallerChild = izquierdaIndex(index);

	            if (derechaIndex(index) <= size
	                    && ((Arista<P>) data[izquierdaIndex(index)]).getPeso()>((Arista<P>)data[derechaIndex(index)]).getPeso()) {
	                smallerChild = derechaIndex(index);
	            }
	            if (((Arista<P>) data[index]).getPeso()>((Arista<P>)data[smallerChild]).getPeso()) {
	                cambiar(index, smallerChild);
	            } else {
	                break;
	            }
	            index = smallerChild;
	        }   
	    }

		private void upNode() {
	        int index = this.size;
	        while (index > 1 && ((Arista<P>) data[parienteIndex(index)]).getPeso()<((Arista<P>)data[index]).getPeso()) {
	            cambiar(index, parienteIndex(index));
	            index = parienteIndex(index);
	        }
		}
		public int izquierdaIndex(int i) {
	    	return i * 2;
	    }
		public int derechaIndex(int i) {
	        return i * 2 + 1;
	    }
		public int parienteIndex(int i) {
	        return i / 2;
	    }
		public Object[] agrandar() {
	        return Arrays.copyOf(data, data.length * 2);
	    }
		public void cambiar(int index1, int index2) {
	        Object tmp = data[index1];
	        data[index1] = data[index2];
	        data[index2] = tmp;
	    }
	}
	public static class ConjuntoDisjunto<T> {
		private List<ArrayList<T>> set;
		public ConjuntoDisjunto() {
			set = new LinkedList<ArrayList<T>>();
		}
		public void crearSubConjuntos(Collection<T> items) {
			for (T item : items) {
				ArrayList<T> subConjunto= new ArrayList<T>(items.size());
				subConjunto.add(item);
				set.add(subConjunto);
			}
		}
		public void unir(int setA, int setB) {
			set.get(setA).addAll(set.get(setB));
			set.remove(setB);
		}
		public int buscar(T buscar) {
			for (int i = 0; i < set.size(); i++) {
				if (set.get(i).contains(buscar)) {
					return i;
				}
			}
			return -1;
		}
	}
	public static class Controlador {
		
		private String solucionProblema1;
		
		private DarkRoads darkRoads;
		
		public Controlador() throws Exception{
			darkRoads= new DarkRoads();
			darkRoads.crearGrafo();
		}
		public ArrayList<String> darSoluciones(){
			return darkRoads.getSoluciones();
		}

		public String getSolucionProblema1() {
			return solucionProblema1;
		}

		public void setSolucionProblema1(String solucionProblema1) {
			this.solucionProblema1 = solucionProblema1;
		}

		public DarkRoads darkRoads() {
			return darkRoads;
		}

		public void darkRoads(DarkRoads darkRoads) {
			this.darkRoads = darkRoads;
		}
	}
	public static class DarkRoads {
		private GrafoListasNoDirigido<String> grafo;
		private ArrayList<String> soluciones;
		
		public DarkRoads() {
			grafo= new GrafoListasNoDirigido<String>();
		}
		
		public void crearGrafo() throws Exception{
			try {
				BufferedReader io= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter rc= new BufferedWriter(new OutputStreamWriter(System.out));
				String mensaje= io.readLine();
				String[] split= mensaje.split(" ");
				while (!split[0].equals("0") && !split[1].equals("0")) {
					grafo= new GrafoListasNoDirigido<String>();
					Integer.parseInt(split[0]);// numVertices
					int roads = Integer.parseInt(split[1]);
					for (int i = 0; i < roads; i++) {
						mensaje= io.readLine();
						split= mensaje.split(" ");
						String referencia= split[0];
						String destino= split[1];
						int peso= Integer.parseInt(split[2]);
						if( grafo.getVertice(referencia)==null) grafo.insertarVertice(new Vertice<String>(referencia));
						if( grafo.getVertice(destino)==null) grafo.insertarVertice(new Vertice<String>(destino));
						grafo.insertarArista(referencia, destino, peso);
					}
					GrafoListasNoDirigido<String> solucion= grafo.Kruskal();
					if(solucion.getAristas().isEmpty()) rc.write("0");
					else {
					List<AristaNoDirigida<String>> aristasSolucion= solucion.getAristasList();
					List<AristaNoDirigida<String>>aristasTotales= grafo.getAristasList();
					int pesoSolucion= 0;
					int pesoTotal=0;
					for (int i = 0; i < aristasTotales.size(); i++) {
						pesoTotal+= aristasTotales.get(i).getPeso();
					}
					
					for (int i = 0; i < aristasSolucion.size(); i++) {
						pesoSolucion+= aristasSolucion.get(i).getPeso();
					}
					int dineroAhorrado= pesoTotal-pesoSolucion;
					rc.write(dineroAhorrado+"");
					}
					mensaje= io.readLine();
					split= mensaje.split(" ");
				}
				io.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error al leer el archivo");
			}
		}
		public GrafoListasNoDirigido<String> getGrafo() {
			return grafo;
		}
		public void setGrafo(GrafoListasNoDirigido<String> grafo) {
			this.grafo = grafo;
		}
		public ArrayList<String> getSoluciones() {
			return soluciones;
		}
		public void setSoluciones(ArrayList<String> soluciones) {
			this.soluciones = soluciones;
		}
	}
}
