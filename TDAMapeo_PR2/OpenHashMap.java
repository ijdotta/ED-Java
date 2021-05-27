package TDAMapeo_PR2;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Class OpenHashMap - Modela un mapeo con tabla hash abierta.
 * @author Ignacio Joaquín Dotta
 * @param <K> Tipo de dato de las claves.
 * @param <V> TIpo de dato de los valores.
 */
public class OpenHashMap<K, V> implements Map<K, V> {
	
	protected PositionList<Entrada<K,V>> [] arreglo;
	protected int size;
	
	protected final float loadFactor = 0.75f;
	protected final int initialSize = 11;
	
	/**
	 * Crea un mapeo vacío.
	 */
	@SuppressWarnings("unchecked")
	public OpenHashMap() {
		arreglo = (PositionList<Entrada<K, V>>[]) new PositionList[initialSize];
		for (int i = 0; i < initialSize; i++) 
			arreglo[i] = new DoubleLinkedList<Entrada<K,V>>();
		size = 0;
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
	public V get(K key) throws InvalidKeyException {
		Iterator<Entrada<K,V>> it;
		Entry<K,V> entry;
		V value = null;
		
		checkKey(key);
		it = arreglo[hash(key)].iterator();
		
		//Busca una entrada con clave equivalente en la lista.
		while (it.hasNext() && value == null) {
			entry = it.next();
			if (entry.getKey().equals(key))
				value = entry.getValue();
		}
		
		return value;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		PositionList<Entrada<K,V>> list;
		Position<Entrada<K,V>> cursor;
		Entrada<K,V> entry;
		V toReturn = null;
		
		checkKey(key);
		
		//Control del factor de carga.
		if ( ((float) size / arreglo.length) >= loadFactor )
			resize();

		//Recupera el bucket de la entrada
		list = arreglo[hash(key)];
		
		try {
			//Busca una entrada con clave equivalente.
			cursor = list.isEmpty()? null : list.first();
			while (cursor != null && toReturn == null) {
				entry = cursor.element();
				
				//Si la clave está en el mapeo, recupera y actualiza el valor de la entrada.
				if (entry.getKey().equals(key)) {
					toReturn = entry.getValue();
					entry.setValue(value);
				}
				else
					cursor = list.last() == cursor? null : list.next(cursor);
			}
			
			//Si la clave no estaba en el mapeo, añade una entrada nueva.
			if (toReturn == null) {
				list.addLast(new Entrada<K,V>(key, value));
				size++;
			}
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return toReturn;
	}
	
	@Override
	public V remove(K key) throws InvalidKeyException {
		PositionList<Entrada<K,V>> list;
		Position<Entrada<K,V>> cursor;
		Entrada<K,V> aux_entry = null;
		V toReturn = null;
		
		checkKey(key);
		list = arreglo[hash(key)];
		
		try {
			//Busca una entrada con clave equivalente.
			cursor = list.isEmpty() ? null : list.first();
			while (cursor != null && aux_entry == null) {
				aux_entry = cursor.element();
				if (!aux_entry.getKey().equals(key)) {
					aux_entry = null;
					cursor = list.last() == cursor? null : list.next(cursor);
				}
			}
			
			//Si existe entrada equivalente, se remueve y se guarda su valor.
			if (aux_entry != null) {
				toReturn = list.remove(cursor).getValue();
				size--;
			}
				
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entry<K,V> entry : arreglo[i])
				iterable.addLast(entry.getKey());
		
		return iterable;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entry<K,V> entry : arreglo[i])
				iterable.addLast(entry.getValue());
		
		return iterable;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entry<K,V> entry : arreglo[i])
				iterable.addLast(entry);
		
		return iterable;
	}
	
	/**
	 * Calcula el valor hash de una clave de forma tal que se encuentre dentro de los límites del arreglo.
	 * @param key Clave de referencia.
	 * @return Valor hash de la clave.
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode() % arreglo.length);
	}
	
	/**
	 * Método auxiliar para controlar que una clave sea válida.
	 * @param key Clave para controlar.
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

	/**
	 * Método auxiliar de put() - Modifica el tamaño del arreglo que modela la tabla hash, aumentando su capacidad.
	 * Las entradas del arreglo anterior se reciclan y reinsertan en el nuevo arreglo según el valor hash correspondiente
	 * a la nueva longitud del arreglo.
	 * @see put
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		PositionList<Entrada<K, V>> bucket;
		Entrada<K,V> entrada;
		int new_size = proximo_primo(size * 2);
		PositionList<Entrada<K, V>> [] arreglo_anterior = arreglo;
		
		//Crear nuevo arreglo de mayor tamaño.
		arreglo = (PositionList<Entrada<K, V>> []) new PositionList[new_size];
		for (int i = 0; i < new_size; i++)
			arreglo[i] = new DoubleLinkedList<Entrada<K,V>>();
		
		try {
			//Recorrer cada bucket del arreglo anterior para transferir entradas al nuevo.
			for (int i = 0; i < arreglo_anterior.length; i++) {
				bucket = arreglo_anterior[i];
				
				//Vaciar bucket actual, reciclando las entradas que ya tenía.
				while (!bucket.isEmpty()) {
					entrada = bucket.remove(bucket.first());
					int hash = hash(entrada.getKey());
					arreglo[hash].addLast(entrada);
				}
			}
		} catch (EmptyListException | InvalidPositionException e) {e.printStackTrace();}
	}

	/**
	 * Método auxiliar para resize() - Busca el próximo número primo a un número dado.
	 * @param num Número de referencia.
	 * @return Número primo siguiente al número de referencia.
	 * @see resize
	 */
	private int proximo_primo(int num) {
		boolean esPrimo = false;
		
		while (!esPrimo) {
			num++;
			esPrimo = true;
			for (int i = 2; i < num && esPrimo; i++)
				esPrimo = num % i != 0;
		}
		
		return num;
	}


}
