package TDAColaCP;

import java.util.Comparator;

public class HeapPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	
	protected Entry<K,V> [] array;
	protected int size;
	protected Comparator<K> comparator;
	
	/**
	 * Crea una cola con prioridad vacía y usa el comparador recibido.
	 * @param comp Comparador de claves.
	 */
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(Comparator<K> comp) {
		array = (Entry<K,V> []) new Entry[10];
		size = 0;
		comparator = comp;
	}
	
	/**
	 * Crea una cola vacía con el comparador por defecto.
	 */
	public HeapPriorityQueue() {
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
		return array[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entry<K,V> new_entry, current, parent;
		int i;
		boolean seguir = true;
		
		//Control y asignación
		checkKey(key);
		new_entry = new Entrada<K,V>(key, value);
		if (size+1 == array.length)
			resize();
		
		//Añadir entrada nueva
		array[++size] = new_entry;
		
		//Burbujear la entrada
		i = size;
		while (i > 1 && seguir) {
			current = array[i];
			parent = array[i/2];
			
			if (comparator.compare(current.getKey(), parent.getKey()) < 0) {
				//swap
				array[i] = parent;
				array[i/2] = current;
				i /= 2;
			}
			else
				seguir = false;
		}
		
		return new_entry;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		Entry<K,V> [] old_array = array;
		array = (Entry<K,V> []) new Entry[old_array.length * 2];
		for (int i = 0; i <= size; i++)
			array[i] = old_array[i];
	}

	@Override
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> swap, to_return;
		int i, hi, hd, min;
		boolean seguir;
		
		//exceptions
		if (size == 0)
			throw new EmptyPriorityQueueException("removeMin() sobre cola vacía. ");
		
		//recuperar raíz [1]
		to_return = array[1];
		
		//Caso especial: un elemento.
		if (size == 1) {
			array[1] = null;
			size = 0;
		}
		//rECORDAR QUE SUBINDICE SIZE ES VÁLIDO, PUES USAMOS DESDE POS 1, NO POS 0
		else {
		//buscar extremo derecho [size] y trasladar a la raíz [1]
			array[1] = array[size];
			array[size] = null;
			size--;
			i = 1;
			seguir = true;
			
			//burbujear hacia abajo hasta encontrar su posición
			while (seguir) {
				hi = i*2;
				hd = i*2 + 1;
				
				//si no tengo hijo izq -> tampoco derecho -> soy hoja, terminé
				if (hi > size)
					seguir = false;
				else {
					//si tengo derecho -> tengo izq -> tomo el mínimo de ellos
					if (hd <= size) {
						if (comparator.compare(array[hi].getKey(), array[hd].getKey()) < 0)
							min = hi;
						else
							min = hd;
					}
					//else :: tengo solo izq -> lo tomo como mínimo
					else
						min = hi;
					
					//comparar mi posicion actual con el mínimo encontrado
					//si actual es mayor -> swap
					if (comparator.compare(array[i].getKey(), array[min].getKey()) >= 0) {
						swap = array[i];
						array[i] = array[min];
						array[min] = swap;
						i = min;
					}
					else
						seguir = false;
						//sino -> terminé
				}
			}
		}
		
		return to_return;
	}
	
	/**
	 * Método auxiliar para valdiar claves.
	 * @param key Clave a validar.
	 * @throws InvalidKeyException si la clave recibida es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}
	
}
