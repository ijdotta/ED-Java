package Operaciones;

import java.util.Iterator;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.LinkedList;
import TDALista.Position;
import TDALista.PositionList;
import TDALista_desarrollo.Lista_circularmente_enlazada;
import TDALista_desarrollo.Node;

/**
 * Usa el TDA lista provisto por la cátedra.
 * @author Ignacio Joaquín Dotta
 *
 */
public class Operaciones_con_listas {
	
	/**
	 * Recibe dos listas por parámetro y verifica que el contenido de la segunda, l2, se corresponda con
	 * el contenido de la primera, seguido del mismo contenido en orden inverso.
	 * @param l1 Lista de referencia.
	 * @param l2 Lista sobre la cual realizar el control.
	 * @return true si cumple con la condición contenido - invertido.
	 */
	public static boolean contenido_e_invertido(PositionList<Character> l1, PositionList<Character> l2) {
		boolean condition = true;
		Position<Character> pointer1, pointer2;
		
		try {
			pointer1 = l1.first();
			pointer2 = l2.first();
			
		
			while (condition && pointer1 != null && pointer2 != null) {
				condition = pointer1.element() == pointer2.element();
				
				if (pointer1 != l1.last()) pointer1 = l1.next(pointer1);
				else pointer1 = null;
				if (pointer2 != l2.last()) pointer2 = l2.next(pointer2);
				else pointer2 = null;
			}
			
			if (condition)
				condition = pointer1 != null;
			
			pointer2 = l2.last();
			
			while (condition && pointer1 != null && pointer2 != null) {
				condition = pointer1.element() == pointer2.element();
				
				if (pointer1 != l1.last()) pointer1 = l1.next(pointer1);
				else pointer1 = null;
				if (pointer2 != l2.first()) pointer2 = l2.prev(pointer2);
				else pointer2 = null;
			}
			
			if (condition)
				condition = pointer1 == null && pointer2 == null;
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return condition;
	}
	
	/**
	 * Siempre asume que las listas no están vacías.
	 * @param <E>
	 * @param l1 Lista a modificar
	 * @param l2 Lista de referencia
	 */
	public static <E> void eliminar(PositionList<E> l1, PositionList<E> l2) {
		
		try {
			
			for(E e2 : l2)
				for(Position<E> p1 : l1.positions())
					if (p1.element().equals(e2))
						l1.remove(p1);
			
			Position<E> last = l1.last();
			for (E e2 : l2)
				l1.addAfter(last, e2);
			
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Recibe una lista por parámetro y la invierte
	 * @param <E>
	 * @param list
	 * @return
	 */
	public static <E> void invertir(PositionList<E> list) {
		Queue<E> cola = new LinkedQueue<E>();
		
		try {
			for (Position<E> e : list.positions()) {
				cola.enqueue(e.element());
				list.remove(e);
			}
			
			while (!cola.isEmpty())
				list.addFirst(cola.dequeue());
		} catch (EmptyQueueException | InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Invierte el contenido de una lista usando recursividad.
	 * @param <E> Tipo genérico de la lista.
	 * @param list Lista que se invierte.
	 */
	public static <E> void invertir_recursivo(PositionList<E> list) {
		try	{
			if (!list.isEmpty())
				invertirRecursivo(list, list.first(), list.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		/*
		 * Invertir recursivo(first)

			CB: si la lista está vacía :: no hacer nada
			
			CB: si la lista tiene 1 elemento :: no hacer nada
			
				addLast(first)
			
			CR: si la lista tiene 2 o más elementos
				
				first = l.first()
				invertir (next(first))
				l.addLast(first)
	

		 */
	}
	
	/**
	 * Método auxiliar para invertir recursivamente.
	 * @param <E> Tipo genérico de la lista.
	 * @param list Lista que se está invirtiendo.
	 * @param first Primer elemento simbólico de la instancia de la lista que se está trabajando.
	 * @param last	Último elemento real de la lista.
	 * @see invertir_recursivo
	 */
	private static <E> void invertirRecursivo(PositionList<E> list, Position<E> first, Position<E> last) {
		try {
			if (first != last) {
				invertirRecursivo(list, list.next(first), last);
				E temp = first.element();
				list.remove(first);
				list.addLast(temp);
			}
		} catch (BoundaryViolationException | InvalidPositionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recibe dos listas ordenadas de menor a mayor y las intercala sin repetidos.
	 * @param l1 Lista origen 1.
	 * @param l2 Lista origen 2.
	 * @return Lista intercalada.
	 */
	public static PositionList<Integer> intercalar_ordenados_sin_repetidos
							(PositionList<Integer> l1, PositionList<Integer> l2) {
		
		PositionList<Integer> l3 = new DoubleLinkedList<Integer>();
		
		Iterator<Integer> it1, it2;
		it1 = l1.iterator();
		it2 = l2.iterator();
		
		int e1, e2;
		e1 = it1.next();
		e2 = it2.next();
		
		while (it1.hasNext() && it2.hasNext()) {
			if (e1 == e2) {
				l3.addLast(e1);
				e1 = it1.next();
				e2 = it2.next();
			}
			else if (e1 < e2) {
				l3.addLast(e1);
				e1 = it1.next();
			}
			else {
				l3.addLast(e2);
				e2 = it2.next();
			}
		}
		
		if (!it1.hasNext() && !it2.hasNext()) {
			if (e1 == e2)
				l3.addLast(e1);
			else if (e1 < e2) {
				l3.addLast(e1);
				l3.addLast(e2);
			}
			else {
				l3.addLast(e2);
				l3.addLast(e1);
			}
		}
		else if (!it1.hasNext()) {
			if (e1 <= e2) {
				l3.addLast(e1);
				if (e1 == e2 && it2.hasNext())
					e2 = it2.next();
			}
			l3.addLast(e2);
			while (it2.hasNext())
				l3.addLast(it2.next());
		}
		else {
			if (e2 <= e1) {
				l3.addLast(e2);
				if (e1 == e2 && it1.hasNext())
					e1 = it1.next();
			}
			l3.addLast(e1);
			while (it1.hasNext())
				l3.addLast(it1.next());
		}
					
		return l3;
		
	}
	
	/**
	 * Recibe tres listas: dos para analizar sus elementos y una para producir un resumen de lo que contienen.
	 * La lista resumen r tiene elementos de tipo Terna donde la primera componente representa un elemento 
	 * de las listas, la segunda la cantidad de apariciones de este en la primera lista y la tercera componente,
	 * la cantidad de apariciones en la segunda lista. 
	 * @param <E> Tipo genérico
	 * @param l1 Lista 1 para analizar
	 * @param l2 Lista 2 para analizar
	 * @param r Lista resumen
	 */
	public static <E> void lista_resumen(PositionList<E> l1, PositionList<E> l2, PositionList<Terna<E>> r) {
		for (E e : l1)
			if (!estaEnR(e, r))
				r.addFirst(new Terna<E>(e, contar(e, l1), contar(e, l2)));
		
		for (E e : l2) {
			if (!estaEnR(e, r))
				r.addFirst(new Terna<E>(e, contar(e, l1), contar(e, l2)));
		}
	}
	
	/**
	 * @param <E>
	 * @param elem Elemento para contabilizar.
	 * @param l Lista de búsqueda.
	 * @return Cantidad de apariciones de elem en l.
	 * @see lista_resumen
	 */
	private static <E> int contar(E elem, PositionList<E> l) {
		int cant = 0;
		for (E e : l)
			if (e.equals(elem))
				cant++;
		return cant;
	}
	
	/**
	 * 
	 * @param <E>
	 * @param e
	 * @param r
	 * @return
	 * @see lista_resumen
	 */
	private static <E> boolean estaEnR(E e, PositionList<Terna<E>> r) {
		Iterator<Terna<E>> it = r.iterator();
		boolean esta = false;
		while (it.hasNext() && !esta)
			esta = it.next().getElement().equals(e);
		return esta;
	}

	/**
	 * Recibe dos listas de enteros y devuelve un lista con los elementos combinados, sin repeticiones.
	 * @param l1 Lista 1 para tomar elementos.
	 * @param l2 Lista 2 para tomar elementos.
	 * @return Lista con elementos combinados, sin repetir.
	 */
	public static PositionList<Integer> intercalar_sin_repetidos
					(PositionList<Integer> l1, PositionList<Integer> l2) {
		PositionList<Integer> l3 = new LinkedList<Integer>();
		
		if (!l1.isEmpty()) insertarDistintos(l1,l3);
		if (!l2.isEmpty()) insertarDistintos(l2,l3);
		
		return l3;
	}
	
	/**
	 * Recibe una lista de origen y una lista de destino. Toma los elementos del origen y los inserta en 
	 * la de destino, sin repetirlos. Siempre inserta delante, como una pila.
	 * @param l1 Lista origen de los elementos.
	 * @param l2 Lista destino de los elementos.
	 * @see intercalar_sin_repetidos
	 */
	private static void insertarDistintos(PositionList<Integer> l1, PositionList<Integer> l2) {
		Position<Integer> e1, last1, e2, last2;
		
		try {
			e1 = l1.first();
			if (l2.isEmpty()) {
				l2.addFirst(e1.element());
				e1 = l1.next(e1);
			}
			last1 = l1.last();
			
			while (e1 != null) {
				
				e2 = l2.first();
				last2 = l2.last();
				
				while (e2 != null && e1.element() != e2.element())
					if (e2 != last2)
						e2 = l2.next(e2);
					else
						e2 = null;
				
				if (e2 == null || e1.element() != e2.element())
					l2.addFirst(e1.element());
				
				if (e1 != last1)
					e1 = l1.next(e1);
				else
					e1 = null;
				
			}
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Leí mal la consigna. No es necesario insertar ordenadamente, lo cual simplifica el algoritmo y reduce
	 * su tiempo de ejecución. Solo se debería analizar el caso de corte del while por un elemento equivalente.
	 * Luego, como no debe insertar ordenadamente, pueden reemplazarse los métodos addBefore() y addLast(), ambos
	 * de O(n), por addFirst(), de O(1). 
	 * @param l1 Lista de origen de los elementos.
	 * @param l2 Lista de salida de elementos, no repetidos.
	 * @see intercalar_sin_repetidos
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private static void insertarOrdenado(PositionList<Integer> l1, PositionList<Integer> l2) {
		Position<Integer> e1, last1, e2, last2;
		
		try {
			e1 = l1.first();
			if (l2.isEmpty()) {
				l2.addFirst(e1.element());
				e1 = l1.next(e1);
			}
			last1 = l1.last();
			
			while (e1 != null) {
				
				e2 = l2.first();
				last2 = l2.last();
				
				while (e2 != null && e1.element() > e2.element())
					if (e2 != last2)
						e2 = l2.next(e2);
					else
						e2 = null;
				
				if (e2 == null)
					l2.addLast(e1.element());
				else if (e1.element() < e2.element())
					l2.addBefore(e2, e1.element());
				
				if (e1 != last1)
					e1 = l1.next(e1);
				else
					e1 = null;
				
			}
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	public static <E> void borrar(Lista_circularmente_enlazada<E> list, int n) {
		try {
			
			if (!list.isEmpty()) {
				Node<E> pointer = list.first(); 
				
				while (list.size() > 1) {
					pointer = avanzar(list, pointer, n);
					Node<E> temp = pointer.getNext();
					list.remove(pointer);
					pointer = temp;
				}
				
			}
		} catch (TDALista_desarrollo.EmptyListException | TDALista_desarrollo.InvalidPositionException e) {
			e.printStackTrace();
		}
	}

	private static <E> Node<E> avanzar(Lista_circularmente_enlazada<E> list, Node<E> pointer, int n) {
		try {
			while (n > 0)
				pointer = list.next(pointer);
		} catch (TDALista_desarrollo.InvalidPositionException e) {
			e.printStackTrace();
		}
		return pointer;
	}
	
}
