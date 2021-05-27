package TDACola_desarrollo;

import TDAPila_desarrollo.Nodo;

public class Cola_con_enlaces<E> implements Queue<E> {
	
	protected Nodo<E> head;
	protected Nodo<E> tail;
	protected int size;
	
	public Cola_con_enlaces() {
		head = tail = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacia.");
		return head.getItem();
	}

	@Override
	public void enqueue(E element) {
		Nodo<E> nodo = new Nodo<E>(element);
		if (size == 0)
			head = nodo;
		else
			tail.setNext(nodo);
		
		size++;
		tail = nodo;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (size == 0)
			throw new EmptyQueueException("Cola vacia.");
		
		E temp = head.getItem();
		head = head.getNext();
		size--;
		if (size == 0)
			tail = 
			null;
		
		return temp;
	}
	
	public void invertir() {
		invertir_aux(head, size);
	}
	
	public void invertir_aux(Nodo<E> local_head, int local_size) {
		if (local_size == 1)
			head = local_head;
		if (local_size > 1) {
			invertir_aux(local_head.getNext(), local_size-1);
			tail.setNext(local_head);
			tail = local_head;
		}
	}
	
	public void invertir_2() {
		invertir_rec(size);
	}

	private void invertir_rec(int size2) {
		if (size2 > 1) {
			try {
				E aux = dequeue();
				invertir_rec(size2-1);
				enqueue(aux);
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
			
		}
	}

}
