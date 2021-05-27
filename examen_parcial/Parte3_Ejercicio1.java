package examen_parcial;

import TDAArbol.BoundaryViolationException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;

/**
 * Tiempo :: 40 minutos cada ejercicio
 * @author Ignacio Joaquín Dotta
 */
public class Parte3_Ejercicio1 {
	
	/**
	 * Implemente un método eliminarPrimos(t,p) que, recibiendo un Tree<Integer> t y una Position<Integer> p,
	 *  modifique a t de forma tal que elimine todos los primos de p en t cuyos rótulos son mayores que 
	 *  el rótulo de p. Se dice que dos posiciones de árbol p1 y p2 son primos, si el padre de los padres 
	 *  de p1 y p2 es el mismo.

	*Un ejemplo del resultado de la ejecución del método puede observarse a continuación.
	 */
	public static void eliminarPrimos(Tree<Integer> t, Position<Integer> p) {
		Position<Integer> abuelo = null, padre = null;
		
		try {
			//Subir al padre
			if (p != null && !t.isRoot(p))
				padre = t.parent(p);
			
			//Subir al abuelo
			if (padre != null && !t.isRoot(padre))
				abuelo = t.parent(padre);
			
			//p solo puede tener primos si tiene abuelo
			if (abuelo != null) {
				
				//Bajar a los tios
				for (Position<Integer> tio : t.children(abuelo)) {
					
					if (tio != padre) {
						
						//Eliminar los primos con rótulo mayor que p
						for (Position<Integer> primo : t.children(tio))
							if (primo.element() > p.element())
								t.removeNode(primo);
					}
				}
			}
			
			
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}

}
