package MyTesters;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbol.InvalidPositionException;
import TDAArbol.LinkedTree;
import TDAArbol.Position;
import TDALista.PositionList;

public class MyTree {
	
	protected static LinkedTree<Integer> tree = gen_tree();

	public static void main(String[] args) {
		test_listar();
		test_extremos_izquierdos_no_hojas();
		test_eliminar_hojas();
		test_eliminar_rotulo();
		test_eliminar_rotulo_nivel();
		test_espejar();
	}

	private static void test_espejar() {
		try {
			tree = new LinkedTree<Integer>();
			tree.createRoot(0);
			for (int i = 1; i <= 3; i++) {
				Position<Integer> child = tree.addLastChild(tree.root(), i);
				for (int j = 1; j <= 3; j++) {
					tree.addLastChild(child, j*10+j);
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		
		test_listar();
		System.out.println("ESPEJANDO ...");
		tree.espejar();
		test_listar();
	}

	private static void test_eliminar_rotulo_nivel() {
		try {
			Position<Integer> root = tree.root();
			
			tree.addLastChild(root, 37);
			tree.addLastChild(root, 38);
			tree.addLastChild(root, 37);
			test_listar();
			
			System.out.println("Eliminando rótulo '37' del nivel 1 ...");
			tree.eliminar_rotulo_nivel(37, 1);
			System.out.println("Resultado: ");
			test_listar();
			System.out.println("Eliminando rótulo '15' del nivel 0 ...");
			tree.eliminar_rotulo_nivel(15, 0);
			System.out.println("Eliminando rótulo '100' del nivel 3 ...");
			tree.eliminar_rotulo_nivel(100, 3);
			System.out.println("Resultado: ");
			test_listar();
			
		} catch (EmptyTreeException | InvalidPositionException e) {e.printStackTrace();}
	}

	private static void test_eliminar_rotulo() {
		tree.eliminar_rotulo(100);
		System.out.println("Resultado: ");
		test_listar();
	}

	private static void test_eliminar_hojas() {
		System.out.println("Eliminando hojas ...");
		tree.eliminar_hojas();
		System.out.println("RESULTADO: ");
		test_listar();
	}

	private static void test_extremos_izquierdos_no_hojas() {
		PositionList<Position<Integer>> extremos = tree.extremos_izquierdos_no_hojas();
		System.out.println("Extremos izquierdos no hojas: ");
		System.out.print(">> ");
		for (Position<Integer> pos : extremos)
			System.out.print(pos.element() + " ");
		System.out.println();
	}

	private static void test_listar() {
		System.out.println("Árbol en preorden: ");
		System.out.print(">> ");
		tree.listar(true);
		
		System.out.println("Árbol en posorden: ");
		System.out.print(">> ");
		tree.listar(false);
	}
	
	private static LinkedTree<Integer> gen_tree() {
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
