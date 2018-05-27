package auxiliares;
import java.util.Arrays;

import grafosLista.Arista;
@SuppressWarnings("unchecked")
public class ColaPrioridad<T extends Comparable<?>,P extends Comparable<?>>
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