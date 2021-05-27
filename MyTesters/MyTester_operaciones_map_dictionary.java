package MyTesters;

import Operaciones.Operaciones_con_mapeos_y_diccionarios;
import TDADiccionario.Dictionary;
import TDAMapeo.MapOpenHash;
import TDAMapeo_PR2.Entry;
import TDAMapeo_PR2.InvalidKeyException;
import TDAMapeo_PR2.Map;
import TDAMapeo_PR2.OpenHashMap;

public class MyTester_operaciones_map_dictionary {

	public static void main(String[] args) {
		tester_contiene_claves();
		tester_acomodar();
		tester_convertir_a_diccionario();
	}

	private static void tester_convertir_a_diccionario() {
		Dictionary<Integer, String> d1;
		Map<String, Integer> m1 = new OpenHashMap<String, Integer>();
		
		try {
			m1.put("Hola", 1);
			m1.put("Chau", 1);
			m1.put("Deberes", 1);
			m1.put("que", 3);
			m1.put("decis", 6);
			
		} catch (InvalidKeyException e) {e.printStackTrace();}
		
		show_map(m1, "Mapeo m1: ");
		System.out.println("Convirtiendo a diccionario ...");
		d1 = Operaciones_con_mapeos_y_diccionarios.convertir_a_diccionario(m1);
		show_dictionary(d1, "Diccionario converted: ");
		
		
	}

	private static void show_dictionary(Dictionary<Integer, String> d1, String string) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.print("("+i+", {");
				for (TDADiccionario.Entry<Integer, String> e : d1.findAll(i))
					System.out.print(e.getValue()+", ");
				System.out.println("}) ");
			}
		} catch (TDADiccionario.InvalidKeyException e) {e.printStackTrace();}
	}

	private static void tester_acomodar() {
		Map<Integer, Character> m1 = new OpenHashMap<Integer, Character>();
		
		try {
			for (int i = 0; i < 30; i++)
				m1.put(i , (char) (i%7 + 65));
		} catch (TDAMapeo_PR2.InvalidKeyException e) {e.printStackTrace();}
		
		show_map(m1, "Mapeo m1");
		System.out.println("Acomodando m1 ...");
		Operaciones_con_mapeos_y_diccionarios.acomodar(m1);
		show_map(m1, "Mapeo m1 ACOMODADO");
	}

	private static void tester_contiene_claves() {
		Map<Integer, Character> m1, m2;
		
		//Inicialización correcta:
		m1 = new OpenHashMap<Integer, Character>();
		m2 = new OpenHashMap<Integer, Character>();
		for (int i = 0; i < 10; i++) {
			try {
				m1.put(i-(i%2), (char) (i*i));
				m2.put(i, (char) (i+70));
			} catch (InvalidKeyException e) {e.printStackTrace();}
		}
		
		
		show_map(m1, "M1");
		show_map(m2, "M2");
		System.out.println("m1 contenido en m2 :: " + Operaciones_con_mapeos_y_diccionarios.contiene_claves(m1,m2));

		//Inicialización incorrecta:
		m1 = new OpenHashMap<Integer, Character>();
		m2 = new OpenHashMap<Integer, Character>();
		for (int j = 0, i = 0; i < 10; i++, j+=2) {
			try {
				m1.put(j, (char) (i*j));
				m2.put(i, (char) (i+70));
			} catch (InvalidKeyException e) {e.printStackTrace();}
		}
		
		show_map(m1, "M1");
		show_map(m2, "M2");
		System.out.println("m1 contenido en m2 :: " + Operaciones_con_mapeos_y_diccionarios.contiene_claves(m1,m2));
		
	}

	private static <K,V> void show_map(Map<K,V> map, String title) {
		System.out.print(title);
		for (Entry<K, V> e : map.entries())
			System.out.print("("+e.getKey()+","+e.getValue()+"), ");
		System.out.println();
	}
	
	

}
