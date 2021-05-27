package promocion_ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.GrafoD_lista_adyacencia;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class FloydTester {
	
	protected static GraphD<Integer, Float> g;
	protected static Map<Integer, Vertex<Integer>> map;
	protected static Pair<float[][], int[][]> floyd;
	protected static Queue<Integer> path;

	public static void main(String[] args) {
		getGraph();
		printGraph();
		performFloyd();
		getMap();
		printMap();
		printMinDistances();
		test_paths();
	}

	private static void test_paths() {
		int[][] P = floyd.getB();
		Pair<PositionList<Integer>, PositionList<Vertex<Integer>>> paths;
		
		for (Integer i : map.keySet())
			for (Integer j : map.keySet()) {
				paths = computePaths(P, i,j);
				System.out.print("Camino según índice matricial: ");
				printIndexPath(paths.getA());
				System.out.print("Camino según rótulo de vértice: ");
				printVertexPath(paths.getB());
			}
	}

	private static void printVertexPath(PositionList<Vertex<Integer>> vertex_path) {
		for (Vertex<Integer> v : vertex_path)
			System.out.print(v.element() + " -> ");
		System.out.println();
	}

	private static void printIndexPath(PositionList<Integer> index_path) {
		for (Integer v : index_path)
			System.out.print(v + " -> ");
		System.out.println();
	}

	private static Pair<PositionList<Integer>, PositionList<Vertex<Integer>>> computePaths(int[][] P, int i, int j) {
		PositionList<Integer> indexes_path = new DoubleLinkedList<>();
		PositionList<Vertex<Integer>> vertex_path = new DoubleLinkedList<>();
		Pair<PositionList<Integer>, PositionList<Vertex<Integer>>> paths = 
				new Pair<PositionList<Integer>, PositionList<Vertex<Integer>>>(indexes_path, vertex_path);
		Queue<Integer> path = Floyd_Algorithm.recuperarCamino(P, i, j);
		int index;
		
		try {
			
			while (!path.isEmpty()) {
				index = path.dequeue();
				indexes_path.addLast(index);
				vertex_path.addLast(map.get(index));
			}
			
		} catch (EmptyQueueException e) {e.printStackTrace();}
		
		indexes_path.addFirst(i);
		indexes_path.addLast(j);
		vertex_path.addFirst(map.get(i));
		vertex_path.addLast(map.get(j));
		
		return paths;
	}

	/**
	 * Imprime la matriz de adyacencia de distancias mínimas.
	 */
	private static void printMinDistances() {
		float[][] A = floyd.getA();
		float val;
		int i, j;
		ArrayList<Entry<Integer, Vertex<Integer>>> indexes = new ArrayList<>();
		
		/* Permite ordenar los índices de forma tal que coincidan con el orden
		 * natural de los rótulos de los vértices en lugar de el órden dado por
		 * la clase Floyd_Algorithm
		 */
		for (Entry<Integer, Vertex<Integer>> e : map.entrySet())
			indexes.add(e);
		indexes.sort(new EntryComparator());
		
		//Por lo anterior, los vértices se recorren según su orden natural y la clave
		//de cada entrada está asociada al índice matricial del vértice.
		for (Entry<Integer, Vertex<Integer>> e_i : indexes) {
			i = e_i.getKey();
			
			for (Entry<Integer, Vertex<Integer>> e_j : indexes) {
				j = e_j.getKey();
				
				val = A[i][j];
				
				if (val == Float.POSITIVE_INFINITY)
					System.out.print("INF");
				else
					System.out.print(val);
				System.out.print(" ");
				
			}
			
			System.out.println();
		}
	}

	private static void printMap() {
		for (Integer i : map.keySet())
			System.out.println("(" + i + " ," + map.get(i).element() + ") ");
	}

	private static void printGraph() {
		try {
			
			for (Edge<Float> e : g.edges())
				System.out.println(g.endvertices(e)[0].element() + 
						" -[" + e.element() + "]-> " + 
						g.endvertices(e)[1].element());
			
		} catch (InvalidEdgeException e) {e.printStackTrace();}
	}

	private static void getMap() {
		map = new HashMap<>();
		for (Vertex<Integer> v : g.vertices())
			map.put((Integer) v.get("index"), v);
	}

	private static void performFloyd() {
		floyd = Floyd_Algorithm.floyd(g);
	}

	@SuppressWarnings("unchecked")
	private static void getGraph() {
		Vertex<Integer> [] v = (Vertex<Integer> []) new Vertex[10];
		g = new GrafoD_lista_adyacencia<>();
		
		for (int i = 1; i < 5; i++)
			v[i] = g.insertVertex(i);

		try {
			
			g.insertEdge(v[1], v[4], 0.5f);
			g.insertEdge(v[2], v[1], 20f);
			g.insertEdge(v[2], v[4], 1.5f);
			g.insertEdge(v[2], v[3], 10f);
			g.insertEdge(v[4], v[1], 2.5f);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Class EntryComparator - Compara entradas de índices matriciales en vértices de graafo 
	 * según el orden natural del rótulo de los vértices.
	 * @author Ignacio Joaquín Dotta
	 */
	private static class EntryComparator implements Comparator<Entry<Integer, Vertex<Integer>>> {

		@Override
		public int compare(Entry<Integer, Vertex<Integer>> o1, Entry<Integer, Vertex<Integer>> o2) {
			return o1.getValue().element().compareTo(o2.getValue().element());
		}
		
	}

}
