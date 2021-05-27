package TDAColaCP;

import java.util.Comparator;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ListPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	
	protected PositionList<Entry<K, V>> list;
	protected Comparator<K> comparator;
	
	/**
	 * Crea un cola con prioirdad vacía y usa el comparador recibido.
	 * @param comp Comparador de claves.
	 */
	public ListPriorityQueue(Comparator<K> comp) {
		list = new DoubleLinkedList<Entry<K,V>>();
		comparator = comp;
	}
	
	/**
	 * Crea una cola con prioridad vacía y usa el comparador por defecto.
	 */
	public ListPriorityQueue() {
		this(new DefaultComparator<K>());
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		Entry<K,V> toReturn = null;
		if (list.isEmpty())
			throw new EmptyPriorityQueueException("min() sobre cola vacía. ");
		
		try {
			toReturn = list.first().element();
		} catch (EmptyListException e) {e.printStackTrace();}
		
		return toReturn;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Position<Entry<K,V>> pointer;
		Entry<K,V> new_entry;
		
		//Control y asignación de valores.
		checkKey(key);
		new_entry = new Entrada<K,V>(key, value);
		
		try {
			//Busca la posición de la nueva entrada.
			pointer = list.isEmpty() ? null : list.first();
			while (pointer != null && comparator.compare(key, pointer.element().getKey()) >= 0)
				pointer = list.last() == pointer ? null : list.next(pointer);
			
			if (pointer == null)
				list.addLast(new_entry);
			else
				list.addBefore(pointer, new_entry);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return new_entry;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> to_return = null;
		if (list.isEmpty())
			throw new EmptyPriorityQueueException("removeMin() sobre cola vacía. ");
		
		try {
			to_return = list.remove(list.first());
		} catch (EmptyListException | InvalidPositionException e) {e.printStackTrace();}
		
		return to_return;
	}
	
	/**
	 * Método auxiliar para validar una clave.
	 * @param key Clave a validar.
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

}
