package TDAMapeo_PR2;

/**
 * Class Entrada - Modela una entrada de mapeo.
 * @author Ignacio Joaquín Dotta
 * @param <K> Tipo de dato de la clave de la entrada.
 * @param <V> Tipo de dato del valor de la entrada.
 */
public class Entrada<K, V> implements Entry<K, V> {
	
	protected K key;
	protected V value;
	
	/**
	 * Crea una entrada con la claves y valor recibidos.
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
	
	/**
	 * Actualiza el valor de una entrada.
	 * @param v Valor a establecer.
	 */
	public void setValue(V v) {
		value = v;
	}

}
