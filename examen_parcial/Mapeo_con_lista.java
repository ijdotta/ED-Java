//package examen_parcial;
//
//import java.util.Comparator;
//
//import ABB.DefaultComparator;
//import TDALista.BoundaryViolationException;
//import TDALista.DoubleLinkedList;
//import TDALista.EmptyListException;
//import TDALista.InvalidPositionException;
//import TDALista.Position;
//import TDALista.PositionList;
//import TDAMapeo_PR2.Entry;
//import TDAMapeo_PR2.InvalidKeyException;
//import TDAMapeo_PR2.Map;
//
//
//public class Mapeo_con_lista<K extends Comparable<K>, V> implements Map<K, V> {
//	
//	protected PositionList<Entrada<K, V>> map;
//	protected Comparator<K> comparator;
//	
//	public Mapeo_con_lista(Comparator<K> comp) {
//		map = new DoubleLinkedList<Entrada<K, V>>();
//	}
//	
//	public Mapeo_con_lista() {
//		this(new DefaultComparator<K>());
//	}
//
//	@Override
//	public V get(K key) throws InvalidKeyException {
//		V toReturn = null;
//		Position<Entrada<K, V>> pointer;
//		
//		if (key == null)
//			throw new InvalidKeyException("Clave inválida. ");
//		else if (map.size() == 0)
//			throw new InvalidKeyException("Entrada no están en el mapeo. ");
//		
//		try {
//			//Busqueda de la posición que contiene la entrada con clave equivalente.
//			
//			pointer = map.first(); //Ya se controló que el mapeo no sea vacío!
//			while (pointer != null && comparator.compare(key, pointer.element().getKey()) < 0)
//				pointer = pointer == map.last()? null : map.next(pointer);
//				
//			//Si la encontró actualiza el valor de retorno
//			if (pointer != null && comparator.compare(key, pointer.element().getKey()) == 0)
//				toReturn = pointer.element().getValue();
//			
//		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
//			e.printStackTrace();
//		}
//		
//		return toReturn;
//	}
//
//	@Override
//	public int size() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean isEmpty() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public V put(K key, V value) throws InvalidKeyException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public V remove(K key) throws InvalidKeyException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<K> keys() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<V> values() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Entry<K, V>> entries() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
//
//public class Entrada<K extends Comparable<K>, V> {
//	
//	protected K key;
//	protected V value;
//	
//	public Entrada(K k, V v) {
//		key = k;
//		value = v;
//	}
//
//}
