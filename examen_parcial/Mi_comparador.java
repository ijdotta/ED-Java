package examen_parcial;

import java.util.Comparator;

public class Mi_comparador implements Comparator<Integer> {

	/*
	Las claves se ordenan en la cola con el criterio:
		Primero salen las impares ordenadas de mayor a menor
		Después salen las pares ordenadas de mayor a menor
		EJ: 9 7 3 1 8 6 2
	*/

	@Override
	public int compare(Integer i, Integer j) {
		int comp;
		boolean i_par, j_par;
		
		i_par = i % 2 == 0;
		j_par = j % 2 == 0;
		
		if (!i_par && j_par)
			comp = -1;
		else if (i_par && !j_par)
			comp = 1;
		else
			comp = j - i;
		
		return comp;
	}
}
