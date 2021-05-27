package MyTesters;

import TDAArbolBinario.BinaryTree;
import TDAArbolBinario.LinkedBinaryTree;
import TDAArbolBinario.Position;
import TDACola.LinkedQueue;
import TDACola.Queue;

import java.util.Random;

public class MyBinaryTree {
	
	protected static LinkedBinaryTree<Integer> t;

	public static void main(String[] args) {
		test_espejar();
		test_padre_de_hojas();
		test_clone();
	}

	private static void setUp(boolean rand, int niveles) {
		Position<Integer> root;
		t = new LinkedBinaryTree<Integer>();
		try {
			root = t.createRoot(0);
			randomChildren(root, niveles, rand);
		} catch (Exception e) {e.printStackTrace();}
	}

	private static void test_clone() {
		LinkedBinaryTree<Integer> clone;
		setUp(true, 5);
		System.out.println("CURRENT TREE: ");
		por_niveles();
		System.out.println("Clonando t en clone ...");
		clone = t.clone();
		System.out.println("CLON: ");
		por_niveles(clone);
	}

	private static void por_niveles(BinaryTree<Integer> t) {
		Queue<Position<Integer>> cola = new LinkedQueue<Position<Integer>>();
		try {	
			if (!t.isEmpty()) {
				cola.enqueue(t.root());
				por_niveles_aux(cola, t);
			}
		} catch (Exception e) {e.printStackTrace();}		
	}

	private static void por_niveles_aux(Queue<Position<Integer>> cola, BinaryTree<Integer> t) {
		Queue<Position<Integer>> cola_hijos = new LinkedQueue<Position<Integer>>();
		Position<Integer> pos;
		
		try {
			
			while (!cola.isEmpty()) {
				pos = cola.dequeue();
				System.out.print(pos.element() + " ");
				if (t.hasLeft(pos))
					cola_hijos.enqueue(t.left(pos));
				if (t.hasRight(pos))
					cola_hijos.enqueue(t.right(pos));
			}
			
			System.out.println();
			
		} catch (Exception e) {e.printStackTrace();}
		
		if (!cola_hijos.isEmpty())
			por_niveles_aux(cola_hijos, t);		
	}

	private static void test_padre_de_hojas() {
		setUp(false, 5);
		System.out.println("CURRENT TREE: ");
		por_niveles();
		System.out.println("PADRES HOJAS: ");
		t.padre_de_hojas();
	}

	private static void test_espejar() {
		setUp(true, 3);
		System.out.println("CURRENT TREE: ");
		por_niveles();
		System.out.println("ESPEJANDO ARBOL ...");
		t.espejar();
		System.out.println("RESULTADO DE ESPEJAR: ");
		por_niveles();
	}
	
	private static void por_niveles() {
		Queue<Position<Integer>> cola = new LinkedQueue<Position<Integer>>();
		try {	
			if (!t.isEmpty()) {
				cola.enqueue(t.root());
				por_niveles_aux(cola);
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	
	private static void randomChildren(Position<Integer> parent, int level, boolean rand) {
		Position<Integer> left, right;
		int lc, rc;
		Random r = new Random();
		try {
			
			if (rand) {
				lc = r.nextInt(23);
				rc = r.nextInt(55);
			} else {
				lc = level * level * 10;
				rc = level * level * 10 + 1;
			}
			
			left = t.addLeft(parent, lc);
			right = t.addRight(parent, rc);
			
			if (level > 1) {
				randomChildren(left, level-1, rand);
				randomChildren(right, level-1, rand);
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}

	private static void por_niveles_aux(Queue<Position<Integer>> cola) {
		Queue<Position<Integer>> cola_hijos = new LinkedQueue<Position<Integer>>();
		Position<Integer> pos;
		
		try {
			
			while (!cola.isEmpty()) {
				pos = cola.dequeue();
				System.out.print(pos.element() + " ");
				if (t.hasLeft(pos))
					cola_hijos.enqueue(t.left(pos));
				if (t.hasRight(pos))
					cola_hijos.enqueue(t.right(pos));
			}
			
			System.out.println();
			
		} catch (Exception e) {e.printStackTrace();}
		
		if (!cola_hijos.isEmpty())
			por_niveles_aux(cola_hijos);
	}
	
}
