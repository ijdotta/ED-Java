package TDAColaCP;

/**
 * Interface Entry - Define el conjunto de operaciones aplicables sobre una entrada.
 * @author Ignacio
 *
 * @param <K> Tipo de dato de la clave de la entrada.
 * @param <V> Tipo de dato del valor de la entrada.
 */
public interface Entry<K, V> {
	
	/**
	 * Devuelve la clave de la entrada.
	 * @return clave de la entrada.
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada.
	 * @return valor de la entrada.
	 */
	public V getValue();
}
