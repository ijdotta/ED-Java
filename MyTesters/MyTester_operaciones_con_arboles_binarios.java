package MyTesters;

import java.util.Random;

import Operaciones.Operaciones_con_arboles_binarios;
import TDAArbolBinario.BinaryTree;
import TDAArbolBinario.EmptyTreeException;
import TDAArbolBinario.InvalidOperationException;
import TDAArbolBinario.InvalidPositionException;
import TDAArbolBinario.LinkedBinaryTree;
import TDAArbolBinario.Position;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.PositionList;

@SuppressWarnings("unused")
public class MyTester_operaciones_con_arboles_binarios {
	
	protected static BinaryTree<Integer> t, perfecto;
	protected static Position<Integer> p1, p2; 

	public static void main(String[] args) {
//		test_prof_altura_camino();
//		test_perfecto();
		test_subarbol();
//		test_iguales();
//		test_iguales_manual();
	}
	
	private static void test_iguales_manual() {
		BinaryTree<Integer> t1, t2;
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		Position<Integer> last;
		
		//Prueba donde t1 == t2 == vacío
		manual_show_aux(t1,t2);
		
		//prueba t1==t2, solo root=0
		try {
			t1.createRoot(0);
			t2.createRoot(0);
		} catch (InvalidOperationException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1!=t2, solo root
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			t1.createRoot(0);
			t2.createRoot(1);
		} catch (InvalidOperationException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
								
		//prueba t1==t2, n-l
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addLeft(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 1);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1!=t2, n-l
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addLeft(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 2);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1==t2, n-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addRight(last, 1);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1!=t2, n-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addRight(last, 2);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1==t2, n-l-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addLeft(last, 5);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 5);
			t2.addRight(last, 1);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1!=t2, n-l-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addLeft(last, 5);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 6);
			t2.addRight(last, 2);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1==t2, n-l-r}
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 5);
			t2.addRight(last, 1);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1!=t2, n-l-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 6);
			t2.addRight(last, 2);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1==t2, n-l-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addLeft(last, 5);
			last = t2.createRoot(0);
			t2.addLeft(last, 5);
			t2.addRight(last, 1);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
		//prueba t1!=t2, n-l-r
		t1 = new LinkedBinaryTree<Integer>();
		t2 = new LinkedBinaryTree<Integer>();
		try {
			last = t1.createRoot(0);
			t1.addLeft(last, 5);
			t1.addRight(last, 1);
			last = t2.createRoot(0);
			t2.addLeft(last, 6);
		} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}	
		manual_show_aux(t1, t2);
		
	}

	private static void manual_show_aux(BinaryTree<Integer> t1, BinaryTree<Integer> t2) {
		System.out.print("t1   :: "); Operaciones_con_arboles_binarios.preorden(t1);
		System.out.print("t2   :: "); Operaciones_con_arboles_binarios.preorden(t2);
		System.out.println("1=2  :: " + Operaciones_con_arboles_binarios.iguales(t1, t2));
		System.out.println("1<=2 :: " + Operaciones_con_arboles_binarios.subarbol(t1, t2));
		System.out.println("2<=1 :: " + Operaciones_con_arboles_binarios.subarbol(t2, t1));
		System.out.println();
	}

	private static void test_iguales() {
		BinaryTree<Integer> t1, t2, t3;
		setUp(true, 5);
		t1 = t;
		setUp(false, 5);
		t2 = t;
		setUp(false, 5);
		t3 = t;

		Operaciones_con_arboles_binarios.preorden(t1);
		Operaciones_con_arboles_binarios.preorden(t2);
		Operaciones_con_arboles_binarios.preorden(t3);

		System.out.println("iguales 1,2 " + Operaciones_con_arboles_binarios.iguales(t1, t2));
		System.out.println("iguales 2,3 " + Operaciones_con_arboles_binarios.iguales(t2, t3));
		System.out.println("iguales 1,3 " + Operaciones_con_arboles_binarios.iguales(t1, t3));
		
	}

	private static void test_subarbol() {
		BinaryTree<Integer> t1, t2, t3, t4;
		PositionList<Integer> l1 = new DoubleLinkedList<Integer>();
		PositionList<Integer> l2 = new DoubleLinkedList<Integer>();
		PositionList<Integer> l3 = new DoubleLinkedList<Integer>();
		
		for (int i = 1; i <= 15; i++)
			l1.addLast(i);
		for (int i = 1; i <= 31; i++)
			l2.addLast(i);
		for (int i = 18; i <= 24; i++)
			l3.addLast(i);
		
		showList("l1", l1);
		showList("l2", l2);
		showList("l3", l3);
		
		t1 = clonar_arbol_perfecto(3, l1);
		t2 = clonar_arbol_perfecto(4, l2);
		t3 = clonar_arbol_perfecto(2, l3);
		t4 = new LinkedBinaryTree<Integer>();
		Operaciones_con_arboles_binarios.preorden(t1);
		Operaciones_con_arboles_binarios.preorden(t2);
		Operaciones_con_arboles_binarios.preorden(t3);
		Operaciones_con_arboles_binarios.por_niveles(t1);
		Operaciones_con_arboles_binarios.por_niveles(t2);
		Operaciones_con_arboles_binarios.por_niveles(t3);

		System.out.println("sub 1 de 2 :: " + Operaciones_con_arboles_binarios.subarbol(t2, t1));
		System.out.println("sub 2 de 1 :: " + Operaciones_con_arboles_binarios.subarbol(t1, t2));
		System.out.println("sub 3 de 1 :: " + Operaciones_con_arboles_binarios.subarbol(t1, t3));
		System.out.println("sub 3 de 2 :: " + Operaciones_con_arboles_binarios.subarbol(t2, t3));
		System.out.println("sub 4 de 1 :: " + Operaciones_con_arboles_binarios.subarbol(t1, t4));
		System.out.println("sub 4 de 2 :: " + Operaciones_con_arboles_binarios.subarbol(t2, t4));
		System.out.println("sub 4 de 3 :: " + Operaciones_con_arboles_binarios.subarbol(t3, t4));
		System.out.println("sub 4 de 4 :: " + Operaciones_con_arboles_binarios.subarbol(t4, t4));
		
		
	}

	private static void test_prof_altura_camino() {
		LinkedBinaryTree<Integer> tree;
		PositionList<Integer> l = new DoubleLinkedList<Integer>();
		Position<Integer> p1 = null, p2 = null;
		
		for (int i = 1; i <= 63; i++)
			l.addLast(i);
		
		showList("int", l);
		tree = (LinkedBinaryTree<Integer>) clonar_arbol_perfecto(5, l);
		Operaciones_con_arboles_binarios.preorden(tree);
		Operaciones_con_arboles_binarios.por_niveles(tree);
		
		try {
			p1 = tree.left(tree.root());
			p2 = tree.right(tree.root());
			for (int i = 0; i < 3; i++) {
				p1 = tree.right(p1);
				p2 = tree.left(p2);
			}
		} catch (Exception e) {e.printStackTrace();}

		System.out.println("Profundidad p1 = "+p1.element()+" :: "+Operaciones_con_arboles_binarios.profundidad(tree, p1));
		System.out.println("Profundidad p2 = "+p2.element()+" :: "+Operaciones_con_arboles_binarios.profundidad(tree, p2));
		System.out.println("Altura p1 = "+p1.element()+" :: "+Operaciones_con_arboles_binarios.altura(tree, p1));
		System.out.println("Altura p2 = "+p2.element()+" :: "+Operaciones_con_arboles_binarios.altura(tree, p2));
		System.out.println("Camino p1-p2 :");
		showCamino("p1->p2", Operaciones_con_arboles_binarios.camino(tree, p1, p2));
		System.out.println("Camino p2-p1 :");
		showCamino("p1->p2", Operaciones_con_arboles_binarios.camino(tree, p2, p1));
		
	}

	private static void showCamino(String name, PositionList<Position<Integer>> camino) {
		System.out.print(name + " :: [");
		for (Position<Integer> e : camino)
			System.out.print(e.element() + ", ");
		System.out.println("] ");
	}

	private static void test_perfecto() {
		PositionList<Integer> perf1 = new DoubleLinkedList<Integer>();
		PositionList<Character> perf2 = new DoubleLinkedList<Character>();
		LinkedBinaryTree<Integer> perfecto1;
		LinkedBinaryTree<Character> perfecto2;
		
		for (int i = 1; i <= 31; i++)
			perf1.addLast(i);
		
		for (int i = 1; i <= 15; i++)
			perf2.addLast((char) (i+64));

		showList("perf1", perf1);
		showList("perf2", perf2);
		perfecto1 = (LinkedBinaryTree<Integer>) Operaciones_con_arboles_binarios.clonar_arbol_perfecto(4, perf1);
		perfecto2 = (LinkedBinaryTree<Character>) Operaciones_con_arboles_binarios.clonar_arbol_perfecto(3, perf2);
		
		System.out.println("Árbol perfecto 1: ");
		Operaciones_con_arboles_binarios.preorden(perfecto1);
		Operaciones_con_arboles_binarios.postorden(perfecto1);
		Operaciones_con_arboles_binarios.por_niveles(perfecto1);
		
		System.out.println();
		
		System.out.println("Árbol perfecto 2: ");
		Operaciones_con_arboles_binarios.preorden(perfecto2);
		Operaciones_con_arboles_binarios.postorden(perfecto2);
		Operaciones_con_arboles_binarios.por_niveles(perfecto2);
		
	}

	private static <E> void showList(String name, PositionList<E> l) {
		System.out.print(name + " :: [");
		for (E e : l)
			System.out.print(e + ", ");
		System.out.println("] ");
	}

	private static void setUp(boolean rand, int niveles) {
		Position<Integer> root;
		t = new LinkedBinaryTree<Integer>();
		try {
			root = t.createRoot(0);
			randomChildren(root, niveles, rand);
		} catch (Exception e) {e.printStackTrace();}
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
//				randomChildren(right, level-1, rand);
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
	
	public static BinaryTree<Integer> clonar_arbol_perfecto(int h, PositionList<Integer> l) {
		BinaryTree<Integer> t = new LinkedBinaryTree<Integer>();
		Position<Integer> root = null;
		
		try {
			if (!l.isEmpty())
				root = t.createRoot(l.remove(l.first()));
				clonarPerfecto_aux(h-1, l, t, root);
		} catch (EmptyListException | InvalidOperationException | TDALista.InvalidPositionException e) {e.printStackTrace();}
		
		return t;
	}

	private static void clonarPerfecto_aux(int h, PositionList<Integer> l, BinaryTree<Integer> t, Position<Integer> root) {
		Position<Integer> child;
		
		try {
			if (h >= 0 && !l.isEmpty()) {
				child = t.addLeft(root, l.remove(l.first()));
				if (child.element() == 12 || child.element() == 46)
					p1 = child;
				clonarPerfecto_aux(h-1, l, t, child);
				child = t.addRight(root, l.remove(l.first()));
				if (child.element() == 46 || child.element() == 12)
					p2 = child;
				clonarPerfecto_aux(h-1, l, t, child);
			}
		} catch (EmptyListException | InvalidOperationException | InvalidPositionException | TDALista.InvalidPositionException e) {e.printStackTrace();}
	}

}
