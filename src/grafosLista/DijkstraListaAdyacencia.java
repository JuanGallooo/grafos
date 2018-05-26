package grafosLista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class DijkstraListaAdyacencia<T> {
	    private HashMap<Object, Arista<T>> aristas;
	    private Set<Vertice<T>> verticesVisitados;
	    private Set<Vertice<T>> verticesNoVisitados;
	    private Map<Vertice<T>, Vertice<T>> predecesores;
	    private Map<Vertice<T>, Integer> distancia;
	    public DijkstraListaAdyacencia(GrafoListasNoDirigido<T> graph) {
	        this.aristas =  graph.getAristas();
	    }
	    public void inicio(Vertice<T> inicio) {
	        verticesVisitados= new HashSet<Vertice<T>>();
	        verticesNoVisitados= new HashSet<Vertice<T>>();
	        distancia = new HashMap<Vertice<T>, Integer>();
	        predecesores = new HashMap<Vertice<T>, Vertice<T>>();
	        distancia.put(inicio, 0);
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
	                distancia.put(destino, suma);
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
	        Integer d = distancia.get(destination);
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
