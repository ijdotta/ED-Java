package Operaciones;

import java.util.Comparator;

import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.HeapPriorityQueue;
import TDAColaCP.InvalidKeyException;
import TDAColaCP.PriorityQueue;

public class Operaciones_con_colasCP {
	
	protected static int [] S = {4, 5, 1, 3, 2, 6, 7};
	
	/**
	 * Dada una secuencia de valores predefinidos, se muestran estos en orden ascendente
	 * mediante el uso de una cola con prioridad.
	 */
	public static void mostrar_secuencia_ascendente() {
		PriorityQueue<Integer, Integer> q = new HeapPriorityQueue<Integer, Integer>();
		try {
			for (int i = 0; i < S.length; i++)
				q.insert(S[i], null);
			
			mostrar_secuencia_claves(q);
			
		} catch (InvalidKeyException e) {e.printStackTrace();}
	}
	
	/**
	 * Dada una secuencia de valores predefinidos, se muestran estos en orden descendente
	 * mediante el uso de una cola con prioridad.
	 */
	public static void mostrar_secuencia_descendente() {
		PriorityQueue<Integer, Integer> q = new HeapPriorityQueue<Integer, Integer>();
		try {
			for (int i = 0; i < S.length; i++)
				q.insert(-S[i], S[i]);
			
			mostrar_secuencia_valores(q);
			
		} catch (InvalidKeyException e) {e.printStackTrace();}
	}
	
	public static void parcial() {
		PriorityQueue<Integer, Integer> q = new HeapPriorityQueue<Integer, Integer>(new examen_parcial.Mi_comparador());
		
		try {
			for (int i = 1; i <= 8; i++)
				q.insert(i, 0);
		} catch (InvalidKeyException e) {e.printStackTrace();}
		
		mostrar_secuencia_claves(q);
	}
	
	/**
	 * Dada una secuencia de valores predefinidos, se muestran estos en orden descendente
	 * según su prioridad.
	 */
	public static void mostrar_secuencia_especial() {
		PriorityQueue<Integer, Integer> q = new HeapPriorityQueue<Integer, Integer>(new ComparadorEspecial<Integer>());
		try {
			for (int i = 0; i < S.length; i++)
				q.insert(S[i], null);
			
			mostrar_secuencia_claves(q);
			
		} catch (InvalidKeyException e) {e.printStackTrace();}
	}

	/**
	 * Método auxiliar para desencolar una cola con prioridad, mostrando sus valores.
	 * @param q Cola con prioridad a desencolar.
	 */
	private static void mostrar_secuencia_valores(PriorityQueue<Integer, Integer> q) {
		System.out.print("<");
		try {
			while (!q.isEmpty()) {
				System.out.print(q.removeMin().getValue());
				if (!q.isEmpty())
					System.out.print(", ");
				else
					System.out.println(">");
			}
		} catch (EmptyPriorityQueueException e) {e.printStackTrace();}
	}
	
	/**
	 * Método auxiliar para desencolar una cola con prioridad, mostrando sus claves.
	 * @param q Cola con prioridad a desencolar.
	 */
	private static void mostrar_secuencia_claves(PriorityQueue<Integer, Integer> q) {
		System.out.print("<");
		try {
			while (!q.isEmpty()) {
				System.out.print(q.removeMin().getKey());
				if (!q.isEmpty())
					System.out.print(", ");
				else
					System.out.println(">");
			}
		} catch (EmptyPriorityQueueException e) {e.printStackTrace();}
	}
	
	/**
	 * Class ComparadorEspecial - Clase auxiliar que modela un comparador de elementos
	 * según un criterio específico.
	 * @author Ignacio Joaquín Dotta
	 * @param <Integer> Tipo de dato de los elementos a comparar.
	 */
	private static class ComparadorEspecial<Integer> implements Comparator<Integer> {

//		@Override
//		public int compare(Integer k1, Integer k2) {
//			boolean i1_par, i2_par;
//			int i1 = (int) k1, i2 = (int) k2;
//			
//			i1_par = i1 % 2 == 0;
//			i2_par = i2 % 2 == 0;
//			
//			//Entre dos números pares, tiene mayor prioridad el menor de ellos.
//			if (i1_par && i2_par) {
//				if (i1 < i2)
//					return -1;
//				else
//					return 1;
//			}
//			//Entre dos números impares, tiene mayor prioridad el mayor de ellos.
//			else if (!i1_par && !i2_par) {
//				if (i1 > i2)
//					return -1;
//				else
//					return 1;
//			}
//			//Todo número par tiene mayor prioridad que un impar.
//			else {
//				if (i1_par)
//					return -1;
//				else
//					return 1;
//			}
//			
//		}
		
		@Override
		public int compare(Integer k1, Integer k2) {
			boolean i1_par, i2_par;
			int i1 = (int) k1, i2 = (int) k2;
			int comp = 1;
			i1_par = i1 % 2 == 0;
			i2_par = i2 % 2 == 0;
			
			//Entre dos números pares, tiene mayor prioridad el menor de ellos.
			if (i1_par && i2_par) {
				if (i1 < i2)
					comp *= -1;
			}
			//Entre dos números impares, tiene mayor prioridad el mayor de ellos.
			else if (!i1_par && !i2_par) {
				if (i1 > i2)
					comp *= -1;
			}
			//Todo número par tiene mayor prioridad que un impar.
			else {
				if (i1_par)
					comp *= -1;
			}
			
			return comp;
			
		}
		
	}
	
}
