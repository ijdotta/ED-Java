package MyTesters;

import Operaciones.Operaciones_con_listas;
import Operaciones.Terna;
import TDALista.DoubleLinkedList;
import TDALista.LinkedList;
import TDALista.Position;
import TDALista.PositionList;

@SuppressWarnings("unused")
public class MyTester_listas {

	public static void main(String[] args) {
//		test_contenido_e_invertido();
//		test_intercalar_sin_repetidos();
//		test_intercalar_sin_repetir_ordenados();
//		test_invertir();
//		test_lista_resumen();
		test_eliminar();
	}
	
	private static void test_eliminar() {
		PositionList<Integer> l1, l2;
		l1 = new LinkedList<Integer>();
		l2 = new LinkedList<Integer>();
		
		int [] i1 = {5,6,3,1,5,9,8,7,4,2,3,5,3};
		int [] i2 = {5,1,3};
		
		for (int i = 0; i < i1.length; i++)
			l1.addFirst(i1[i]);
		
		for (int i = 0; i < i2.length; i++)
			l2.addFirst(i2[i]);
		
		showList(l1,"1");
		showList(l2,"2");
		
		System.out.println("Eliminar sobre l1, con l2 ...");
		Operaciones_con_listas.eliminar(l1, l2);
		
		showList(l1, "1~");
		
	}

	private static void test_lista_resumen() {
		PositionList<Character> l1, l2;
		l1 = new LinkedList<Character>();
		l2 = new LinkedList<Character>();
		PositionList<Terna<Character>> r = new LinkedList<Terna<Character>>();
		
		char [] c1 = {'a', 'a', 's', 'b', 'r', 'b', 'b', 'q'};
		char [] c2 = {'b', 'b', 'f', 'b', 'r', 'z', 'b', 'q', 'z', 'y', 'j'};
		
		for (int i = 0; i < c1.length; i++)
			l1.addFirst(c1[i]);
		for (int i = 0; i < c2.length; i++)
			l2.addLast(c2[i]);
		
		showList(l1, "1");
		showList(l2, "2");
		
		System.out.println("Generando resumen ...");
		Operaciones_con_listas.lista_resumen(l1, l2, r);
		
		System.out.print("[");
		for (Terna<Character> t : r)
			System.out.print(t+", ");
		System.out.println("]");
			
	}

	private static void test_invertir() {
		PositionList<Integer> l1, l2;
		l1 = new DoubleLinkedList<Integer>();
		for (int i = 0; i < 50; i=i+3) 
			l1.addLast(i);
		
		showList(l1, "1");
		
		System.out.println("Invirtiendo l1 ...");
		Operaciones_con_listas.invertir_recursivo(l1);
		
		showList(l1, "1");
	}

	private static void test_intercalar_sin_repetir_ordenados() {
		PositionList<Integer> l1, l2, l3;
		l1 = new DoubleLinkedList<Integer>();
		l2 = new DoubleLinkedList<Integer>();
		
		int[] n1 = {1,2,3,4,5,6,7,8,34,45};
		int [] n2 = {5,6,7,8,17,18,19,33,34};
		
		for (int i = 0; i < n1.length; i++)
			l1.addLast(n1[i]);
		for (int i = 0; i < n2.length; i++) 
			l2.addLast(n2[i]);
		
		showList(l1, "1");
		showList(l2, "2");
		
		System.out.println("Intercalando ordenados sin repetidos ...");
		l3 = Operaciones_con_listas.intercalar_ordenados_sin_repetidos(l1, l2);
		
		showList(l3, "3");
		
	}

	private static void test_intercalar_sin_repetidos() {
		PositionList<Integer> l1, l2, l3;
		l1 = new LinkedList<Integer>();
		l2 = new LinkedList<Integer>();
		
		for (int i = 30; i > 0; i=i-2)
			l1.addLast(i);
		for (int i = 0; i < 30; i=i+3)
			l2.addLast(i);
		
		showList(l1, "1");
		showList(l2, "2");
		
		System.out.println("Intercalando sin repetidos ...");
		l3 = Operaciones_con_listas.intercalar_sin_repetidos(l1, l2);
		
		showList(l3, "3");
	}

	private static void test_contenido_e_invertido() {
		PositionList<Character> l1, l2;
		l1 = new DoubleLinkedList<Character>();
		l2 = new DoubleLinkedList<Character>();
		
		for (int i = 70; i < 80; i++) {
			l1.addLast((char) i);
			l2.addLast((char) i);
		}
		
		for (int i = 79; i >= 70; i--)
			l1.addLast((char) i);
		
		showList(l1, "1");
		showList(l2, "2");
		
		System.out.println("Chequeando contenido_e_invertido :: " + Operaciones_con_listas.contenido_e_invertido(l1, l2));
		
	}
	
	private static <E> void showList(PositionList<E> list, String name) {
		System.out.print("L"+name+" = [");
		for (Position<E> e : list.positions())
			System.out.print(e.element() + ", ");
		System.out.println("] ");
	}

}
