package TDAPila_desarrollo;

public class Nodo<E> {
	
	/**
	 * Atributos de clase:
	 * item :: contenido del nodo
	 * next :: nodo siguiente
	 */
	
	private E item;
	private Nodo<E> next;
	
	/*
	 * constructores
	 */
	
	public Nodo(E item, Nodo<E> next) {
		this.item = item;
		this.next = next;
	}
	
	public Nodo(E item) {
		this(item,null);
	}
	
	//setters
	
	public void setItem(E item) {
		this.item = item;
	}
	
	public void setNext(Nodo<E> next) {
		this.next = next;
	}
	
	//getters
	
	public E getItem() {
		return item;
	}
	
	public Nodo<E> getNext() {
		return next;
	}

}
