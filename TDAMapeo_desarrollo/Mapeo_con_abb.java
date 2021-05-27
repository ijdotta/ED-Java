package TDAMapeo_desarrollo;

import ABB.BSTNode;
import ABB.BinarySearchTree;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Mapeo_con_abb<K extends Comparable<K>, V> implements Map<K, V> {
	
	protected BinarySearchTree<EntradaComparable<K,V>> map;
	protected int size;
	
	/**
	 * Crea un mapeo vacío.
	 */
	public Mapeo_con_abb() {
		map = new BinarySearchTree<EntradaComparable<K,V>>();
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
		EntradaComparable<K,V> entrada;
		BSTNode<EntradaComparable<K,V>> node;
		V to_return = null;
		
		checkKey(key);		
		entrada = new EntradaComparable<K,V>(key, null);
		node = map.buscar(entrada);
		
		if (node.element() != null)
			to_return = node.element().getValue();
		
		return to_return;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		EntradaComparable<K,V> entrada;
		BSTNode<EntradaComparable<K,V>> node;
		V to_return = null;
		
		checkKey(key);
		entrada = new EntradaComparable<K,V>(key, value);
		node = map.buscar(entrada);
		
		if (node.element() == null) {
			map.expandir(node, entrada);
			size++;
		}
		else {
			to_return = node.element().getValue();
			node.element().setValue(value);
		}
		
		return to_return;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		EntradaComparable<K,V> entrada;
		BSTNode<EntradaComparable<K,V>> node;
		V to_return = null;
		
		checkKey(key);		
		entrada = new EntradaComparable<K,V>(key, null);
		node = map.buscar(entrada);
		
		if (node.element() != null) {
			to_return = node.element().getValue();
			map.eliminar(entrada);
			size--;
		}
		
		return to_return;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		BSTNode<EntradaComparable<K,V>> node;
		
		//Recupera la raíz del abb.
		node = map.raiz();
		
		//Si la raíz no es dummy, ejecuta inorder recursivo a los subárboles.
		if (node.element() != null)
			inorder_keys(node, iterable);
		
		return iterable;
	}
	
	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		BSTNode<EntradaComparable<K,V>> node;
		
		node = map.raiz();
		
		if (node.element() != null)
			inorder_values(node, iterable);
		
		return iterable;
	}
	
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		BSTNode<EntradaComparable<K,V>> node;
		
		node = map.raiz();
		
		if (node.element() != null)
			inorder_entries(node, iterable);
		
		return iterable;
	}

	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

	/**
	 * Método auxiliar para generar una coleccion iterable inorder de claves, recursivamente.
	 * @param node Nodo del subárbol.
	 * @param iterable Colección iterable.
	 */
	private void inorder_keys(BSTNode<EntradaComparable<K, V>> node, PositionList<K> iterable) {
		if (node.leftChild().element() != null)
			inorder_keys(node.leftChild(), iterable);
		
		iterable.addLast(node.element().getKey());
		
		if (node.rightChild().element() != null)
			inorder_keys(node.rightChild(), iterable);
	}

	/**
	 * Método auxiliar para generar una coleccion iterable inorder de valores, recursivamente.
	 * @param node Nodo del subárbol.
	 * @param iterable Colección iterable.
	 */
	private void inorder_values(BSTNode<EntradaComparable<K, V>> node, PositionList<V> iterable) {
		if (node.leftChild().element() != null)
			inorder_values(node.leftChild(), iterable);
		
		iterable.addLast(node.element().getValue());
		
		if (node.rightChild().element() != null)
			inorder_values(node.rightChild(), iterable);
	}

	/**
	 * Método auxiliar para generar una coleccion iterable inorder de entradas, recursivamente.
	 * @param node Nodo del subárbol.
	 * @param iterable Colección iterable.
	 */
	private void inorder_entries(BSTNode<EntradaComparable<K, V>> node, PositionList<Entry<K, V>> iterable) {
		if (node.leftChild().element() != null)
			inorder_entries(node.leftChild(), iterable);
		
		iterable.addLast(node.element());
		
		if (node.rightChild().element() != null)
			inorder_entries(node.rightChild(), iterable);
	}

}
