package TDAGrafo;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class GrafoD_lista_adyacencia<V, E> implements GraphD<V, E> {
	
	protected PositionList<Vertice<V, E>> vertices;
	protected PositionList<Arco<V,E>> edges;
	
	/**
	 * Crea un grafo no dirigido vacío.
	 */
	public GrafoD_lista_adyacencia() {
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
		
		return incidentEdges;
	}
	
	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> succesor = new DoubleLinkedList<Edge<E>>();
		Vertice<V, E> vv = checkVertex(v);
		
		for (Arco<V, E> e : vv.getIncidentEdges())
			if (e.getV1() == vv)
				succesor.addLast(e);
		
		return succesor;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		checkVertex(v);
		Vertex<V> toReturn;
		Arco<V, E> arco = checkEdge(e);
		
		if (arco.getV1() == v)
			toReturn = arco.getV2();
		else
			throw new InvalidVertexException("El vértice no es el origen del arco. ");
		
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
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
		
		for (Arco<V, E> e : vv.getIncidentEdges())
			if (e.getV1() == ww && e.getV2() == vv) {
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
		Vertice<V, E> opposite, toRemove = checkVertex(v);
		PositionList<Arco<V, E>> incident;
		TDALista.Position<Arco<V, E>> pointer;
		
		try {
			
			//Eliminar todos los arcos adyacentes
			for (Arco<V, E> e : toRemove.getIncidentEdges()) {
				
				//Identifica el vértice opuesto
//				aux = (Vertice<V, E>) e.getV1();
//				if (aux == toRemove)
//					aux = (Vertice<V, E>) e.getV2();
				if (e.getV1() == toRemove)
					opposite = (Vertice<V, E>) e.getV2();
				else if (e.getV2() == toRemove)
					opposite = (Vertice<V, E>) e.getV1();
				else
					throw new InvalidVertexException("Grafo corrupto. ");
				/*
				 * Considerar modificar identificar vértice opuesto y opposite con algo similar a la implementación de
				 * opposite en Grafo_matriz_adyacencias
				 */
				
				incident = opposite.getIncidentEdges();
				
				//Recupera la posicion de lista del arco en la lista de incidentes del vertice opuesto a toRemove
				/*
				 * Si todas las operaciones funcionan correctamente, necesariamente el opposite está en incident.
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

}
