package TDACola_desarrollo;

public class Cola_con_arreglo_circular<E> implements Queue<E> {
	
	protected E[] datos;
	protected int front;
	protected int tail;
	
	@SuppressWarnings("unchecked")
	public Cola_con_arreglo_circular(int size) {
		datos = (E[]) new Object[size];
	}
	
	public Cola_con_arreglo_circular() {
		this(10);
	}

	@Override
	public int size() {
		return (datos.length - front + tail) % datos.length;
	}

	@Override
	public boolean isEmpty() {
		return front == tail;
	}

	@Override
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía.");
		return datos[front];
	}

	@Override
	public void enqueue(E element) {
		
		if (size() == datos.length-1)
			extender(10);
		
		datos[tail] = element;
		tail = (tail+1) % datos.length; 
	}
	
	private void extender(int extra_slots) {
		@SuppressWarnings("unchecked")
		E[] aux = (E[]) new Object[datos.length + extra_slots];
		
		int i = 0;
		
		while (front < datos.length)
			aux[i++] = datos[front++];
		
		front = 0;
		
		while (front <= tail)
			aux[i++] = datos[front++];
		
		datos = aux;
		tail = i;
		front = 0;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía.");
		
		E aux = datos[front];
		datos[front] = null;
		front = (front+1) % datos.length;
		
		return aux;
	}
	
	

}
