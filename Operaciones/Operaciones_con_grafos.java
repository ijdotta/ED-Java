package Operaciones;


import java.util.Iterator;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.Grafo_lista_adyacencia;
import TDAGrafo.Graph;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;
import TDAMapeo_PR2.Entry;
import TDAMapeo_PR2.InvalidKeyException;

public class Operaciones_con_grafos {
	
	/**
	 * Recorrido de un grafo con el algoritmo Depth-First-Search
	 * @param <V> Tipo de dato del rótulo de un vértice.
	 * @param <E> Tipo de dato del rótulo de un arco.
	 * @param g Grafo de búsqueda.
	 */
	public static <V, E> void dfs(Graph<V, E> g) {
		Object estado, visitado, no_visitado;
		
		//Objetos de registro
		estado = new Object();
		visitado = new Object();
		no_visitado = new Object();
		
		//Precondiciones
		for (Vertex<V> v : g.vertices())
			v.put(estado, no_visitado);
		
		//Recorrido de todos los vértices que aun no se hayan visitado
		for (Vertex<V> v : g.vertices())
			if (v.get(estado) == no_visitado)
				dfs_aux(g, v, estado, visitado, no_visitado);
		
		//Postcondiciones
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
	}

	/**
	 * Método auxiliar a dfs.
	 * @param <V> Tipo de dato del rótulo de los vértices.
	 * @param <E> Tipo de dato del rótulo de los arcos.
	 * @param g Grafo de búsqueda.
	 * @param v Vértice actual.
	 * @param estado Clave del mapeo de estado de un vértice.
	 * @param visitado Objeto de estado visitado de un vértice.
	 * @param no_visitado Objeto de estado no visitado de un vértice
	 * @see dfs
	 */
	private static <V, E> void dfs_aux(Graph<V, E> g, Vertex<V> v, Object estado, Object visitado, Object no_visitado) {
		Vertex<V> adj;
		System.out.println(v.element());
		v.put(estado, visitado);
		
		try {
			for (Edge<E> e : g.incidentEdges(v)) {
				adj = g.opposite(v, e);
				if (adj.get(estado) == no_visitado)
					dfs_aux(g, adj, estado, visitado, no_visitado);
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Recorrido de un grafo con el algoritmo Breadth-First-Search
	 * @param <V> Tipo de dato del rótulo de un vértice.
	 * @param <E> Tipo de dato del rótulo de un arco.
	 * @param g Grafo de búsqueda.
	 */
	public static <V, E> void bfs(Graph<V, E> g) {
		Object estado, visitado, no_visitado;
		
		//Objetos de registro
		estado = new Object();
		visitado = new Object();
		no_visitado = new Object();
		
		//Precondiciones
		for (Vertex<V> v : g.vertices())
			v.put(estado, no_visitado);
		
		//Recorrido de todos los vértices que aun no se hayan visitado
		for (Vertex<V> v : g.vertices())
			if (v.get(estado) == no_visitado)
				bfs_aux(g, v, estado, visitado, no_visitado);
		
		//Postcondiciones
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
	}

	/**
	 * Método auxiliar a bfs.
	 * @param <V> Tipo de dato del rótulo de los vértices.
	 * @param <E> Tipo de dato del rótulo de los arcos.
	 * @param g Grafo de búsqueda.
	 * @param v Vértice actual.
	 * @param estado Clave del mapeo de estado de un vértice.
	 * @param visitado Objeto de estado visitado de un vértice.
	 * @param no_visitado Objeto de estado no visitado de un vértice
	 * @see bfs
	 */
	private static <V, E> void bfs_aux(Graph<V, E> g, Vertex<V> v, Object estado, Object visitado, Object no_visitado) {
		Queue<Vertex<V>> q = new LinkedQueue<Vertex<V>>();
		Vertex<V> w, x;
		
		try {
			System.out.println(v.element());
			v.put(estado, visitado);
			q.enqueue(v);
			
			while (!q.isEmpty()) {
				w = q.dequeue();
				System.out.println(w.element());
				
				for (Edge<E> e : g.incidentEdges(w)) {
					x = g.opposite(w, e);
					if (x.get(estado) == no_visitado) {
						q.enqueue(x);
						x.put(estado, visitado);
					}
				}
				
			}
			
		} catch (EmptyQueueException | InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
	}
	
	/**
	 * Uso del algoritmo Breadth-First-Search y un mapeo para hallar el camino más corto entre
	 * dos vértices. 
	 * @param <V> Tipo de dato del rótulo de los vértices.
	 * @param <E> Tipo de dato del rótulo de los arcos.
	 * @param g Grafo de búsqueda.
	 * @param a Vértice de origen.
	 * @param b Vértice de destino.
	 * @return Camino más corto entre a y b.
	 */
	public static <V,E> PositionList<Vertex<V>> camino_corto(Graph<V, E> g, Vertex<V> a, Vertex<V> b) {
		PositionList<Vertex<V>> camino = new DoubleLinkedList<Vertex<V>>(); 
		TDAMapeo_PR2.Map<Vertex<V>, Vertex<V>> prev = new TDAMapeo_PR2.OpenHashMap<Vertex<V>, Vertex<V>>(); //No funciona el hash para claves vertex
//		Mapeo_con_lista <Vertex<V>, Vertex<V>> prev = new Mapeo_con_lista<Vertex<V>, Vertex<V>>();
		Queue<Vertex<V>> q = new LinkedQueue<Vertex<V>>();
		Vertex<V> v;
//		Vertex<V> w;
		Object estado, visitado, no_visitado;
		boolean existe_camino = false;
		
		//Objetos de registro
		estado = new Object();
		visitado = new Object();
		no_visitado = new Object();
		
		
		try {
			//Precondiciones:
			for (Vertex<V> x : g.vertices())
				x.put(estado, no_visitado);
			
			//Búsqueda del camino
			q.enqueue(a);
			a.put(estado, visitado);
			
			while (!q.isEmpty() && !existe_camino) {
				v = q.dequeue();
				System.out.println("CURRENT :: " + v.element() + " :: ");
				if (v == b)
					existe_camino = true;
				else {
					for (Edge<E> e : g.incidentEdges(v)) {
						Vertex<V> w = g.opposite(v, e);
						if (w.get(estado) == no_visitado) {
							System.out.print("SOY w >> " + w.element());
							q.enqueue(w);
							w.put(estado, visitado);
							System.out.print(" <<Mapping>> (w,v) ("+w.element()+", "+v.element()+") -- ");
							System.out.println("<<Hashcode>> w :: " + Math.abs(w.hashCode()));
							prev.put(w, v);
						}
					}
				}
			}
			//Fin búsqueda camino
			
			/*
			 * PREGUNTAR EN LA PRÁCTICA :: POR ALGUNA RAZÓN EL HASHCODE DE TODOS LOS VÉRTICES DE UN MISMO RECORRIDO ES IGUAL
			 * ESTIMO QUE DEBE ESTAR RELACIONADO AL USO DE DecorablePosition, que ya implementa un HashMap
			 */
			printMap(prev);
			
			//Postcondiciones:
			for (Vertex<V> x : g.vertices())
				x.remove(estado);
			
			//Construcción del camino en la lista
			if (existe_camino) {
				v = b;
				while (v != null) {
					camino.addFirst(v);
					v = prev.get(v);
				}
			}
			//Fin construcción del camino
			
		} catch (EmptyQueueException | InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();}
		
		return camino;
	}

	private static <V> void printMap(TDAMapeo_PR2.Map<Vertex<V>, Vertex<V>> prev) {
		System.out.println("PREV >>");
		for (Entry<Vertex<V>, Vertex<V>> e : prev.entries())
			System.out.print("("+e.getKey().element()+", "+e.getValue().element()+")");
		System.out.println();
	}
	
	public static <V> PositionList<Vertex<V>> camino_economico(Graph<V, Float> g, Vertex<V> a, Vertex<V> b) {
		PositionList<Vertex<V>> camino_minimo = new DoubleLinkedList<Vertex<V>>();
		PositionList<Vertex<V>> camino_actual = new DoubleLinkedList<Vertex<V>>();
		Object estado, visitado;
		estado = new Object();
		visitado = new Object();
		
		camino_minimo = dfs_backtracking(g, a, b, camino_actual, 0f, camino_minimo,	Float.MAX_VALUE, estado, visitado);
		
		return camino_minimo;
	}

	private static <V> PositionList<Vertex<V>> dfs_backtracking(Graph<V, Float> g, Vertex<V> origen, Vertex<V> destino,
			PositionList<Vertex<V>> camino_actual, float costo_actual, PositionList<Vertex<V>> camino_minimo,
		float costo_minimo, Object estado, Object visitado) {
		
		Vertex<V> v;
		
		try {
			origen.put(estado, visitado);
			camino_actual.addLast(origen);
			
			if (origen == destino) {
				if (costo_actual < costo_minimo) {
					//Esta mal esto. Costo minimo no está siendo actualizado globalmente, pues es una variable local.
					//Para el tester actual funciona de casualidad, pero debe usarse una clase que lo encapsule.
					camino_minimo = new DoubleLinkedList<Vertex<V>>();
					for (Vertex<V> w : camino_actual) {
						camino_minimo.addLast(w);
//						System.out.print(w.element() + " ");
					}
//					System.out.println();
					
					costo_minimo = costo_actual;
				}
			}
			else {
				for (Edge<Float> e : g.incidentEdges(origen)) {
					v = g.opposite(origen, e);
					if (v.get(estado) == null) {
						camino_minimo = dfs_backtracking(g, v, destino, camino_actual, costo_actual + e.element(), camino_minimo, costo_minimo, estado, visitado);
					}
				}
			}
			
			camino_actual.remove(camino_actual.last());
			origen.remove(estado);
			
		} catch (InvalidVertexException | InvalidPositionException | EmptyListException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		
		return camino_minimo;
		
	}
	
	public static <V, E> PositionList<PositionList<Vertex<V>>> caminos(Graph<V, E> g, Vertex<V> a, Vertex<V> b) {
		PositionList<PositionList<Vertex<V>>> caminos = new DoubleLinkedList<PositionList<Vertex<V>>>();
		Object estado, visitado;
		estado = new Object();
		visitado = new Object();
		
		hallarCamino(g, a, b, new DoubleLinkedList<Vertex<V>>(), caminos, estado, visitado);
		
		return caminos;
	}

	private static <V,E> void hallarCamino(Graph<V, E> g, Vertex<V> origen, Vertex<V> destino,
			PositionList<Vertex<V>> camino, PositionList<PositionList<Vertex<V>>> caminos, Object estado, Object visitado) {
		PositionList<Vertex<V>> new_camino;
		Vertex<V> v;
		
		try {
			origen.put(estado, visitado);
			camino.addLast(origen);
			
			if (origen == destino) {
				new_camino = new DoubleLinkedList<Vertex<V>>();
				for (Vertex<V> w : camino)
					new_camino.addLast(w);
				caminos.addLast(new_camino);
			}
			else {
				for (Edge<E> e : g.incidentEdges(origen)) {
					v = g.opposite(origen, e);
					if (v.get(estado) == null) {
						hallarCamino(g, v, destino, camino, caminos, estado, visitado);
					}
				}
			}
			
			camino.remove(camino.last());
			origen.remove(estado);
		} catch (InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
	}
	
	public static <V,E> PositionList<Vertex<V>> camino_largo(GraphD<V, E> g, Vertex<V> a, Vertex<V> b) {
		PositionList<Vertex<V>> camino_largo = new DoubleLinkedList<Vertex<V>>();
		Object estado, visitado;
		estado = new Object();
		visitado = new Object();
		
		hallarCaminoLargo(g, a, b, new DoubleLinkedList<Vertex<V>>(), camino_largo, estado, visitado);
		
		return camino_largo;
	}

	private static <V, E> void hallarCaminoLargo(GraphD<V, E> g, Vertex<V> origen, Vertex<V> destino,
			PositionList<Vertex<V>> camino_actual, PositionList<Vertex<V>> camino_largo, Object estado, Object visitado) {
		
		Vertex<V> v;
		
		try {
			camino_actual.addLast(origen);
			origen.put(estado, visitado);
			
			if (origen == destino) {
				if (camino_actual.size() > camino_largo.size()) {
					actualizar_camino_largo(camino_actual, camino_largo);
				}
			}
			else {
				for (Edge<E> e : g.succesorEdges(origen)) {
					v = g.opposite(origen, e);
					if (v.get(estado) == null)
						hallarCaminoLargo(g, v, destino, camino_actual, camino_largo, estado, visitado);
				}
			}
			
			origen.remove(estado);
			camino_actual.remove(camino_actual.last());
			
		} catch (InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		
	}

	private static <V,E> void actualizar_camino_largo(PositionList<Vertex<V>> camino_actual,
			PositionList<Vertex<V>> camino_largo) {
		
		TDALista.Position<Vertex<V>> pointer_largo, pointer_actual;
		
		/*
		 * En una primera aproximación -> vaciar camino largo y reemplazar con camino actual
		 * Luego, si efectivamente funciona, mejorar el método, eliminando solo las posiciones distintas y dejando las que ya tenían comunes.
		 */
		
		try {
			while (!camino_largo.isEmpty())
				camino_largo.remove(camino_largo.last());
			
			for (Vertex<V> v : camino_actual)
				camino_largo.addLast(v);
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		
	}
	
	public static <V, E> boolean lista_con_cabeza(GraphD<V, E> g, Vertex<V> a) {
		/*
		 * Lista si:
		 * 	saliendo desde a y yendo siempre a su primer emergente (que debería ser el único)
		 *  resulta que se recorre todo el graph -> en otras palabras, todos los vértices quedan visitados. 
		 *  
		 *  verificar que cada vértice tenga solo 1 emergente, excepto el último.
		 *  
		 */
		boolean lista = true;
		Object estado, visitado;
		Vertex<V> next;
		Iterator<Edge<E>> succesor;
		
		estado = new Object();
		visitado = new Object();
		
		try {
			
			//Recorrer cada vértice adyacente desde a, verificando que tengan uno y solo un adyacente.
			succesor = g.succesorEdges(a).iterator();
			next = a;
			while (succesor.hasNext() && lista) {
				if (next.get(estado) == visitado)
					lista = false;
				else {
					next.put(estado, visitado);
					next = g.opposite(next, succesor.next());
					lista = !succesor.hasNext();
					succesor = g.succesorEdges(next).iterator();
				}
			}
			next.put(estado, visitado);
			
			//Verificar que se haya recorrido el grafo por completo
			for (Vertex<V> v : g.vertices())
				if (v.get(estado) == null) {
					lista = false;
					break;
				}
			
			//Limpiar el grafo
			for (Vertex<V> v : g.vertices())
				v.remove(estado);
			
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace(); lista = false;}
		
		return lista;
	}
	
	public static <V, E> boolean es_arbol(GraphD<V, E> g, Vertex<V> a) {
		boolean esArbol;
		Object estado, visitado;
		
		
		estado = new Object();
		visitado = new Object();
		
		esArbol = es_arbol_aux(g, a, estado, visitado);
		
		//Chequeo de que se haya llegado a todos los vértices:
		for (Vertex<V> v : g.vertices()) {
			esArbol = v.get(estado) == visitado;
			if (!esArbol)
				break;
		}
		
		//Limpieza
		for (Vertex<V> v : g.vertices())
			v.remove(estado);
		
		return esArbol;
		
		/*
		 * Es arbol si:
		 *  > a solo tiene arcos emergentes y 
		 *  > si son árboles los vértices opuestos de a.
		 */
	}

	private static <V, E> boolean es_arbol_aux(GraphD<V, E> g, Vertex<V> a, Object estado, Object visitado) {
		Iterator<Edge<E>> it;
		boolean esArbol = a.get(estado) == null;
		
		try {
			
			if (esArbol) {
				a.put(estado, visitado);
				it = g.succesorEdges(a).iterator();
				
				while (it.hasNext() && esArbol)
					esArbol = es_arbol_aux(g, g.opposite(a, it.next()), estado, visitado);

			}
			
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
		
		return esArbol;
	}
	
	public static <V, E> void eliminar_incidente(GraphD<V, E> g) {
		Vertex<V> v, toRemove = null;
		Object incidence;
		Integer grade;
		int greatest = 0;
		
		incidence = new Object();
		
		try {
			for (Edge<E> e : g.edges()) {
				v = g.endvertices(e)[1]; //Recupera el vértice destino
				grade = (Integer) v.get(incidence);

				if (grade == null)
					grade = 0;
				
				v.put(incidence, (grade+1));
			}
			
			
			for (Vertex<V> w : g.vertices()) {
				grade = (Integer) w.get(incidence);
				
				if (grade != null && grade > greatest) {
					toRemove = w;
					greatest = grade;
				}
			}
			
			if (toRemove != null) {
				System.out.println("Removiendo vértice <" + toRemove.element() + "> (grado=" + greatest + ")");
				g.removeVertex(toRemove);
			}
			else {
				System.out.println("No se encontró ningún arco. ");
			}
			
		} catch (InvalidEdgeException | InvalidVertexException e) {
			e.printStackTrace();
		}
		
	}
	
}
