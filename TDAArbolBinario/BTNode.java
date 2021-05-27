package TDAArbolBinario;

public class BTNode<E> implements Position<E> {
	
	protected E element;
	protected BTNode<E> parent, leftChild, rightChild;
	
	public BTNode(E e, BTNode<E> p, BTNode<E> l, BTNode<E> r) {
		element = e;
		parent = p;
		leftChild = l;
		rightChild = r;
	}
	
	public BTNode(E e, BTNode<E> p) {
		this(e, p, null, null);
	}
	
	public BTNode(E e) {
		this(e, null);
	}

	@Override
	public E element() {
		return element;
	}

	/**
	 * @return the parent
	 */
	public BTNode<E> getParent() {
		return parent;
	}

	/**
	 * @return the leftChild
	 */
	public BTNode<E> getLeftChild() {
		return leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public BTNode<E> getRightChild() {
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
	public void setParent(BTNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(BTNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(BTNode<E> rightChild) {
		this.rightChild = rightChild;
	}
	
	public BTNode<E> clone() {
		BTNode<E> new_node = new BTNode<E>(element, parent);
		if (leftChild != null)
			new_node.setLeftChild(leftChild.clone());
		if (rightChild != null)
			new_node.setRightChild(rightChild.clone());
		return new_node;
	}
}
