package TDALista_desarrollo;

import java.util.Iterator;

import TDALista_desarrollo.Position;
import TDALista_desarrollo.PositionList;

public class Lista_doble_enlazada<E> implements PositionList<E> {

	protected DNode<E> header;
	protected DNode<E> trailer;
	protected int size;

	public Lista_doble_enlazada() {
		header = new DNode<E>(null);
		trailer = new DNode<E>(null);
		header.setNext(trailer);
		trailer.setPrev(header);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return header.getNext() == trailer;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista vacía. ");
		return header.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista vacía. ");
		return trailer.getPrev();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> pos = checkPosition(p);
		
		if (pos.getNext() == trailer)
			throw new BoundaryViolationException("Posición es fin de lista. ");
		
		return pos.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> pos = checkPosition(p);
		
		if (pos.getPrev() == header)
			throw new BoundaryViolationException("Posición es inicio de lista. "); 
		
		return pos.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNode<E> node = new DNode<E>(element, header, header.getNext());
		header.setNext(node);
		node.getNext().setPrev(node);
		size++;
	}

	@Override
	public void addLast(E element) {
		DNode<E> node = new DNode<E>(element, trailer.getPrev(), trailer);
		trailer.setPrev(node);
		node.getPrev().setNext(node);
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> pos = checkPosition(p);
		DNode<E> node = new DNode<E>(element, pos, pos.getNext());
		pos.setNext(node);
		node.getNext().setPrev(node);
		size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> pos = checkPosition(p);
		DNode<E> node = new DNode<E>(element, pos.getPrev(), pos);
		pos.setPrev(node);
		node.getPrev().setNext(node);
		size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNode<E> node = checkPosition(p);
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		size--;
		E temp = node.element();
		node.setElement(null);
		node.setPrev(null);
		node.setNext(null);
		return temp;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> node = checkPosition(p);
		E temp = node.element();
		node.setElement(element);
		return temp;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list = new Lista_doble_enlazada<Position<E>>();
		if (!isEmpty()) {
			DNode<E> pointer = header.getNext();
			while (pointer != trailer) {
				list.addLast(pointer);
				pointer = pointer.getNext();
			}
		}
			
		return list;
	}
	
	/**
	 * Invierte el orden del contenido de la pila.
	 */
	public void invertir() {
		DNode<E> pointer, node; 
		pointer = header;
		while (pointer != null) {
			node = pointer;
			pointer = pointer.getNext();
			swapReferences(node);
		}
		swapDummies();
	}
	
	public PositionList<E> zigzag() {
		PositionList<E> l2 = new Lista_doble_enlazada<E>();
		DNode<E> leftNode, rightNode;
		
		leftNode = header.getNext();
		rightNode = trailer.getPrev();
		
		while (leftNode != rightNode && rightNode.getNext() != leftNode) {
			l2.addLast(leftNode.element());
			l2.addLast(rightNode.element());
			leftNode = leftNode.getNext();
			rightNode = rightNode.getPrev();
		}
		
		return l2;
	}
	
	/**
	 * Intercambia las referencias de header y trailer.
	 * @see invertir
	 */
	private void swapDummies() {
		DNode<E> aux = header;
		header = trailer;
		trailer = aux;
	}

	private void swapReferences(DNode<E> node) {
		DNode<E> aux = node.getPrev();
		node.setPrev(node.getNext());
		node.setNext(aux);
	}

	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException("P es inválida. ");
		if (p.element() == null)
			throw new InvalidPositionException("P eliminada previamente. ");
		return (DNode<E>) p;
	}

	private class DNode<T> implements Position<T> {
	
		private T element;
		private DNode<T> prev, next;
	
		public DNode(T elem, DNode<T> p, DNode<T> n) {
			element = elem;
			prev = p;
			next = n;
		}
	
		public DNode(T elem) {
			this(elem, null, null);
		}
	
		@Override
		public T element() {
			return element;
		}
	
		public DNode<T> getPrev() {
			return prev;
		}
	
		public DNode<T> getNext() {
			return next;
		}
	
		public void setElement(T element) {
			this.element = element;
		}
	
		public void setPrev(DNode<T> prev) {
			this.prev = prev;
		}
	
		public void setNext(DNode<T> next) {
			this.next = next;
		}
		
	}

}