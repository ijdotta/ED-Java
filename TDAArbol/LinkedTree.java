package TDAArbol;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.PositionList;

public class LinkedTree<E> implements Tree<E> {
	
	protected TNode<E> root;
	protected int size;
	
	/**
	 * Crea un árbol vacío.
	 */
	public LinkedTree() {
		root = null;
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
	public Iterator<E> iterator() {
		PositionList<E> list_of_elements = new DoubleLinkedList<E>();
		
		if (root != null)
			preorder(root, list_of_elements);
		
		return list_of_elements.iterator();
	}

	private void preorder(TNode<E> subroot, PositionList<E> list) {
		list.addLast(subroot.element());
		for (TNode<E> child : subroot.getChildren())
			preorder(child, list);
	}

	public void listar(boolean b) {
		if (root != null)
			listar_aux(root, b);
		System.out.println("EOT"); //EOF = End Of Tree
	}

	private void listar_aux(TNode<E> subroot, boolean b) {
		if (b)
			System.out.print(subroot.element().toString() + " ");
		
		for (TNode<E> child : subroot.getChildren())
			listar_aux(child, b);
		
		if (!b)
			System.out.print(subroot.element().toString() + " ");
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list_of_positions = new DoubleLinkedList<Position<E>>();
		
		if (root != null)
			insert_positions(root, list_of_positions);
		
		return list_of_positions;
	}

	private void insert_positions(TNode<E> subpos, PositionList<Position<E>> list) {
		list.addLast(subpos);
		for (TNode<E> pos : subpos.getChildren())
			insert_positions(pos, list);
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> node = checkPosition(v);
		
		E temp = node.element();
		node.setElement(e);
		
		return temp;
	}

	private TNode<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null)
			throw new InvalidPositionException("Posición nula. ");
		else if (v.element() == null)
			throw new InvalidPositionException("Posición elimindada previamente. ");
		
		TNode<E> to_return = null;
		
