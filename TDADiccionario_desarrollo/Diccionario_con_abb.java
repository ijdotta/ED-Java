package TDADiccionario_desarrollo;

import ABB.BSTNode;
import ABB.BinarySearchTree;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Diccionario_con_abb<K extends Comparable<K>, V> implements Dictionary<K, V> {
	
	protected BinarySearchTree<EntradaComparable<K,PositionList<Entry<K,V>>>> dic;
	protected int size;
	
	/**
	 * Crea un diccionario vacío.
	 */
	public Diccionario_con_abb() {
		dic = new BinarySearchTree<EntradaComparable<K,PositionList<Entry<K,V>>>>();
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		EntradaComparable<K, PositionList<Entry<K,V>>> entrada_lista;
		BSTNode<EntradaComparable<K, PositionList<Entry<K, V>>>> node;
		PositionList<Entry<K,V>> entradas;
		Entry<K,V> to_return = null;
		
		//Valida la clave
		checkKey(key);
		
		//Busca el nodo que contiene la lista de entradas asociada a la clave
		entrada_lista = new EntradaComparable<K, PositionList<Entry<K,V>>>(key, null);
		node = dic.buscar(entrada_lista);
		
		try {
			//Verifica que la clave esté en el diccionario y recupera la lista de entradas
			if (node.element() != null) {
				entradas = node.element().getValue();
				to_return = entradas.isEmpty() ? null : entradas.first().element();
			}
		} catch (EmptyListException e) {e.printStackTrace();}
		
		return to_return;
	}

	/**
	 * Método auxiliar para validar claves.
	 * @param key Clave a validar.
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida. ");
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		EntradaComparable<K, PositionList<Entry<K,V>>> entrada_lista;
		BSTNode<EntradaComparable<K, PositionList<Entry<K, V>>>> node;
		PositionList<Entry<K, V>> to_return;
		
		//Valida la clave
		checkKey(key);
		
		//Busca el nodo que contiene la lista de entradas asociada a la clave
		entrada_lista = new EntradaComparable<K, PositionList<Entry<K,V>>>(key, null);
		node = dic.buscar(entrada_lista);
		
		//Si la clave existe en el diccionario se retorna la lista asociada.
		if (node.element() != null)
			to_return = node.element().getValue();
		//Sino, se retorna una lista vacía.
		else
			to_return = new DoubleLinkedList<Entry<K,V>>();
		
		return to_return;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		BSTNode<EntradaComparable<K, PositionList<Entry<K, V>>>> node;
		EntradaComparable<K, PositionList<Entry<K, V>>> entrada;
		Entry<K,V> to_return;
		
		//Valida la clave
		checkKey(key);
		
		//Busca el nodo donde debería estar la lista de entradas
		entrada = new EntradaComparable<K, PositionList<Entry<K,V>>>(key, null);
		node = dic.buscar(entrada);
		
		//Crea la nueva entrada
		to_return = new EntradaComparable<K, V>(key, value);
		
		//Si la clave ya existía en el diccionario, se añade la entrada nueva.
		if (node.element() != null)
			node.element().getValue().addLast(to_return);
		//Sino se crea el registro.
		else {
			dic.expandir(node, entrada);
			node.element().setValue(new DoubleLinkedList<Entry<K,V>>());
			node.element().getValue().addLast(to_return);
		}
		size++;
		return to_return;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		BSTNode<EntradaComparable<K, PositionList<Entry<K, V>>>> node;
		EntradaComparable<K, PositionList<Entry<K, V>>> entrada;
		PositionList<Entry<K,V>> list;
		Position<Entry<K,V>> pointer;
		
		checkEntry(e);
		entrada = new EntradaComparable<K, PositionList<Entry<K,V>>>(e.getKey(), null);
		node = dic.buscar(entrada);
		
		
		try {
			//La entrada solo puede estar en el diccionario si su nodo no es dummy
			if (node.element() != null) {
				list = node.element().getValue();
				
				//Busca su posición de lista
				pointer = list.isEmpty() ? null : list.first();
				while (pointer != null && pointer.element() != e)
					pointer = list.last() == pointer ? null : list.next(pointer);
				
				if (pointer == null)
					throw new InvalidEntryException("La entrada no está en el diccionario. ");
				
				//Remueve la entrada
				list.remove(pointer);
				size--;
				
				//Si la lista se vació, no hay más entradas asociadas a la clave -> se elimina el registro
				if (list.isEmpty())
					dic.eliminar(entrada);
			} else {
				throw new InvalidEntryException("La entrada no está en el diccionario. ");
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {e1.printStackTrace();}
		
		return e;
	}

	/**
	 * Método auxiliar para validar una entrada.
	 * @param e Entrada a validar.
	 * @throws InvalidEntryException si la entrada es inválida.
	 */
	private void checkEntry(Entry<K, V> e) throws InvalidEntryException {
		if (e == null)
			throw new InvalidEntryException("Entrada inválida. ");
		if (e.getKey() == null)
			throw new InvalidEntryException("Entrada con clave inválida. ");
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		BSTNode<EntradaComparable<K, PositionList<Entry<K, V>>>> node;
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		
		node = dic.raiz();
		if (node.element() != null)
			inorder(node, iterable);
		
		return iterable;
	}

	private void inorder(BSTNode<EntradaComparable<K, PositionList<Entry<K, V>>>> node,
			PositionList<Entry<K, V>> iterable) {
		
		if (node.leftChild().element() != null)
			inorder(node.leftChild(), iterable);
		
		for (Entry<K,V> e : node.element().getValue())
			iterable.addLast(e);
		
		if (node.rightChild().element() != null)
			inorder(node.rightChild(), iterable);
	}

}
