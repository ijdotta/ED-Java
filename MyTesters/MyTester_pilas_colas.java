package MyTesters;

import java.util.Random;

import Operaciones.Operaciones_pilas_colas;
import TDACola.ArrayQueue;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAPila.EmptyStackException;
import TDAPila.LinkedStack;
import TDAPila.Stack;

public class MyTester_pilas_colas {
	
	protected static ArrayQueue<Character> q;
	protected static Stack<Queue<Integer>> p1, p2, pout;
	protected static Stack<Queue<Character>> c1, c2, cout;
	
	public static void main(String[] args) {
		/*
		tester_aplanar1();
		tester_aplanar2();
		*/
		tester_chequear_cadena();
	}
	
	private static void tester_chequear_cadena() {
		Operaciones_pilas_colas.chequear_cadena("bzbbxbbzb");
		Operaciones_pilas_colas.chequear_cadena("azaaxaaza");
		Operaciones_pilas_colas.chequear_cadena("abzababxbabazba");
		Operaciones_pilas_colas.chequear_cadena("bzbbxbbzbxazaaxaaza");
		Operaciones_pilas_colas.chequear_cadena("aaabzaaabaaabxbaaabaaazbaaaxabzababxbabazba");
		Operaciones_pilas_colas.chequear_cadena("awgsegrsergserg");
		Operaciones_pilas_colas.chequear_cadena("bzbbxbbzbqewgrergwegr");
		Operaciones_pilas_colas.chequear_cadena("abzababxbabazbaxabzababxbabazba");
	}

	@SuppressWarnings("unused")
	private static void tester_aplanar2() {
		
		c1 = new LinkedStack<Queue<Character>>();
		c2 = new LinkedStack<Queue<Character>>();
		
		System.out.println("Generando p1 ...");
		pilaColasChar(c1, 10);

		System.out.println("Generando p2 ...");
		pilaColasChar(c2, 15);
		
		cout = Operaciones_pilas_colas.aplanar2(c1, c2);
		
		System.out.println("Pila salida: ");
		while (!cout.isEmpty())
			try {
				System.out.print(cout.pop().size() + " - ");
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		
	}

	private static void pilaColasChar(Stack<Queue<Character>> c, int lon) {
		Random r = new Random();
		int size = r.nextInt(15);
		for (int i = 0; i < lon; i++) {
			c.push(genColaChar(size++));
			try {
				System.out.println("Se insertó una cola de tamaño " + c.top().size());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static LinkedQueue<Character> genColaChar(int lon) {
		LinkedQueue<Character> cola = new LinkedQueue<Character>();
		for (int i = 0; i < lon; i++)
			cola.enqueue((char) i);
		return cola;
	}

	@SuppressWarnings("unused")
	private static void tester_aplanar1() {
		
		p1 = new LinkedStack<Queue<Integer>>();
		p2 = new LinkedStack<Queue<Integer>>();
		
		System.out.println("Generando p1 ...");
		pilaColas(p1, 10);

		System.out.println("Generando p2 ...");
		pilaColas(p2, 15);
		
		pout = Operaciones_pilas_colas.aplanar1(p1, p2);
		
		System.out.println("Pila salida: ");
		while (!pout.isEmpty())
			try {
				System.out.print(pout.pop().size() + " - ");
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
	}
	
	private static void pilaColas(Stack<Queue<Integer>> p, int lon) {
		
		Random r = new Random();
		int size = r.nextInt(15);
		for (int i = 0; i < lon; i++) {
			p.push(genColaInt(size++));
			try {
				System.out.println("Se insertó una cola de tamaño " + p.top().size());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static LinkedQueue<Integer> genColaInt(int lon) {
		LinkedQueue<Integer> cola = new LinkedQueue<Integer>();
		for (int i = 0; i < lon; i++)
			cola.enqueue(i);
		return cola;
	}
	
	@SuppressWarnings("unused")
	private void tester_chequear_formato() {
		String s;
		char x = '$';
		q = new ArrayQueue<Character>();
		
		System.out.println("--------------------TEST--------------------");
		
		  s = ""; 
		  setString(s);
		  System.out.println("Chequear string: "+
		  s +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
		  
		  s = "a&aab"; 
		  setString(s); 
		  System.out.println("Chequear string: "+ s
		  +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
		  
		  s = "$"; 
		  setString(s); 
		  System.out.println("Chequear string: "+ s
		  +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
		  
		  s = "a$aa"; 
		  setString(s); 
		  System.out.println("Chequear string: "+ s
		  +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
		  
		  s = "a$a"; 
		  setString(s); 
		  System.out.println("Chequear string: "+ s
		  +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
		 
		s = "abc$cbaaba";
		setString(s);
		System.out.println("Chequear string: "+ s +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
		
		s = "abc$abc";
		setString(s);
		System.out.println("Chequear string: "+ s +" :: "+Operaciones_pilas_colas.chequear_formato(q, x));
	}
	
	private static void setString(String s) {
		for (int i = 0; i < s.length(); i++)
			q.enqueue(s.charAt(i));
	}

	

}
