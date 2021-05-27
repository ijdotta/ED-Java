package ED2021;

import TDALista_desarrollo.EmptyListException;
import TDALista_desarrollo.InvalidPositionException;
import TDALista_desarrollo.PositionList;

public class Services {
	
	/*
	 * Planteo.

		CB, L = <>.
		Si L es vacía, entonces L* = <>
		
		CR, L = <...> (tiene elementos)
		Si L tiene elementos, entonces L* es L' invertida con el primer elemento de L insertado al final.
		
		Donde L' es L sin su primer elemento.
	 */
	
	public static <E> void invertir(PositionList<E> l) {
		
		if (l.isEmpty()) {
			// la dejamos como está
		}
		else {
			try {
				E primerElemento = l.remove(l.first());
				
				invertir(l); //Esto transforma a l' en l' invertida.
				
				l.addLast(primerElemento);
				
				
			} catch (InvalidPositionException | EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
