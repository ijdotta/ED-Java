package promocion_ejercicios;

import TDAGrafo.Graph;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;

public class Algorithms {
	
	/**
	 * Retorna la matriz M* resultante de aplicar la clausura transitiva a un grafo g,
	 * mediante el algoritmo de Warshall. 
	 * @param <V> Tipo de dato del rótulo de los vétrtices.
	 * @param <E> Tipo de dato del rótulo de los arcos.
	 * @param g Grafo
	 * @return Matriz de adyacencias de la clausura transitiva del grafo g.
	 */
	public static <V, E> boolean[][] warshall(Graph<V, E> g) {
		boolean [][] W = toAdjacencyMatrix(g);
		
		//Cáclula la clausura transitiva en la matriz de adyacencias.
		for (int k = 0; k < W.length; k++)
			for (int i = 0; i < W.length; i++)
				for (int j = 0; j < W.length; j++)
					W[i][j] = W[i][j] || (W[i][k] && W[k][j]);
		
		return W;
	}
	
	/**
	 * Dado un grafo g, computa y retorna la matriz de adyacencias de este.
	 * @param <V> Tipo de dato del rótulo de los vétrtices.
	 * @param <E> Tipo de dato del rótulo de los arcos.
	 * @param g Grafo
	 * @return Matriz de adyacencias del grafo g.
	 */
	private static <V, E> boolean [][] toAdjacencyMatrix(Graph<V, E> g) {
		int i = 0, j = 0;
		boolean [][] M = new boolean[10][10];
		
		try {
			
			for (Vertex<V> v : g.vertices()) {
				j = 0;
				for (Vertex<V> w : g.vertices()) {
					
					if (j == M.length)
						M = resize(M);
					
					if (v == w || g.areAdjacent(v, w))
						M[i][j] = true;
					
					j++;
				}
				i++;
			}
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
		
		return M;
	}

	private static boolean[][] resize(boolean[][] M) {
		int new_length = M.length * 2;
		boolean [][] new_M = new boolean[new_length][new_length];
		
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				new_M[i][j] = M[i][j];
			}
		}
		
		return new_M;
	}
	
}
