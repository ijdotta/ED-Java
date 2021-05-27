package TDAColaCP;

/**
 * Class InvalidKeyException - Modela un excepci�n producida por clave inv�lida.
 * @author Ignacio Joaqu�n Dotta
 *
 */
public class InvalidKeyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea un nueva excepci�n con un mensaje de error.
	 * @param msg Mensaje asociado a la excepci�n.
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}

}
