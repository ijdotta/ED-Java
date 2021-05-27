package TDAColaCP;

/**
 * Class EmptyPriorityQueueException - Modela un excepci�n producida por cola con prioridad vac�a.
 * @author Ignacio Joaqu�n Dotta
 *
 */
public class EmptyPriorityQueueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea un nueva excepci�n con un mensaje de error.
	 * @param msg Mensaje asociado a la excepci�n.
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}

}
