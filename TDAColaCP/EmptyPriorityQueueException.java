package TDAColaCP;

/**
 * Class EmptyPriorityQueueException - Modela un excepción producida por cola con prioridad vacía.
 * @author Ignacio Joaquín Dotta
 *
 */
public class EmptyPriorityQueueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea un nueva excepción con un mensaje de error.
	 * @param msg Mensaje asociado a la excepción.
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}

}
