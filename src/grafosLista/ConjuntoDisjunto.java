package grafosLista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ConjuntoDisjunto<E> {
	private List<ArrayList<E>> set;
	public ConjuntoDisjunto() {
		set = new LinkedList<ArrayList<E>>();
	}
	public void crearSubConjuntos(Collection<E> items) {
		for (E item : items) {
			ArrayList<E> subConjunto= new ArrayList<E>(items.size());
			subConjunto.add(item);
			set.add(subConjunto);
		}
	}
	public void unir(int setA, int setB) {
		set.get(setA).addAll(set.get(setB));
		set.remove(setB);
	}
	public int buscar(E buscar) {
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i).contains(buscar)) {
				return i;
			}
		}
		return -1;
	}
}
