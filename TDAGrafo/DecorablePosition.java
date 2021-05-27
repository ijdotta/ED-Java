/**
 * 
 */
package TDAGrafo;

import java.util.Map;

/**
 * Interface DecorablePosition - Define las operaciones de una posici�n que adem�s es un mapeo, de forma
 * tal que pueda asociarse una clave ESTADO y un valor VISITADO / NO VISITADO
 * @author Ignacio Joaqu�n Dotta
 *
 * @param <E> Tipo de dato a almacenar en la posici�n decorable.
 */
public interface DecorablePosition<E> extends Position<E>, Map<Object, Object> {

}
