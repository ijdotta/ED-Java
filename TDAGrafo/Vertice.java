package TDAGrafo;

import java.util.HashMap;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Vertice<V, E> extends HashMap<Object, Object> implements Vertex<V> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected V label;
	protected PositionList<Arco<V,E>> incidentEdges;
	protected TDALista.Position<Vertice<V, E>> listPosition;
	
	/**
	 * Crea un vértice con el rótulo recibido.
	 * @param l Rótulo del vértice.
	 */
	public Vertice(V l) {
		label = l;
		incidentEdges = new DoubleLinkedList<Arco<V,E>>();
	}

	@Override
	public V element() {
		return label;
	}

	/**
	 * @return the incidentEdges
	 */
	public PositionList<Arco<V,E>> getIncidentEdges() {
		return incidentEdges;
	}

	/**
	 * @return the listPosition
	 */
	public TDALista.Position<Vertice<V, E>> getListPosition() {
		return listPosition;
	}
	
	public void setElement(V l) {
		label = l;
	}

	/**
	 * @param listPosition the listPosition to set
	 */
	public void setListPosition(TDALista.Position<Vertice<V, E>> listPosition) {
		this.listPosition = listPosition;
	}
	
}
