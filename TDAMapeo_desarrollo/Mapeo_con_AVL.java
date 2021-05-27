package TDAMapeo_desarrollo;

import AVL_Tree.AVLNode;
import AVL_Tree.AVLTree;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

/**
 * Class Mapeo_con_AVL - Implementa un mapeo a través de un árbol AVL.
 * Warning: las operaciones put y get fallan a veces, probablemente sea por las rotaciones del AVL.
 * @see AVL_Tree.AVLTree
 * @author Ignacio
 * @param <K> Tipo de dato de las claves del mapeo.
 * @param <V> Tipo de dato de los valores del mapeo.
 */
public class Mapeo_con_AVL<K extends Comparable<K>, V> implements Map<K, V> {
	
	protected AVLTree<EntradaComparable<K,V>> map;
	protected int size;
	
	public Mapeo_con_AVL() {
		map = new AVLTree<EntradaComparable<K,V>>();
		size = 0;
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
	public V get(K key) throws InvalidKeyException {
		EntradaComparable<K, V> entrada;
		AVLNode<EntradaComparable<K, V>> node;
		
		checkKey(key);
		
		entrada = new EntradaComparable<K, V>(key, null);
		node = map.buscar(entrada);
		
		if (node.element() == null || node.isDeleted())
			return null;
		else
			return node.element().getValue();
		
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		AVLNode<EntradaComparable<K, V>> node;
		EntradaComparable<K, V> entrada;
		V removed = null;
		
		checkKey(key);
		
		entrada = new EntradaComparable<K, V>(key, value);
		node = map.buscar(entrada);
		
//		if (node.element() != null && !node.isDeleted()) {
//			entrada = node.element();
//			removed = entrada.getValue();
//			entrada.setValue(value);
//		}
//		else {
//			map.insertar(entrada);
//			node.setDeleted(false);
//			size++;
//		}
		
		if (node.element() == null) {
			map.insertar(entrada);
			size++;
		}
		else {
			if (node.isDeleted()) {
				node.setDeleted(false);
				size++;
			}
			else {
				entrada = node.element();
				removed = entrada.getValue();
				entrada.setValue(value);
			}
		}
		
		return removed;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		EntradaComparable<K, V> entrada;
		AVLNode<EntradaComparable<K, V>> node;
		V removed = null;
		
		checkKey(key);
		
		entrada = new EntradaComparable<K, V>(key, null);
		node = map.buscar(entrada);
		
		if (node.element() != null && !node.isDeleted()) {
			removed = node.element().getValue();
			map.remove(entrada);
			size--;
		}
		
		return removed;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		AVLNode<EntradaComparable<K, V>> node;
		
		//Recupera la raíz del abb.
		node = map.root();
		
		//Si la raíz no es dummy, ejecuta inorder recursivo a los subárboles.
		if (node.element() != null)
			inorder_keys(node, iterable);
		
		return iterable;
	}
	
	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		AVLNode<EntradaComparable<K, V>> node;
		
		node = map.root();
		
		if (node.element() != null)
			inorder_values(node, iterable);
		
		return iterable;
	}
	
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		AVLNode<EntradaComparable<K, V>> node;
		
		node = map.root();
		
		if (node.element() != null)
			inorder_entries(node, iterable);
		
		return iterable;
	}
	
	protected void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}
	
	/**
	 * Método auxiliar para generar una coleccion iterable inorder de claves, recursivamente.
	 * @param node Nodo del subárbol.
	 * @param iterable Colección iterable.
	 */
	private void inorder_keys(AVLNode<EntradaComparable<K, V>> node, PositionList<K> iterable) {
		if (node.getLeftChild().element() != null)
			inorder_keys(node.getLeftChild(), iterable);
		
		iterable.addLast(node.element().getKey());
		
		if (node.getRightChild().element() != null)
			inorder_keys(node.getRightChild(), iterable);
	}

	/**
	 * Método auxiliar para generar una coleccion iterable inorder de valores, recursivamente.
	 * @param node Nodo del subárbol.
	 * @param iterable Colección iterable.
	 */
	private void inorder_values(AVLNode<EntradaComparable<K, V>> node, PositionList<V> iterable) {
		if (node.getLeftChild().element() != null)
			inorder_values(node.getLeftChild(), iterable);
		
		iterable.addLast(node.element().getValue());
		
		if (node.getRightChild().element() != null)
			inorder_values(node.getRightChild(), iterable);
	}

	/**
	 * Método auxiliar para generar una coleccion iterable inorder de entradas, recursivamente.
	 * @param node Nodo del subárbol.
	 * @param iterable Colección iterable.
	 */
	private void inorder_entries(AVLNode<EntradaComparable<K, V>> node, PositionList<Entry<K, V>> iterable) {
		if (node.getLeftChild().element() != null)
			inorder_entries(node.getLeftChild(), iterable);
		
		iterable.addLast(node.element());
		
		if (node.getRightChild().element() != null)
			inorder_entries(node.getRightChild(), iterable);
	}

}
