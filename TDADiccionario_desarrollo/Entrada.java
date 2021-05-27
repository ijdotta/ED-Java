package TDADiccionario_desarrollo;

public class Entrada<K, V> implements Entry<K, V> {
	
	protected K key;
	protected V value;
	
	public Entrada(K k, V v) {
		key = k;
		value = v;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

}
