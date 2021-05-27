package TDADiccionario_desarrollo;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Diccionario_hash_abierto<K,V> implements Dictionary<K, V> {
	
	protected PositionList<Entry<K,V>> [] arreglo;
	protected int size;
	
	protected final int initialSize = 10;
	protected final float loadFactor = 0.5f;
	
	@SuppressWarnings("unchecked")
	public Diccionario_hash_abierto() {
		arreglo = (PositionList<Entry<K,V>>[]) new PositionList[initialSize];
		for (int i = 0; i < initialSize; i++)
			arreglo[i] = new DoubleLinkedList<Entry<K,V>>();
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
		
		//Solución NO estructurada
		int bucket = hash(key);
		for (Entry<K,V> e : arreglo[bucket])
			if (e.getKey().equals(key))
				return e;
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private Entry<K,V> find_estructurado(K key) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> to_return = null;
		boolean hay_entrada_asociada = false;
		
		Iterator<Entry<K,V>> bucket = arreglo[hash(key)].iterator(); 
		
		while (bucket.hasNext() && !hay_entrada_asociada) {
			to_return = bucket.next();
			if (to_return.getKey().equals(key))
				hay_entrada_asociada = true;
			else
				to_return = null;
		}
		
		return to_return;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		PositionList<Entry<K,V>> bucket = arreglo[hash(key)];
		for (Entry<K,V> e : bucket)
			if (e.getKey().equals(key))
				iterable.addLast(e);
		
		return iterable;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> entrada = new Entrada<K,V>(key, value);
		
		arreglo[hash(key)].addLast(entrada);
		size++;
		
		return entrada;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		checkEntry(e);
		
		PositionList<Entry<K,V>> bucket = arreglo[hash(e.getKey())];
		Iterator<Position<Entry<K,V>>> positions = bucket.positions().iterator();
		boolean hay_entrada = false;
		Entry<K,V> entry = null;
		
		try {
			while (positions.hasNext() && !hay_entrada) {
				Position<Entry<K,V>> entrada = positions.next();
				if (entrada.element().equals(e)) {
					hay_entrada = true;
					entry = bucket.remove(entrada);
					size--;
				}
			}
		} catch (InvalidPositionException exc) {
			exc.printStackTrace();
		}
		
		if (!hay_entrada)
			throw new InvalidEntryException("No hay entrada equivalente en la lista. ");
		
		return entry;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (int i = 0; i < arreglo.length; i++)
			for (Entry<K,V> e : arreglo[i])
				iterable.addLast(e);
		
		return iterable;
	}
	
	public void convertir_a_mapeo() {
		
		//Recorrer arreglo de buckets
		for (int i = 0; i < arreglo.length; i++) {
			PositionList<Entry<K,V>> bucket = arreglo[i];
			Position<Entry<K,V>> cursor, recolector;
			
			try {
				if (!bucket.isEmpty()) {
					
					cursor = bucket.first();
					
					//Tomar cada entrada y eliminar toda entrada siguiente con clave equivalente
					while (cursor != null) {
						
						K current_key = cursor.element().getKey();
						recolector = bucket.next(cursor);
						
						//El recolector buscará cada entrada con clave equivalente y la removerá
						while (recolector != null) {
							
							Position<Entry<K,V>> aux = recolector;
							recolector = recolector == bucket.last()? null : bucket.next(recolector);
							
							if (aux.element().getKey().equals(current_key))
								bucket.remove(aux);
							
						}
						cursor = cursor == bucket.last()? null : bucket.next(cursor);
					}
				}
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				e.printStackTrace();
			}
		}
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

}
