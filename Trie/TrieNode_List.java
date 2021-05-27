package Trie;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.Position;
import TDALista.PositionList;

public class TrieNode_List<E> {
	
	private char character;
	protected TrieNode_List<E> parent;
	protected PositionList<TrieNode_List<E>> children;
	protected E image;
	
	public TrieNode_List(TrieNode_List<E> p, E img, char c) {
		character = c;
		parent = p;
		children = new DoubleLinkedList<TrieNode_List<E>>();
		image = img;
	}
	
	public TrieNode_List() {
		this(null, null, (char) Character.NON_SPACING_MARK);
	}

	public char character() {
		return character;
	}
	
	public TrieNode_List<E> getParent() {
		return parent;
	}

	public E getImage() {
		return image;
	}

	public void setImage(E image) {
		this.image = image;
	}
	
	public Position<TrieNode_List<E>> createChild(char c) {
		Position<TrieNode_List<E>> toReturn = null;
		
		try {
			
			children.addLast(new TrieNode_List<E>(this, null, c));
			toReturn = children.last();
			
		} catch (EmptyListException e) {e.printStackTrace();}
		
		return toReturn;
	}
	
	public void makeEndOfString() {
		image = (E) new Object();
	}
	
	public void removeEndOfString() {
		image = null;
	}

}
