package practica_parcial;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Lista_invertir {
	
	public static void main(String [] args) {
		PositionList<Integer> list = new DoubleLinkedList<Integer>();
		
		for (int i  = 0; i < 10; i++)
			list.addLast(i);
		
		print(list);
		
		invertir(list);
		
		print(list);
	}
	
	private static void print(PositionList<Integer> list) {
		for (Integer i : list)
			System.out.print(i + " ");
		System.out.println();
	}

	public static <E> void invertir(PositionList<E> list) {
		Position<E> pointer, next;
		E aux;
		
		try {
			pointer = list.isEmpty() ? null : list.first();
			
			while (pointer != null) {
				next = list.last() == pointer ? null : list.next(pointer);
				aux = list.remove(pointer);
				list.addFirst(aux);
				pointer = next;
			}
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
	}
}
