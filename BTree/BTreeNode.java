package BTree;

public class BTreeNode<E> {
	
	protected BTreeNode<E> parent;
	protected BTreeNode<E> [] child;
	protected int maxDegree;
	
	public BTreeNode(BTreeNode<E> p, BTreeNode<E>... children) {
		parent = p;
		child = (BTreeNode<E> []) new BTreeNode[maxDegree];
		for (int i = 0; i < children.length && i < maxDegree; i++)
			child[i] = children[i];
	}
	
	public BTreeNode() {
		this(null);
	}

}
