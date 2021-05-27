package TDAColaCP;

public class Entrada<K, V> implements Entry<K, V> {
	
	protected K key;
	protected V value;
	
	/**
	 * Crea una entrada con la clave y valor recibidos.
	 * @param k Clave para la entrada.
	 * @param v Valor para la entrada.
	 */
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
