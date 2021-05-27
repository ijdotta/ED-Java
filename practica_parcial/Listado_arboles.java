package practica_parcial;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbol.InvalidPositionException;
import TDAArbol.LinkedTree;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;

public class Listado_arboles {
	
	protected static Tree<Integer> t = gen_tree();
	
	public static void main(String [] args) {
		
		Tree<Integer> t1;
		Position<Integer> [] p = (Position<Integer>[]) new Position[10];
		
		try {
			t1 = new LinkedTree<Integer>();
			t1.createRoot(1);
			p[1] = t1.root();
			p[2] = t1.addLastChild(p[1], 2);
			p[3] = t1.addLastChild(p[1], 3);
			p[4] = t1.addLastChild(p[1], 4);
			p[5] = t1.addLastChild(p[2], 5);
			p[6] = t1.addLastChild(p[2], 6);
			p[7] = t1.addLastChild(p[3], 7);
			p[8] = t1.addLastChild(p[7], 8);
			p[9] = t1.addLastChild(p[7], 9);
			
			por_niveles(t1);
			por_niveles_saltos(t1);
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	

	
	public static <E> void por_niveles(Tree<E> t) {
		Queue<Position<E>> q = new LinkedQueue<Position<E>>();
		Position<E> v;
		
		try {
			q.enqueue(t.root());
			while (!q.isEmpty()) {
				v = q.dequeue();
				System.out.print(v.element() + " ");
				for (Position<E> w : t.children(v))
					q.enqueue(w);
			}
		} catch (EmptyTreeException | EmptyQueueException | InvalidPositionException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public static <E> void por_niveles_saltos(Tree<E> t) {
		Queue<Position<E>> cola_padres = new LinkedQueue<Position<E>>();
		
		try {
			if (!t.isEmpty()) {
				cola_padres.enqueue(t.root());
				saltos(cola_padres, t);
			}
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		
	}
	
	private static <E> void saltos(Queue<Position<E>> cola_padres, Tree<E> t) {
		Queue<Position<E>> cola_hijos = new LinkedQueue<Position<E>>();
		Position<E> v;
		
		try {
			while (!cola_padres.isEmpty()) {
				v = cola_padres.dequeue();
				System.out.print(v.element() + " ");
				for (Position<E> w : t.children(v))
					cola_hijos.enqueue(w);
			}
		} catch (EmptyQueueException | InvalidPositionException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		if (!cola_hijos.isEmpty())
			saltos(cola_hijos, t);
	}

	private static LinkedTree<Integer> gen_tree() {
		LinkedTree<Integer> tree = null;
		Position<Integer> root, currentChild_0, currentChild_1;
		try {
			tree = new LinkedTree<Integer>();
			tree.createRoot(0);
			root = tree.root();
			
			for (int i = 1; i < 3; i++) {
				currentChild_0 = tree.addLastChild(root, i);
				for (int j = 0; j < 3; j++) {
					currentChild_1 = tree.addLastChild(currentChild_0, i*10+j);
					for (int k = 0; k < 5; k++) {
						tree.addLastChild(currentChild_1, i*10*10+k);
					}
				}
			}
			
		} catch (InvalidOperationException | EmptyTreeException | InvalidPositionException e) {e.printStackTrace();}
		return tree;
	}
	
}
