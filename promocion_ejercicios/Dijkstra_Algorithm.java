package promocion_ejercicios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;

public class Dijkstra_Algorithm {
	
	protected static final float INFINITY = Float.POSITIVE_INFINITY;
	
	public static <V> Pair<Map<Vertex<V>, Float>, Map<Vertex<V>, Vertex<V>>>
	dijkstra(GraphD<V, Float> g, Vertex<V> a) {
		Pair<Map<Vertex<V>, Float>, Map<Vertex<V>, Vertex<V>>> pair = new Pair<>();
		Map<Vertex<V>, Float> D = new HashMap<>();
		Map<Vertex<V>, Vertex<V>> P = new HashMap<>();
		Set<Vertex<V>> S = new HashSet<>();
		Vertex<V> u, w;
		int n; 
		float weigth;
		
		for (Vertex<V> v : g.vertices()) {
			D.put(v, INFINITY);
			P.put(v, null);
		}
		
		D.put(a, 0f);
		n = D.size();
		try {
			for (int i = 0; i < n; i++) {
				u = min(g, S, D);
				S.add(u);
				for (Edge<Float> e : g.succesorEdges(u)) {
					w = g.opposite(u, e);
					if (!S.contains(w)) {
						weigth = D.get(u) + e.element();
						if (weigth < D.get(w)) {
							D.put(w, weigth);
							P.put(w, u);
						}
					}
				}
			}
			
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
		
		pair.setA(D);
		pair.setB(P);
		
		return pair;
	}

	private static <V> Vertex<V> min(GraphD<V, Float> g, Set<Vertex<V>> S, Map<Vertex<V>, Float> D) {
		Vertex<V> v = null, w;
		float min = -1, dist;
		
		for (Entry<Vertex<V>, Float> e : D.entrySet()) {
			w = e.getKey();
			if (!S.contains(w)) {
				if (v == null) {
					v = w;
					min = D.get(w);
				}
				else {
					dist = D.get(w);
					if (dist < min) {
						dist = min;
						v = w;
					}
				}
			}
		}
		
		return v;
	}
	
	public static <V> Queue<Vertex<V>> recuperar(Map<Vertex<V>, Vertex<V>> P, Vertex<V> destino, Vertex<V> a) {
		Queue<Vertex<V>> q = new LinkedQueue<>();
//		q.enqueue(a);
//		recuperar_aux(P, destino, q);
		
		while (a != null) {
			q.enqueue(a);
			a = P.get(a);
		}
		
		return q;
	}

	private static <V> void recuperar_aux(Map<Vertex<V>, Vertex<V>> p, Vertex<V> destino, Queue<Vertex<V>> q) {
		Vertex<V> prev = p.get(destino);
		if (prev != null) {
			recuperar_aux(p, prev, q);
			q.enqueue(prev);
		}
			
	}

}