		try {
			to_return = (TNode<E>) v;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		
		return to_return;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (size == 0)
			throw new EmptyTreeException("root() sobre árbol vacío. ");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNode<E> node = checkPosition(v);
		if (node == root)
			throw new BoundaryViolationException("parent() sobre root. ");
		return node.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNode<E> node = checkPosition(v);
		PositionList<Position<E>> iterable = new DoubleLinkedList<Position<E>>();
		
		for (TNode<E> pos_node : node.getChildren())
			iterable.addLast(pos_node);
			
		return iterable;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return !( checkPosition(v).getChildren().isEmpty() );
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return checkPosition(v).getChildren().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		return checkPosition(v).getParent() == null;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if (root != null)
			throw new InvalidOperationException("createRoot() sobre árbol no vacío. ");
		root = new TNode<E>(e);
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> parent, new_child;
		if (size == 0)
			throw new InvalidPositionException("addFirstChild() sobre árbol vacío. ");
		
		parent = checkPosition(p);
		new_child = new TNode<E>(e, parent);
		parent.getChildren().addFirst(new_child);
		size++;
		return new_child;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> parent, new_child;
		if (size == 0)
			throw new InvalidPositionException("addLastChild() sobre árbol vacío. ");
		
		parent = checkPosition(p);
		new_child = new TNode<E>(e, parent);
		parent.getChildren().addLast(new_child);
		size++;
		return new_child;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		PositionList<TNode<E>> children;
		TNode<E> parent, right_child, new_child;
		TDALista.Position<TNode<E>> current_child_pos;
		
		if (size == 0)
			throw new InvalidPositionException("addBefore() sobre árbol vacío. ");
		
		parent = checkPosition(p);
		right_child = checkPosition(rb);
		children = parent.getChildren();
		new_child = null;
		
		if (children.isEmpty())
			throw new InvalidPositionException("El hermano derecho de referencia no es un hijo de la posición indicada. ");
		
		try {
			current_child_pos = children.first();
			while (current_child_pos != null && !current_child_pos.element().equals(right_child))
				current_child_pos = current_child_pos == children.last()? null : children.next(current_child_pos);
			
			if (current_child_pos == null)
				throw new InvalidPositionException("El hermano derecho de referencia no es un hijo de la posición indicada. ");
			
			new_child = new TNode<E>(e, parent);
			children.addBefore(current_child_pos, new_child);
			
		} catch (EmptyListException | TDALista.InvalidPositionException | TDALista.BoundaryViolationException exc) {exc.printStackTrace();}
		
		size++;
		return new_child;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		PositionList<TNode<E>> children;
		TNode<E> parent, left_child, new_child;
		TDALista.Position<TNode<E>> current_child_pos;
		
		if (size == 0)
			throw new InvalidPositionException("addAfter() sobre árbol vacío. ");
		
		parent = checkPosition(p);
		left_child = checkPosition(lb);
		children = parent.getChildren();
		new_child = null;
		
		if (children.isEmpty())
			throw new InvalidPositionException("El hermano izquierdo de referencia no es un hijo de la posición indicada. ");
		
		try {
			current_child_pos = children.first();
			while (current_child_pos != null && !current_child_pos.element().equals(left_child))
				current_child_pos = current_child_pos == children.last()? null : children.next(current_child_pos);
			
			if (current_child_pos == null)
				throw new InvalidPositionException("El hermano izquierdo de referencia no es un hijo de la posición indicada. ");
			
			new_child = new TNode<E>(e, parent);
			children.addAfter(current_child_pos, new_child);
			
		} catch (EmptyListException | TDALista.InvalidPositionException | TDALista.BoundaryViolationException exc) {exc.printStackTrace();}
		
		size++;
		return new_child;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNode<E> to_remove = checkPosition(p);
		if (!to_remove.getChildren().isEmpty())
			throw new InvalidPositionException("removeExternalNode() sobre nodo interno. ");
		removeNode(to_remove);
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNode<E> to_remove = checkPosition(p);
		if (to_remove.getChildren().isEmpty())
			throw new InvalidPositionException("removeInternalNode() sobre nodo externo. ");
		removeNode(to_remove);
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNode<E> parent, toRemove, child;
		PositionList<TNode<E>> children, siblings;
		TDALista.Position<TNode<E>> listPos_toRemove;
		
		toRemove = checkPosition(p);
		parent = toRemove.getParent();
		children = toRemove.getChildren();
		
		if (size == 0)
			throw new InvalidPositionException("removeNode() sobre árbol vacío. ");
		
		try {
			//Si es la raíz, hay casos especiales en los que efectivamente puede eliminarse.
			if (toRemove == root) {
				if (children.size() == 0)
					root = null;
				else if (children.size() == 1)
					root = children.remove(children.first());
				else
					throw new InvalidPositionException("removeNode() sobre raíz con más de un hijo. ");
			}
			else {
				//Si no es la raíz, tiene padre.
				siblings = parent.getChildren();
				
				//recorrer hermanos hasta hallar la posición de toRemove:
				listPos_toRemove = siblings.isEmpty()? null : siblings.first();
				while (listPos_toRemove != null && listPos_toRemove.element() != toRemove)
					listPos_toRemove = siblings.last() == listPos_toRemove? null : siblings.next(listPos_toRemove);
				
				if (listPos_toRemove == null)
					throw new InvalidPositionException("Árbol corrupto. ");
				
				//Reinsertar hijos de toRemove como hermanos:
				while (!children.isEmpty()) {
					child = children.remove(children.first());
					child.setParent(parent);
					siblings.addBefore(listPos_toRemove, child);
				}
				
				siblings.remove(listPos_toRemove);
			}
			
		} catch (EmptyListException | TDALista.InvalidPositionException | TDALista.BoundaryViolationException e) {e.printStackTrace();}
		
		toRemove.setParent(null);
		toRemove.setElement(null);
		size--;
	}
	
