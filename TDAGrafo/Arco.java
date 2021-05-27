package TDAGrafo;

public class Arco<V, E> implements Edge<E> {
	
	protected E label;
	protected Vertex<V> v1;
	protected Vertex<V> v2;
	protected TDALista.Position<Arco<V,E>> listPosition;
	
	/**
	 * Crea un arco con el r�tulo recibido e incidencia el los v�rtices recibidos.
	 * @param l R�tulo del arco.
	 * @param v V�rtice extremo 1.
	 * @param w V�rtice extremo 2.
	 * @param p Posici�n del arco en la lista de arcos del grafo al que pertenece.
	 */
	public Arco(E l, Vertex<V> v, Vertex<V> w) {
		label = l;
		v1 = v;
		v2 = w;
	}

	@Override
	public E element() {
		return label;
	}

	/**
	 * @return the v1
	 */
	public Vertex<V> getV1() {
		return v1;
	}

	/**
	 * @return the v2
	 */
	public Vertex<V> getV2() {
		return v2;
	}

	/**
	 * @return the listPosition
	 */
	public TDALista.Position<Arco<V, E>> getListPosition() {
		return listPosition;
	}

	/**
	 * @param listPosition the listPosition to set
	 */
	public void setListPosition(TDALista.Position<Arco<V, E>> listPosition) {
		this.listPosition = listPosition;
	}

}
