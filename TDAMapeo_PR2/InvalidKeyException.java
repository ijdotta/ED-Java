package TDAMapeo_PR2;

/**
 * Class InvalidKeyException - Modela la excepción producida por clave inválida.
 * @author Ignacio Joaquín Dotta
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nueva excepción con el mensaje recibido.
	 * @param msg Mensaje de error.
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
	
}
