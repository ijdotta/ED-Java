
package ABB;

import java.util.Comparator;

public class BinarySearchTree<E extends Comparable<E>> {
	
	BSTNode<E> root;
	Comparator<E> comparator;
	
	public BinarySearchTree(Comparator<E> comp) {
		root = new BSTNode<E>();
		comparator = comp;
	}
	
	public BinarySearchTree() {
		this(new DefaultComparator<E>());
	}
	
	/**
	 * Retorna el nodo raiz del ABB.
	 * @return Nodo raíz.
	 */
	public BSTNode<E> raiz() {
		return root;
	}
	
	// Retorna el nodo del ABB donde se encuentra o deberia encontrarse el elemento e.
	public BSTNode<E> buscar(E e) {
		return buscar_aux(e, root);
	}
	
	private BSTNode<E> buscar_aux(E e, BSTNode<E> node) {
		BSTNode<E> toReturn = node;
		E rotulo = node.element();
		
		if (rotulo != null)	{
			int comp = comparator.compare(e, rotulo);
			if (comp == -1) toReturn = buscar_aux(e, node.leftChild());
			else if (comp == 1) toReturn = buscar_aux(e, node.rightChild());
		}
		return toReturn;
	}

	// Expande el nodo n del ABB, de forma luego de la ejecucion del metodo:
	// 1) n contiene a e como elemento.
	// 2) n tiene por hijos izquierdo y derechos dos nodos dummy.
	public void expandir(BSTNode<E> n, E e) {
		n.setElement(e);
		n.setLeftChild(new BSTNode<E>());
		n.leftChild().setParent(n);
		n.setRightChild(new BSTNode<E>());
		n.rightChild().setParent(n);
	}
	
	// Elimina el nodo del ABB donde se encuentra el elemento e.
	public void eliminar(E e) {
		BSTNode<E> toRemove, leftChild, rightChild;
		
		//Validar e
		if (e != null) {
			toRemove = buscar(e);
			
			//Validar existencia de nodo e en el árbol
			if (toRemove.element() != null) {
				
				leftChild = toRemove.leftChild();
				rightChild = toRemove.rightChild();
				
				//toRemove es una hoja -> transf. toRemove en dummy
				if (leftChild.element() == null && rightChild.element() == null) {
					toRemove.setElement(null);
					toRemove.setLeftChild(null);
					toRemove.setRightChild(null);
				}
				//toRemove tiene solo hijo izq. -> bypass
				else if (rightChild.element() == null)
					bypass(toRemove, leftChild);
				//toRemove tiene solo hijo der. -> bypass
				else if (leftChild.element() == null)
					bypass(toRemove, rightChild);
				//toRemove tiene dos hijos -> reemplazar rotulo de toRemove con su sucesor inorder y eliminar el sucesor inorder de toRemove
				else {
					//Hallar sucesor inorder
					leftChild = rightChild;
					while (leftChild.leftChild().element() != null)
						leftChild = leftChild.leftChild();
					
					//Actualizar rotulo de toRemove
					toRemove.setElement(leftChild.element());
					
					rightChild = leftChild.rightChild();
					//Si 'sucesor' es hoja -> transf. en dummy
					if (rightChild.element() == null) {
						leftChild.setElement(null);
						leftChild.setLeftChild(null);
						leftChild.setRightChild(null);
					}
					//Si no es hoja -> bypass
					else
						bypass(leftChild, rightChild);
				}
			}
		}
	}
	
	private void bypass(BSTNode<E> toRemove, BSTNode<E> child) {
		BSTNode<E> parent = toRemove.parent();
		
		if (toRemove == root)
			root = child;
		else {
			if (parent.leftChild() == toRemove)
				parent.setLeftChild(child);
			else
				parent.setRightChild(child);
		}
		
		child.setParent(parent);
		toRemove.setElement(null);
		toRemove.setLeftChild(null);
		toRemove.setRightChild(null);
		toRemove.setParent(null);
	}

}
