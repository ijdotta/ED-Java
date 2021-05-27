package TDALista_desarrollo;

public class Lista_circularmente_enlazada<E> {
	
	protected Node<E> cursor;
	protected int size;
	
	public Lista_circularmente_enlazada() {
		cursor = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return cursor == null;
	}
	
	public Node<E> first() throws EmptyListException {
		if (cursor == null)
			throw new EmptyListException("Lista vacía. ");
		
		return cursor;
	}
	
	public Node<E> last( ) throws EmptyListException {
		if (cursor == null)
			throw new EmptyListException("Lista vacía. ");
		
		Node<E> pointer = cursor;
		while (pointer.getNext() != cursor)
			pointer = pointer.getNext();
		return pointer;
	}
	
	public Node<E> next(Node<E> n) throws InvalidPositionException {
		checkNode(n);
		return n.getNext();
	}
	
	public Node<E> prev(Node<E> n) throws InvalidPositionException {
		checkNode(n);
		Node<E> pointer = cursor;
		while (pointer.getNext() != n)
			pointer = pointer.getNext();
		return pointer;
	}
	
	public void addFirst(E e) {
		Node<E> pointer = cursor;
		while (pointer.getNext() != cursor)
			pointer = pointer.getNext();
		pointer.setNext(new Node<E>(e, cursor));
		cursor = pointer.getNext();
	}
	
	public void addLast(E e) {
		Node<E> pointer = cursor;
		while (pointer.getNext() != cursor)
			pointer = pointer.getNext();
		pointer.setNext(new Node<E>(e, cursor));
	}
	
	public void addAfter(Node<E> n, E e) throws InvalidPositionException {
		checkNode(n);
		
		Node<E> pointer = cursor;
		while (pointer != n)
			pointer = pointer.getNext();
		pointer.setNext(new Node<E>(e, pointer.getNext()));
	}
	
	public void addBefore(Node<E> n, E e) throws InvalidPositionException {
		checkNode(n);
		
		Node<E> pointer = cursor;
		while (pointer.getNext() != n)
			pointer = pointer.getNext();
		pointer.setNext(new Node<E>(e, pointer.getNext()));
	}
	
	public E remove(Node<E> n) throws InvalidPositionException{
		checkNode(n);
		
		Node<E> pointer = cursor;
		while (pointer.getNext() != n)
			pointer = pointer.getNext();
		
		
		E temp = n.element();
		pointer.setNext(n.getNext());
		n.setElement(null);
		n.setNext(null);
		return temp;
	}
	
	public void set(Node<E> n, E e) throws InvalidPositionException {
		checkNode(n);
		n.setElement(e);
	}
	
	private void checkNode(Node<E> n) throws InvalidPositionException {
		if (n == null)
			throw new InvalidPositionException("N es inválido. ");
		if (n.element() == null)
			throw new InvalidPositionException("N eliminado previamente. ");
	}
	
//	iterator()
//	positions()
	
}
