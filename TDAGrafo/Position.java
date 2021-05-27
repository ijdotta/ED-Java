package TDAGrafo;

/**
 * Interface Position - Define las operaciones de una posici�n de grafo.
 * @author Ignacio Joaqu�n Dotta
 *
 * @param <E> Tipo de dato a almacenar en la posici�n.
 */
public interface Position<E> {
	
	/**
	 * Devuelve el elemento de la posici�n.
	 * @return Elemento en la posici�n.
	 */
	public E element();
	
}
