package promocion_practica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import TDAGrafo.Edge;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import promocion_ejercicios.Pair;

public class Dijkstra {
	
	public static <V> Pair<Map<Vertex<V>, Float>, Map<Vertex<V>, Vertex<V>>> execute(GraphD<V, Float> g, Vertex<V> a) {
		Pair<Map<Vertex<V>, Float>, Map<Vertex<V>, Vertex<V>>> solution = new Pair<>();
		Map<Vertex<V>, Float> d = new HashMap<>();
		Map<Vertex<V>, Vertex<V>> p = new HashMap<>();
		Set<Vertex<V>> s = new HashSet<>();
		Vertex<V> u, v;
		float dist;
		int n = 0;
//		index(g);
		
		for (Vertex<V> w : g.vertices()) {
			d.put(w, Float.POSITIVE_INFINITY);
			p.put(w, null);
			n++; //Determina la cant de vértices del graph
		}
		
		d.put(a, 0f);
		
		try {
			for (int i = 0; i < n; i++) {
				u = nextVertex(g, s, d);
				s.add(u);
				
				for (Edge<Float> e : g.succesorEdges(u)) {
					v = g.opposite(u, e);
					if (!s.contains(v)) {
						dist = d.get(u) + e.element();
						if (dist < d.get(v)) {
							d.put(v, dist);
							p.put(v, u);
						}
					}
				}
				
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		
		solution.setA(d);
		solution.setB(p);
		return null;
	}

	/**
	 * Retorna un vértice v de g con distancia mínima en d y que no está en s.
	 * @param <V>
	 * @param g
	 * @param s
	 * @param d
	 * @return
	 */
	private static <V> Vertex<V> nextVertex(GraphD<V, Float> g, Set<Vertex<V>> s, Map<Vertex<V>, Float> d) {
		// TODO Auto-generated method stub
		return null;
	}

}
