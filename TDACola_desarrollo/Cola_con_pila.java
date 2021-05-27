package TDACola_desarrollo;

import TDAPila_desarrollo.EmptyStackException;
import TDAPila_desarrollo.Pila_arreglo;

public class Cola_con_pila<E> implements Queue<E> {
	
	/*
	 * idea: usar pila con arreglo
	 * 		dequeue = pop;
	 * 		enqueue = invertir --> pop --> invertir
	 */
	
	protected Pila_arreglo<E> pila;
	
	public Cola_con_pila() {
		pila = new Pila_arreglo<E>();
	}
	

	@Override
	public int size() {
		return pila.size();
	}

	@Override
	public boolean isEmpty() {
		return pila.isEmpty();
	}

	@Override
	public E front() throws EmptyQueueException {
		if (pila.isEmpty())
			throw new EmptyQueueException("Cola vacia.");
		
		E aux = null;
		pila.invertir();
		try {
			aux = pila.top();
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		pila.invertir();
		return aux;
	}

	@Override
	public void enqueue(E element) {
		pila.push(element);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (pila.isEmpty())
			throw new EmptyQueueException("Cola vacia.");
		
		E aux = null;
		pila.invertir();
		try {
			aux = pila.pop();
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		pila.invertir();
		return aux;
	}

}
