package TDAArbol;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class TNode<E> implements Position<E> {
	
	protected E element;
	protected TNode<E> parent;
	protected PositionList<TNode<E>> children;
	
	/**
	 * Crea un nodo nodo en inicializa sus campos.
	 * @param e
	 * @param p
	 */
	public TNode(E e, TNode<E> p) {
		element = e;
		parent = p;
		children = new DoubleLinkedList<TNode<E>>();
	}
	
	public TNode(E e) {
		this(e, null);
	}

	@Override
	public E element() {
		return element;
	}

	/**
	 * @return the parent
	 */
	public TNode<E> getParent() {
		return parent;
	}

	/**
	 * @return the children
	 */
	public PositionList<TNode<E>> getChildren() {
		return children;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(E element) {
		this.element = element;
	}
	
	/**
	 * @param p Parent
	 */
	public void setParent(TNode<E> p) {
		parent = p;
	}

}
