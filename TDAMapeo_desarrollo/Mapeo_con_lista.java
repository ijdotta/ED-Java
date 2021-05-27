package TDAMapeo_desarrollo;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
//import TDALista.LinkedList;
import TDALista.Position;
import TDALista.PositionList;

public class Mapeo_con_lista<K, V> implements Map<K, V> {
	
	protected PositionList<Entrada<K,V>> map;
	
	public Mapeo_con_lista() {
		map = new DoubleLinkedList<Entrada<K,V>>();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
			
		Entrada<K,V> entry = hallarEntradaDadaKey(key);
		
		if (entry == null)
			return null;
		else
			return entry.getValue();
	}

	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("No existe entrada para tal clave. ");
	}

	private Entrada<K, V> hallarEntradaDadaKey(K key) {
		Iterator<Entrada<K, V>> it = map.iterator();
		Entrada<K,V> entry = null;
		
		while (it.hasNext() && entry == null) {
			entry = it.next();
			if (!entry.getKey().equals(key))
				entry = null;
		}
		return entry;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		try {
			for (Position<Entrada<K,V>> pos : map.positions()) {
				if (pos.element().getKey().equals(key)) {
					V aux = pos.element().getValue();
					map.remove(pos);
					map.addLast(new Entrada<K,V>(key, value));
					return aux;
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		map.addLast(new Entrada<K,V>(key, value));
		return null;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		
		Position<Entrada<K,V>> pos = null;
		Iterator<Position<Entrada<K,V>>> positions = map.positions().iterator();
		V value = null;
		
		try {
			while (positions.hasNext() && pos == null) {
				pos = positions.next();
				if (!pos.element().getKey().equals(key))
					pos = null;
				else {
					value = pos.element().getValue();
					map.remove(pos);
				}
			}
		} catch (InvalidPositionException e) {e.printStackTrace();}
			
		return value;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		
		for (Entrada<K,V> entry : map) 
			iterable.addLast(entry.getKey());
		
		return iterable;
	}
	
//	@Override
//	public Iterable<K> keys() {
//		/*
//		 * Uso LinkedList porque a los fines de un iterable, no es necesario hallar el anterior a una
//		 * posición y, por lo tanto, no crece el tiempo de ejecución respecto de la DoubleLinkedList, pero
//		 * sí se reduce considerablemente el espacio de memoria usado.
//		 */
//		PositionList<K> iterable = new LinkedList<K>();
//		
//		try {
//			Position<Entrada<K,V>> first, pointer;
//			first = map.first();
//			pointer = map.last();
//			
//			/*
//			 * Inserta desde el ultimo al primer elemento, con addFirst. La lista queda con el mismo orden que antes
//			 * pero lo hace en orden uno. (addLast de LinkedList es O(n))
//			 */
//			while (pointer != first) {
//				iterable.addFirst(pointer.element().getKey());
				/*
				 * Si uso prev, este tiene O(n) para las LinkedList, y ya cagué la implementación.
				 */
//				pointer = map.prev(pointer);
//			}
//			iterable.addFirst(pointer.element().getKey());
//			
//		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
//			e.printStackTrace();
//		}
//		
//		return iterable;
//	}

	@Override
	public Iterable<V> values() {
		/*
		 * Usa DoubleLinkedList para reducir el tiempo de addLast a O(1) en lugar de O(n) de LinkedList.
		 * Beneficio respecto a la solución con LinkedList: código más legible.
		 */
		PositionList<V> iterable = new DoubleLinkedList<V>();
		
		for (Entrada<K,V> entry : map) 
			iterable.addLast(entry.getValue());
		
		return iterable;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		for (Entrada<K,V> entry : map) 
			iterable.addLast(entry);
		
		return iterable;
	}

}
