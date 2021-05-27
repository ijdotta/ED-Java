package TDALista_desarrollo;

public class MyTest {
	
	public static void main(String[] args) {
		//test_invertir();
		//test_clone();
		//test_eliminar_consecutivos();
		//test_zigzag();
		test_proximo_primo();
	}
	
	private static void test_proximo_primo() {
		for (int i = 10; i < 100; i++)
			System.out.println("Próximo primo de " + i + " es " + next_prime_number(i));
	}
	
	private static int next_prime_number(int primo) {
		boolean esPrimo = false;
		
		do {
			esPrimo = true;
			
			for (int i = 2; i < primo && esPrimo; i++)
				esPrimo = primo % i != 0;
			
			if (!esPrimo)
				primo++;
			
		} while (!esPrimo);
		
		return primo;
	}

	@SuppressWarnings("unused")
	private static void test_zigzag() {
		Lista_doble_enlazada<Integer> l1 = new Lista_doble_enlazada<Integer>();
		PositionList<Integer> l2;
		
		for (int i = 0; i < 10; i++)
			l1.addLast(i);
		
		System.out.println("Contenido de l1: ");
		for (Position<Integer> i : l1.positions())
			System.out.print(i.element() + " - ");
		System.out.println();
		
		System.out.println("Generando zigzag ... ");
		l2 = l1.zigzag();
		
		System.out.println("Contenido zigzag ... ");
		for (Position<Integer> i : l2.positions())
			System.out.print(i.element() + " - ");
		System.out.println();
	}

	@SuppressWarnings("unused")
	private static void test_eliminar_consecutivos() {
		Lista_simple_enlazada<String> l = new Lista_simple_enlazada<String>();
		
		String[] s = {"perro", "casa", "mansion", "perro", "casa", "gato"};
		for (int i = 0; i < s.length; i++)
			l.addLast(s[i]);
		
		System.out.println("Contenido actual: ");
		for (Position<String> p : l.positions())
			System.out.print(p.element()+" - ");
		System.out.println();
		
		System.out.println("Eliminar consecutivos [perro] [casa]: ");
		l.eliminar_consecutivos("perro", "casa");
		
		System.out.println("Contenido actual: ");
		for (Position<String> p : l.positions())
			System.out.print(p.element()+" - ");
		
	}
//	private static void test_clone() {
//		Lista_simple_enlazada<String> lista1, lista2;
//		lista1 = new Lista_simple_enlazada<String>();
//		
//		lista1.addLast("n1");
//		lista1.addLast("n2");
//		lista1.addLast("n3");
//		
//		System.out.println("Contenido lista 1: ");
//		for (Position<String> s : lista1.positions())
//			System.out.print(s.element() + " - ");
//		System.out.println();
//		
//		System.out.println("Clonando lista1 en lista2 ...");
//		lista2 = lista1.clone();
//		
//		System.out.println("Contenido lista 2: ");
//		for (Position<String> s : lista2.positions())
//			System.out.print(s.element() + " - ");
//		System.out.println();
//		
//		/*
//		 * Requiere habilitar el método getNode() a la clase lista.
//		 */
//		System.out.println("Comparacion de referencias (resultado falso indica que la clonaciín fue exitosa): ");
//		try {
//			Position<String> p1, p2;
//			p1 = lista1.first();
//			p2 = lista2.first();
//			
//			while (p1 != null) {
//				System.out.print("Nodo p1 == nodo p2 :: " + (lista1.getNode(p1) == lista2.getNode(p2)));
//				p1 = lista1.next(p1);
//				p2 = lista2.next(p2);
//			}
//			
//		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
//			e.printStackTrace();
//		}
//	}

	@SuppressWarnings("unused")
	private static void test_invertir() {
		Lista_doble_enlazada<Integer> list = new  Lista_doble_enlazada<Integer>();
		
		System.out.println("Añadiendo elementos ... ");
		for (int i = 0; i < 10; i++)
			list.addLast(i);
		
		
		System.out.println("Contenido actual: ");
		for (Position<Integer> i : list.positions())
			System.out.print(i.element() + " - ");
		System.out.println();
		
		System.out.println("Invirtiendo ...");
		list.invertir();
		
		System.out.println("Contenido actual: ");
		for (Position<Integer> i : list.positions())
			System.out.print(i.element() + " - ");
		System.out.println();

	}

}
