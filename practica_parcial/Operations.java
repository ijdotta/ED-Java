package practica_parcial;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.Tree;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Operations {
	
public static boolean contenido_e_invertido(PositionList<Character> l1, PositionList<Character> l2) {
  boolean cond = l1 != null && l2 != null;
  Position<Character> p1, p2;
  
  try {
    if (!l1.isEmpty() && !l2.isEmpty()) {
      
      p1 = l1.first();
      p2 = l2.first();
      
      //Buscar l2 contenida en l1 de principio a fin.
      while (cond && p1 != null && p2 != null) {
        cond = p1.element() == p2.element();
        p1 = l1.last() == p1? null : l1.next(p1);
        p2 = l2.last() == p2? null : l2.next(p2);
      }
      
      //Control de que l2 terminó y l1 aún tiene elementos.
      cond = cond && p1 != null && p2 == null;
      
      p2 = l2.last();
      
      //Buscar l2 contenida en l1 de fin a inicio.
      while (cond && p1 != null && p2 != null) {
        cond = p1.element() == p2.element();
        p1 = l1.last() == p1? null : l1.next(p1);
        p2 = l2.first() == p2? null : l2.prev(p2);
      }
      
      //Control de que ambas llegaron a su fin.
      cond = cond && p1 == null && p2 == null;
      
    }
    else {
      //Si alguna es vacía, necesariamente ambas deben estar vacías para cumplirse la cond.
      cond = l1.isEmpty() && l2.isEmpty();
    }
  } catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
  
  return cond;
}
	
	public static void inorder(Tree<Integer> t) {
		try {
			if (!t.isEmpty())
				inorder_aux(t.root(), t);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
	}

	private static void inorder_aux(TDAArbol.Position<Integer> n, Tree<Integer> t) {
		Iterator<TDAArbol.Position<Integer>> hermanos;
		TDAArbol.Position<Integer> p;
		
		try {
			if (t.isExternal(n)) {
				System.out.println(n.element() + " ");
			}
			else {
				hermanos = t.children(n).iterator();
				p = hermanos.next();
				inorder_aux(p, t);
				System.out.println(n.element() + " ");
				
				while (hermanos.hasNext()) {
					p = hermanos.next();
					inorder_aux(p, t);
				}
			}
		} catch (TDAArbol.InvalidPositionException e) {
			e.printStackTrace();
		}
	}

}
