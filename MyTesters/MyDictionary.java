package MyTesters;

import TDADiccionario_desarrollo.Diccionario_hash_abierto;
import TDADiccionario_desarrollo.Diccionario_hash_cerrado;
import TDADiccionario_desarrollo.Dictionary;
import TDADiccionario_desarrollo.Entry;
import TDADiccionario_desarrollo.InvalidEntryException;
import TDADiccionario_desarrollo.InvalidKeyException;

public class MyDictionary {
	
	public static void main(String[] args) {
//		test_1();
//		test_looping_hash();
		forced_loop();
	}

	private static void forced_loop() {
		Diccionario_hash_cerrado<Integer, Character> dic = new Diccionario_hash_cerrado<Integer,Character>();
		try {
			for (int i = 0; i <= 20; i++) {
				Entry<Integer,Character> e = dic.insert(i, (char)(i+97));
				System.out.print("size :: " + dic.size() + " --- ");
				showDictionary(dic);
				dic.remove(e);
				System.out.print("size :: " + dic.size() + " --- ");
				showDictionary(dic);
			}
			
			System.out.println("Trying to find key in an empty hash table :: ");
			dic.find(5);
			System.out.println("I don't loop!");
			
		} catch (InvalidEntryException | InvalidKeyException e) {e.printStackTrace();}
	}

	private static void test_looping_hash() {
		Diccionario_hash_cerrado<Integer, Character> dic = new Diccionario_hash_cerrado<Integer,Character>();
		
		try {
			int key = 1;
			
			for (int i = 0; i < 10; i++)
				dic.insert(key, (char)(97+i));
			
			dic.insert(2, 'Z');
			
			showDictionary(dic);
			
			System.out.println("dic.size() :: " + dic.size());
			
			remove(dic);
			
			showDictionary(dic);
			
			System.out.println("dic.size() :: " + dic.size());
			System.out.println("trying to find keys on an empty table :: ");
			System.out.println("Finding k = 1 :: " + dic.find(1));
			System.out.println("Finding k = 2 :: " + dic.find(2));
			System.out.println("Haha, I stopped. ");
		} catch (InvalidKeyException | InvalidEntryException e) {e.printStackTrace();}
		
		
		
	}

	private static <K,V> void remove(Dictionary<K,V> dic) throws InvalidEntryException {
		for (Entry<K,V> e : dic.entries())
			dic.remove(e);
	}

	private static <K,V> void showDictionary(Dictionary<K, V> dic) {
		System.out.println("Dic :: ");
		for (Entry<K,V> e : dic.entries())
			System.out.print("("+e.getKey()+", "+e.getValue()+") ");
		System.out.println();
	}

	private static void test_1() {
		Diccionario_hash_abierto<Integer, Character> dic = new Diccionario_hash_abierto<Integer,Character>();
		
		try {
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < 15; i++)
					dic.insert(i, (char) (i+60));
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		System.out.println("Contenido: ");
		for (Entry<Integer, Character> e : dic.entries())
			System.out.print("("+e.getKey()+","+e.getValue()+")");
		
		System.out.println("Conviertiendo dic a mapeo ...");
		dic.convertir_a_mapeo();
		
		System.out.println("Contenido: ");
		for (Entry<Integer, Character> e : dic.entries())
			System.out.print("("+e.getKey()+","+e.getValue()+")");
	}
}
