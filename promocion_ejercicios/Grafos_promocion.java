package promocion_ejercicios;

import java.util.Iterator;

import TDAGrafo.Edge;
import TDAGrafo.Graph;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;

public class Grafos_promocion {
	
	/*
	 * ******************************+-----------------------+**************************************
	 * ******************************|Ejercicios de promoción|**************************************
	 * ******************************+-----------------------+**************************************
	 */
	
	public static <V, E> boolean esConexo(Graph<V, E> g) {
		boolean conexo = true;
		Iterator<Vertex<V>> vertices = g.vertices().iterator();
		Object estado, visitado;
		
		estado = new Object();
		visitado = new Object();
		
		//Recorrer componente conexa desde un vértice.
		if (vertices.hasNext())
			recorrer_conexo(g, vertices.next(), estado, visitado);
		
		//Si g es conexo -> todos los vértices deberían haber sido visitados.
		while (vertices.hasNext() && conexo)
			conexo = vertices.next().get(estado) == visitado;
		
		//Limpieza
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
		
		return conexo;
	}

	private static <V, E> void recorrer_conexo(Graph<V, E> g, Vertex<V> v, Object estado, Object visitado) {
		Vertex<V> w;
		
		try {
			v.put(estado, visitado);
			for (Edge<E> e : g.incidentEdges(v)) {
				w = g.opposite(v, e);
				if (w.get(estado) == null)
					recorrer_conexo(g, w, estado, visitado);
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		
	}
	
	public static <V, E> void componentesConexas(Graph<V, E> g) {
		Object estado, visitado;
		int comp = 0;
		
		estado = new Object();
		visitado = new Object();
		
		for (Vertex<V> v : g.vertices())
			if (v.get(estado) == null) {
				System.out.println("Componente conexa " + (comp++) + ": " );
				mostrarConexas(g, v, estado, visitado);
				System.out.println();
			}
		
		//Limpieza
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
	}

	private static <V, E> void mostrarConexas(Graph<V, E> g, Vertex<V> v, Object estado, Object visitado) {
		Vertex<V> w;
		
		v.put(estado, visitado);
		System.out.print(v.element() + " - ");
		
		try {
			for (Edge<E> e : g.incidentEdges(v)) {
				w = g.opposite(v, e);
				if (w.get(estado) == null) {
					System.out.print("[" + e.element() + "] - ");
					mostrarConexas(g, w, estado, visitado);
				}
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Elimina el vértice v y controla si varió la cantidad de componentes conexas.
	 * @param <V>
	 * @param <E>
	 * @param g
	 * @param e
	 */
	public static <V, E> void eliminar(Graph<V, E> g, Vertex<V> v) {
		int comp_conexas_previo_eliminar = cantidadComponentesConexas(g);
		
		try {
			g.removeVertex(v);
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
		if (comp_conexas_previo_eliminar == cantidadComponentesConexas(g))
			System.out.println("La cantidad de componentes conexas NO varió. ");
		else
			System.out.println("La cantidad de componentes conexas SI varió. ");
	}

	private static <V, E> int cantidadComponentesConexas(Graph<V, E> g) {
		int cant = 0;
		Object estado, visitado;
		
		estado = new Object();
		visitado = new Object();
		
		for (Vertex<V> v : g.vertices())
			if (v.get(estado) == null) {
				cant++;
				recorrer_conexo(g, v, estado, visitado);
			}
		
		//Limpieza
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
		
		return cant;
	}

}
