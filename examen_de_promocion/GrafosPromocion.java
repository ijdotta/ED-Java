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
	 * conexa que contiene al v�rtice con r�tulo x, si es que existe tal v�rtice.
	 * @param <V> Tipo de dato del r�tulo de los v�rtices.
	 * @param <E> Tipo de dato del r�tulo de los arcos.
	 * @param g Grafo de trabajo.
	 * @param x R�tulo del v�rtice fuente.
	 * @return Mapeo con la cantidad de v�rtices adyacentes para cada v�rtice.
	 * @throws Exception si no existe v�rtice con r�tulo x en el grafo g o si g es null.
	 */
	public static <V, E> Map<Vertex<V>, Integer> solucion(Graph<V, E> g, V x) throws Exception {
		Map<Vertex<V>, Integer> m = new MapOpenHash<Vertex<V>, Integer>();
		Vertex<V> s = null;
		
		if (g == null)
			throw new Exception("Grafo G inv�lido. ");
		
		//Hallar v�rtice correspondiente a x:
		for (Vertex<V> v : g.vertices())
			if (v.element() == x) {
				s = v;
				break;
			}
		
		if (s == null)
			throw new Exception("No se encontr� un v�rtice con r�tulo X en el grafo G. ");
		
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
				 * Comentario: creo que el mapeo no funciona bien porque por alguna raz�n
				 * el hashCode de todos los v�rtices termina siendo el mismo.
				 */
				
				for (Edge<E> e : g.incidentEdges(v)) {
					w = g.opposite(v, e);
					
					if (v != w) //Evita contar como adyacente al mismo v�rtice si hubiera arcos circulares.
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
