package TDAColaCP;

/**
 * Class InvalidKeyException - Modela un excepción producida por clave inválida.
 * @author Ignacio Joaquín Dotta
 *
 */
public class InvalidKeyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea un nueva excepción con un mensaje de error.
	 * @param msg Mensaje asociado a la excepción.
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}

}
