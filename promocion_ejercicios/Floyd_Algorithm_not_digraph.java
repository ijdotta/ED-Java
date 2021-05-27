package promocion_ejercicios;

import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Edge;
import TDAGrafo.Graph;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.Vertex;

public class Floyd_Algorithm_not_digraph {
	
	protected final static float INFINITY = Float.POSITIVE_INFINITY;
	
	/**
	 * Debe para conocer el índice de un vértice usar la clave "index": 
	 * 							v.get("index")
	 * @param <V>
	 * @param g
	 * @return
	 */
	public static <V> Pair<float[][], int[][]> floyd(Graph<V, Float> g){
		Pair<float[][], int[][]> pair = new Pair<float[][], int[][]>();
		float [][] A;
		int [][] P;
		int n;
		float dist;
		//Construir matriz ponderada de adyacencias, numerando vértices.
		/*
		 * Para esto podría usar el mapeo interno de los vértices, o basarlo en el recorrido de iterator
		 */
		toMatrix(g, pair);
		A = pair.getA();
		P = pair.getB();
		
		//Programar algoritmo
		n = A.length;
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					dist = A[i][k] + A[k][j];
					if (dist < A[i][j]) {
						A[i][j] = dist;
						P[i][j] = k;
					}
				}
		
		return pair;
	}

	private static <V> void toMatrix(Graph<V, Float> g, Pair<float[][], int[][]> pair) {
		float [][] A = new float[10][10];
		int [][] P = new int[10][10];
		int index = 0;
		Integer i1, i2;
		Vertex<V> [] v;
		
		//Inicializa la matriz A en infinito, excepto a los arcos circulares.
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] = INFINITY;
				P[i][j] = -1;
			}
			A[i][i] = 0;
		}
		
		//Recorre los arcos y añade su peso a los indices de vértices de la matriz A
		try {
			for (Edge<Float> e : g.edges()) {
				
				v = g.endvertices(e);
				i1 = (Integer) v[0].get("index");
				i2 = (Integer) v[1].get("index");

				if (i1 == null) {
					i1 = index++;
					v[0].put("index", i1);
				}
				if (i2 == null) {
					i2 = index++;
					v[1].put("index", i2);
				}
				
				if (index == A.length || index == P.length) {
					resize(pair);
				}
				
				A[i1][i2] = e.element();
			}
		} catch (InvalidEdgeException e) {
			e.printStackTrace();
		}
		
		pair.setA(A);
		pair.setB(P);
	}

	private static void resize(Pair<float[][], int[][]> pair) {
		float[][] old_A, A;
		int[][] old_P, P;
		int new_size;
		
		old_A = pair.getA();
		old_P = pair.getB();
		
		new_size = old_A.length * 2;
		
		A = new float[new_size][new_size];
		P = new int[new_size][new_size];
		
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				A[i][j] = INFINITY;
				P[i][j] = 0;
			}
			A[i][i] = 0;
		}
		
		for (int i = 0; i < old_A.length; i++)
			for (int j = 0; j < old_A.length; j++) {
				A[i][j] = old_A[i][j];
				P[i][j] = old_P[i][j];
			}
		
		pair.setA(A);
		pair.setB(P);
	}
	
	public static <V> Queue<Integer> recuperarCamino(int[][] P, int i, int j) {
		Queue<Integer> path = new LinkedQueue<Integer>();
		
//		path.enqueue(i);
		recuperarCamino_rec(P, i, j, path);
//		path.enqueue(j);
		
		return path;
	}

	private static void recuperarCamino_rec(int[][] P, int i, int j, Queue<Integer> path) {
		int k = P[i][j];
		
		if (k != -1) {
			recuperarCamino_rec(P, i, k, path);
			path.enqueue(k);
			recuperarCamino_rec(P, k, j, path);
		}
		
	}

}
