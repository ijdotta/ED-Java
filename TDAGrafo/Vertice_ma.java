package TDAGrafo;

import java.util.HashMap;

import TDALista.Position;

/**
 * Class Vertice_ma - Modela un vértice de grafo con matriz de adyacencias.
 * @author Ignacio Joaquín Dotta
 *
 * @param <V> Tipo de dato del rótulo del vértice.
 * @param <E> Tipo de dato del rótulo del arco incidente.
 */
public class Vertice_ma<V, E> extends HashMap<Object, Object> implements Vertex<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected V label;
	protected TDALista.Position<Vertice_ma<V, E>> listPosition;
	protected int matrixIndex;
	
	/**
	 * Crea un vértice con el rótulo y el índice correspondiente a la matriz de adyacencias del grafo recibidos. 
	 * @param l Rótulo del vértice.
	 * @param index Índice correspondiente al vértice en la matriz de adyacencias del grafo.
	 */
	public Vertice_ma(V l, int index) {
		label = l;
		matrixIndex = index;
	}
	
	/**
	 * Actualiza el rótulo de un vértice.
	 * @param l Rótulo nuevo.
	 */
	public void setElement(V l) {
		label = l;
	}

	/**
	 * @param position the listPosition to set
	 */
	public void setListPosition(Position<Vertice_ma<V, E>> position) {
		this.listPosition = position;
	}

	/**
	 * @param matrixIndex the matrixIndex to set
	 */
	public void setMatrixIndex(int matrixIndex) {
		this.matrixIndex = matrixIndex;
	}

	@Override
	public V element() {
		return label;
	}

	/**
	 * @return the listPosition
	 */
	public Position<Vertice_ma<V, E>> getListPosition() {
		return listPosition;
	}

	/**
	 * @return the matrixIndex
	 */
	public int getMatrixIndex() {
		return matrixIndex;
	}
	
	
	
}
