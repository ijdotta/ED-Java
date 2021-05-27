package TDAPila_desarrollo;

public class Pila_arreglo<E> implements Stack<E> {
	
	protected E[] datos;
	protected int tope;
	
	@SuppressWarnings("unchecked")
	public Pila_arreglo(int tam) {
		datos = (E[]) new Object[tam];
		tope = -1;
	}
	
	public Pila_arreglo() {
		this(20);
	}
	
	@Override
	public int size() {
		return tope+1;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila vacia. No existe item tope.");
		return datos[tope];
	}

	@Override
	public void push(E element) {
		if (size() == datos.length)
			extender();
		datos[++tope] = element;
	}

	private void extender() {
		extender(10);
	}

	@SuppressWarnings("unchecked")
	private void extender(int lon) {
		E[] aux = (E[]) new Object[size()+lon];
		
		/*
		 * opto por size() en lugar de datos.length para permitir que en algun momento
		 * pueda usarse el método extender sin necesidad de que el arreglo haya superado su capacidad
		 */
		
		for (int i = 0; i < size(); i++)
			aux[i] = datos[i];
		
		datos = aux;
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila vacia. No item to pop.");
		E aux = datos[tope];
		datos[tope] = null;
		tope--;
		return aux;
	}
	
	public void invertir() {
		int izq = 0, der = tope;
		while (izq < der) {
			E aux = datos[izq];
			datos[izq] = datos[der];
			datos[der] = aux;
			izq++;
			der--;
		}
	}

}
