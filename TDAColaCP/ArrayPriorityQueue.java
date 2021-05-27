package TDAColaCP;

import java.util.Comparator;

public class ArrayPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	
	/**
	 * OPCIONES: COLA CON ARREGLO CIRCULAR - CASOS ESPECIALES PARA INSERTAR MÍNINO
	 * 			 COLA CON ARREGLO - 
	 */
	
	protected Entry<K,V> [] array;
	protected int size;
	protected Comparator<K> comparator;
	
	/**
	 * Crea una cola con prioridad vacía y con el comparador recibido.
	 * @param comp Comparador de claves.
	 */
	@SuppressWarnings("unchecked")
	public ArrayPriorityQueue(Comparator<K> comp) {
		array = (Entry<K, V>[]) new Entry[10];
		size = 0;
		comparator = comp;
	}
	
	/**
	 * Crea una cola con prioridad vacía con el comparador por defecto.
	 */
	public ArrayPriorityQueue() {
		this(new DefaultComparator<K>());
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (size == 0)
			throw new EmptyPriorityQueueException("min() sobre cola vacía. ");
		return array[0];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entry<K,V> new_entry;
		checkKey(key);
		
		if (size == array.length)
			resize();
		
		new_entry = new Entrada<K,V>(key, value);
		
		//Solución parche momentáneo:
		int i = 0;
		while (array[i] != null && (comparator.compare(key, array[i].getKey()) > 0) )
			i++;
		
		for (int j = size-1; j >= i; j--)
			array[j+1] = array[j];
		array[i] = new_entry;
		//FIN SOLUCIÓN PARCHE ----- CONSIDERAR HALLAR LA POSICIÓN CON BINARYSEARCH
		
		size++;
		return new_entry;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int new_size = array.length * 2;
		Entry<K,V> [] old_array = array;
		array = (Entry<K,V>[]) new Entry[new_size];
		
		for (int i = 0; i < size; i++)
			array[i] = old_array[i];
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> to_return;
		if (size == 0)
			throw new EmptyPriorityQueueException("removeMin() sobre cola vacía. ");
		
		//Recupera la entrada antes de eliminarla
		to_return = array[0];
		
		//Arrastra las entradas a la primera posición
		for (int i = 0; i < size-1; i++)
			array[i] = array[i+1];
		array[size-1] = null;
		size--;
		return to_return;
	}
	
	/**
	 * Método auxiliar para verificar validez de las claves.
	 * @param key Clave a verificar
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

}
