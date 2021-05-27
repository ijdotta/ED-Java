package TDAMapeo_desarrollo;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

/**
 * Revisar el código, sobre todo el de hallar_entrada_bucket porque es un asco.
 * @author Ignacio
 *
 * @param <K>
 * @param <V>
 */
public class Mapeo_hash_cerrado<K, V> implements Map<K, V> {
	
	protected Entrada<K,V> [] arreglo;
	protected int initialSize = 11;
	protected int size;
	protected float loadFactor;
	
	protected final Entrada<K,V> bucket_no_usado = new Entrada<K,V>(null, null);
	protected final Entrada<K,V> bucket_disponible = new Entrada<K,V>(null, null);
	
	@SuppressWarnings("unchecked")
	public Mapeo_hash_cerrado() {
		arreglo = (Entrada<K,V>[]) new Entrada[initialSize];
		for (int i = 0; i < initialSize; i++) 
			arreglo[i] = bucket_no_usado;
		loadFactor = 0.5f;
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
		
		int bucket = hallar_entrada_bucket(key);
		
		if (bucket != -1)
			return arreglo[bucket].getValue();
		
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		if ( (size / arreglo.length) >= loadFactor )
			resize();
		
		V value_to_return = null;
		
		//Busca una entrada equivalente con sondeo lineal
		int bucket = hallar_entrada_bucket(key);
		
		//Si encuentra una entrada con clave equivalente, recupera el valor
		if (bucket != -1)
			value_to_return = arreglo[bucket].getValue();
		//Sino, busca su bucket con sondeo lineal
		else 
			bucket = bucket_sondeo_lineal(key);
			
		
		//Si encontro una entrada, entonces reemplaza la entrada
		//Sino, entonces se inserta en el primero libre
		arreglo[bucket] = new Entrada<K,V>(key, value);
		size++;
		
		return value_to_return;
	}
	
	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		
		V value_to_return = null;
		int bucket = hallar_entrada_bucket(key);
		
		if (bucket != -1) {
			value_to_return = arreglo[bucket].getValue();
			arreglo[bucket] = bucket_disponible;
			size--;
		}
		
		return value_to_return;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		
		for (int i = 0; i < arreglo.length; i++)
			if (arreglo[i] != bucket_no_usado && arreglo[i] != bucket_disponible)
				iterable.addLast(arreglo[i].getKey());
		
		return iterable;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		
		for (int i = 0; i < arreglo.length; i++)
			if (arreglo[i] != bucket_no_usado && arreglo[i] != bucket_disponible)
				iterable.addLast(arreglo[i].getValue());
		
		return iterable;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (int i = 0; i < arreglo.length; i++)
			if (arreglo[i] != bucket_no_usado && arreglo[i] != bucket_disponible)
				iterable.addLast(arreglo[i]);
		
		return iterable;
	}

	protected int hash(K key) {
		return Math.abs(key.hashCode() % arreglo.length);
	}

	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

	private int bucket_sondeo_lineal(K key) {
		int bucket = hash(key);
		
		while (arreglo[bucket] != bucket_no_usado && arreglo[bucket] != bucket_disponible)
			bucket = (bucket + 1) % arreglo.length;
		
		return bucket;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		int new_size = next_prime_number(arreglo.length * 2);
		Entrada<K,V>[] arreglo_viejo = arreglo;
		
		//Crea una tabla nueva
		arreglo = (Entrada<K,V>[]) new Entrada[new_size];
		for (int i = 0; i < new_size; i++) 
			arreglo[i] = bucket_no_usado;
		
		//Rehash
		for (int i = 0; i < arreglo_viejo.length; i++)
			if (arreglo_viejo[i] != bucket_no_usado && arreglo_viejo[i] != bucket_disponible) {
				Entrada<K,V> entrada = arreglo_viejo[i];
				int bucket = bucket_sondeo_lineal(entrada.getKey());
				arreglo_viejo[i] = null;
				arreglo[bucket] = entrada;
			}
	}

	/**
	 * Recibe un número y retorna el próximo primo.
	 * @param num Número de entrada.
	 * @return Primo siguiente a num.
	 */
	private int next_prime_number(int num) {
		boolean esPrimo = false;
		num++;
		
		do {
			esPrimo = true;
			
			for (int i = 2; i < num && esPrimo; i++)
				esPrimo = num % i != 0;
			
			if (!esPrimo)
				num++;
			
		} while (!esPrimo);
		
		return num;
	}

	/**
	 * Busca el bucket de una entrada, dada una clave y retorna su índice o -1 si no existe.
	 * @param key Clave para buscar entrada.
	 * @return Índice de la entrada o -1 si no existe.
	 */
	private int hallar_entrada_bucket(K key) {
		int hashCode = hash(key);
		
		if (arreglo[hashCode] != bucket_no_usado) {
			
			if (arreglo[hashCode] != bucket_disponible && arreglo[hashCode].getKey().equals(key))
				return hashCode;
			else {
				int sondeo = (hashCode + 1) % arreglo.length;
				
				while (arreglo[sondeo] != bucket_no_usado && sondeo != hashCode) {
					if (arreglo[sondeo] != bucket_disponible && arreglo[sondeo].getKey().equals(key))
						return sondeo;
					else
						sondeo = (sondeo + 1) % arreglo.length;
				}
				return -1;
			}
			
		}
		else
			return -1;
	}

}
