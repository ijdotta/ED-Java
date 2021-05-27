package practica_parcial;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Tester {

	public static void main(String[] args) {
		PositionList<Integer> l1, l2, l3, l4;
		l1 = new DoubleLinkedList<Integer>();
		l2 = new DoubleLinkedList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			l1.addLast(i);
			if (i % 2 == 0)
				l2.addLast(i);
//			else
//				l2.addLast(i+1);
		}
		
		print(l1);
		print(l2);
		
		l3 = Intercalar.intercalar_sin_repetidos(l1, l2);
		
		print(l3);
	}

	private static void print(PositionList<Integer> l) {
		System.out.println("[");
		for (Integer i : l)
			System.out.print(i + ", ");
		System.out.println("]");
	}

}
