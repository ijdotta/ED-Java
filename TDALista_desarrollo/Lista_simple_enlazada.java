package TDALista_desarrollo;

import java.util.Iterator;

public class Lista_simple_enlazada<E> implements PositionList<E> {
	
	protected Node<E> head;
	
	public Lista_simple_enlazada() {
		head = null;
	}
	
	private Lista_simple_enlazada(Node<E> h) {
		head = h.clone();
	}
	
	/**
	 * Método para fines de testing. Comparación de referencias de nodos en un clone.
	 * Switch to public if want to use out of the class and switch to private again after finish using.
	 * @param p Posición cuyo nodo se desea obtener.
	 * @return	Nodo de la posición.
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private Node<E> getNode(Position<E> p) {
		return (Node<E>) p;
	}

	@Override
	public int size() {
		int size = 0;
		Node<E> aux = head;
		
		while (aux != null) {
			size++;
			aux = aux.getNext();
		}
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista vacía. ");
		
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista vacía. ");
		
		Node<E> aux = head;
		while (aux.getNext() != null)
			aux = aux.getNext();
		
		return aux;
	}
	
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> n = checkPosition(p);
		if (n.getNext() == null)
			throw new BoundaryViolationException("Next :: última posición. ");
		return n.getNext();
	}
	
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		checkPosition(p);
		
		if (p == head)
			throw new BoundaryViolationException("Fuera de los límites. P es la cabeza. ");
		
		Node<E> aux = head;
		while (aux.getNext() != p && aux.getNext() != null)
			aux = aux.getNext();
		
		if (aux.getNext() == null)
			throw new InvalidPositionException("P no pertenece a la lista. ");
		return aux;
	}

	@Override
	public void addFirst(E element) {
		head = new Node<E>(element, head);
	}

	@Override
	public void addLast(E element) {
		if (head == null)
			head = new Node<E>(element);
		else {
			Node<E> aux = head;
			while (aux.getNext() != null)
				aux = aux.getNext();
			aux.setNext(new Node<E>(element));
		}
	}
	
	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		n.setNext(new Node<E>(element, n.getNext()));
	}
	
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		checkPosition(p);
		if (p == head)
			addFirst(element);
		else {
			Node<E> aux = head;
			while (aux.getNext() != p && aux.getNext() != null)
				aux = aux.getNext();
			if (aux.getNext() == p)
				aux.setNext(new Node<E>(element, aux.getNext()));
			else
				throw new InvalidPositionException("P no está en la lista. ");
			//Alternativa para el bloque else :: addAfter(prev(p), element);
		}
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		
		if (p == head)
			head = n.getNext();
		else
			try {
				((Node<E>) prev(p)).setNext(n.getNext());
			} catch (BoundaryViolationException e) {
				e.printStackTrace();
			}
	
		E temp = p.element();
		n.setElement(null);
		n.setNext(null);
		return temp;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (p == null || isEmpty())
			throw new InvalidPositionException("Posición inválida. Lista vacía. ");
		
		Node<E> aux = head;
		while (aux != p && aux.getNext() != null)
			aux = aux.getNext();
		
		if (aux == p) {
			E temp = aux.element();
			aux.setElement(element);
			return temp;
		} 
		else throw new InvalidPositionException("Posición inválida. Lista vacía.");
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list = new Lista_simple_enlazada<Position<E>>();
		
		if (head != null) {
			Node<E> pointer = head;
			while (pointer != null) {
				list.addLast(pointer);
				pointer = pointer.getNext();
			}
		}
		
		return list;
	}
	
	@Override
	public Lista_simple_enlazada<E> clone() {
		return new Lista_simple_enlazada<E>(head);
	}
	
	/**
	 * Recibe dos elementos y elimina toda aparición en la lista de su aparicón consecutiva.
	 * @param e1 Primer elemento del par
	 * @param e2 Segundo elemento del par
	 */
	public void eliminar_consecutivos(E e1, E e2) {
		Node<E> puntero = head;
		while (puntero != null)
			if (puntero.element() == e1 && puntero.getNext() != null && puntero.getNext().element() == e2)
				puntero = eliminar_y_recuperar_nueva_posicion(puntero);
			else
				puntero = puntero.getNext();
	}

	private Node<E> eliminar_y_recuperar_nueva_posicion(Node<E> puntero) {
		Node<E> nuevaPos = puntero.getNext().getNext();
		
		try {
			if (puntero == head)
				head = nuevaPos;
			else
				((Node<E>)prev(puntero)).setNext(nuevaPos);
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		puntero.getNext().setNext(null);
		puntero.getNext().setElement(null);
		puntero.setNext(null);
		puntero.setElement(null);
		
		return nuevaPos;
	}

	private Node<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException("P es inválida. ");
		if (p.element() == null)
			throw new InvalidPositionException("P eliminada previamente. ");
		return (Node<E>) p;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class Node<T> implements Position<T> {
		
		protected T element;
		protected Node<T> next;
		
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
		
		public Node(T element) {
			this(element, null);
		}
		
		public void setElement(T element) {
			this.element = element;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		@Override
		public T element() {
			return element;
		}
		
		@Override
		protected Node<T> clone() {
			Node<T> node = new Node<T>(this.element);
			if (next != null)
				node.setNext(next.clone());
			return node;
		}
	}
	
	
}
