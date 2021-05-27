package Operaciones;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAPila.EmptyStackException;
import TDAPila.LinkedStack;
import TDAPila.Stack;

/**
 * 
 * @author Ignacio Joaquín Dotta
 *
 */

public class Operaciones_pilas_colas {
	
	/**
	 * @version 1.2
	 * @param cad Recibe una cadena de caracteres y chequea su formato
	 */
	public static void chequear_cadena(String cad) {
		//new version
		boolean condition = true;
		LinkedStack<Character> pila = new LinkedStack<Character>();
		LinkedQueue<Character> cola1, cola2;
		cola1 = new LinkedQueue<Character>();
		cola2 = new LinkedQueue<Character>();
		int i = 0;
		
		
		try {
			
			char c;
			
			while (i < cad.length()) {
				
			//read and push until x, checking CzCC format
				while (i < cad.length() && (c = cad.charAt(i)) != 'x') {
					
				//read and push until z (and enqueue without z)
					while (i < cad.length() && (c = cad.charAt(i)) != 'z') {
						pila.push(c);
						cola1.enqueue(c);
						cola2.enqueue(c);
						i++;
					}
					
					pila.push('z'); i++;
					
				//read, push and dequeue iff front queue1 == cad i
					
					while (i < cad.length() && !cola1.isEmpty() && condition) {
						if ((c = cad.charAt(i)) == cola1.dequeue())
							pila.push(c);
						else
							condition = false;
						i++;
					}
					
				//read, push and dequeue iff front queue2 == cad i
					
					while (i < cad.length() && !cola2.isEmpty() && condition) {
						if ((c = cad.charAt(i)) == cola2.dequeue())
							pila.push(c);
						else
							condition = false;
						i++;
					}
				}
				
				i++; //saltea la x
				
			//read and pop until x or empty
				while (i < cad.length() && !pila.isEmpty() && condition) {
					condition = cad.charAt(i) == pila.pop();
					i++;
				}
				
				//o bien se termino la cadena, o bien tengo una x en frente, then:
				i++;
			}
		//finally :: condition = condition && !empty stack && cad end 
			
			condition = condition && pila.isEmpty() && i >= cad.length();
			
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		
		if (condition) System.out.println("CADENA "+cad+" ACEPTADA");
		else System.out.println("CADENA "+cad+" RECHAZADA");
		
	}
	
	public static boolean chequear_formato(Queue<Character> q, char x) {
		LinkedStack<Character> aux1, aux2; 
		aux1 = new LinkedStack<Character>();
		aux2 = new LinkedStack<Character>();
		boolean formato = false;
		
		try {
			
			char c = ' ';
			while (!q.isEmpty() && (c = q.dequeue()) != x)
				aux1.push(c);
			
			formato = c == x;
			
			if (!q.isEmpty()) {
				
				while (!q.isEmpty() && !aux1.isEmpty() && formato) {
					formato = q.dequeue() == (c = aux1.pop());
					aux2.push(c);
				}
				
				formato = !q.isEmpty();
				
				while (!q.isEmpty() && !aux2.isEmpty() && formato)
					formato = q.dequeue() == (c = aux2.pop());
				
			}
			
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		
		return formato;
		
	}
	
	public static Stack<Queue<Integer>> aplanar1(Stack<Queue<Integer>> p1, Stack<Queue<Integer>> p2) {
		
		LinkedStack<Queue<Integer>> output = new LinkedStack<Queue<Integer>>();
		
		p1 = invertir(p1);
		p2 = invertir(p2);
		
		try {
			while (!p1.isEmpty() && !p2.isEmpty())
				if (p1.top().size() < p2.top().size())
					output.push(p1.pop());
				else
					output.push(p2.pop());
			
			while (!p1.isEmpty())
				output.push(p1.pop());
			
			while (!p2.isEmpty())
				output.push(p2.pop());
			
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static Stack<Queue<Character>> aplanar2(Stack<Queue<Character>> p1, Stack<Queue<Character>> p2) {
		
		LinkedStack<Queue<Character>> output = new LinkedStack<Queue<Character>>();
		
		p1 = invertir(p1);
		p2 = invertir(p2);
		
		try {
			while (!p1.isEmpty() && !p2.isEmpty())
				if (detSize(p1.top()) < detSize(p2.top()))
					output.push(p1.pop());
				else
					output.push(p2.pop());
			
			while (!p1.isEmpty())
				output.push(p1.pop());
			
			while (!p2.isEmpty())
				output.push(p2.pop());
			
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private static <E> Stack<Queue<E>> invertir(Stack<Queue<E>> pila) {
		
		LinkedStack<Queue<E>> aux = new LinkedStack<Queue<E>>();
		
		while (!pila.isEmpty())
			try {
				aux.push(pila.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		
		return aux;
	}
	
	private static <E> int detSize(Queue<E> queue) {
		LinkedQueue<E> aux = new LinkedQueue<E>();
		int size = 0;
		
		try {
			while (!queue.isEmpty()) {
				size++;
				aux.enqueue(queue.dequeue());
			}
			
			
			/*
			 * devuelve los elementos a la cola original.
			 * mas eficiente sería actualizar la referencia de queue con aux, pero 
			 * requiere cambios en la clase cliente
			 */
			while (!aux.isEmpty())
				queue.enqueue(aux.dequeue());
				
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		
		return size;
	}
}
