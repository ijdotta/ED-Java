package AVL_Tree;

import java.util.Comparator;

/**
 * Class AVLTree - Implementa un árbol AVL de búsqueda.
 * Warning: probablemente haya errores en las rotaciones, FIX IT!
 * @author Ignacio Joaquín Dotta
 * @param <E> Tipo de dato de los rótulos de lso nodos.
 */
public class AVLTree<E extends Comparable<E>> {
	
	protected AVLNode<E> root;
	protected Comparator<E> comparator;
	
	public AVLTree(Comparator<E> comp) {
		root = new AVLNode<E>();
		comparator = comp;
	}
	
	public AVLTree() {
		this(new DefaultComparator<E>());
	}
	
	public AVLNode<E> root() {
		return root;
	}
	
	//Buscar
	public AVLNode<E> buscar(E e) {
		return buscar_aux(root, e);
	}
	
	private AVLNode<E> buscar_aux(AVLNode<E> node, E e) {
		AVLNode<E> toReturn;
		int comparison;
		
		if (node.element() == null)
			toReturn = node;
		else {
			comparison = comparator.compare(e, node.element());
			if (comparison == 0)
				toReturn = node;
			else if (comparison < 0)
				toReturn = buscar_aux(node.getLeftChild(), e);
			else
				toReturn = buscar_aux(node.getRightChild(), e);
		}
		
		return toReturn;
	}

	//Insertar
	public void insertar(E e) {
		insertar_aux(root, e);
	}

	private void insertar_aux(AVLNode<E> node, E e) {
		E y;
		int comparacion;
		
		//Claramente el nodo insertado no requiere balancearse
		if (node.element() == null) {
			node.setElement(e);
			node.setHeight(1);
			node.setLeftChild(new AVLNode<E>(node));
			node.setRightChild(new AVLNode<E>(node));
		}
		else {
			comparacion = comparator.compare(e, node.element());
			if (comparacion == 0) {
				node.setDeleted(false);
			}
			else if (comparacion < 0) {
				insertar_aux(node.getLeftChild(), e);
				/*
				 * Puedo estar en casos:
				 * 	1) subarbol izq. del hijo izq. de a
				 * 	2) subarbol der. del hijo izq. de a
				 * 		donde a = node.
				 */
				if (Math.abs(node.getLeftChild().getHeight() -
						node.getRightChild().getHeight()) > 1) {
					//Determino rotación izq-izq o izq-der
					//Según del lugar al que se dirigió e, causando desbalance
					y = node.getLeftChild().element();
					if (comparator.compare(e, y) < 0)
						rightRotation(node);
					else
						leftRightRotation(node);
				}
				
			}
			else {
				insertar_aux(node.getRightChild(), e);
				if (Math.abs(node.getLeftChild().getHeight() - 
						node.getRightChild().getHeight()) > 1) {
					y = node.getLeftChild().element();
					if (comparator.compare(y, e) < 0)
						leftRotation(node);
					else
						rightLeftRotation(node);
				}
			}
			node.setHeight(Math.max(node.getLeftChild().getHeight(),
					node.getRightChild().getHeight()));
		}
	}

	private void rightRotation(AVLNode<E> x) {
		AVLNode<E> y;
		boolean x_is_leftChild = x.getParent().getLeftChild() == x;
		y = x.getLeftChild();
		
		y.setParent(x.getParent());
		if (x_is_leftChild)
			y.getParent().setLeftChild(y);
		else
			y.getParent().setRightChild(y);
		
		x.setLeftChild(y.getRightChild());
		x.getLeftChild().setParent(x);
		
		x.setParent(y);
		y.setRightChild(x);
		
	}

	private void leftRotation(AVLNode<E> x) {
		AVLNode<E> y;
		boolean x_is_leftChild = x.getParent().getLeftChild() == x;
		y = x.getRightChild();
		
		y.setParent(x.getParent());
		if (x_is_leftChild)
			y.getParent().setLeftChild(y);
		else
			y.getParent().setRightChild(y);
		
		x.setRightChild(y.getLeftChild());
		x.getRightChild().setParent(x);
		
		x.setParent(y);
		y.setLeftChild(x);
	}

	private void leftRightRotation(AVLNode<E> x) {
		leftRotation(x.getLeftChild());
		rightRotation(x.getLeftChild());
	}

	private void rightLeftRotation(AVLNode<E> x) {
		rightRotation(x.getRightChild());
		leftRotation(x.getRightChild());
	}

	//Eliminar : lazy
	public void remove(E e) {
		AVLNode<E> toRemove = buscar(e);
		if (toRemove.element() != null)
			toRemove.setDeleted(true);
	}
	
}
