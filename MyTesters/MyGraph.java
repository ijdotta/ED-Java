package MyTesters;

import java.util.Random;

import Operaciones.Operaciones_con_grafos;
import TDAGrafo.Edge;
import TDAGrafo.Grafo_lista_adyacencia;
import TDAGrafo.Grafo_matriz_adyacencia;
import TDAGrafo.Graph;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDALista.PositionList;

public class MyGraph {

	public static void main(String[] args) {
//		test_graph();
//		test_dfs_bfs();
//		test_remove_label();
//		test_camino_corto();
//		test_camino_economico();
		test_caminos();
	}

	private static void test_caminos() {
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		int i = 0;
		visualize_referential_graph();
		
		try {
			
			vx[i++] = g.insertVertex(1); //0
			vx[i++] = g.insertVertex(12);//1
			vx[i++] = g.insertVertex(19);//2
			vx[i++] = g.insertVertex(21);//3
			vx[i++] = g.insertVertex(7); //4
			vx[i++] = g.insertVertex(31);//5
			vx[i++] = g.insertVertex(14);//6
			vx[i++] = g.insertVertex(67);//7
			
			i = 0;
			eg[i++] = g.insertEdge(vx[0], vx[1], 3);
			eg[i++] = g.insertEdge(vx[0], vx[2], 3);
			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
			eg[i++] = g.insertEdge(vx[3], vx[4], 13);
			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
			eg[i++] = g.insertEdge(vx[3], vx[6], 23);

			printCaminos(1, 14, Operaciones_con_grafos.caminos(g, vx[0], vx[6]) );
			printCaminos(19, 7, Operaciones_con_grafos.caminos(g, vx[2], vx[4]) );
			printCaminos(21, 7, Operaciones_con_grafos.caminos(g, vx[3], vx[4]) );
			
			System.out.println("FIN");
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	private static <V> void printCaminos(int i, int j, PositionList<PositionList<Vertex<V>>> caminos) {
		System.out.println("CAMINOS ENTRE " + i + " - " + j);
		for (PositionList<Vertex<V>> camino : caminos) {
			for (Vertex<V> v : camino)
				System.out.print(v.element() + " -> ");
			System.out.println();
		}
		
	}

	private static void test_camino_economico() {
		Graph<Integer, Float> g = new Grafo_lista_adyacencia<Integer, Float>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Float> [] eg = (Edge<Float> []) new Edge[10];
		int i = 0;
		visualize_referential_graph();
		
		try {
			
			vx[i++] = g.insertVertex(1); //0
			vx[i++] = g.insertVertex(12);//1
			vx[i++] = g.insertVertex(19);//2
			vx[i++] = g.insertVertex(21);//3
			vx[i++] = g.insertVertex(7); //4
			vx[i++] = g.insertVertex(31);//5
			vx[i++] = g.insertVertex(14);//6
			vx[i++] = g.insertVertex(67);//7
			
			i = 0;
			eg[i++] = g.insertEdge(vx[0], vx[1], 38f);
			eg[i++] = g.insertEdge(vx[0], vx[2], 67f);
			eg[i++] = g.insertEdge(vx[0], vx[3], 189f);
			eg[i++] = g.insertEdge(vx[0], vx[4], 34.6f);
			eg[i++] = g.insertEdge(vx[1], vx[2], 1f);
			eg[i++] = g.insertEdge(vx[2], vx[3], 1f);
			eg[i++] = g.insertEdge(vx[3], vx[4], 5f);
			eg[i++] = g.insertEdge(vx[3], vx[5], 978.45f);
			eg[i++] = g.insertEdge(vx[3], vx[6], 3.5f);
			
			/*
			 * 12-7
			 * 1-14
			 * 19-7
			 * 1-1
			 * 1-67
			 */
			vertices_incident_edges(g);
			
			System.out.println("Caminos económicos: ");
			System.out.println(vx[1].element() + " -- " + vx[4].element());
			printPath(12, 7, Operaciones_con_grafos.camino_economico(g, vx[1], vx[4]) );
			printPath(1, 14, Operaciones_con_grafos.camino_economico(g, vx[0], vx[6]) );
			printPath(19, 7, Operaciones_con_grafos.camino_economico(g, vx[2], vx[4]) );
			printPath(1, 1, Operaciones_con_grafos.camino_economico(g, vx[0], vx[0]) );
			printPath(1, 67, Operaciones_con_grafos.camino_economico(g, vx[0], vx[7]) );
			
			System.out.println("FIN");
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	private static void test_camino_corto() {
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		int i = 0;
		visualize_referential_graph();
		
		try {
			
			vx[i++] = g.insertVertex(1); //0
			vx[i++] = g.insertVertex(12);//1
			vx[i++] = g.insertVertex(19);//2
			vx[i++] = g.insertVertex(21);//3
			vx[i++] = g.insertVertex(7); //4
			vx[i++] = g.insertVertex(31);//5
			vx[i++] = g.insertVertex(14);//6
			vx[i++] = g.insertVertex(67);//7
			
			i = 0;
			eg[i++] = g.insertEdge(vx[0], vx[1], 3);
			eg[i++] = g.insertEdge(vx[0], vx[2], 3);
			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
			eg[i++] = g.insertEdge(vx[3], vx[4], 13);
			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
			eg[i++] = g.insertEdge(vx[3], vx[6], 23);
			
			/*
			 * 12-7
			 * 1-14
			 * 19-7
			 * 1-1
			 * 1-67
			 */
			System.out.println("Caminos cortos: ");
			System.out.println(vx[1].element() + " -- " + vx[4].element());
			printPath(12, 7, Operaciones_con_grafos.camino_corto(g, vx[1], vx[4]) );
			printPath(1, 14, Operaciones_con_grafos.camino_corto(g, vx[0], vx[6]) );
			printPath(19, 7, Operaciones_con_grafos.camino_corto(g, vx[2], vx[4]) );
			printPath(1, 1, Operaciones_con_grafos.camino_corto(g, vx[0], vx[0]) );
			printPath(1, 67, Operaciones_con_grafos.camino_corto(g, vx[0], vx[7]) );
			
			System.out.println("FIN");
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	private static <V> void printPath(int a, int b, PositionList<Vertex<V>> camino_corto) {
		System.out.print(a + "-" + b + " >> ");
		for (Vertex<V> v : camino_corto)
			System.out.print(v.element() + " ");
		System.out.println();
	}

	private static void test_remove_label() {
		int cant = 10;
		
		Grafo_lista_adyacencia<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[cant];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[100];
		Random r = new Random();
		
		try {	
			for (int i = 0; i < cant; i++) {
				vx[i] = g.insertVertex(i);
				if (i % 2 == 0) {
					g.insertVertex(i);
					if (i % 4 == 0)
						g.insertVertex(i);
				}
			}
			
			for (int i = 0; i < cant; i++) {
				int i1, i2;
				i1 = r.nextInt(cant);
				i2 = r.nextInt(cant);
				if (i1 == i2)
					i2 = (i2 + 1) % cant;
				if (!g.areAdjacent(vx[i1], vx[i2])) {
					g.insertEdge(vx[i1], vx[i2], i);
				}
			}
			
			vertices_incident_edges(g);
			System.out.println("Removing labels :: 2, 4, 8");
			System.out.println("2");
			g.eliminar_rotulo(2);
			vertices_incident_edges(g);
			System.out.println("4");
			g.eliminar_rotulo(4);
			vertices_incident_edges(g);
			System.out.println("8");
			g.eliminar_rotulo(8);
			vertices_incident_edges(g);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
	}

	@SuppressWarnings("unchecked")
	private static void test_dfs_bfs() {
		Grafo_lista_adyacencia<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		int i;
		visualize_referential_graph();
		
		try {
			
			//Grafo GUI
//			i = 0;
//			vx[i++] = g.insertVertex(1);
//			vx[i++] = g.insertVertex(12);
//			vx[i++] = g.insertVertex(19);
//			vx[i++] = g.insertVertex(21);
//			vx[i++] = g.insertVertex(7);
//			vx[i++] = g.insertVertex(31);
//			vx[i++] = g.insertVertex(14);
//			vx[i++] = g.insertVertex(67);
//			
//			i = 0;
//			eg[i++] = g.insertEdge(vx[0], vx[1], 3);
//			eg[i++] = g.insertEdge(vx[0], vx[2], 3);
//			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
//			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
//			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
//			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
//			eg[i++] = g.insertEdge(vx[3], vx[4], 13);
//			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
//			eg[i++] = g.insertEdge(vx[3], vx[6], 23);
			
			//Grafo internet::
			for (int j = 0; j < 6; j++)
				vx[j] = g.insertVertex(j+1);
			
			i = 0;
			eg[i++] = g.insertEdge(vx[0], vx[1], i);
			eg[i++] = g.insertEdge(vx[0], vx[2], i);
			eg[i++] = g.insertEdge(vx[0], vx[3], i);
			eg[i++] = g.insertEdge(vx[2], vx[4], i);
			eg[i++] = g.insertEdge(vx[2], vx[5], i);
			eg[i++] = g.insertEdge(vx[3], vx[5], i);
			
			
			vertices_incident_edges(g);
			
			System.out.println("EXECUTING DFS ..........................");
			g.dfs();
			System.out.println("EXECUTING BFS ..........................");
			g.bfs();
			
		} catch (InvalidVertexException e) {e.printStackTrace();}		
	}

	@SuppressWarnings("unchecked")
	private static void test_graph() {
		Graph<Integer, Integer> g = new Grafo_matriz_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		Integer removed;
		int i = 0;
		visualize_referential_graph();
		
		try {
			
			//Grafo vacío:
			System.out.println("Vertices vacío ? :: " + (g.vertices().iterator().hasNext() == false) );
			System.out.println("Edges vacío ? :: " + (g.edges().iterator().hasNext() == false) );
			vertices_incident_edges(g);
			
			vx[i++] = g.insertVertex(1);
			vx[i++] = g.insertVertex(12);
			vx[i++] = g.insertVertex(19);
			vx[i++] = g.insertVertex(21);
			vx[i++] = g.insertVertex(7);
			vx[i++] = g.insertVertex(31);
			vx[i++] = g.insertVertex(14);
			vx[i++] = g.insertVertex(67);
			
			//Grafo sin arcos
			vertices(g);
			System.out.println("Vertices vacío ? :: " + (g.vertices().iterator().hasNext() == false) );
			System.out.println("Edges vacío ? :: " + (g.edges().iterator().hasNext() == false) );
			vertices_incident_edges(g);
			
			i = 0;
			eg[i++] = g.insertEdge(vx[0], vx[1], 3);
			eg[i++] = g.insertEdge(vx[0], vx[2], 3);
			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
			eg[i++] = g.insertEdge(vx[3], vx[4], 13);
			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
			eg[i++] = g.insertEdge(vx[3], vx[6], 23);
			
			edges(g);
			vertices_incident_edges(g);
			
			
			test_various(g, vx, eg);
			
			
			//Remove some vertex
			removed = g.removeVertex(vx[3]);
			System.out.println("Removido :: " + removed);
			
			/**
			 * Se espera que si se remueve un vértice que tiene arcos asociados, este los elimine todos, incluso desde los demás nodos.
			 */
			
			System.out.println("Resultados colaterales :: ");
			vertices_incident_edges(g);
			test_various(g, vx, eg);
			
			System.out.println("Vaciando grafo ...");
			empty(g);
			vertices_incident_edges(g);
			System.out.println("Vertices vacío ? :: " + (g.vertices().iterator().hasNext() == false) );
			System.out.println("Edges vacío ? :: " + (g.edges().iterator().hasNext() == false) );
			
			test_various(g, vx, eg);
			
			System.out.println("FIN");
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
	}

	private static void test_various(Graph<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {
//		try {
//			System.out.println("Adjacency test >>");
//			for (int i = 0; i <= 7; i++)
//				for (int j = 0; j <= 7; j++) {
//					System.out.println("Adjacent " + vx[i].element() + " & " + vx[j].element() + " :: " + g.areAdjacent(vx[i], vx[j]));
//				}
//			
//			System.out.println("endPoint test >>");
//			for (Edge<Integer> e : g.edges()) {
//				Vertex<Integer> [] ep = g.endvertices(e);
//				System.out.println("Endpoint :: <"+ep[0].element()+">---["+e.element()+"]---<"+ep[1].element()+">");
//			}
//		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
			
	}

	private static <V,E> void vertices_incident_edges(Graph<V, E> g) {
		try {
			for (Vertex<V> v : g.vertices()) {
				System.out.print("<"+v.element()+"> :: ");
				for (Edge<E> e : g.incidentEdges(v))
					System.out.print("["+e.element()+"] ");
				System.out.println();
			}
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	/**
	 * Método para vaciar un grafo de enteros recibido.
	 * @param g Grafo a vaciar.
	 */
	private static void empty(Graph<Integer, Integer> g) {
		try {
			for (Vertex<Integer> v : g.vertices())
				g.removeVertex(v);
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	/**
	 * Método para mostrar los arcos de un grafo de enteros recibido.
	 * @param g Grafo.
	 */
	private static void edges(Graph<Integer, Integer> g) {
		for (Edge<Integer> e : g.edges())
			System.out.println(" [" + e.element() + "] ");
	}

	/**
	 * Método para mostrar los vértices de un grafo de enteros recibido.
	 * @param g Grafo.
	 */
	private static void vertices(Graph<Integer, Integer> g) {
		for (Vertex<Integer> v : g.vertices())
			System.out.println(" <" + v.element() + "> ");
	}

	private static void visualize_referential_graph() {
		//Ruta archivo con grafo referencia:
		String image_path = "C:\\Users\\Ignacio\\Google Drive\\UNS\\Ingeniería en Sistemas de Información\\Tercer cuatrimestre\\ED\\Práctica\\Recursos\\Grafo ejemplo referencia.png";
		Visualizer graph_ref = new Visualizer(image_path); 
	}
	
}
