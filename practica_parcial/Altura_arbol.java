package practica_parcial;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;

public class Altura_arbol {
	
	public static void main(String [] args) {
		
	}
	
	public static <E> int altura(Tree<E> t) {
		int h = 0;
		try {
			if (!t.isEmpty())
				h = height(t.root(), t);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return h;
	}

	private static <E> int height(Position<E> root, Tree<E> t) {
		int h = 0;
		
		try {
			if (t.isInternal(root))
				for (Position<E> child : t.children(root))
					h = Math.max(h, height(child, t)+1);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		
		return h;
	}
	
}
