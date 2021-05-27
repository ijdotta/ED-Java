package TDAMapeo_PR2;

/**
 * Class InvalidKeyException - Modela la excepci�n producida por clave inv�lida.
 * @author Ignacio Joaqu�n Dotta
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nueva excepci�n con el mensaje recibido.
	 * @param msg Mensaje de error.
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
	
}
