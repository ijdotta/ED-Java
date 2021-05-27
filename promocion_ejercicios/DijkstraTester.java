package promocion_ejercicios;

import java.util.Map;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.GrafoD_lista_adyacencia;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;

public class DijkstraTester {
	
	protected static GraphD<Character, Float> g;
	protected static Vertex<Character> [] v;
	protected static Pair<Map<Vertex<Character>, Float>, Map<Vertex<Character>, Vertex<Character>>> dijkstra;
	
	public static void main(String[] args) {
		Vertex<Character> a;
		getGraph();
		printGraph();
		
		a = v[0];
		dijkstra(a);
		printAllPaths(a);
	}

	private static void printAllPaths(Vertex<Character> a) {
		Queue<Vertex<Character>> path;
		try {
			
			for (Vertex<Character> w : v)
				if (w != a) {
					path = Dijkstra_Algorithm.recuperar(dijkstra.getB(), w, a);
					while (!path.isEmpty())
						System.out.print(path.dequeue().element() + " ");
					System.out.println();
				}
			
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}

	private static void dijkstra(Vertex<Character> a) {
		dijkstra = Dijkstra_Algorithm.dijkstra(g, a);
	}

	private static void getGraph() {
		v = (Vertex<Character>[]) new Vertex[10];
		g = new GrafoD_lista_adyacencia<>();
		
		for (char c = 'a'; c <= 'f'; c++) {
			v[(int) (c-'a')] = g.insertVertex(c);
		}

		try {
			
			g.insertEdge(v[0], v[1], 4f);
			g.insertEdge(v[0], v[2], 2f);
			g.insertEdge(v[1], v[3], 5f);
			g.insertEdge(v[2], v[1], 1f);
			g.insertEdge(v[2], v[3], 8f);
			g.insertEdge(v[2], v[4], 10f);
			g.insertEdge(v[3], v[4], 2f);
			g.insertEdge(v[3], v[5], 6f);
			g.insertEdge(v[4], v[5], 3f);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
	}
	
	private static void printGraph() {
		try {
			
			for (Edge<Float> e : g.edges())
				System.out.println(g.endvertices(e)[0].element() + 
						" -[" + e.element() + "]-> " + 
						g.endvertices(e)[1].element());
			
		} catch (InvalidEdgeException e) {e.printStackTrace();}
	}

}
