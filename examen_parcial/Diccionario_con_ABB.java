package examen_parcial;

import TDADiccionario_desarrollo.Dictionary;
import TDADiccionario_desarrollo.EntradaComparable;
import TDADiccionario_desarrollo.Entry;
import TDADiccionario_desarrollo.InvalidEntryException;
import TDADiccionario_desarrollo.InvalidKeyException;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Diccionario_con_ABB<K extends Comparable<K>,V> implements Dictionary<K,V>{
    protected ABB<EntradaComparable<K,PositionList<Entry<K,V>>>> abb;
    protected int size;
   
    public Diccionario_con_ABB(){
        abb = new ABB<EntradaComparable<K,PositionList<Entry<K,V>>>>();
        size = 0;
    }
    
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		PositionList<Entry<K, V>> list;
		Position<Entry<K, V>> pointer;
		EntradaComparable<K, PositionList<Entry<K, V>>> entrada;
		NodoABB<EntradaComparable<K, PositionList<Entry<K, V>>>> nodo;
		
		//Chequeo de entrada
		if (e == null || e.getKey() == null)
			throw new InvalidEntryException("Entrada inválida. ");
		
		//Búsqueda del nodo asociado a la clave de e.
		entrada = new EntradaComparable<K, PositionList<Entry<K, V>>>(e.getKey(), null);
		nodo = abb.buscar(entrada);
		
		//Si es dummy, la clave no está en el dicionario
		if (nodo.element() == null)
			throw new InvalidEntryException("Entrada no pertenece al diccionario. ");
		
		try {
			
			//Búsqueda de la posición de la entrada e.
			list = nodo.element().getValue();
			pointer = list.isEmpty() ? null : list.first();
			while (pointer != null && pointer.element() != e)
				pointer = list.last() == pointer ? null : list.next(pointer);
			
			if (pointer == null)
				throw new InvalidEntryException("Entrada no pertenece al diccionario. ");
			
			list.remove(pointer);
			size--;
			
			//Si se vació la lista -> la clave ya no pertenece al diccionario.
			if (list.isEmpty())
				abb.eliminar(entrada);
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException | InvalidEntryException e1) {
			e1.printStackTrace();
		}
		
		return e;
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}
}
