package examen_parcial;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Tester_listas {
	
	protected static PositionList<Integer> list;

	public static void main(String[] args) {
		initialize();
		print();
		
		//------------------------AREA DE TESTING------------------------//
		
		
		
		//------------------------AREA DE TESTING------------------------//
		
	}

	private static void print() {
		for (Integer i : list)
			System.out.print(i + " ");
		System.out.println();
	}

	private static void initialize() {
		list = new DoubleLinkedList<Integer>();
		for (int i = 0; i < 10; i++)
			list.addLast(i);
	}
	
	

}