	/**
	 * MAL INTERPRETADA LA CONSIGNA. VER IMPLEMENTACIÓN ADECUADA MÁS ABAJO.
	 */
	public PositionList<Position<E>> extremos_izquierdos_no_hojas() {
		PositionList<Position<E>> list = new DoubleLinkedList<Position<E>>();
		
		if (root != null)
			extremos_izquierdos_no_hojas_aux(root, list);
		
		return list;
	}

	private void extremos_izquierdos_no_hojas_aux(TNode<E> extremo, PositionList<Position<E>> list) {
		PositionList<TNode<E>> hijos = extremo.getChildren();
		try {
			if (!hijos.isEmpty()) {
				list.addLast(extremo);
				extremos_izquierdos_no_hojas_aux(hijos.first().element(), list);
			}
		} catch (EmptyListException e) {e.printStackTrace();}
	}
	
	public void eliminar_hojas() {
		if (root != null)
			eliminar_hojas_aux(root);
	}

	private void eliminar_hojas_aux(TNode<E> current) {
		PositionList<TNode<E>> children = current.getChildren();
		
		try {
			if (children.isEmpty())
				removeNode(current);
			else {
				for (TNode<E> child : children)
					eliminar_hojas_aux(child);
			}
		} catch (InvalidPositionException e) {e.printStackTrace();}
		
	}
	
	public void eliminar_rotulo(E r) {
		if (root != null)
			eliminar_rotulo_aux(root, r);
	}

	private void eliminar_rotulo_aux(TNode<E> current, E r) {
		try {
			for (TNode<E> node : current.getChildren())
				eliminar_rotulo_aux(node, r);
			
			if (current.element().equals(r))
				removeNode(current);
		} catch (InvalidPositionException e) {e.printStackTrace();}
	}
	
	public void eliminar_rotulo_nivel(E r, int n) {
		if (root != null)
			eliminar_rotulo_nivel_aux(root, r, n);
	}

	private void eliminar_rotulo_nivel_aux(TNode<E> current, E r, int n) {
		try {	
			if (n == 0) {
				if (current.element().equals(r))
					removeNode(current);
			}
			else
				for (TNode<E> child : current.getChildren())
					eliminar_rotulo_nivel_aux(child, r, n-1);
			
		} catch (InvalidPositionException e) {e.printStackTrace();}
	}
	
	public void espejar() {
		espejar_aux(root);
	}

	private void espejar_aux(TNode<E> current) {
		PositionList<TNode<E>> children;
		if (current != null) {
			children = current.getChildren();
			if (!children.isEmpty()) {
				espejar_hijos(children);
				for (TNode<E> child : children)
					espejar_aux(child);
			}
		}
	}

	private void espejar_hijos(PositionList<TNode<E>> children) {
		TDALista.Position<TNode<E>> ultimo_insertado;
			
		try {
			children.addLast(children.remove(children.first()));
			ultimo_insertado = children.last();
			
			//Si entra en este bucle, entonces hay más de un hijo
			while (children.first() != ultimo_insertado) {
				children.addBefore(ultimo_insertado, children.remove(children.first()));
				//Como el bucle se detiene cuando ultimo_insertado es igual al primero, nunca produce excepción.
				ultimo_insertado = children.prev(ultimo_insertado);
			}
			
		} catch (EmptyListException | TDALista.InvalidPositionException | TDALista.BoundaryViolationException e) {e.printStackTrace();}
	}
	
	public PositionList<Position<E>> left_nodes_not_leaves() {
		PositionList<Position<E>> toReturn = new DoubleLinkedList<Position<E>>();

		if (root != null) 
			extremos(root, toReturn);

		return toReturn;
	}

	private void extremos(TNode<E> node, PositionList<Position<E>> list) {
		PositionList<TNode<E>> children = node.getChildren();
		TNode<E> hijo_izq;
		
		try {
			if (!children.isEmpty()) {
				
				hijo_izq = children.first().element();
				if (!hijo_izq.getChildren().isEmpty())
					list.addFirst(hijo_izq);
				
				for (TNode<E> n : children)
					extremos(n, list);
			}
			
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		
	}
	
}
