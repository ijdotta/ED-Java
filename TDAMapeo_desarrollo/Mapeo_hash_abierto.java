package TDAMapeo_desarrollo;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Mapeo_hash_abierto<K, V> implements Map<K, V> {
	
	protected PositionList<Entrada<K,V>> [] arreglo;
	protected int initialSize = 11;
	protected int size;
	protected float loadFactor; //factor de carga de referencia.
	
	@SuppressWarnings("unchecked")
	public Mapeo_hash_abierto() {
		arreglo = (PositionList<Entrada<K, V>>[]) new PositionList[initialSize];
		for (int i = 0; i < initialSize; i++)
			arreglo[i] = new DoubleLinkedList<Entrada<K,V>>();
		loadFactor = 0.9f;
	}
	
	/**
	 * Devuelve la ubicación del bucket correspondiente a la clave.
	 * @param key Clave para hallar bucket.
	 * @return Ubicación del bucket.
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode() % arreglo.length);
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
		checkKey(key);
		
		Entrada<K,V> entry = hallar_entrada(key);
		
		if (entry != null)
			return entry.getValue();
		
		return null;
	}
	
	/**
	 * Devuelve la entrada asociada una clave, o nulo si no existe.
	 * @param key Clave para hallar entrada
	 * @return Entrada asociada a key o null.
	 */
	private Entrada<K, V> hallar_entrada(K key) {
		PositionList<Entrada<K,V>> lista = arreglo[hash(key)];
		
		for (Entrada<K,V> entry : lista)
			if (entry.getKey().equals(key))
				return entry;
		
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		//Crea la nueva entrada
		Entrada<K,V> entrada = new Entrada<K,V>(key, value);
		
		//Control del factor de carga
		if ( (size / arreglo.length) >= loadFactor)
			resize();
		
		//Obtiene la lista del bucket correspondiente.
		PositionList<Entrada<K,V>> lista = arreglo[hash(key)];
		
		//Busca una entrada asociada a la clave.
		Iterator<Position<Entrada<K, V>>> it = lista.positions().iterator();
		Position<Entrada<K,V>> aux = null;
		boolean hay_entrada_asociada = false;
		
		while (it.hasNext() && !hay_entrada_asociada) {
			aux = it.next();
			hay_entrada_asociada = aux.element().getKey().equals(key);
		}
		
		
		//Si hay una entrada asociada, se recupera el valor y luego se elimina.
		V valor_anterior = null;
		try {
			if (hay_entrada_asociada) {
				valor_anterior = aux.element().getValue();
				lista.remove(aux);
				size--;
			}
		} catch (InvalidPositionException e) {e.printStackTrace();}
		
		//Añade la entrada nueva.
		lista.addLast(entrada);
		size++;
		
		return valor_anterior;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		int new_size = next_prime_number(size * 2);
		PositionList<Entrada<K, V>>[] arreglo_anterior = arreglo;
		
		arreglo = (PositionList<Entrada<K, V>>[]) new PositionList[new_size];
		for (int i = 0; i < arreglo.length; i++)
			arreglo[i] = new DoubleLinkedList<Entrada<K,V>>();
		
		try {
			for (int i = 0; i < arreglo_anterior.length; i++) {
				PositionList<Entrada<K, V>> bucket = arreglo_anterior[i];
				while (!bucket.isEmpty()) {
					Entrada<K,V> entrada = bucket.remove(bucket.first());
					int hash = hash(entrada.getKey());
					arreglo[hash].addLast(entrada);;
				}
			}
		} catch (EmptyListException | InvalidPositionException e) {
			e.printStackTrace();}
	}
	
	/**
	 * Recibe un número y retorna el próximo primo.
	 * @param num Número de entrada.
	 * @return Primo siguiente a num.
	 */
	private int next_prime_number(int num) {
		boolean esPrimo = false;
		
		do {
			esPrimo = true;
			
			for (int i = 2; i < num && esPrimo; i++)
				esPrimo = num % i != 0;
			
			if (!esPrimo)
				num++;
			
		} while (!esPrimo);
		
		return num;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave nula. ");
		
		Iterator<Position<Entrada<K,V>>> it = arreglo[hash(key)].positions().iterator();
		Position<Entrada<K,V>> aux = null;
		boolean hay_entrada_asociada = false;
		
		while (it.hasNext() && !hay_entrada_asociada) {
			aux = it.next();
			hay_entrada_asociada = aux.element().getKey().equals(key);
		}
		
		
		try {
			
			//Remueve entrada asociada a key y retorna el valor.
			if (hay_entrada_asociada) {
				size--;
				return (arreglo[hash(key)].remove(aux)).getValue();
			}
			
		} catch (InvalidPositionException e) {e.printStackTrace();}
		
		//Si no hay entrada asociada, retorna null.
		return null;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entrada<K,V> e : arreglo[i])
				iterable.addLast(e.getKey());
		
		return iterable;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entrada<K,V> e : arreglo[i])
				iterable.addLast(e.getValue());
		
		return iterable;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entrada<K,V> e : arreglo[i])
				iterable.addLast(e);
		
		return iterable;
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

}
