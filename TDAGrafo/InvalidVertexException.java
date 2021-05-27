package TDAGrafo;

/**
 * Class InvalidVertexException - Modela la excepci�n producida por un v�rtice inv�lido.
 * @author Ignacio Joaqu�n Dotta
 *
 */
public class InvalidVertexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea la excepci�n con el mensaje recibido.
	 * @param msg Mensaje de error de la excepci�n.
	 */
	public InvalidVertexException(String msg) {
		super(msg);
	}

}
