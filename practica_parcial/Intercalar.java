package practica_parcial;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo_PR2.InvalidKeyException;
import TDAMapeo_PR2.Map;
import TDAMapeo_PR2.OpenHashMap;

public class Intercalar {
	
public static PositionList<Integer> intercalar_sin_repetidos(PositionList<Integer> l1, PositionList<Integer> l2) {
  PositionList<Integer> l3 = new DoubleLinkedList<Integer>();
  Map<Integer,Boolean> repetido = new OpenHashMap<Integer, Boolean>();
  Position<Integer> p1, p2;
  
  try {
    p1 = l1.isEmpty() ? null : l1.first();
    p2 = l2.isEmpty() ? null : l2.first();
    
    do {
      
      p1 = buscarElemento(p1, l1, repetido);
      if (p1 != null) {
        l3.addLast(p1.element());
        p1 = p1 == l1.last()? null : l1.next(p1);
      }
      
      p2 = buscarElemento(p2, l2, repetido);
      if (p2 != null) {
        l3.addLast(p2.element());
        p2 = p2 == l2.last()? null : l2.next(p2);
      }
      
    } while (p1 != null && p2 != null);
    
    if (p1 != null)
      descargar(p1, l1, repetido, l3);
    else
      descargar(p2, l2, repetido, l3);
    
  } catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
    e.printStackTrace();
  }
  
  return l3;
}

private static void descargar(Position<Integer> p, PositionList<Integer> l, Map<Integer, Boolean> repetido,
    PositionList<Integer> l3) {
  
  while (p != null) {
    p = buscarElemento(p, l, repetido);
    if (p != null)
      l3.addLast(p.element());
  }
  
}

private static Position<Integer> buscarElemento(Position<Integer> p, PositionList<Integer> l,
    Map<Integer, Boolean> repetido) {
  boolean encontrado = false;
  
  try {
    while (p != null && !encontrado) {
      if (repetido.get(p.element()) == null) {
        repetido.put(p.element(), true);
        encontrado = true;
      }
      else
    	  p = l.last() == p ? null : l.next(p);
    }
  } catch (EmptyListException | InvalidPositionException | BoundaryViolationException | InvalidKeyException e) {
    e.printStackTrace();
  }
  
  return p;
}

}
