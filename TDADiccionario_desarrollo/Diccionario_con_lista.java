package TDADiccionario_desarrollo;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Diccionario_con_lista<K, V> implements Dictionary<K, V> {
	
	protected PositionList<Entrada<K,V>> dictionary;
	
	public Diccionario_con_lista() {
		dictionary = new DoubleLinkedList<Entrada<K,V>>();
	}

	@Override
	public int size() {
		return dictionary.size();
	}

	@Override
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}
	
	/**
	 * Solución no estructurada.
	 */
	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		
		for (Entry<K,V> entry : dictionary)
			if (entry.getKey().equals(key))
				return entry;
		
		return null;
	}
	
	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (Entry<K,V> entry : dictionary)
			if (entry.getKey().equals(key))
				iterable.addLast(entry);
		
		return iterable;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		Entrada<K,V> entry = new Entrada<K,V>(key, value);
		dictionary.addLast(entry);
		return entry;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e == null)
			throw new InvalidEntryException("Entrada nula .");
		
		Iterator<Position<Entrada<K, V>>> positions = dictionary.positions().iterator();
		Entrada<K,V> entry = null;
		
		try {
			while (positions.hasNext() && entry == null) {
				Position<Entrada<K, V>> aux = positions.next();
				if (aux.element().equals(e)) {
					entry = aux.element();
					dictionary.remove(aux);
				}
			}
		} catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}
		
		if (entry == null)
			throw new InvalidEntryException("Entrada no está en el diccionario. ");
		
		return entry;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (Entry<K,V> entry : dictionary)
			iterable.addLast(entry);
		
		return iterable;
	}

	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

}
