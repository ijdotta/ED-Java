package examen_parcial;

import java.util.Comparator;

import ABB.DefaultComparator;

public class ABB<E extends Comparable<E>> {
    protected NodoABB<E> raiz;
    protected Comparator<E> comparador;
   
    public ABB() {
        raiz = new NodoABB<E>(null, null);
        comparador = new DefaultComparator<E>();
    }
   
    // Retorna el nodo raiz del ABB.
    public NodoABB<E> raiz(){ return null; }
   
    // Retorna el nodo del ABB donde se encuentra o deberia encontrarse el elemento e.
    public NodoABB<E> buscar(E e) { return null; }
   
    // Expande el nodo n del ABB, de forma que, luego de la ejecucion del metodo:
    // 1) n contiene a e como elemento.
    // 2) n tiene por hijos izquierdo y derechos dos nodos dummy.
    public void expandir(NodoABB<E> n, E e){  }

    // Elimina el nodo del ABB donde se encuentra el elemento e.
    public void eliminar(E e){  }
}

