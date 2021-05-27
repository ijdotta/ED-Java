package practica_parcial;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;

public class Inorder_tree_gen {
	
	public static <E> void inorder(Tree<E> t) {
		try {
			if (!t.isEmpty())
				in(t.root(), t);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
	}

	private static <E> void in(Position<E> node, Tree<E> t) {
		Iterator<Position<E>> hermanos;
		
		
		try {
			hermanos = t.children(node).iterator();
			
			if (hermanos.hasNext())
				in(hermanos.next(), t);
			
			System.out.print(node.element() + " ");
			
			while (hermanos.hasNext())
				in(hermanos.next(), t);
			
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}

}
