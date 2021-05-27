package TDAPila_desarrollo;

public class Pila_con_enlaces<E> implements Stack<E> {
	
	protected Nodo<E> head;
	protected int size;
	
	public Pila_con_enlaces() {
		head = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila vacia.");
		return head.getItem();
	}

	@Override
	public void push(E element) {
		head =  new Nodo<E>(element, head);
		size++;
	}

	@Override
	public E pop() throws EmptyStackException {
		E aux = head.getItem();
		head = head.getNext();
		size--;
		return aux;
	}

}
