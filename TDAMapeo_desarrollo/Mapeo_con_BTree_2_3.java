package TDAMapeo_desarrollo;

import BTree.BTree_2_3;

public class Mapeo_con_BTree_2_3<K extends Comparable<K>, V> implements Map<K, V> {
	
	protected BTree_2_3<EntradaComparable<K, V>> map;
	protected int size;
	
	public Mapeo_con_BTree_2_3() {
		map = new BTree_2_3<>();
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

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

}
