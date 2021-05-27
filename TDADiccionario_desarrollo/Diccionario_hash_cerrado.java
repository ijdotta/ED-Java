package TDADiccionario_desarrollo;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Diccionario_hash_cerrado<K,V> implements Dictionary<K, V> {
	
	protected Entry<K,V> [] arreglo;
	protected int size;
	
	protected final int initialSize = 11;
	protected final float loadFactor = 0.5f;
	protected Entry<K,V> bucket_no_usado = new Entrada<K,V>(null,null);
	protected Entry<K,V> bucket_libre = new Entrada<K,V>(null,null);
	
	@SuppressWarnings("unchecked")
	public Diccionario_hash_cerrado() {
		arreglo = (Entrada<K,V> []) new Entrada[initialSize];
		for (int i = 0; i < initialSize; i++)
			arreglo[i] = bucket_no_usado;
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		
		Entry<K,V> to_return = null;
		int bucket = hash(key);
		int buckets_revisados = 0;
		boolean seguir = true;
		
		while (to_return == null && seguir && buckets_revisados < arreglo.length) {
			Entry<K,V> current = arreglo[bucket];
			buckets_revisados++;
			
			if (current == bucket_no_usado)
				seguir = false;
			else if (current != bucket_libre && current.getKey().equals(key))
				to_return = current;
			else
				bucket = (bucket + 1) % arreglo.length;
			
		}
		
		if (buckets_revisados >= arreglo.length)
			System.out.println("HOLA NACHO ! Corregir put o corregir arreglos");
		
		return to_return;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);

		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		Entry<K,V> current = null;
		int bucket = hash(key);
		int buckets_revisados = 0;
		
		while (current != bucket_no_usado && buckets_revisados <= size) {
			current = arreglo[bucket];
			buckets_revisados++;
			
			if (current != bucket_no_usado && current != bucket_libre && current.getKey().equals(key))
				iterable.addLast(current);
			
			bucket = (bucket + 1) % arreglo.length;
		}
		
		return iterable;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> new_entry = new Entrada<K,V>(key, value);
		
		System.out.println("arraysize :: " + arreglo.length); //Control debug
		mostrarCond();
		float current_loadFactor = (float) size / arreglo.length;
		boolean hasToResize = current_loadFactor >= loadFactor;
		if (hasToResize)
			resize();
		
		System.out.println("arraysize :: " + arreglo.length); //Control debug
		
		int bucket = hash(key);
		
		while (arreglo[bucket] != bucket_libre && arreglo[bucket] != bucket_no_usado)
			bucket = (bucket + 1) % arreglo.length;
		
		arreglo[bucket] = new_entry;
		size++;
		
		return new_entry;
	}

	private void mostrarCond() {
		System.out.println("Size is :: " + size);
		System.out.println("Arreglo.length is :: " + arreglo.length);
		float fc = (float) size / arreglo.length;
		System.out.println("Then, loadFactor is :: " + fc + " and so siz/arreglo.length >= lf :: " + (fc >= loadFactor));
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		checkEntry(e);
		
		Entry<K,V> to_return = null;
		int buckets_revisados = 0;
		int bucket = hash(e.getKey());
		boolean seguir = true;
		
		while (to_return == null && seguir && buckets_revisados < arreglo.length) {
			Entry<K,V> current = arreglo[bucket];
			buckets_revisados++;
			
			if (current == bucket_no_usado)
				seguir = false;
			else if (current.equals(e)) {
				to_return = current;
				arreglo[bucket] = bucket_libre;
				size--;
			}
			else
				bucket = (bucket + 1) % arreglo.length;
		}
		
		if (to_return == null)
			throw new InvalidEntryException("La entrada no está en el diccionario. ");
		
		return to_return;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (int i = 0; i < arreglo.length; i++) {
			Entry<K,V> current = arreglo[i];
			if (current != bucket_no_usado && current != bucket_libre)
				iterable.addLast(current);
		}
		
		return iterable;
	}
	
	protected int hash(K key) {
		return Math.abs(key.hashCode() % arreglo.length);
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

	private void checkEntry(Entry<K, V> e) throws InvalidEntryException {
		if (e == null)
			throw new InvalidEntryException("Entrada inválida. ");
		if (e.getKey() == null)
			throw new InvalidEntryException("Entrada con clave inválida. ");
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		Entry<K,V> [] arreglo_viejo = arreglo;
		
		int new_size = next_prime_number(arreglo.length);
		arreglo = (Entrada<K,V> []) new Entrada[new_size];
		for (int i = 0; i < new_size; i++)
			arreglo[i] = bucket_no_usado;
		
		for (int i = 0; i < arreglo_viejo.length; i++) {
			Entry<K,V> current = arreglo_viejo[i];
			
			if (current != bucket_no_usado && current != bucket_libre) {
				int bucket = hash(current.getKey());
				
				while (arreglo[bucket] != bucket_libre && arreglo[bucket] != bucket_no_usado)
					bucket = (bucket + 1) % arreglo.length;
				
				arreglo[bucket] = current;
			}
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

}
