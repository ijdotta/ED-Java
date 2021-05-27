package AVL_Tree;

public class AVLNode<E> {
	
	private E element;
	private AVLNode<E> leftChild, rightChild, parent;
	private int height;
	private boolean deleted;
	
	public AVLNode(E e, AVLNode<E> p, AVLNode<E> lc, AVLNode<E> rc, int h) {
		element = e;
		parent = p;
		leftChild = lc;
		rightChild = rc;
		height = h;
		deleted = false;
	}
	
	public AVLNode(AVLNode<E> p) {
		this(null, p, null, null, 0);
	}
	
	public AVLNode() {
		this(null);
	}

	public E element() {
		return element;
	}

	public AVLNode<E> getLeftChild() {
		return leftChild;
	}

	public AVLNode<E> getRightChild() {
		return rightChild;
	}

	public AVLNode<E> getParent() {
		return parent;
	}

	public int getHeight() {
		return height;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public void setLeftChild(AVLNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(AVLNode<E> rightChild) {
		this.rightChild = rightChild;
	}

	public void setParent(AVLNode<E> parent) {
		this.parent = parent;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
