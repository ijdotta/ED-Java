package TDACola_desarrollo;

public class My_tester {

	public static void main(String[] args) {
		
		Cola_con_enlaces<Integer> cola = new Cola_con_enlaces<Integer>();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("Encolando: "+i);
			cola.enqueue(i);
		}
		
		try {
			System.out.println("front: " + cola.front());
		} catch (EmptyQueueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Inviertiendo... ");
		
		cola.invertir();
		
		try {
			System.out.println("front: " + cola.front());
		} catch (EmptyQueueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while (!cola.isEmpty())
				System.out.println(cola.dequeue());
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		
	}

}
