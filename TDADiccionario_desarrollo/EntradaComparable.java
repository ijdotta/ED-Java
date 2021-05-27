package TDADiccionario_desarrollo;

public class EntradaComparable<K extends Comparable<K>, V> implements Entry<K, V>, Comparable<EntradaComparable<K, V>> {
	
	protected K key;
	protected V value;
	
	/**
	 * Crea una entrada con la clave y valor recibidos.
	 * @param k Clave para la entrada.
	 * @param v Valor para la entrada.
	 */
	public EntradaComparable(K k, V v) {
		key = k;
		value = v;
	}
	
	@Override
	public int compareTo(EntradaComparable<K, V> o) {
		return key.compareTo(o.getKey());
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	/**
	 * Actualiza el valor de una entrada.
	 * @param v Valor nuevo.
	 */
	public void setValue(V v) {
		value = v;
	}

}
