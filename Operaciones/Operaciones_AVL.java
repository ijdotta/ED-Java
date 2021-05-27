package Operaciones;

import TDAArbolBinario.BinaryTree;
import TDAArbolBinario.BoundaryViolationException;
import TDAArbolBinario.EmptyTreeException;
import TDAArbolBinario.InvalidPositionException;
import TDAArbolBinario.Position;

public class Operaciones_AVL {
	
	public static <E> boolean isAVL(BinaryTree<E> t) {
		try {
			
			if (t.isEmpty())
				return true;
			else
				return isAVL(t, t.root()) != -1;
			
		} catch (EmptyTreeException e) {e.printStackTrace();}
		return false;
	}

	private static <E> int isAVL(BinaryTree<E> t, Position<E> node) {
		int leftHeight = 0, rightHeight = 0;
		
		try {
			
			if (t.hasLeft(node)) {
				leftHeight = isAVL(t, t.left(node));
				if (leftHeight == -1)
					return leftHeight;
			}
			
			if (t.hasRight(node)) {
				rightHeight = isAVL(t, t.right(node));
				if (rightHeight == -1)
					return -1;
			}
			
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		
		return Math.max(leftHeight, rightHeight) + 1;
	}

}
