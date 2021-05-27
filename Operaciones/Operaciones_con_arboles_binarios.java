package Operaciones;

import java.util.Iterator;

import TDAArbolBinario.BinaryTree;
import TDAArbolBinario.BoundaryViolationException;
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
import TDAPila.EmptyStackException;
import TDAPila.LinkedStack;
import TDAPila.Stack;

public class Operaciones_con_arboles_binarios {

	public static <E> void preorden(BinaryTree<E> t) {
		try {
			if (!t.isEmpty())
				pre(t.root(), t);
			System.out.println();
		} catch (EmptyTreeException e) {e.printStackTrace();}
	}
	
	private static <E> void pre(Position<E> parent, BinaryTree<E> t) {
		try {
			System.out.print(parent.element() + " ");
			
			if (t.hasLeft(parent))
				pre(t.left(parent), t);
			if (t.hasRight(parent))
				pre(t.right(parent), t);
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
	}

	public static <E> void postorden(BinaryTree<E> t) {
		try {
			if (!t.isEmpty())
				post(t.root(), t);
			System.out.println();
		} catch (EmptyTreeException e) {e.printStackTrace();}
	}
	
	private static <E> void post(Position<E> parent, BinaryTree<E> t) {
		try {
			if (t.hasLeft(parent))
				pre(t.left(parent), t);
			if (t.hasRight(parent))
				pre(t.right(parent), t);
			
			System.out.print(parent.element() + " ");
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
	}

	public static <E> void por_niveles(BinaryTree<E> t) {
		Queue<Position<E>> cola = new LinkedQueue<Position<E>>();
		try {	
			if (!t.isEmpty()) {
				cola.enqueue(t.root());
				por_niveles_aux(cola, t);
			}
		} catch (Exception e) {e.printStackTrace();}		
	}

	private static <E> void por_niveles_aux(Queue<Position<E>> cola, BinaryTree<E> t) {
		Queue<Position<E>> cola_hijos = new LinkedQueue<Position<E>>();
		Position<E> pos;
		
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
	
	public static <E> int profundidad(BinaryTree<E> t, Position<E> p) {
		int prof = 0;
		
		try {
			if (!t.isRoot(p))
				prof = 1 + profundidad(t, t.parent(p));
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return prof;
	}
	
	public static <E> int altura(BinaryTree<E> t, Position<E> p) {
		int altura = 0;
		
		try {
			if (t.isInternal(p)) {
				
				if (t.hasLeft(p) && t.hasRight(p))
					altura = 1 + Math.max(altura(t, t.left(p)), altura(t, t.right(p)));
				else if (t.hasLeft(p))
					altura = 1 + altura(t, t.left(p));
				else
					altura = 1 + altura(t, t.right(p));
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return altura;
	}
	
	public static <E> PositionList<Position<E>> camino(BinaryTree<E> t, Position<E> p1, Position<E> p2){
			PositionList<Position<E>> camino = new DoubleLinkedList<Position<E>>();
			Stack<Position<E>> camino_p1, camino_p2;
			Position<E> pos_comun;
			
			//Si es el mismo nodo, no se ejecuta nada, retorna lista vacía.
			if (p1.element() != p2.element()) {
				
					camino_p1 = caminoRaiz(p1, t);
					camino_p2 = caminoRaiz(p2, t);
					pos_comun = posComun(camino_p1, camino_p2);
					
					camino.addLast(pos_comun);
					
				try {	
					
					while (!camino_p1.isEmpty())
						camino.addFirst(camino_p1.pop());
					
					while (!camino_p2.isEmpty())
						camino.addLast(camino_p2.pop());
					
				} catch (EmptyStackException e) {e.printStackTrace();}
				
			}
			
			return camino;
		}

		private static <E> Position<E> posComun(Stack<Position<E>> camino_p1, Stack<Position<E>> camino_p2) {
			Position<E> pos_comun = null;
			
			try {
				do {
					pos_comun = camino_p1.pop();
					camino_p2.pop();
				} while (!camino_p1.isEmpty() && !camino_p2.isEmpty() && camino_p1.top() == camino_p2.top());
			} catch (EmptyStackException e) {e.printStackTrace();}
			
			return pos_comun;
		}

		private static <E> Stack<Position<E>> caminoRaiz(Position<E> p1, BinaryTree<E> t) {
			Stack<Position<E>> camino_raiz = new LinkedStack<Position<E>>(); 
			
			try {
				if (!t.isEmpty())
					while (p1 != null) {
						camino_raiz.push(p1);
						p1 = t.isRoot(p1)? null : t.parent(p1);
					}
			} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
			
			return camino_raiz;
		}
	
	public static <E> BinaryTree<E> clonar(BinaryTree<E> t1) {
		BinaryTree<E> t2 = new LinkedBinaryTree<E>();
		Position<E> root1, root2;
		
		try {
			if (!t1.isEmpty()) {
				root1 = t1.root();
				root2 = t2.createRoot(root1.element());
				clonar_aux(root1, t1, root2, t2);
			}
		} catch (EmptyTreeException | InvalidOperationException e) {e.printStackTrace();}
		
		return t2;
	}
	
	private static <E> void clonar_aux(Position<E> parentOrigen, BinaryTree<E> t1, Position<E> parentDestino, BinaryTree<E> t2) {
		Position<E> childDestino, leftChildOrigen = null, rightChildOrigen = null;
		
		try {
			if (t1.hasLeft(parentOrigen))
				leftChildOrigen = t1.left(parentOrigen);
			if (t2.hasRight(parentOrigen))
				rightChildOrigen = t1.right(parentOrigen);
			
			if (leftChildOrigen != null) {
				childDestino = t2.addLeft(parentDestino, leftChildOrigen.element());
				clonar_aux(leftChildOrigen, t1, childDestino, t2);
			}
			
			if (rightChildOrigen != null) {
				childDestino = t2.addRight(parentDestino, leftChildOrigen.element());
				clonar_aux(rightChildOrigen, t1, childDestino, t2);
			}
			
		} catch (InvalidPositionException | BoundaryViolationException | InvalidOperationException e) {e.printStackTrace();}
		
	}

	public static <E> BinaryTree<E> clonar_espejado(BinaryTree<E> t1) {
		BinaryTree<E> t2 = new LinkedBinaryTree<E>();
		Position<E> root1, root2;
		
		try {
			if (!t1.isEmpty()) {
				root1 = t1.root();
				root2 = t2.createRoot(root1.element());
				clonar_aux_espejado(root1, t1, root2, t2);
			}
		} catch (EmptyTreeException | InvalidOperationException e) {e.printStackTrace();}
		
		return t2;
	}
	
	private static <E> void clonar_aux_espejado(Position<E> parentOrigen, BinaryTree<E> t1, Position<E> parentDestino, BinaryTree<E> t2) {
		Position<E> childDestino, leftChildOrigen = null, rightChildOrigen = null;
		
		try {
			if (t1.hasLeft(parentOrigen))
				leftChildOrigen = t1.left(parentOrigen);
			if (t2.hasRight(parentOrigen))
				rightChildOrigen = t1.right(parentOrigen);
			
			if (leftChildOrigen != null) {
				childDestino = t2.addRight(parentDestino, leftChildOrigen.element());
				clonar_aux(leftChildOrigen, t1, childDestino, t2);
			}
			
			if (rightChildOrigen != null) {
				childDestino = t2.addLeft(parentDestino, leftChildOrigen.element());
				clonar_aux(rightChildOrigen, t1, childDestino, t2);
			}
			
		} catch (InvalidPositionException | BoundaryViolationException | InvalidOperationException e) {e.printStackTrace();}
		
	}
	
	public static <E> boolean iguales(BinaryTree<E> t1, BinaryTree<E> t2) {
		boolean eq = t1.size() == t2.size();
		
		try {
			
			if (eq && !t1.isEmpty() && !t2.isEmpty()) {
				eq = iguales_aux(t1.root(), t1, t2.root(), t2);
			}
			
		} catch (EmptyTreeException e) {e.printStackTrace();}
		
		return eq;
	}
	
	
	
	private static <E> boolean iguales_aux(Position<E> parent1, BinaryTree<E> t1, Position<E> parent2, BinaryTree<E> t2) {
		boolean iguales = parent1.element().equals(parent2.element());
		
		try {
			if (iguales) {
				if (t1.hasLeft(parent1) && t2.hasLeft(parent2))
					iguales = iguales_aux(t1.left(parent1), t1, t2.left(parent2), t2);
				else if (t1.hasLeft(parent1) || t2.hasLeft(parent2))
					iguales = false;
			}
			
			if (iguales) {
				if (t1.hasRight(parent1) && t2.hasRight(parent2))
					iguales = iguales_aux(t1.right(parent1), t1, t2.right(parent2), t2);
				else if (t1.hasRight(parent1) || t2.hasRight(parent2))
					iguales = false;
			}
			
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return iguales;
	}

	public static <E> boolean old_subarbol(BinaryTree<E> t1, BinaryTree<E> t2) {
		Position<E> raiz_subarbol = null;
		
		//Si t2 vacio -> subarbol trivial de todo arbol.
		if (t2.isEmpty())
			return true;
		//t2 no es vacío, si t1 es vacío -> imposible ser subarbol
		if (t1.isEmpty())
			return false;
		//ninguno vacío::
		try {
			raiz_subarbol = buscar_subraiz(t1.root(), t1, t2.root());
			if (raiz_subarbol != null)
				return iguales_aux(raiz_subarbol, t1, t2.root(), t2);
			
		} catch (EmptyTreeException e) {e.printStackTrace();}
		
		return false;
	}
	
	public static <E> boolean subarbol(BinaryTree<E> t1, BinaryTree<E> t2) {
		boolean sub = t2.size() <= t1.size();
		Position<E> raiz_t2_en_t1;
		
		try {
			if (!t2.isEmpty()) {
				raiz_t2_en_t1 = hallarRaiz(t1, t2.root());
				sub = raiz_t2_en_t1 != null? iguales_aux(raiz_t2_en_t1, t1, t2.root(), t2) : false;
			}
			
		} catch (EmptyTreeException e) {e.printStackTrace();}
		
		return sub;
	}
	
	private static <E> Position<E> hallarRaiz(BinaryTree<E> t1, Position<E> root) {
		//ALERTA! ESTA IMPLEMENTACIÓN ES O(n^2)!!!!!!!!!!
		
		Iterator<Position<E>> positions = t1.positions().iterator();
		Position<E> objetive = null;
		
		while (positions.hasNext() && objetive == null) {
			objetive = positions.next();
			if (!objetive.element().equals(root.element()))	objetive = null;
		}
		
		
		return objetive;
	}

	private static <E> Position<E> buscar_subraiz(Position<E> subroot, BinaryTree<E> t1, Position<E> refRoot) {
		Position<E> root = subroot.equals(refRoot)? subroot : null;
		
		try {
			if (root == null) {
				if (t1.hasLeft(subroot))
					root = buscar_subraiz(t1.left(subroot), t1, refRoot);
				if (root == null && t1.hasRight(subroot))
					root = buscar_subraiz(t1.right(subroot), t1, refRoot);
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		return root;
	}

	public static <E> BinaryTree<E> clonar_arbol_perfecto(int h, PositionList<E> l) {
		BinaryTree<E> t = new LinkedBinaryTree<E>();
		Position<E> root = null;
		
		try {
			if (!l.isEmpty()) {
				root = t.createRoot(l.remove(l.first()));
				clonarPerfecto_aux(h-1, l, t, root);
			}
		} catch (EmptyListException | InvalidOperationException | TDALista.InvalidPositionException e) {e.printStackTrace();}
		
		return t;
	}

	private static <E> void clonarPerfecto_aux(int h, PositionList<E> l, BinaryTree<E> t, Position<E> root) {
		Position<E> child;
		
		try {
			if (h >= 0 && !l.isEmpty()) {
				child = t.addLeft(root, l.remove(l.first()));
				clonarPerfecto_aux(h-1, l, t, child);
				child = t.addRight(root, l.remove(l.first()));
				clonarPerfecto_aux(h-1, l, t, child);
			}
		} catch (EmptyListException | InvalidOperationException | InvalidPositionException | TDALista.InvalidPositionException e) {e.printStackTrace();}
	}

}
