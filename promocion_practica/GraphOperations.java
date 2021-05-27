package promocion_practica;

import java.util.Iterator;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.Graph;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;

public class GraphOperations {
	
	public static <V, E> boolean esConexo(Graph<V, E> g){
		boolean conexo = true;
		Object estado, visitado;
		estado = new Object();
		visitado = new Object();
		Iterator<Vertex<V>> vertices;

		if (g == null)
			return false;
		
		vertices = g.vertices().iterator();
		if (vertices.hasNext())
			bfs(g, vertices.next(), estado, visitado);
		
		while (vertices.hasNext() && conexo)
			conexo = vertices.next().get(estado) == visitado;
		
		//Limpieza:
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
		
		return conexo;
	}

	private static <V, E> void bfs(Graph<V, E> g, Vertex<V> v, Object estado, Object visitado) {
		Queue<Vertex<V>> q = new LinkedQueue<>();
		Vertex<V> w;
		q.enqueue(v);
		v.put(estado, visitado);
		
		try {
			while (!q.isEmpty()) {
				v = q.dequeue();
				for (Edge<E> e : g.incidentEdges(v)) {
					w = g.opposite(v, e);
					if (w.get(estado) != visitado) {
						w.put(estado, visitado);
						q.enqueue(w);
					}
				}
			}
		} catch (EmptyQueueException | InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
	}
}
