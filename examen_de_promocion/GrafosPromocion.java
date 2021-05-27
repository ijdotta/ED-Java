package examen_de_promocion;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.Graph;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.MapOpenHash;

public class GrafosPromocion {
	
	/**
	 * Se asume que si el grafo g no es conexo, entonces el BFS y el mapeo se limitan a la componente
	 * conexa que contiene al vértice con rótulo x, si es que existe tal vértice.
	 * @param <V> Tipo de dato del rótulo de los vértices.
	 * @param <E> Tipo de dato del rótulo de los arcos.
	 * @param g Grafo de trabajo.
	 * @param x Rótulo del vértice fuente.
	 * @return Mapeo con la cantidad de vértices adyacentes para cada vértice.
	 * @throws Exception si no existe vértice con rótulo x en el grafo g o si g es null.
	 */
	public static <V, E> Map<Vertex<V>, Integer> solucion(Graph<V, E> g, V x) throws Exception {
		Map<Vertex<V>, Integer> m = new MapOpenHash<Vertex<V>, Integer>();
		Vertex<V> s = null;
		
		if (g == null)
			throw new Exception("Grafo G inválido. ");
		
		//Hallar vértice correspondiente a x:
		for (Vertex<V> v : g.vertices())
			if (v.element() == x) {
				s = v;
				break;
			}
		
		if (s == null)
			throw new Exception("No se encontró un vértice con rótulo X en el grafo G. ");
		
		bfs(g, s, m);
		
		return m;
	}

	private static <V, E> void bfs(Graph<V, E> g, Vertex<V> s, Map<Vertex<V>, Integer> m) {
		Queue<Vertex<V>> q = new LinkedQueue<Vertex<V>>();
		Object estado = new Object(), visitado = new Object();
		Vertex<V> v, w;
		
		for (Vertex<V> x : g.vertices())
			x.put(estado, null);
		
		try {
			s.put(estado, visitado);
			q.enqueue(s);
			
			while (!q.isEmpty()) {
				v = q.dequeue();
				m.put(v, 0); //Permite evitar el caso null dentro del for.
				
				//System.out.println(v.element() + " v.hashCode() " + v.hashCode());
				/*
				 * Comentario: creo que el mapeo no funciona bien porque por alguna razón
				 * el hashCode de todos los vértices termina siendo el mismo.
				 */
				
				for (Edge<E> e : g.incidentEdges(v)) {
					w = g.opposite(v, e);
					
					if (v != w) //Evita contar como adyacente al mismo vértice si hubiera arcos circulares.
						m.put(v, m.get(v)+1);
					
					if (w.get(estado) == null) {
						w.put(estado, visitado);
						q.enqueue(w);
					}
					
				}
			}
			
			
		} catch (InvalidKeyException | EmptyQueueException | InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		
	}


}
