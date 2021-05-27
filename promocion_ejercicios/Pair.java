package promocion_ejercicios;

public class Pair<A, B> {
	
	protected A a;
	protected B b;
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	public Pair() {
	}

	public A getA() {
		return a;
	}
	
	public B getB() {
		return b;
	}
	
	public void setA(A a) {
		this.a = a;
	}
	
	public void setB(B b) {
		this.b = b;
	}
	
}
