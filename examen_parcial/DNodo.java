package examen_parcial;

import TDALista_desarrollo.Position;

public class DNodo<E> implements Position<E> {
	
	protected E element;
	protected DNodo<E> prev, next;
	
	public DNodo(E e, DNodo<E> p, DNodo<E> n) {
		element = e;
		prev = p;
		next = n;
	}
	
	public DNodo(E e) {
		this(e, null, null);
	}

	public E element() {
		return element;
	}

	public DNodo<E> getPrev() {
		return prev;
	}

	public DNodo<E> getNext() {
		return next;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public void setPrev(DNodo<E> prev) {
		this.prev = prev;
	}

	public void setNext(DNodo<E> next) {
		this.next = next;
	}
	
}
