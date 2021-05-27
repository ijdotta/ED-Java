package TDAGrafo;

/**
 * Class InvalidEdgeException - Modela la excepción producida por un arco inválido.
 * @author Ignacio Joaquín Dotta
 *
 */
public class InvalidEdgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea la excepción con el mensaje recibido.
	 * @param msg Mensaje de error de la excepción.
	 */
	public InvalidEdgeException(String msg) {
		super(msg);
	}

}
