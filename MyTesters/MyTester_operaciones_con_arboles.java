package MyTesters;

import Operaciones.Operaciones_con_arboles;
import TDAArbol.EmptyTreeException;
import TDAArbol.LinkedTree;
import TDAArbol.Position;
import TDAArbol.TNode;
import TDAArbol.Tree;
import TDALista.EmptyListException;
import TDALista.PositionList;

public class MyTester_operaciones_con_arboles {
	
	protected static Tree<Integer> t;
	
	public static void main(String[] args) {
		test_por_niveles();
		test_profundidad();
		test_altura();
		test_camino();
		test_clonar();
	}
	
	private static void test_clonar() {
		Tree<Integer> clone;
		
		t = genTree();
		System.out.println("ARBOL ORIGINAL: ");
		Operaciones_con_arboles.por_niveles(t);
		
		System.out.println();
		
		clone = Operaciones_con_arboles.clonar(t);
		System.out.println("ARBOL CLONADO: ");
		Operaciones_con_arboles.por_niveles(clone);
		
		System.out.println();
		
		clone = Operaciones_con_arboles.clonar_espejado(t);
		System.out.println("ARBOL CLONADO ESPEJADO: ");
		Operaciones_con_arboles.por_niveles(clone);
	}

	private static void test_camino() {
		TDAArbol.TNode<Integer> d0, d1, d2;
		t = genTree();
		Operaciones_con_arboles.por_niveles(t);
		try {
			d0 = (TDAArbol.TNode<Integer>) t.root();
			d1 = d0.getChildren().first().element();
			d2 = d1.getChildren().last().element();
			
			PositionList<Position<Integer>> camino;
			System.out.println("Camino entre d1 -> d2 :: ");
			camino = Operaciones_con_arboles.camino(d1, d2, t);
			showPath(camino);
			System.out.println("Camino entre d2 -> d1 :: ");
			camino = Operaciones_con_arboles.camino(d2, d1, t);
			showPath(camino);
			
		} catch (EmptyListException | EmptyTreeException e) {e.printStackTrace();}
	}

	private static void showPath(PositionList<Position<Integer>> camino) {
		for (Position<Integer> n : camino)
			System.out.print(n.element() + " - ");		
		System.out.println();
	}

	private static void test_profundidad() {
		TDAArbol.TNode<Integer> d0, d1, d2;
		t = genTree();
		Operaciones_con_arboles.por_niveles(t);
		try {
			d0 = (TDAArbol.TNode<Integer>) t.root();
			d1 = d0.getChildren().first().element();
			d2 = d1.getChildren().last().element();

			System.out.println("Altura de " + d0.element() + " es " + Operaciones_con_arboles.profundidad(d0, t));
			justificar(d0, t);
			System.out.println("Altura de " + d1.element() + " es " + Operaciones_con_arboles.profundidad(d1, t));
			System.out.println("Altura de " + d2.element() + " es " + Operaciones_con_arboles.profundidad(d2, t));
			
		} catch (EmptyListException | EmptyTreeException e) {e.printStackTrace();}
		
	}
	
	private static <E> void justificar(TNode<E> d, Tree<E> t) {
		PositionList<Position<E>> camino = Operaciones_con_arboles.altura_justificada(d, t);
		for (Position<E> pos : camino)
			System.out.println(pos.element() + " ");
	}

	private static void test_altura() {
		TDAArbol.TNode<Integer> d0, d1, d2;
		t = genTree();
		Operaciones_con_arboles.por_niveles(t);
		try {
			d0 = (TDAArbol.TNode<Integer>) t.root();
			d1 = d0.getChildren().first().element();
			d2 = d1.getChildren().last().element();

			System.out.println("Produndidad de " + d0.element() + " es " + Operaciones_con_arboles.altura(d0, t));
			System.out.println("Produndidad de " + d1.element() + " es " + Operaciones_con_arboles.altura(d1, t));
			System.out.println("Produndidad de " + d2.element() + " es " + Operaciones_con_arboles.altura(d2, t));
			
		} catch (EmptyListException | EmptyTreeException e) {e.printStackTrace();}
		
	}

	private static void test_por_niveles() {
		t = genTree();
		pre_post_orden(t);
		Operaciones_con_arboles.por_niveles(t);
	}

	private static Tree<Integer> genTree() {
		Tree<Integer> t = new LinkedTree<Integer>();
		try {
			t = new LinkedTree<Integer>();
			t.createRoot(0);
			for (int i = 1; i <= 3; i++) {
				Position<Integer> child = t.addLastChild(t.root(), i);
				for (int j = 1; j <= 3; j++) {
					t.addLastChild(child, j*10+j);
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return t;
	}
	
	private static <E> void pre_post_orden(Tree<E> t) {
		Operaciones_con_arboles.preorden(t);
		Operaciones_con_arboles.postorden(t);
	}

}
