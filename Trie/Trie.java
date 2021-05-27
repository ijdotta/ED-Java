package Trie;

public class Trie<E> {
	
	protected TrieNode<E> root;
	
	public Trie() {
		root = new TrieNode<E>();
	}
	
	public void insert(String s) {
		insert_aux(root, s, 0);
	}
	
	private void insert_aux(TrieNode<E> node, String s, int i) {
		TrieNode<E> nextChar;
		char c;
		
		if (i < s.length()) {
			c = s.charAt(i);
			nextChar = node.getChild(c);
			
			if (nextChar == null)
				node.createChild(c);
				
			insert_aux(node.getChild(c), s, i+1);
		}
		else
			node.makeEndOfString();
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
		
		public TrieNode(TrieNode<T> p) {
			this(p, 26, null);
		}
		
		public TrieNode() {
			this(null);
		}

		public TrieNode<T> getParent() {
			return parent;
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
