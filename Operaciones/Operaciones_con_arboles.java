package Operaciones;

import TDAArbol.*;
import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;
import TDAPila.EmptyStackException;
import TDAPila.LinkedStack;
import TDAPila.Stack;

public class Operaciones_con_arboles {
	
	public static <E> void preorden(Tree<E> t) {
		try {	
			if (!t.isEmpty())
				preorden_aux(t.root(), t);
			System.out.println();
		} catch (EmptyTreeException e) {e.printStackTrace();} 
	}

	private static <E> void preorden_aux(Position<E> root, Tree<E> t) {
		try {
			System.out.print(root.element() + " ");
			for (Position<E> child : t.children(root))
				postorden_aux(child, t);
		} catch (InvalidPositionException e) {e.printStackTrace();}
	}
	
	public static <E> void postorden(Tree<E> t) {
		try {	
			if (!t.isEmpty())
				postorden_aux(t.root(), t);
			System.out.println();
		} catch (EmptyTreeException e) {e.printStackTrace();} 
	}

	private static <E> void postorden_aux(Position<E> root, Tree<E> t) {
		try {
			for (Position<E> child : t.children(root))
				postorden_aux(child, t);
			System.out.print(root.element() + " ");
		} catch (InvalidPositionException e) {e.printStackTrace();}
	}
	
	public static <E> void por_niveles(Tree<E> t) {
		Queue<Position<E>> cola = new LinkedQueue<Position<E>>();
		try {	
			if (!t.isEmpty()) {
				cola.enqueue(t.root());
				por_niveles_aux(cola, t);
			}
		} catch (EmptyTreeException e) {e.printStackTrace();}
	}

	private static <E> void por_niveles_aux(Queue<Position<E>> cola_hermanos, Tree<E> t) {
		Queue<Position<E>> cola_hijos = new LinkedQueue<Position<E>>();
		Position<E> pos;
		
		try {
			
			while (!cola_hermanos.isEmpty()) {
				pos = cola_hermanos.dequeue();
				System.out.print(pos.element() + " ");
				for (Position<E> child : t.children(pos))
					cola_hijos.enqueue(child);
			}
			
			System.out.println();
			
		} catch (EmptyQueueException | InvalidPositionException e) {e.printStackTrace();}
		
		if (!cola_hijos.isEmpty())
			por_niveles_aux(cola_hijos, t);
	}
	
//	public static <E> void por_niveles_invertido(Tree<E> t) {
//		Queue<TDAArbol.Position<E>> cola_hojas = new LinkedQueue<TDAArbol.Position<E>>();
//		
//		try {
//			if (!t.isEmpty()) {
//				hojas_cola(t.root(), t, cola_hojas);
//				por_niveles_invertido_aux(cola_hojas, t);
//			}
//		} catch (EmptyTreeException | InvalidPositionException e) {e.printStackTrace();}
//	}
//
//	private static <E> void por_niveles_invertido_aux(Queue<Position<E>> cola_hijos, Tree<E> t) {
//		Queue<TDAArbol.Position<E>> cola_padres = new LinkedQueue<TDAArbol.Position<E>>();
//		TDAArbol.Position<E> pos;
//		
//		while (!cola_hijos.isEmpty()) {
//			pos = cola_hijos.dequeue();
//			System.out.print(pos.element() + " ");
//			if (cola_padres.)
//		}
//		
//	}
//
//	private static <E> void hojas_cola(Position<E> node, Tree<E> t, Queue<Position<E>> cola_hojas) throws InvalidPositionException {
//		if (t.isInternal(node))
//			cola_hojas.enqueue(node);
//		else
//			for (TDAArbol.Position<E> child : t.children(node))
//				hojas_cola(child, t, cola_hojas);
//	}
	
	public static <E> int profundidad(Position<E> pos, Tree<E> t) {
		try {
			if (!t.isEmpty())
				return profundidad_aux(pos, t);
		} catch (Exception e) {e.printStackTrace();}
		return -1;
	}

	private static <E> int profundidad_aux(Position<E> pos, Tree<E> t) throws InvalidPositionException, BoundaryViolationException, EmptyTreeException {
		if (t.root() == pos)
			return 0;
		else
			return 1 + profundidad_aux(t.parent(pos), t);
	}
	
