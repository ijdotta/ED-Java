package Operaciones;

public class Terna<E> {
	protected E element;
	protected int cant1, cant2;
	
	public Terna(E e, int c1, int c2) {
		element = e;
		cant1 = c1;
		cant2 = c2;
	}
	
	public E getElement() {
		return element;
	}

	public int getCant1() {
		return cant1;
	}

	public int getCant2() {
		return cant2;
	}

	public String toString() {
		return "(" + element.toString() + ", " + cant1 + ", " +cant2+ ")";
	}
}
