package TDAGrafo;

import java.util.HashMap;

import TDALista.Position;

/**
 * Class Vertice_ma - Modela un v�rtice de grafo con matriz de adyacencias.
 * @author Ignacio Joaqu�n Dotta
 *
 * @param <V> Tipo de dato del r�tulo del v�rtice.
 * @param <E> Tipo de dato del r�tulo del arco incidente.
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
	 * Crea un v�rtice con el r�tulo y el �ndice correspondiente a la matriz de adyacencias del grafo recibidos. 
	 * @param l R�tulo del v�rtice.
	 * @param index �ndice correspondiente al v�rtice en la matriz de adyacencias del grafo.
	 */
	public Vertice_ma(V l, int index) {
		label = l;
		matrixIndex = index;
	}
	
	/**
	 * Actualiza el r�tulo de un v�rtice.
	 * @param l R�tulo nuevo.
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
