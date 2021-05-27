package MyTesters;

import Operaciones.Operaciones_con_grafos;
import TDAGrafo.Arco;
import TDAGrafo.Edge;
import TDAGrafo.GrafoD_lista_adyacencia;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDALista.PositionList;

public class MyGraphD {

	public static void main(String[] args) {
//		test_graph();
//		test_camino_largo();
//		test_lista_cabeza();
//		test_es_arbol();
		test_remover_incidente();
	}
	
	private static void test_remover_incidente() {
		GraphD<Integer, Integer> g = new GrafoD_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		
		restore(g, vx, eg);
		get_grafo_lista(g, vx, eg);
		vertices_incident_edges(g);
		edges_relation(g);
		Operaciones_con_grafos.eliminar_incidente(g);
		
		restore(g, vx, eg);
		get_grafo_ref(g, vx, eg);
		vertices_incident_edges(g);
		edges_relation(g);
		Operaciones_con_grafos.eliminar_incidente(g);
		
		restore(g, vx, eg);
		get_grafo_arbol(g, vx, eg);
		vertices_incident_edges(g);
		edges_relation(g);
		Operaciones_con_grafos.eliminar_incidente(g);
		
		System.out.println("FIN");
	}

	private static void restore(GraphD<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {
		try {
			for (Vertex<Integer> v : g.vertices())
				g.removeVertex(v);
			for (Edge<Integer> e : g.edges())
				g.removeEdge(e);
			for (int i = 0; i < vx.length; i++)
				vx[i] = null; 
			for (int i = 0; i < eg.length; i++)
				eg[i] = null;
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
	}

	private static void test_es_arbol() {
		GraphD<Integer, Integer> g = new GrafoD_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		int i = 0;
		visualize_referential_graph();
		
//		get_grafo_lista(g, vx, eg);
//		get_grafo_ref(g, vx, eg);
		get_grafo_arbol(g, vx, eg);
		
		vertices_incident_edges(g);
		edges_relation(g);
		System.out.println("Grafo arbol iniciando en " + vx[0].element() + " ? :: " + Operaciones_con_grafos.es_arbol(g, vx[0]));
		System.out.println("Grafo arbol iniciando en " + vx[1].element() + " ? :: " + Operaciones_con_grafos.es_arbol(g, vx[1]));
		System.out.println("Grafo arbol iniciando en " + vx[2].element() + " ? :: " + Operaciones_con_grafos.es_arbol(g, vx[2]));
		System.out.println("Grafo arbol iniciando en " + vx[6].element() + " ? :: " + Operaciones_con_grafos.es_arbol(g, vx[6]));
//		System.out.println("Grafo arbol iniciando en " + vx[9].element() + " ? :: " + Operaciones_con_grafos.es_arbol(g, vx[9]));
		
		
		System.out.println("FIN");
	}

	private static void test_lista_cabeza() {
		GraphD<Integer, Integer> g = new GrafoD_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		int i = 0;
		visualize_referential_graph();
		
		get_grafo_lista(g, vx, eg);
//		get_grafo_ref(g, vx, eg);
		
		vertices_incident_edges(g);
		edges_relation(g);
		System.out.println("Grafo lista iniciando en " + vx[0].element() + " ? :: " + Operaciones_con_grafos.lista_con_cabeza(g, vx[0]));
		System.out.println("Grafo lista iniciando en " + vx[1].element() + " ? :: " + Operaciones_con_grafos.lista_con_cabeza(g, vx[1]));
		System.out.println("Grafo lista iniciando en " + vx[2].element() + " ? :: " + Operaciones_con_grafos.lista_con_cabeza(g, vx[2]));
		System.out.println("Grafo lista iniciando en " + vx[6].element() + " ? :: " + Operaciones_con_grafos.lista_con_cabeza(g, vx[6]));
		System.out.println("Grafo lista iniciando en " + vx[9].element() + " ? :: " + Operaciones_con_grafos.lista_con_cabeza(g, vx[9]));
		
		
		System.out.println("FIN");
	}

	private static <V,E> void edges_relation(GraphD<V, E> g) {
		try {
			Vertex<V> [] v;
			for (Edge<E> e : g.edges()) {
				v = g.endvertices(e);
				System.out.println("<"+v[0].element()+">-["+e.element()+"]-<"+v[1].element()+">");
			}
		} catch (InvalidEdgeException e) {
			e.printStackTrace();
		}
	}

	private static void get_grafo_arbol(GraphD<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {
			int cant = 8;
			try {
				
				for (int i = 0; i < cant; i++)
					vx[i] = g.insertVertex(i);
				
				int i = 0;
				eg[i++] = g.insertEdge(vx[0], vx[1], i);
				eg[i++] = g.insertEdge(vx[0], vx[2], i);
				eg[i++] = g.insertEdge(vx[0], vx[3], i);
				eg[i++] = g.insertEdge(vx[1], vx[4], i);
				eg[i++] = g.insertEdge(vx[1], vx[5], i);
				eg[i++] = g.insertEdge(vx[1], vx[6], i);
				eg[i++] = g.insertEdge(vx[5], vx[7], i);
				
	//			g.insertEdge(vx[9], vx[0], 99);
				
			} catch (InvalidVertexException e) {e.printStackTrace();}
		}

	private static void get_grafo_lista(GraphD<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {
			int cant = 10;
			try {
				
				for (int i = 0; i < cant; i++)
					vx[i] = g.insertVertex(i);
				
				for (int i = 0; i < cant-1; i++)
					eg[i] = g.insertEdge(vx[i], vx[i+1], i*10);
				
	//			g.insertEdge(vx[9], vx[0], 99);
				
			} catch (InvalidVertexException e) {e.printStackTrace();}
		}

	private static void get_grafo_ref(GraphD<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {
		int i;
		
		try {
			
			i = 0;
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
			eg[i++] = g.insertEdge(vx[2], vx[0], 3);
			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
			eg[i++] = g.insertEdge(vx[4], vx[3], 13);
			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
			eg[i++] = g.insertEdge(vx[3], vx[6], 23);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	private static void test_camino_largo() {
		GraphD<Integer, Integer> g = new GrafoD_lista_adyacencia<Integer, Integer>();
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
			eg[i++] = g.insertEdge(vx[2], vx[0], 3);
			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
			eg[i++] = g.insertEdge(vx[4], vx[3], 13);
			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
			eg[i++] = g.insertEdge(vx[3], vx[6], 23);
			
//			printOrderedEdges(g);
			emergency(g);

			printPath(1, 14, Operaciones_con_grafos.camino_largo(g, vx[0], vx[6]) );
			printPath(19, 7, Operaciones_con_grafos.camino_largo(g, vx[2], vx[4]) );
			printPath(21, 7, Operaciones_con_grafos.camino_largo(g, vx[3], vx[4]) );
			
			System.out.println("FIN");
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}


	private static <V> void printPath(int a, int b, PositionList<Vertex<V>> camino) {
		System.out.print(a + "-" + b + " >> ");
		for (Vertex<V> v : camino)
			System.out.print(v.element() + " ");
		System.out.println();
	}

	private static void test_remove_label() {
//		int cant = 10;
//		Grafo_lista_adyacencia<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
//		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[cant];
//		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[100];
//		Random r = new Random();
//		
//		try {	
//			for (int i = 0; i < cant; i++) {
//				vx[i] = g.insertVertex(i);
//				if (i % 2 == 0) {
//					g.insertVertex(i);
//					if (i % 4 == 0)
//						g.insertVertex(i);
//				}
//			}
//			
//			for (int i = 0; i < cant; i++) {
//				int i1, i2;
//				i1 = r.nextInt(cant);
//				i2 = r.nextInt(cant);
//				if (i1 == i2)
//					i2 = (i2 + 1) % cant;
//				if (!g.areAdjacent(vx[i1], vx[i2])) {
//					g.insertEdge(vx[i1], vx[i2], i);
//				}
//			}
//			
//			vertices_incident_edges(g);
//			System.out.println("Removing labels :: 2, 4, 8");
//			System.out.println("2");
//			g.eliminar_rotulo(2);
//			vertices_incident_edges(g);
//			System.out.println("4");
//			g.eliminar_rotulo(4);
//			vertices_incident_edges(g);
//			System.out.println("8");
//			g.eliminar_rotulo(8);
//			vertices_incident_edges(g);
//			
//		} catch (InvalidVertexException e) {e.printStackTrace();}
		
	}

	@SuppressWarnings("unchecked")
	private static void test_graph() {
		GraphD<Integer, Integer> g = new GrafoD_lista_adyacencia<Integer, Integer>();
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
			
			i=0;
			vx[i++] = g.insertVertex(1); //0
			vx[i++] = g.insertVertex(12);//1
			vx[i++] = g.insertVertex(19);//2
			vx[i++] = g.insertVertex(21);//3
			vx[i++] = g.insertVertex(7); //4
			vx[i++] = g.insertVertex(31);//5
			vx[i++] = g.insertVertex(14);//6
			vx[i++] = g.insertVertex(67);//7
			
			//Grafo sin arcos
			vertices(g);
			System.out.println("Vertices vacío ? :: " + (g.vertices().iterator().hasNext() == false) );
			System.out.println("Edges vacío ? :: " + (g.edges().iterator().hasNext() == false) );
			vertices_incident_edges(g);
			
			i = 0;
			eg[i++] = g.insertEdge(vx[0], vx[1], 3);
			eg[i++] = g.insertEdge(vx[2], vx[0], 3);
			eg[i++] = g.insertEdge(vx[0], vx[3], 12);
			eg[i++] = g.insertEdge(vx[0], vx[4], 4);
			eg[i++] = g.insertEdge(vx[1], vx[2], 16);
			eg[i++] = g.insertEdge(vx[2], vx[3], 2);
			eg[i++] = g.insertEdge(vx[4], vx[3], 13);
			eg[i++] = g.insertEdge(vx[3], vx[5], 14);
			eg[i++] = g.insertEdge(vx[3], vx[6], 23);
			
			edges(g);
			vertices_incident_edges(g);
			
			
			System.out.println("Mostrar arcos como pares ordenados:: ");
			printOrderedEdges(g);
			emergency(g);
			
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

	private static void emergency(GraphD<Integer, Integer> g) {
		try {
			System.out.println("Emergencias de cada vértice: ");
			for (Vertex<Integer> v : g.vertices()) {
				System.out.print("<"+v.element()+"> :: ");
				for (Edge<Integer> e : g.succesorEdges(v))
					System.out.print("[" + e.element() + "] ");
				System.out.println();
			}
		} catch (InvalidVertexException e) {
			e.printStackTrace();
		}
		
	}

	private static <V,E> void printOrderedEdges(GraphD<V, E> g) {
			for (Edge<E> e : g.edges()) {
				System.out.print("(" + ((Arco<V,E>) e).getV1().element() + ", " +((Arco<V,E>) e).getV2().element() + ")");
			}
	}

	private static void test_various(GraphD<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {

		try {
			System.out.println("Adjacency test >>");
				for (int i = 0; i <= 7; i++)
					for (int j = 0; j <= 7; j++) {
						System.out.println("Adjacent " + vx[i].element() + " & " + vx[j].element() + " :: " + g.areAdjacent(vx[i], vx[j]));
					}
				
				System.out.println("endPoint test >>");
				for (Edge<Integer> e : g.edges()) {
					Vertex<Integer> [] ep = g.endvertices(e);
					System.out.println("Endpoint :: <"+ep[0].element()+">---["+e.element()+"]---<"+ep[1].element()+">");
				}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
			
	}

	private static <V,E> void vertices_incident_edges(GraphD<V, E> g) {
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
	private static void empty(GraphD<Integer, Integer> g) {
		try {
			for (Vertex<Integer> v : g.vertices())
				g.removeVertex(v);
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	/**
	 * Método para mostrar los arcos de un grafo de enteros recibido.
	 * @param g Grafo.
	 */
	private static void edges(GraphD<Integer, Integer> g) {
		for (Edge<Integer> e : g.edges())
			System.out.println(" [" + e.element() + "] ");
	}

	/**
	 * Método para mostrar los vértices de un grafo de enteros recibido.
	 * @param g Grafo.
	 */
	private static void vertices(GraphD<Integer, Integer> g) {
		for (Vertex<Integer> v : g.vertices())
			System.out.println(" <" + v.element() + "> ");
	}

	private static void visualize_referential_graph() {
		//Ruta archivo con grafo referencia:
		String image_path = "C:\\Users\\Ignacio\\Google Drive\\UNS\\Ingeniería en Sistemas de Información\\Tercer cuatrimestre\\ED\\Práctica\\Recursos\\Grafo ejemplo referencia.png";
		Visualizer graph_ref = new Visualizer(image_path); 
	}
	
}
