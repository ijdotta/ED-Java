package TDALista_desarrollo;

public class Node<E> implements Position<E> {
	
	protected E element;
	protected Node<E> next;
	
	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}
	
	public Node(E element) {
		this(element, null);
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public Node<E> getNext() {
		return next;
	}
	
	@Override
	public E element() {
		return element;
	}
	
	@Override
	protected Node<E> clone() {
		Node<E> node = new Node<E>(this.element);
		if (next != null)
			node.setNext(next.clone());
		return node;
	}
}