package TDALista_desarrollo;

public class NodeSandbox<E> {

	public static void main(String[] args) {
		Node<String> n1, n2, n3, headerClone;
		n1 = new Node<String>("n1");
		n2 = new Node<String>("n2");
		n3 = new Node<String>("n3");
		n1.setNext(n2);
		n2.setNext(n3);
		headerClone = n1.clone();
		
		System.out.println("n :: "+n1.element()+" n:: "+n2.element()+" n:: "+n3.element());
		System.out.print("h :: "+headerClone.element()+" ");
		System.out.print("h :: "+headerClone.getNext().element()+" ");
		System.out.print("h :: "+headerClone.getNext().getNext().element()+" ");
		System.out.println("n1 == headerClone? : "+ (n1==headerClone) );
	}
	
	static class Node<T> implements Position<T> {
		
		protected T element;
		protected Node<T> next;
		
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
		
		public Node(T element) {
			this(element, null);
		}
		
		public void setElement(T element) {
			this.element = element;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		@Override
		public T element() {
			return element;
		}
		
		@Override
		public Node<T> clone() {
			/*
			 * El parámetro next.clone() desencadena una clonacion en cadena, por lo que
			 * la clonacion de la lista solo requeriría enviar el mensaje clone al primer elemento,
			 * head, y luego cada nodo siguiente llamaría al clone de su nodo siguiente.
			 * 
			 * Entonces, sería necesario definir una condicion de corte para no enviar el mensaje 
			 * null.clone() en el nodo final.
			 */
			
			Node<T> node = new Node<T>(this.element);
			if (next != null)
				node.setNext(next.clone());
			
			return node;
		}
	}

}
