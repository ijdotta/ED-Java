package practica_parcial;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAPila.EmptyStackException;
import TDAPila.LinkedStack;
import TDAPila.Stack;

public class Chequear_formato {
	
	/*
	 * a) | Un m´etodo public static void chequear formato(Queue<Character> q, char x) que
		recibiendo una cola de caracteres q y un caracter x determina si q respeta el siguiente formato: AXA0A, 
		donde A es una cadena de caracteres formada por 1 o m´as caracteres y A0
		corresponde al inverso de A. Puede asumir que el caracter x parametrizado no est´a presente
		en A. Finalmente, calcule el orden del tiempo de ejecuci´on de su soluci´on. Obs.: Se sugiere
		testear su implementaci´on considerando que la cola q y el caracter x son los siguientes:
	 */
	
	public static boolean chequear_formato(String cad, Queue<Character> q, char x) {
		boolean formato = q != null && cad != null && cad.length() >= 1;
		Stack<Character> s = new LinkedStack<Character>();
		int i = 0;
		char c;
		
		q.enqueue(x);
		
		try {
			//AXA'A
			
			//A
			while (formato && i < cad.length() && !q.isEmpty() && (c = q.dequeue()) != x) {
				formato = c == cad.charAt(i++);
				s.push(c);
				q.enqueue(c);
			}
			
			//X
			formato = formato && i < cad.length() && cad.charAt(i) == x;
			i++;
			
			//A'
			while (formato && i < cad.length() && !s.isEmpty())
				formato = cad.charAt(i++) == s.pop();
			
			//A
			while (formato && i < cad.length() && !q.isEmpty())
				formato = cad.charAt(i++) == q.dequeue();
			
			formato = formato && i == cad.length() && q.isEmpty();
			
		} catch (EmptyQueueException | EmptyStackException e) {
			e.printStackTrace();
		}
		
		return formato;
	}

}
