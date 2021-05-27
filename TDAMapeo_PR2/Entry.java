package TDAMapeo_PR2;

/**
 * Interface Entry - Define el conjunto de operaciones aplicables sobre una entrada.
 * @author Ignacio Joaquín Dotta
 * @param <K> Tipo de dato de la clave de la entrada.
 * @param <V> Tipo de dato del valor de la entrada.
 */
public interface Entry<K, V> {
	
	/**
	 * Devuelve la clave de la entrada o null si no tiene.
	 * @return Clave de la entrada.
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada o null si no tiene.
	 * @return Valor de la entrada.
	 */
	public V getValue();
}