	public static <E> int altura(Position<E> pos, Tree<E> t) {
		try {
			if (!t.isEmpty())
				return altura_aux(pos, t);
		} catch (Exception e) {e.printStackTrace();}
		return -1;
	}

	private static <E> int altura_aux(Position<E> pos, Tree<E> t) throws InvalidPositionException {
		int h = 0;
		
		if (t.isInternal(pos)) //en realidad no es necesario el if ya que si es externo entonces el for es 0 veces.
			for (Position<E> child : t.children(pos))
				h = Math.max(h, 1 + altura_aux(child, t));
			
		return h;
	}
	
	public static <E> PositionList<Position<E>> altura_justificada(Position<E> pos, Tree<E> t) {
		
		try {
			if (!t.isEmpty())
				return altura_camino(pos, t);
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}

	private static <E> PositionList<Position<E>> altura_camino(Position<E> pos, Tree<E> t) throws InvalidPositionException {
		PositionList<Position<E>> aux, camino;
		aux = new DoubleLinkedList<Position<E>>();
		camino = new DoubleLinkedList<Position<E>>();
		
		if (t.isExternal(pos))
			camino.addLast(pos);
		else
			for (Position<E> child : t.children(pos)) {
				aux = altura_camino(child, t);
				if (camino.size() < aux.size())
					camino = aux;
			}
				
		return camino;
	}
	
	public static <E> PositionList<Position<E>> camino(Position<E> p1, Position<E> p2, Tree<E> t) {
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

	private static <E> Stack<Position<E>> caminoRaiz(Position<E> p, Tree<E> t) {
		Stack<Position<E>> camino_raiz = new LinkedStack<Position<E>>(); 
		
		try {
			if (!t.isEmpty())
				while (p != null) {
					camino_raiz.push(p);
					p = t.isRoot(p)? null : t.parent(p);
				}
			
//			while (!t.isRoot(p))
//				camino_raiz.push(p);
//			camino_raiz.push(p);
			
		} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		
		return camino_raiz;
	}

	public static <E> Tree<E> clonar(Tree<E> t1) {
		Tree<E> t2 = new LinkedTree<E>();
		Position<E> root1;
		
		try {
			
			if (!t1.isEmpty()) {
				
				root1 = t1.root();
				t2.createRoot(root1.element());
				
				clonar_hijos(root1, t1, t2.root(), t2);
			}
			
		} catch (EmptyTreeException | InvalidOperationException e) {e.printStackTrace();}
		
		return t2;
	}

	private static <E> void clonar_hijos(Position<E> padreOrigen, Tree<E> t1, Position<E> padreDestino, Tree<E> t2) {
		Position<E> nuevoHijo;
		
		try {
			for (Position<E> hijoOrigen : t1.children(padreOrigen)) {
				nuevoHijo = t2.addLastChild(padreDestino, hijoOrigen.element());
				clonar_hijos(hijoOrigen, t1, nuevoHijo, t2);
			}
		} catch (InvalidPositionException e) {e.printStackTrace();}
	}
	
	public static <E> Tree<E> clonar_espejado(Tree<E> t1) {
		Tree<E> t2 = new LinkedTree<E>();
		Position<E> root1;
		
		try {
			
			if (!t1.isEmpty()) {
				
				root1 = t1.root();
				t2.createRoot(root1.element());
				
				clonar_hijos_espejados(root1, t1, t2.root(), t2);
			}
			
		} catch (EmptyTreeException | InvalidOperationException e) {e.printStackTrace();}
		
		return t2;
	}

	private static <E> void clonar_hijos_espejados(Position<E> padreOrigen, Tree<E> t1, Position<E> padreDestino, Tree<E> t2) {
		Position<E> nuevoHijo;
		
		try {
			for (Position<E> hijoOrigen : t1.children(padreOrigen)) {
				nuevoHijo = t2.addFirstChild(padreDestino, hijoOrigen.element());
				clonar_hijos_espejados(hijoOrigen, t1, nuevoHijo, t2);
			}
		} catch (InvalidPositionException e) {e.printStackTrace();}
	}

}
