package promocion_ejercicios;

import java.util.HashMap;
import java.util.Map;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.Grafo_lista_adyacencia;
import TDAGrafo.Grafo_matriz_adyacencia;
import TDAGrafo.Graph;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Use_of_Algorithms {

	public static void main(String[] args) {
		binaryRelationClosure();
	}
	
	/**
	 * Utilizando el algoritmo de Warshall, encuentre la clausura reflexo-transitiva de la relación R de
	 * enteros en enteros tal que: R = {(1; 1); (1; 2); (1; 7); (2; 3); (3; 4); (4; 5); (4; 7); (5; 6); (8; 7); (4; 9)}
	 */
	@SuppressWarnings("unchecked")
	public static void binaryRelationClosure() {
		Graph<Integer, Integer> relation = new Grafo_matriz_adyacencia<Integer, Integer>();
		int n = 9; //Enteros desde 1 hasta n para definir en la relación.
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[n+1];
		int [][] pairs = {
				{1, 1, 1, 2, 3, 4, 4, 5, 8, 4}, 
				{1, 2, 7, 3, 4, 5, 7, 6, 7, 9}
		};
		
		//Test más pequeño
//		int [][] pairs = {
//				{1, 2}, 
//				{2, 3}
//		};
		
		boolean [][] closure;
		
		try {
			
			for (int i = 1; i <= n; i++)
				vx[i] = relation.insertVertex(i);
			
			for (int i = 0; i < pairs[0].length; i++)
				relation.insertEdge(vx[ pairs[0][i] ], vx[ pairs[1][i] ], null);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
		closure = Algorithms.warshall(relation);
		
		printRel(relation);
		printMatrix(closure);
		
		System.out.print("R* = {");
		for (int i = 0; i < vx.length-1; i++) {
			for (int j = 0; j < vx.length-1; j++) {
				if (closure[i][j])
					System.out.print("(" + vx[i+1].element() + ", " + vx[j+1].element() + "), ");
			}
		}
		System.out.println("} ");
		
	}

	/**
	 * Imprime un grafo interpretado como una relación binaria.
	 * @param relation Grafo que modela la relación binaria.
	 */
	private static void printRel(Graph<Integer, Integer> relation) {
		try {
			for (Edge<Integer> e : relation.edges())
				System.out.print("("+relation.endvertices(e)[0].element()+", "+relation.endvertices(e)[1].element()+")");
			System.out.println();
		} catch (InvalidEdgeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imprime una matriz de adyacencia representada con boolean
	 * @param closure Matriz de adyacencias.
	 */
	private static void printMatrix(boolean[][] closure) {
		for (int i = 0; i < closure.length; i++) {
			for (int j = 0; j < closure[i].length; j++) {
				if (closure[i][j])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
	}
	
	public <V, E> PositionList<Vertex<V>> camino_corto(Graph<V, E> g, Vertex<V> a, Vertex<V> b) {
		PositionList<Vertex<V>> camino = new DoubleLinkedList<>(), camino_aux = new DoubleLinkedList<>();
		Graph<V, Float> graph = new Grafo_lista_adyacencia<>();
		Vertex<V> [] vx;
		Vertex<V> w;
		Map<Vertex<V>, Vertex<V>> map_g = new HashMap<>(), map_graph = new HashMap<>();
		
		try {
			//Clonar grafo g para usar peso 1 en arcos
			for (Vertex<V> v : g.vertices()) {
				w = graph.insertVertex(v.element());
				map_g.put(v, w);
				map_graph.put(w, v);
			}
			
			for (Edge<E> e : g.edges()) {
				vx = g.endvertices(e);
				graph.insertEdge(map_g.get(vx[0]), map_g.get(vx[1]), 1f);
			}
			
		} catch (InvalidEdgeException | InvalidVertexException e) {e.printStackTrace();}
		
		/* 
		 * Computar el camino más económico entre los vértices correspondientes a 'a' y 'b' en el
		 * grafo 'graph'.
		 * Como el peso de los arcos es siempre 1, el camino más económico implica más corto.
		 */
		camino_aux = camino_economico(graph, map_g.get(a), map_g.get(b));
		
		//Conversión del camino de vértices de 'graph' a vértices de 'g'.
		for (Vertex<V> v : camino_aux)
			camino.addLast(map_graph.get(v));
		
		return camino;
	}
	
	public <V> PositionList<Vertex<V>> camino_economico(Graph<V, Float> g, Vertex<V> a, Vertex<V> b) {
		PositionList<Vertex<V>> camino = new DoubleLinkedList<>();
		Pair<float[][], int[][]> floyd = Floyd_Algorithm_not_digraph.floyd(g);
		Map<Integer, Vertex<V>> map_index = new HashMap<>();
		Map<Vertex<V>, Integer> map_vertex = new HashMap<>();
		Queue<Integer> path;
		
		for (Vertex<V> v : g.vertices()) {
			map_index.put((Integer) v.get("index"), v);
			map_vertex.put(v, (Integer) v.get("index"));
		}
		
		try {
			
			path = Floyd_Algorithm_not_digraph.recuperarCamino(floyd.getB(), map_vertex.get(a), map_vertex.get(b));
			while (!path.isEmpty())
				camino.addLast(map_index.get(path.dequeue()));
			camino.addFirst(a);
			camino.addLast(b);
		} catch (EmptyQueueException e) {e.printStackTrace();}
		
		return camino;
	}

}
