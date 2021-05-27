package examen_parcial;

import TDAArbol.BoundaryViolationException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;

/**
 * Tiempo :: 40 minutos cada ejercicio
 * @author Ignacio Joaqu�n Dotta
 */
public class Parte3_Ejercicio1 {
	
	/**
	 * Implemente un m�todo eliminarPrimos(t,p) que, recibiendo un Tree<Integer> t y una Position<Integer> p,
	 *  modifique a t de forma tal que elimine todos los primos de p en t cuyos r�tulos son mayores que 
	 *  el r�tulo de p. Se dice que dos posiciones de �rbol p1 y p2 son primos, si el padre de los padres 
	 *  de p1 y p2 es el mismo.

	*Un ejemplo del resultado de la ejecuci�n del m�todo puede observarse a continuaci�n.
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
						
						//Eliminar los primos con r�tulo mayor que p
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
