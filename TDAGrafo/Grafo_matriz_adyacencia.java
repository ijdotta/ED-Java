package TDAGrafo;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

/**
 * Class Grafo_matriz_adyacencia - Modela un grafo no dirigido mediante una matriz de adyacencias.
 * 
 * @author Ignacio Joaquín Dotta
 *
 * @param <V> Tipo de dato de los rótulos de los vértices.
 * @param <E> Tipo de dato de los rótulos de los arcos.
 */
public class Grafo_matriz_adyacencia<V, E> implements Graph<V, E> {
	
	protected PositionList<Vertice_ma<V, E>> vertices;
	protected PositionList<Arco<V, E>> edges;
	protected Arco<V, E> [][] adj;
	protected final int initialSize = 10;
	
	/**
	 * Crea un grafo vacío.
	 */
	@SuppressWarnings("unchecked")
	public Grafo_matriz_adyacencia() {
		vertices = new DoubleLinkedList<Vertice_ma<V,E>>();
		edges = new DoubleLinkedList<Arco<V,E>>();
		adj = (Arco<V, E> [][]) new Arco[initialSize][initialSize];
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> iterable = new DoubleLinkedList<Vertex<V>>();
		
		for (Vertex<V> v : vertices)
			iterable.addLast(v);
		
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> iterable = new DoubleLinkedList<Edge<E>>();

		for (Edge<E> e : edges)
			iterable.addLast(e);
		
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice_ma<V, E> vv = checkVertex(v);
		PositionList<Edge<E>> iterable = new DoubleLinkedList<Edge<E>>();
		Edge<E> current;
		int i = vv.getMatrixIndex();
		
		for (int j = 0; j < vertices.size(); j++) {
			current = adj[i][j];
			if (current != null)
				iterable.addLast(current);
		}
				
		return iterable;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		checkVertex(v);
		Arco<V, E> arco = checkEdge(e);
		Vertex<V> toReturn;
		
		if (arco.getV1() == v)
			toReturn = arco.getV2();
		else if (arco.getV2() == v)
			toReturn = arco.getV1();
		else
			throw new InvalidEdgeException("Arco y vértice no relacionados. ");
		
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V, E> arco = checkEdge(e);
		Vertex<V> [] toReturn = (Vertex<V> []) new Vertex[2];
		
		toReturn[0] = arco.getV1();
		toReturn[1] = arco.getV2();
		
		return toReturn;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice_ma<V, E> vv, ww;
		vv = checkVertex(v);
		ww = checkVertex(w);
		
		return adj[vv.getMatrixIndex()][ww.getMatrixIndex()] != null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice_ma<V, E> vv = checkVertex(v);
		V toReturn = vv.element();
		vv.setElement(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		//Requiere que la matriz esté compactada.
		Vertice_ma<V, E> new_vertex;
		
		if (vertices.size() == adj.length)
			resize();
		
		//Creación del nuevo vértice
		new_vertex = new Vertice_ma<V, E>(x, vertices.size());
		try {
			vertices.addLast(new_vertex);
			new_vertex.setListPosition(vertices.last());
		} catch (EmptyListException e) {e.printStackTrace();}
		
		return new_vertex;
	}

	/**
	 * Método auxiliar para insertVertex - Redimensiona la matriz para soportar una mayor cantidad de vértices en el grafo.
	 * @see insertVertex
	 */
	private void resize() {
		Arco<V, E> [][] old_adj = adj;
		int new_size = adj.length * 2;
		adj = (Arco<V, E> [][]) new Arco[new_size][new_size];
		
		for (int i = 0; i < old_adj.length; i++)
			for (int j = 0; j < old_adj[i].length; j++) //No es necesario pedir [i].length (suficiente con old_adj.length) dado que old_adj es cuadrada.
				adj[i][j] = old_adj[i][j];
		
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Arco<V, E> new_edge;
		Vertice_ma<V, E> vv, ww;
		int v_index, w_index;
		
		//Validación de vértices
		vv = checkVertex(v);
		ww = checkVertex(w);
		
		//Creación del nuevo arco
		new_edge = new Arco<V, E>(e, v, w);
		try {
			edges.addLast(new_edge);
			new_edge.setListPosition(edges.last());
		} catch (EmptyListException e1) {e1.printStackTrace();}
		
		//Actualización de matriz de adyacencias
		v_index = vv.getMatrixIndex();
		w_index = ww.getMatrixIndex();
		adj[v_index][w_index] = new_edge;
		adj[w_index][v_index] = new_edge;
		
		return new_edge;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		/*
		 * Mi planteo es:
		 * 	Remover directamente cuando se remueve un vértice y dejar el espacio en blaco, NO COMPACTAR
		 * 	La compactación puede efectivisarse en el resize de insert.
		 * 
		 * Nueva obs: para que funcione el método insert, sin tener una variable que indique el índice proximo de matriz
		 * disponible, es necesario compactar luego de cada remove.
		 */
		
		Arco<V, E> current;
		Vertice_ma<V, E> aux, vv = checkVertex(v);
		V toReturn = vv.element();
		int outter, index = vv.getMatrixIndex();
		
		try {
			
			//Eliminar arcos:
			for (int i = 0; i < adj.length; i++) {
				current = adj[index][i];
				if (current != null)
					edges.remove(current.getListPosition());
			}
			
			
			outter = vertices.size()-1;
			//Si se removió un vértice que no es el último, entonces se traslada el extremo a esa posición.
			if (index < outter) {
				
				//Trasladar vértice extremo
				for (int i = 0; i < adj.length; i++) {
					adj[index][i] = adj[outter][i];
					adj[outter][i] = null;
					adj[i][index] = adj[i][outter];
					adj[i][outter] = null;
				}
				
				//Actualizar el índice del vértice trasladado.
				for (Vertice_ma<V, E> vertex : vertices)
					if (vertex.getMatrixIndex() == outter) {
						vertex.setMatrixIndex(index);
						break;
					}
				
			}
			else {
				for (int i = 0; i < adj.length; i++)
					adj[index][i] = adj[i][index] = null;
			}
			
			//Anular vértice
			vv.setElement(null);
			vertices.remove(vv.getListPosition());
			vv.setListPosition(null);
		} catch (InvalidPositionException e) {e.printStackTrace();}
		
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V, E> arco = checkEdge(e);
		Vertice_ma<V, E> v1, v2;
		int index1, index2;
		
		v1 = (Vertice_ma<V, E>) arco.getV1();
		v2 = (Vertice_ma<V, E>) arco.getV2();
		
		index1 = v1.getMatrixIndex();
		index2 = v2.getMatrixIndex();

		adj[index1][index2] = null;
		adj[index2][index1] = null;
		
		try {
			edges.remove(arco.getListPosition());
		} catch (InvalidPositionException e1) {e1.printStackTrace();}
		
		return arco.element();
	}
	
	/**
	 * Método auxiliar para validar un vértice.
	 * @param v Vértice a validar.
	 * @return Vértice casteado a su clase.
	 * @throws InvalidVertexException si el vértice es inválido.
	 */
	@SuppressWarnings("unchecked")
	private Vertice_ma<V, E> checkVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice_ma<V, E> to_return;
		
		if (v == null)
			throw new InvalidVertexException("Vértice inválido. ");
		
		try {
			to_return = (Vertice_ma<V,E>) v;
		} catch (ClassCastException e) {
			throw new InvalidVertexException("Vértice inválido. ");
		}
		
		return to_return;
	}

	/**
	 * Método auxiliar para validar un arco.
	 * @param e Arco a validar.
	 * @return Arco casteado a su clase.
	 * @throws InvalidEdgeException si el arco es inválido.
	 */
	@SuppressWarnings("unchecked")
	private Arco<V, E> checkEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V, E> to_return;
		if (e == null)
			throw new InvalidEdgeException("Arco inválido. ");
		
		try {
			to_return = (Arco<V, E>) e;
		} catch (ClassCastException exc) {
			throw new InvalidEdgeException("Arco inválido. ");
		}
		
		return to_return;
	}

}
