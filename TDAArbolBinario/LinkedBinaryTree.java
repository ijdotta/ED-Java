package TDAArbolBinario;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
	
	protected BTNode<E> root;
	protected int size;
	
	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	public LinkedBinaryTree(BTNode<E> originRoot, int size) {
		root = originRoot.clone();
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> list_to_iterate = new DoubleLinkedList<E>();
		
		if (root != null)
			preorder(root, list_to_iterate);
		
		return list_to_iterate.iterator();
	}

	private void preorder(BTNode<E> node, PositionList<E> list_to_iterate) {
		BTNode<E> leftChild, rightChild;
		leftChild = node.getLeftChild();
		rightChild = node.getRightChild();
		
		list_to_iterate.addLast(node.element());
		
		if (leftChild != null)
			preorder(leftChild, list_to_iterate);
		
		if (rightChild != null)
			preorder(rightChild, list_to_iterate);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable_list = new DoubleLinkedList<Position<E>>();
		
		if (root != null)
			pre_positions(root, iterable_list);
		
		return iterable_list;
	}

	private void pre_positions(BTNode<E> node, PositionList<Position<E>> iterable_list) {
		BTNode<E> leftChild, rightChild;
		leftChild = node.getLeftChild();
		rightChild = node.getRightChild();
		
		iterable_list.addLast(node);
		
		if (leftChild != null)
			pre_positions(leftChild, iterable_list);
		
		if (rightChild != null)
			pre_positions(rightChild, iterable_list);		
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNode<E> node = checkPosition(v);
		E temp = node.element();
		node.setElement(e);
		return temp;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (size == 0)
			throw new EmptyTreeException("root() sobre ï¿½rbol vacï¿½o. ");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> node = checkPosition(v);
		if (node == root)
			throw new BoundaryViolationException("parent() sobre root. ");
		return node.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTNode<E> node, leftChild, rightChild;
		node = checkPosition(v);
		PositionList<Position<E>> children = new DoubleLinkedList<Position<E>>();
		
		leftChild = node.getLeftChild();
		rightChild = node.getRightChild();
		
		if (leftChild != null)
			children.addLast(leftChild);
		if (rightChild != null)
			children.addLast(rightChild);
		
		return children;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkPosition(v);
		return node.getLeftChild() != null || node.getRightChild() != null;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkPosition(v);
		return node.getLeftChild() == null && node.getRightChild() == null;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkPosition(v);
		return node == root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> leftChild, node;
		node = checkPosition(v);
		leftChild = node.getLeftChild();
		if (leftChild == null)
			throw new BoundaryViolationException("left() sobre nodo que no tiene hijo izquierdo. ");
		return leftChild;
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> rightChild, node;
		node = checkPosition(v);
		rightChild = node.getRightChild();
		if (rightChild == null)
			throw new BoundaryViolationException("right() sobre nodo que no tiene hijo derecho. ");
		return rightChild;
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkPosition(v);
		return node.getLeftChild() != null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkPosition(v);
		return node.getRightChild() != null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if (root != null)
			throw new InvalidOperationException("createRoot() sobre ï¿½rbol que ya tiene raï¿½z. ");
		root = new BTNode<E>(r);
		size++;
		return root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> child, node = checkPosition(v);
		if (node.getLeftChild() != null)
			throw new InvalidOperationException("addLeft() sobre nodo que ya tiene hijo izquierdo. ");
		
		child = new BTNode<E>(r, node);
		node.setLeftChild(child);
		size++;
		return child;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> child, node = checkPosition(v);
		if (node.getRightChild() != null)
			throw new InvalidOperationException("addRight() sobre nodo que ya tiene hijo derecho. ");
		
		child = new BTNode<E>(r, node);
		node.setRightChild(child);
		size++;
		return child;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> parent, leftChild, rightChild, toRemove;
		E temp;
		boolean isToRemoveLeftChild = false;
		
		toRemove = checkPosition(v);
		leftChild = toRemove.getLeftChild();
		rightChild = toRemove.getRightChild();
		
		if (leftChild != null && rightChild != null) {
			throw new InvalidOperationException("remove() sobre nodo con dos hijos. ");
		}
		else if (toRemove == root) {
			
			if (leftChild == null && rightChild == null)
				root = null;
			else if (leftChild != null)
				root = leftChild;
			else
				root = rightChild;
			
		}
		else {
			
			parent = toRemove.getParent();
			isToRemoveLeftChild = parent.getLeftChild() == toRemove; 
			
			//Anula el hijo correspondiente del padre, o hace un bypass, según corresponda.
			if (leftChild == null && rightChild == null)
				updateParent(parent,  null, isToRemoveLeftChild);
			else if (leftChild != null) 
				updateParent(parent, leftChild, isToRemoveLeftChild);
			else 
				updateParent(parent, rightChild, isToRemoveLeftChild);
			
		}
		
		temp = toRemove.element();
		toRemove.setLeftChild(null);
		toRemove.setRightChild(null);
		toRemove.setParent(null);
		toRemove.setElement(null);
		size--;
		return temp;
	}
	
	private void updateParent(BTNode<E> parent, BTNode<E> child, boolean modifyLeft) {
		if (modifyLeft)
			parent.setLeftChild(child);
		else
			parent.setRightChild(child);
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTNode<E> local_root_t1, local_root_t2, node = checkPosition(r);
		Position<E> root_t1, root_t2;
		
		if (node.getLeftChild() != null || node.getRightChild() != null)
			throw new InvalidPositionException("attach() sobre nodo interno. ");
		
		try {
			//Clonación de t1 como subárbol izquierdo
			if (!t1.isEmpty()) {
				root_t1 = t1.root();
				local_root_t1 = new BTNode<E>(root_t1.element(), node);
				node.setLeftChild(local_root_t1);
				clonar(local_root_t1, root_t1, t1);
			}

			//Clonación de t2 como subárbol derecho
			if (!t2.isEmpty()) {
				root_t2 = t2.root();
				local_root_t2 = new BTNode<E>(root_t2.element(), node);
				node.setRightChild(local_root_t2);
				clonar(local_root_t2, root_t2, t2);
			}
			
			size += t1.size() + t2.size();
			
		} catch (EmptyTreeException e) {
			e.printStackTrace();
			node.setLeftChild(null);
			node.setRightChild(null);
		}
			
	}
	
	private void clonar(BTNode<E> local_parent_t, Position<E> parent_t, BinaryTree<E> t) {
		BTNode<E> leftChild, rightChild;
		Position<E> left, right;
		try {
			if (t.hasLeft(parent_t)) {
				left = t.left(parent_t);
				leftChild = new BTNode<E>(left.element(), local_parent_t);
				local_parent_t.setLeftChild(leftChild);
				clonar(leftChild, left, t);
			}
			if (t.hasRight(parent_t)) {
				right = t.right(parent_t);
				rightChild = new BTNode<E>(right.element(), local_parent_t);
				local_parent_t.setRightChild(rightChild);
				clonar(rightChild, right, t);
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			local_parent_t.setLeftChild(null);
			local_parent_t.setRightChild(null);
		}
	}

	private BTNode<E> checkPosition(Position<E> v) throws InvalidPositionException {
		BTNode<E> toReturn = null;
		
		if (v == null)
			throw new InvalidPositionException("PosiciÃ³n nula. ");
		if (v.element() == null)
			throw new InvalidPositionException("Posiciï¿½n eliminada previamente. ");
		if (size == 0)
			throw new InvalidPositionException("Posiciï¿½n no pertenece a este ï¿½rbol. ");
		
		try {
			toReturn = (BTNode<E>) v;
		} catch (ClassCastException e) {e.printStackTrace();}
		
		return toReturn;
	}
	
	public void espejar() {
		if (root != null)
			espejar_aux(root);
	}

	private void espejar_aux(BTNode<E> parent) {
		BTNode<E> leftChild, rightChild;
		
		//SWAP ::
		leftChild= parent.getLeftChild();
		rightChild = parent.getRightChild();
		parent.setLeftChild(rightChild);
		parent.setRightChild(leftChild);
		
		if (rightChild != null)
			espejar_aux(rightChild);
		if (leftChild != null)
			espejar_aux(leftChild);
	}
	
	public void padre_de_hojas() {
		//obs: se imprimen pares (l,n) , l = level , n = node element
		if (root != null)
			padre_de_hojas_aux(root, 0);
		System.out.println();
	}
	
	/**
	 * Auxiliar a padres de hojas. 
	 * @param parent Nodo que podría tener hijos.
	 * @param level Nivel el nodo recibido.
	 * @return true si el nodo es una hoja.
	 */
	private boolean padre_de_hojas_aux(BTNode<E> parent, int level) {
		BTNode<E> leftChild, rightChild;
		boolean tieneHoja;
		leftChild = parent.getLeftChild();
		rightChild = parent.getRightChild();
		
		//Si es hoja, retorna true.
		if (leftChild == null && rightChild == null)
			return true;
		//Si no es hoja, se ejecuta recursivamente para los niveles inferiores.
		//Si el nivel infeior inmediato es hoja, entonces tieneHoja y puede imprimirse.
		//Como descartamos que sea hoja, retorna false y su padre no puede imprimirse, al menos, por este nodo.
		else {
			tieneHoja = leftChild != null && padre_de_hojas_aux(leftChild, level+1);
			tieneHoja = (rightChild != null && padre_de_hojas_aux(rightChild, level+1)) | tieneHoja;
			if (tieneHoja)
				System.out.print("("+level+","+parent.element()+")");
			return false;
		}
	}
	
	public LinkedBinaryTree<E> clone() {
		return new LinkedBinaryTree<E>(root, size);
	}
	
}
