package Operaciones;

import TDAPila_desarrollo.EmptyStackException;
import TDAPila_desarrollo.Pila_arreglo;
import TDAPila_desarrollo.Pila_con_enlaces;
import TDAPila_desarrollo.Stack;

public class Operaciones_con_pilas {
	
	public static void invertir_arreglo(int [] a) {
		Pila_con_enlaces<Integer> pila = new Pila_con_enlaces<Integer>();
		for (int i = 0; i < a.length; i++) {
			pila.push(a[i]);
		}
		
		int i = 0;
		while (!pila.isEmpty()) {
			try {
				a[i++] = pila.pop();
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public static <E> void invertir(Stack<E> p) {
		Pila_arreglo<E> aux = new Pila_arreglo<E>(p.size());
		
		while (!p.isEmpty()) {
			try {
				aux.push(p.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		
		aux.invertir();
		
		while (!aux.isEmpty()) {
			try {
				p.push(aux.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Stack<Integer> aplanar(Stack<Stack<Integer>> ppe) {
		
		Pila_arreglo<Integer> pe = new Pila_arreglo<Integer>();
		
		while (!ppe.isEmpty()) {
			try {
				Stack<Integer> aux = ppe.pop();
				while (!aux.isEmpty())
					pe.push(aux.pop());
				
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		
		pe.invertir();
		
		return pe;
	}

}
