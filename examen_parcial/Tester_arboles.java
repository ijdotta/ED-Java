package examen_parcial;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidPositionException;
import TDAArbol.LinkedTree;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;

public class Tester_arboles {
	
	protected static Tree<Integer> t;
	protected static Position<Integer> [] p;
	
	public static void main(String[] args) {
		new_arbol();
		print(t);
		
		//------------------------AREA DE TESTING------------------------//
		Parte3_Ejercicio1.eliminarPrimos(t, p[5]);
		print(t);
		
		//------------------------AREA DE TESTING------------------------//
	}

	@SuppressWarnings("unchecked")
	private static void new_arbol() {
		t = new LinkedTree<Integer>();
		p = (Position<Integer>[]) new Position[10];
		
		try {
			t.createRoot(1);
			p[1] = t.root();
			p[2] = t.addLastChild(p[1], 2);
			p[3] = t.addLastChild(p[1], 3);
			p[4] = t.addLastChild(p[1], 4);
			p[5] = t.addLastChild(p[2], 5);
			p[6] = t.addLastChild(p[2], 6);
			p[7] = t.addLastChild(p[3], 7);
			p[8] = t.addLastChild(p[7], 8);
			p[9] = t.addLastChild(p[7], 9);
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static <E> void print(Tree<E> t) {
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

}
