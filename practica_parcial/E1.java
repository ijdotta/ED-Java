package practica_parcial;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;

public class E1 {
	
	public static void inorder(Tree<Integer> t) {
		try {
			if (!t.isEmpty())
				inorder_aux(t.root(), t);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
	}

private static void inorder_aux(Position<Integer> n, Tree<Integer> t) {
  Iterator<Position<Integer>> hermanos;
  
  try {
    hermanos = t.children(n).iterator();
    
    if (hermanos.hasNext());
      inorder_aux(hermanos.next(), t);
      
    System.out.println(n.element() + " ");
    
    while (hermanos.hasNext())
      inorder_aux(hermanos.next(), t);
    
  } catch (InvalidPositionException e) {
    e.printStackTrace();
  }
}

}
