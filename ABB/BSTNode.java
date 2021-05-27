package ABB;

public class BSTNode<E extends Comparable<E>> {
	
	E element;
	BSTNode<E> parent, leftChild, rightChild;
	
	public BSTNode(E e, BSTNode<E> p, BSTNode<E> lc, BSTNode<E> rc) {
		element = e;
		parent = p;
		leftChild = lc;
		rightChild = rc;
	}
	
	public BSTNode(E e, BSTNode<E> p) {
		this(e, p, null, null);
	}
	
	public BSTNode() {
		this(null, null);
	}
	
	/**
	 * @return the element
	 */
	public E element() {
		return element;
	}

	/**
	 * @return the parent
	 */
	public BSTNode<E> parent() {
		return parent;
	}

	/**
	 * @return the leftChild
	 */
	public BSTNode<E> leftChild() {
		return leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public BSTNode<E> rightChild() {
		return rightChild;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(BSTNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(BSTNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(BSTNode<E> rightChild) {
		this.rightChild = rightChild;
	}
	
}
