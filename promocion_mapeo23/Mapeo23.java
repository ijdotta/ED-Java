package promocion_mapeo23;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;
import TDAMapeo_desarrollo.Entry;
import TDAMapeo_desarrollo.InvalidKeyException;
import TDAMapeo_desarrollo.Map;

/**
 * 
 * @author Ignacio Joaquín Dotta - LU 126269
 */
public class Mapeo23<K extends Comparable<K>, V> implements Map<K, V> {
	
	/*
	 * La convención adoptada para un nodo 2 es que tiene solo entrada1 
	 * y puede tener hi e hd (es decir, se descarta hm).
	 *  
	 * Si se tienen entrada1 y entrada2, y se elimina entrada1, entonces entrada2
	 * se traslada al campo entrada1. (Esto lo hace el cliente, es decir, Mapeo23)
	 * 
	 */
	
	/*
	 * Comentario: como en diagrama UML no se especifica que la clase tenga un comparador de claves,
	 * asumo que se usará el método compareTo directamente, 
	 * en lugar de delegar la comparación a un Comparator.
	 */

	protected Nodo23<K, V> raiz;
	protected int size;
	
	
	
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> entries = new DoubleLinkedList<Entry<K,V>>();
		
		if (raiz != null)
			entries_inorder(raiz, entries);
		
		return entries;
	}

	/**
	 * El cliente del método debe pasar un nodo no nulo. Si es no nulo, se asume que al menos
	 * tiene una entrada (además podría tener hijos y una segunda entrada).
	 */
	private void entries_inorder(Nodo23<K, V> nodo, PositionList<Entry<K, V>> entries) {
		Nodo23<K, V> hi, hm, hd;
		Entry<K, V> e1, e2;
		
		e1 = nodo.getEntrada1();
		e2 = nodo.getEntrada2();
		hi = nodo.getHi();
		hm = nodo.getHm();
		hd = nodo.getHd();
		
		if (hi != null)
			entries_inorder(hi, entries);
		
		entries.addLast(e1);
		
		if (e2 != null) {
			
			if (hm != null)
				entries_inorder(hm, entries);
			
			entries.addLast(e2);
		}
		
		if (hd != null)
			entries_inorder(hd, entries);
	}
	
	
	
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class Nodo23<K extends Comparable<K>, V> {
		
		private Entry<K, V> entrada1, entrada2;
		private Nodo23<K, V> hi, hm, hd;
		
		public Nodo23(Entry<K, V> e1, Entry<K, V> e2, Nodo23<K, V> i, Nodo23<K, V> m, Nodo23<K, V> d) {
			entrada1 = e1;
			entrada2 = e2;
			hi = i;
			hm = m;
			hd = d;
		}
		
		public Nodo23(Entry<K, V> e1, Nodo23<K, V> i, Nodo23<K, V> d) {
			this(e1, null, i, null, d);
		}
		
		public Nodo23(Entry<K, V> e1, Entry<K, V> e2) {
			this(e1, e2, null, null, null);
		}
		
		public Nodo23(Entry<K, V> e1) {
			this (e1, null);
		}
		
		public Entry<K, V> getEntrada1() {
			return entrada1;
		}
		
		public Entry<K, V> getEntrada2() {
			return entrada2;
		}
		
		public Nodo23<K, V> getHi() {
			return hi;
		}
		
		public Nodo23<K, V> getHm() {
			return hm;
		}
		
		public Nodo23<K, V> getHd() {
			return hd;
		}
		
		public void setEntrada1(Entry<K, V> entrada1) {
			this.entrada1 = entrada1;
		}
		
		public void setEntrada2(Entry<K, V> entrada2) {
			this.entrada2 = entrada2;
		}
		
		public void setHi(Nodo23<K, V> hi) {
			this.hi = hi;
		}
		
		public void setHm(Nodo23<K, V> hm) {
			this.hm = hm;
		}
		
		public void setHd(Nodo23<K, V> hd) {
			this.hd = hd;
		}

	}

}
