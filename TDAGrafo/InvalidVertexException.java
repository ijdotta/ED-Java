package TDAGrafo;

/**
 * Class InvalidVertexException - Modela la excepción producida por un vértice inválido.
 * @author Ignacio Joaquín Dotta
 *
 */
public class InvalidVertexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea la excepción con el mensaje recibido.
	 * @param msg Mensaje de error de la excepción.
	 */
	public InvalidVertexException(String msg) {
		super(msg);
	}

}
