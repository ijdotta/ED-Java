/**
 * 
 */
package TDAGrafo;

import java.util.Map;

/**
 * Interface DecorablePosition - Define las operaciones de una posición que además es un mapeo, de forma
 * tal que pueda asociarse una clave ESTADO y un valor VISITADO / NO VISITADO
 * @author Ignacio Joaquín Dotta
 *
 * @param <E> Tipo de dato a almacenar en la posición decorable.
 */
public interface DecorablePosition<E> extends Position<E>, Map<Object, Object> {

}
