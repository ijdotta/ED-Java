package TDAGrafo;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class Grafo_lista_adyacencia<V, E> implements Graph<V, E> {
	
	protected PositionList<Vertice<V, E>> vertices;
	protected PositionList<Arco<V,E>> edges;
	
	/**
	 * Crea un grafo no dirigido vacío.
	 */
	public Grafo_lista_adyacencia() {
		vertices = new DoubleLinkedList<Vertice<V, E>>();
		edges = new DoubleLinkedList<Arco<V,E>>();
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
		Vertice<V, E> vertice = checkVertex(v);
		PositionList<Edge<E>> incidentEdges = new DoubleLinkedList<Edge<E>>();
		
		for (Edge<E> e : vertice.getIncidentEdges())
			incidentEdges.addLast(e);
		
		/*
		 * ¿por qué en la teoría propone retornar una lista distinta a la que tiene la instancia de Vertex v?
		 * 1) Al retornar un iterable, el cliente no puede modificarla. POR ELLO ES ABSURDO REINSERTARLOS., PERO:
		 * 2) El cliente si conociera la implementación concreta (DoubleLinkedList) podría forzar un casting y 
		 * 		modificarla.
		 */
		
		return incidentEdges;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V, E> toReturn, vertice = checkVertex(v);
		Arco<V, E> arco = checkEdge(e);
		
		//Asume opuesto a v1
		toReturn = (Vertice<V, E>) arco.getV1();
		
		/*
		 * Posiblemente no sea necesario castear v : Vertex a vertice : Vertice
		 */
		
		//Control y reasignación
		if (toReturn == vertice)
			toReturn = (Vertice<V, E>) arco.getV2();
		
		/*
		 * VER excepcion por arco-vertice no relacionado
		 */
		
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		Vertex<V> [] endVertices = (Vertex<V>[]) new Vertex[2];
		
		endVertices[0] = arco.getV1();
		endVertices[1] = arco.getV2();
		
		return endVertices;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V, E> vv, ww;
		boolean adjacent = false;
		
		vv = checkVertex(v);
		ww = checkVertex(w);
		
		//Busca algún arco en vv cuyo extremo opuesto sea ww
		for (Arco<V, E> e : vv.getIncidentEdges())
			if /*(e.getV1() == ww || e.getV2() == ww)*/
				((e.getV1() == ww && e.getV2() == vv) || (e.getV1() == vv && e.getV2() == ww)) {
				adjacent = true;
				break;
			}
		
		return adjacent;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V, E> vertice = checkVertex(v);
		V toReturn = vertice.element();
		vertice.setElement(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V, E> new_vertex = new Vertice<V, E>(x);
		vertices.addLast(new_vertex);
		
		try {
			new_vertex.setListPosition(vertices.last());
		} catch (EmptyListException e) {e.printStackTrace();}
		
		return new_vertex;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V, E> vv = checkVertex(v), ww = checkVertex(w);
		Arco<V, E> new_edge = new Arco<V, E>(e, vv, ww);
		
		//Agregar a la lista de arcos
		edges.addLast(new_edge);
		try {
			new_edge.setListPosition(edges.last());
		} catch (EmptyListException e1) {e1.printStackTrace();}
		
		//Agregar a las listas de incidentes.
		vv.getIncidentEdges().addLast(new_edge);
		ww.getIncidentEdges().addLast(new_edge);
		
		return new_edge;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V, E> aux, toRemove = checkVertex(v);
		PositionList<Arco<V, E>> incident;
		TDALista.Position<Arco<V, E>> pointer;
		
		try {
			
			//Eliminar todos los arcos adyacentes
			for (Arco<V, E> e : toRemove.getIncidentEdges()) {
				
				//Identifica el vértice opuesto
				aux = (Vertice<V, E>) e.getV1();
				if (aux == toRemove)
					aux = (Vertice<V, E>) e.getV2();
				
				/*
				 * Considerar modificar identificar vértice opuesto y opposite con algo similar a la implementación de
				 * opposite en Grafo_matriz_adyacencias
				 */
				
				incident = aux.getIncidentEdges();
				
				//Recupera la posicion de lista del arco en la lista de incidentes del vertice opuesto a toRemove
				/*
				 * Si todas las operaciones funcionan correctamente, necesariamente el aux está en incident.
				 */
				pointer = incident.first();
				while (pointer.element() != e)
					pointer = incident.next(pointer);
				
				incident.remove(pointer);
				edges.remove(e.getListPosition());
				//No se remueve en la lista del arco toRemove; queda como basura no referenciada.
			}
			
			//Eliminar el vertice
			vertices.remove(toRemove.getListPosition());
			
		} catch (InvalidPositionException | EmptyListException | BoundaryViolationException e) {e.printStackTrace();}
		
		return toRemove.element();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V, E> toRemove = checkEdge(e);
		Vertice<V, E> v1, v2;
		PositionList<Arco<V, E>> incident;
		TDALista.Position<Arco<V, E>> pointer;
		
		v1 = (Vertice<V, E>) toRemove.getV1();
		v2 = (Vertice<V, E>) toRemove.getV2();
		
		try {
			//Eliminar arco en v1:
			incident = v1.getIncidentEdges();
			pointer = incident.first();
			while (pointer.element() != toRemove)
				pointer = incident.next(pointer);
			incident.remove(pointer);
			
			//Eliminar arco en v2:
			incident = v2.getIncidentEdges();
			pointer = incident.first();
			while (pointer.element() != toRemove)
				pointer = incident.next(pointer);
			incident.remove(pointer);
			
			//Elimino arco de edges:
			edges.remove(toRemove.getListPosition());
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e2) {e2.printStackTrace();}
		
		/*
		 * Por la restricción de que cada arco NO PUEDA CONOCER SU POSICIÓN
		 * en la lista de incidentes de los vertices adyacentes, debe recorrerse
		 * por completo la lista de cada vertice.
		 */
		
		return toRemove.element();
	}

	/**
	 * Método auxiliar para validar un vértice.
	 * @param v Vértice a validar.
	 * @return Vértice casteado a su clase.
	 * @throws InvalidVertexException si el vértice es inválido.
	 */
	@SuppressWarnings("unchecked")
	private Vertice<V, E> checkVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V, E> to_return;
		
		if (v == null)
			throw new InvalidVertexException("Vértice inválido. ");
		
		try {
			to_return = (Vertice<V,E>) v;
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
	
	/**
	 * Realiza un recorrido del grafo con el algoritmo Depth-First-Search
	 */
	public void dfs() {
		Object estado, visitado, no_visitado;
		
		//Objetos de registro
		estado = new Object();
		visitado = new Object();
		no_visitado = new Object();
		
		//Precondiciones
		for (Vertice<V, E> v : vertices)
			v.put(estado, no_visitado);
		
		//Recorrido de todos los vértices que aun no se hayan visitado
		for (Vertice<V, E> v : vertices)
			if (v.get(estado) == no_visitado)
				dfs_aux(v, estado, visitado, no_visitado);
		
		//Postcondiciones
		for (Vertice<V, E> v : vertices)
			v.remove(estado);
	}

	/**
	 * Método auxiliar a dfs.
	 * @param v Vértice actual
	 * @param estado Clave del mapeo para averiguar el estado de un vértice
	 * @param visitado Estado visitado de un vértice
	 * @param no_visitado Estado no_visitado de u vértice
	 * @see dfs
	 */
	@SuppressWarnings("unchecked")
	private void dfs_aux(Vertice<V, E> v, Object estado, Object visitado, Object no_visitado) {
		Vertice<V, E> adj_vertex;
		
		System.out.println(v.element());
		v.put(estado, visitado);
		
		for (Arco<V, E> e : v.getIncidentEdges()) {
			//Calcular el vertice adyacente para el arco dado:
			adj_vertex = (Vertice<V, E>) (e.getV1() == v? e.getV2() :  e.getV1());
			if (adj_vertex.get(estado) == no_visitado)
				dfs_aux(adj_vertex, estado, visitado, no_visitado);
		}
		
	}
	
	/**
	 * Realiza un recorrido en anchura implementando el algoritmo
	 * Breadth-First-Search
	 */
	public void bfs() {
		Object estado, visitado, no_visitado;
		
		//Objetos de registro
		estado = new Object();
		visitado = new Object();
		no_visitado = new Object();
		
		//Precondiciones
		for (Vertice<V, E> v : vertices)
			v.put(estado, no_visitado);
		
		//Recorrido de todos los vértices que aun no se hayan visitado
		for (Vertice<V, E> v : vertices)
			if (v.get(estado) == no_visitado)
				bfs_aux(v, estado, visitado, no_visitado);
		
		//Postcondiciones
		for (Vertice<V, E> v : vertices)
			v.remove(estado);
	}

	private void bfs_aux(Vertice<V, E> v, Object estado, Object visitado, Object no_visitado) {
		Queue<Vertice<V, E>> q = new LinkedQueue<Vertice<V, E>>();
		Vertice<V, E> w, adj_vertex;
		
		try {
			q.enqueue(v);
			v.put(estado, visitado);
			
			while (!q.isEmpty()) {
				w = q.dequeue();
				System.out.println(w.element());
				
				for (Arco<V, E> e : v.getIncidentEdges()) {
					//Calcular el vertice adyacente para el arco dado:
					adj_vertex = (Vertice<V, E>) (e.getV1() == v? e.getV2() :  e.getV1());
					if (adj_vertex.get(estado) == no_visitado) {
						q.enqueue(adj_vertex);
						adj_vertex.put(estado, visitado);
					}
				}
			}
			
		} catch (EmptyQueueException e) {e.printStackTrace();}
		
	}
	
	public void eliminar_rotulo(V r) {
		try {
			for (Vertex<V> v : vertices)
				if (v.element().equals(r))
					removeVertex(v);
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

}
