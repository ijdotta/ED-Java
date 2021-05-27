package practica_parcial;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;

public class Postorden {
	
public static void postorden(Tree<Integer> t) {
  try {
    if (!t.isEmpty())
      post(t.root(), t);
  } catch (EmptyTreeException e) {
    e.printStackTrace();
  }
}

private static void post(Position<Integer> node, Tree<Integer> t) {
  try {
    for (Position<Integer> n : t.children(node))
      post(n, t);
  } catch (InvalidPositionException e) {
    e.printStackTrace();
  }
  System.out.print(node.element() + " ");
}

}
