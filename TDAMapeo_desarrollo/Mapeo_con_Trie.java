package TDAMapeo_desarrollo;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Mapeo_con_Trie<E> implements Map<String, E>{
	
	protected TrieNode<E> root;
	protected int size;
	
	public Mapeo_con_Trie() {
		root = new TrieNode<E>();
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
	public E get(String key) throws InvalidKeyException {
		checkKey(key);
		return get_aux(root, key, 0);
	}
	
	private E get_aux(Mapeo_con_Trie<E>.TrieNode<E> node, String key, int i) {
		TrieNode<E> next;
		
		if (i == key.length())
			return node.getImage();
		else {
			next = node.getChild(key.charAt(i));
			if (next == null)
				return null;
			else
				return get_aux(next, key, i+1);
		}
		
	}

	@Override
	public E put(String key, E value) throws InvalidKeyException {
		checkKey(key);
		return put_aux(root, key, 0, value);
	}

	private E put_aux(Mapeo_con_Trie<E>.TrieNode<E> node, String key, int i, E value) {
		TrieNode<E> next;
		E old_value;
		char c;
		
		if (i == key.length()) {
			old_value = node.getImage();
			node.setImage(value);
			return old_value;
		}
		else {
			c = key.charAt(i);
			next = node.getChild(c);
			
			if (next == null)
				next = node.createChild(c);
			/*
			 * En realidad, una vez que detecté que no existe un hijo para un caracter dado, necesariamente
			 * no existe ningun otro de los caracteres de la clave siguientes, por lo que en lugar de hacer una
			 * llamada recursiva a métodos que retornarán null recursivamente, podría invocar un método auxiliar,
			 * como construir_camino_restante, y retornar null desde acá.
			 */
			
			return put_aux(next, key, i+1, value);
		}
		
	}

	@Override
	public E remove(String key) throws InvalidKeyException {
		checkKey(key);
		return remove_aux(root, key, 0);
	}

	private E remove_aux(Mapeo_con_Trie<E>.TrieNode<E> node, String key, int i) {
		TrieNode<E> next;
		char c;
		E toReturn;
		
		if (i == key.length()) {
			toReturn = node.getImage();
			node.setImage(null);
		}
		else {
			c = key.charAt(i);
			next = node.getChild(c);
			
			if (next == null)
				toReturn = null;
			else
				toReturn = remove_aux(next, key, i+1);
		}
		
		if (node != root && esHoja(node))
			node.getParent().removeChild(node);
		
		return toReturn;
	}
		

	private boolean esHoja(Mapeo_con_Trie<E>.TrieNode<E> node) {
		boolean hoja = true;
		
		for (char c = 'a'; c <= 'z' && hoja; c++)
			hoja = node.getChild(c) == null;
			
		return hoja;
	}

	@Override
	public Iterable<String> keys() {
		//Recorrer recursivamente todos los nodos, concatenando los valores.
		//cuando se encuentra un nodo con imagen no null, agregar al iterable y seguir buscando.
		PositionList<String> iterable = new DoubleLinkedList<>();
		
		keys_aux(root, "", iterable);
		
		return iterable;
	}

	private void keys_aux(Mapeo_con_Trie<E>.TrieNode<E> node, String s, PositionList<String> iterable) {
		TrieNode<E> child;
		
		if (node.getImage() != null)
			iterable.addLast(s);
		
		for (char c = 'a'; c <= 'z'; c++) {
			child = node.getChild(c);
			if (child != null)
				keys_aux(child, s+c, iterable);
		}
		
	}

	@Override
	public Iterable<E> values() {
		PositionList<E> iterable = new DoubleLinkedList<>();
		
		values_aux(root, iterable);
		
		return iterable;
	}

	private void values_aux(Mapeo_con_Trie<E>.TrieNode<E> node, PositionList<E> iterable) {
		TrieNode<E> child;
		E image;
		
		if ((image = node.getImage()) != null)
			iterable.addLast(image);
		
		for (char c = 'a'; c <= 'z'; c++)
			if ((child = node.getChild(c)) != null)
				values_aux(child, iterable);
	}

	@Override
	public Iterable<Entry<String, E>> entries() {
		PositionList<Entry<String, E>> iterable = new DoubleLinkedList<>();
		
		entries_aux(root, "", iterable);
		
		return iterable;
	}
	
	private void entries_aux(Mapeo_con_Trie<E>.TrieNode<E> node, String s,
			PositionList<Entry<String, E>> iterable) {
		
		TrieNode<E> child;
		E img;
		
		if ( (img = node.getImage()) != null)
			iterable.addLast(new Entrada<String, E>(s, img));
		
		for (char c = 'a'; c <= 'z'; c++) {
			child = node.getChild(c);
			if (child != null)
				entries_aux(child, s+c, iterable);
		}
		
	}

	private void checkKey(String key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave inválida .");
	}
	
	public String longestWord() {
		return longest(root, "");
	}

	private String longest(Mapeo_con_Trie<E>.TrieNode<E> node, String s) {
		String new_string, longest = s;
		TrieNode<E> child;
		
		for (char c = 'a'; c <= 'z'; c++) {
			child = node.getChild(c);
			if (child != null) {
				new_string = longest(child, s+c);
				if (new_string.length() > longest.length())
					longest = new_string;
			}
		}
		
		return longest;
	}

	protected class TrieNode<T> {
		
		//No es necesario un campo 'character' puesto que se conoce su valor por el padre del nodo actual.
		protected TrieNode<T> parent;
		protected TrieNode<T> [] children; //En general representamos [a, b, c, ..., x, y, z]
		protected T image; //image != null >> nodo terminador de String
		
		public TrieNode(TrieNode<T> p, int childs, T img) {
			parent = p;
			children = (TrieNode<T>[]) new TrieNode[childs];
			image = img;
		}
		
		public TrieNode<T> getChild(int i) {
			return (0 <= i && i < children.length) ? children[i] : null;
		}

		public TrieNode(TrieNode<T> p) {
			this(p, 26, null);
		}
		
		public TrieNode() {
			this(null);
		}

		public TrieNode<T> getParent() {
			return parent;
		}

		public TrieNode<T>[] getChildren() {
			return children;
		}

		public TrieNode<T> getChild(char c) {
			return children[(int) (c - 'a')];
		}

		public T getImage() {
			return image;
		}

		public void setParent(TrieNode<T> parent) {
			this.parent = parent;
		}

		public TrieNode<T> createChild(char c) {
			int index = (int) c-'a';
			children[index] = new TrieNode<T>(this);
			return children[index];
		}
		
		public void removeChild(char c) {
			children[(int) c-'a'] = null;
		}
		
		public void removeChild(TrieNode<E> node) {
			int i = 0;
			while (i < children.length && children[i] != node)
				i++;
			
			if (i < children.length)
				children[i] = null;
		}

		public void setImage(T image) {
			this.image = image;
		}
		
		public void makeEndOfString() {
			image = (T) new Object();
		}
		
		public void removeEndOfString() {
			image = null;
		}
		
	}
}
