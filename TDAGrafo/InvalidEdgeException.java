package TDAGrafo;

/**
 * Class InvalidEdgeException - Modela la excepci�n producida por un arco inv�lido.
 * @author Ignacio Joaqu�n Dotta
 *
 */
public class InvalidEdgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea la excepci�n con el mensaje recibido.
	 * @param msg Mensaje de error de la excepci�n.
	 */
	public InvalidEdgeException(String msg) {
		super(msg);
	}

}
