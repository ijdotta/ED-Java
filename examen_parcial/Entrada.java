package examen_parcial;

public class Entrada<K extends Comparable<K>, V> {
	
	protected K key;
	protected V value;
	
	public Entrada(K k, V v) {
		key = k;
		value = v;
	}

}
