package Testers;

import TDAMapeo_desarrollo.Entry;
import TDAMapeo_desarrollo.InvalidKeyException;
import TDAMapeo_desarrollo.Map;
import TDAMapeo_desarrollo.Mapeo_con_Trie;

public class TrieMapTest {
	
	public static void main(String[] args) {
		//Crear mapeo
		Map<String, Integer> map = new Mapeo_con_Trie<Integer>();
		String[] keys = {"bell", "buy", "bull", "bet", "stop", "stock", "socket"};
		
		try {
			for (int i = 0; i < keys.length; i++) {
				System.out.println("Añadiendo (" + keys[i] + ", " + i + ")");
				map.put(keys[i], i);
				System.out.println("Current longest word: " + ((Mapeo_con_Trie<Integer>) map).longestWord());
			}
			
			printList("Entradas", map.entries());
			
			for (int i = 0; i < keys.length / 2; i++)
				System.out.println("Se removió la clave " + keys[i*2] + " asociada a " + map.remove(keys[i*2]));
			
			printList("Entradas restantes", map.entries());
			
			System.out.println("Vaciando mapeo... ");
			for (String s : keys)
				map.remove(s);
			
			printList("Entradas del mapeo vacío", map.entries());
			
		} catch (InvalidKeyException e) {e.printStackTrace();}
		
	}

	private static void printList(String title, Iterable<Entry<String, Integer>> entries) {
		System.out.print(title + ": {");
		for (Entry<String, Integer> e : entries)
			System.out.print("(" + e.getKey() + ", " + e.getValue() + "), ");
		System.out.println("} ");
	}

}
