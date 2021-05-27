package MyTesters;

import promocion_ejercicios.Grafos_promocion;
import TDAGrafo.Edge;
import TDAGrafo.Grafo_lista_adyacencia;
import TDAGrafo.Graph;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDAMapeo.Entry;
import TDAMapeo.Map;

public class MyGraph_promocion {
	
	public static void main(String[] args) {
//		test_esConexo();
//		test_componentesConexas();
//		test_eliminar(); //Veriación de componentes conexas.
		test_promocion();
	}
	
	private static void test_promocion() {
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		
		test_componentesConexas();
		
		restore(g, vx, eg);
		get_grafo_ref(g, vx, eg);
		try {
			printMap(examen_de_promocion.GrafosPromocion.solucion(g, 7));
//			printMap(examen_de_promocion.GrafosPromocion.solucion(g, 64));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static <V> void printMap(Map<Vertex<V>, Integer> solucion) {
		System.out.print("Adyacencias: {");
		for (Entry<Vertex<V>, Integer> e : solucion.entries())
			System.out.print("("+e.getKey().element()+", "+e.getValue()+") ");
		System.out.println("} ");
	}

	private static void test_eliminar() {
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		
		restore(g, vx, eg);
		get_grafo_ref(g, vx, eg);
		System.out.println("Eliminando " + vx[4].element() + "... ");
		Grafos_promocion.eliminar(g, vx[4]);
		System.out.println("Eliminando " + vx[3].element() + "... ");
		Grafos_promocion.eliminar(g, vx[3]);
	}

	private static void test_componentesConexas() {
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		
		restore(g, vx, eg);
		get_grafo_arbol(g, vx, eg);
		System.out.println("Componentes conexas arbol :: ");
		Grafos_promocion.componentesConexas(g);
		
		restore(g, vx, eg);
		get_grafo_lista(g, vx, eg);
		System.out.println("Componentes conexas lista :: ");
		Grafos_promocion.componentesConexas(g);
		
		restore(g, vx, eg);
		get_grafo_ref(g, vx, eg);
		System.out.println("Componentes conexas ref :: ");
		Grafos_promocion.componentesConexas(g);
	}

	/*
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
	 */
	
	@SuppressWarnings("unchecked")
	private static void test_esConexo() {
		Graph<Integer, Integer> g = new Grafo_lista_adyacencia<Integer, Integer>();
		Vertex<Integer> [] vx = (Vertex<Integer> []) new Vertex[10];
		Edge<Integer> [] eg = (Edge<Integer> []) new Edge[10];
		
		restore(g, vx, eg);
		get_grafo_arbol(g, vx, eg);
		System.out.println("Es conexo g arbol :: " + Grafos_promocion.esConexo(g));
		
		restore(g, vx, eg);
		get_grafo_lista(g, vx, eg);
		System.out.println("Es conexo g lista :: " + Grafos_promocion.esConexo(g));
		
		restore(g, vx, eg);
		get_grafo_ref(g, vx, eg);
		System.out.println("Es conexo g ref :: " + Grafos_promocion.esConexo(g));
		
	}

	private static void get_grafo_arbol(Graph<Integer, Integer> g, Vertex<Integer>[] vx, 
			Edge<Integer>[] eg) {
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

	private static void get_grafo_lista(Graph<Integer, Integer> g, Vertex<Integer>[] vx, 
			Edge<Integer>[] eg) {
		int cant = 10;
		try {
			
			for (int i = 0; i < cant; i++)
				vx[i] = g.insertVertex(i);
			
			for (int i = 0; i < cant-1; i++)
				eg[i] = g.insertEdge(vx[i], vx[i+1], i*10);
			
//			g.insertEdge(vx[9], vx[0], 99);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
	}

	private static void get_grafo_ref(Graph<Integer, Integer> g, Vertex<Integer>[] vx, 
			Edge<Integer>[] eg) {
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
	
	private static void restore(Graph<Integer, Integer> g, Vertex<Integer>[] vx, Edge<Integer>[] eg) {
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

}
