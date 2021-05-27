package promocion_ejercicios;

public class RelacionBinaria {
	
	public static void main(String[] args) {
		go();
	}
	
	
	private static void go() {
		boolean [][] a = new boolean[10][10];
		int [] x = {1, 1, 1, 2, 1, 7, 2, 3, 3, 4, 4, 5, 4, 7, 5, 6, 8, 7, 4, 9};
		for (int i = 0; i < x.length; i+=2)
			a[x[i]-1][x[i+1]-1] = true;
		printRelation(a);
		warshall(a);
		printRelation(a);
	}


	private static void printRelation(boolean[][] a) {
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++)
				if (a[i][j])
					System.out.print("("+(i+1)+", "+(j+1)+") ");
		System.out.println();
	}


	private static void warshall(boolean [][] a) {
		for (int k = 0; k < a.length; k++)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a.length; j++)
					a[i][j] = a[i][j] || (a[i][k] && a[k][j]);
	}

}
