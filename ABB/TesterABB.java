package ABB;

public class TesterABB {

	public static void main(String[] args) {
		test_1();
		test_2();
	}
	
	private static void test_1() {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		int [] numbers = {7, 2, 9, 0, 4, 5, 6, 8, 1, 3 };
		
		for (int i = 0; i < numbers.length; i++) {
			BSTNode<Integer> aux = t.buscar(numbers[i]);
			t.expandir(aux, numbers[i]);
		}
		inorder(t);
	}

	private static void test_2() {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		int [] numbers = {7, 2, 9, 0, 4, 5, 6, 8, 1, 3, -1 };
		
		for (int i = 0; i < numbers.length; i++) {
			BSTNode<Integer> aux = t.buscar(numbers[i]);
			t.expandir(aux, numbers[i]);
		}
		
		inorder(t);
		System.out.println("Eliminando raíz :: " + t.raiz().element());
		t.eliminar(t.raiz().element());
		inorder(t);
		System.out.println("Eliminar hoja :: " + 1);
		t.eliminar(1);
		inorder(t);
		System.out.println("Eliminar solo hijo izquierdo :: " + 0);
		t.eliminar(0);
		inorder(t);
		System.out.println("Eliminar solo hijo derecho :: " + 5);
		t.eliminar(5);
		inorder(t);
		System.out.println("Eliminar interno (!= de raíz) :: " + 4);
		t.eliminar(4);
		inorder(t);
		System.out.println("VACIAR :: ");
		for (int i = -1; i <= 9; i++)
			if ( (t.buscar(i)).element() != null ) {
				System.out.println("Eliminar " + i);
				t.eliminar(i);
				inorder(t);
			}
	}

	private static <E extends Comparable<E>>  void inorder(BinarySearchTree<E> t) {
		System.out.print("CURRENT STATE :: ");
		inorder_aux(t.raiz());
		System.out.println("-END");
	}

	private static <E extends Comparable<E>> void inorder_aux(BSTNode<E> node) {
		if (node.element() != null) {
			inorder_aux(node.leftChild());
			System.out.print(node.element() + " ");
			inorder_aux(node.rightChild());
		}
	}

}
