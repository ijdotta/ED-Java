package TDAGrafo;

/**
 * Interface Position - Define las operaciones de una posición de grafo.
 * @author Ignacio Joaquín Dotta
 *
 * @param <E> Tipo de dato a almacenar en la posición.
 */
public interface Position<E> {
	
	/**
	 * Devuelve el elemento de la posición.
	 * @return Elemento en la posición.
	 */
	public E element();
	
}
