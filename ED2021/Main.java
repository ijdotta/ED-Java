package ED2021;

import TDALista_desarrollo.Lista_doble_enlazada;
import TDALista_desarrollo.Position;
import TDALista_desarrollo.PositionList;

public class Main {
	
	public static void main(String[] args) {
		PositionList<Integer> l1 = new Lista_doble_enlazada<Integer>();
		
		for (int i = 0; i < 10; i++) {
			l1.addLast(i);
		}
		
		printList(l1);
		
		Services.invertir(l1);
		
		printList(l1);
	}

	private static <E> void printList(PositionList<E> l1) {
		
		for(E elem : l1 ) {
			System.out.print(elem.toString() + " ");
		}
		System.out.println();
	}
	
}
