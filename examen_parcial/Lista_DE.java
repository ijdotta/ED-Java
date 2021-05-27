package examen_parcial;

import java.util.Iterator;

import TDALista_desarrollo.BoundaryViolationException;
import TDALista_desarrollo.EmptyListException;
import TDALista_desarrollo.InvalidPositionException;
import TDALista_desarrollo.Position;
import TDALista_desarrollo.PositionList;

/*
 * Implemente una clase Lista_DE que represente el TDA Lista mediante una colección 
 * doblemente enlazada de nodos, sin celdas de encabezamiento (esto es, no existen nodos dummies). 
 * Defina para esto las clases Lista_DE y DNodo, y en ellas, únicamente los atributos de instancia y 
 * el o los constructores que usted considere adecuados y necesarios. Tenga en cuenta que la lista 
 * implementada debe permitir computar las operaciones first(), last() y size() en O(1).

Una vez esto, agregue a la clase Lista_DE la implementación de la operación remove(p). 
Para esta implementación, tenga en cuenta que no puede hacer uso de otras operaciones 
provistas por el TDA Lista, sino que debe valerse únicamente de la estructura subyacente a las 
clases Lista_DE y DNodo. Implemente todo método auxiliar que utilice para resolver dicha operación.
 */

public class Lista_DE<E> implements PositionList<E> {
	
	//Observación: last en O(1) estableciendo siempre al último nodo como prev de head.
	
	protected DNodo<E> head;
	protected int size;
	
	public Lista_DE() {
		//Constructor por defecto
		head = null;
		size = 0;
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> pointer, toRemove = checkPosition(p);
		E temp = null;
		
		if (size == 0)
			throw new InvalidPositionException("Posición no pertenece a la lista. ");
		
		pointer = head;
		do {
			if (pointer == toRemove) {
				temp = pointer.element();
				
				//Actualizar referencias
				pointer.getPrev().setNext(pointer.getNext());
				pointer.getNext().setPrev(pointer.getPrev());
				
				//Anular
				pointer.setElement(null);
				pointer.setNext(null);
				pointer.setPrev(null);
			}
			else
				pointer = pointer.getNext();
			
		} while (pointer != head && temp == null);
		
		return temp;
	}

	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		DNodo<E> toReturn = null;
		
		if (p == null)
			throw new InvalidPositionException("Posición inválida. ");
		else if (p.element() == null)
			throw new InvalidPositionException("Posición eliminada previamente. ");
		
		try {
			toReturn = (DNodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Posición inválida. ");
		}
		
		return toReturn;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
}
