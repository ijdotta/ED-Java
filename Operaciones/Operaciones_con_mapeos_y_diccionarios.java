package Operaciones;

import java.util.Iterator;

import TDADiccionario.Dictionary;
import TDADiccionario.DictionaryOpenHash;
import TDADiccionario.InvalidKeyException;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;
import TDAMapeo.MapOpenHash;
import TDAMapeo_PR2.Entry;
import TDAMapeo_PR2.Map;
import TDAMapeo_PR2.OpenHashMap;

public class Operaciones_con_mapeos_y_diccionarios {
	
	public static <K,V> boolean contiene_claves(Map<K,V> m1, Map<K,V> m2) {
		Iterable<K> keys_m2;
		Iterator<K> it_keys_m1, it_keys_m2;
		boolean contiene = true;
		
		it_keys_m1 = m1.keys().iterator();
		keys_m2 = m2.keys();
		
		//Mientras se cumpla la condición se toma cada clave de m1
		while (contiene && it_keys_m1.hasNext()) {
			K current_key1 = it_keys_m1.next();
			contiene = false;
			it_keys_m2 = keys_m2.iterator();
			
			//Se busca en las claves de m2 hasta encontrar una equivalente a m1
			while (!contiene && it_keys_m2.hasNext())
				contiene = it_keys_m2.next().equals(current_key1);
			
		}
		
		return contiene;
	}
	
	public static <K,V> PositionList<Entry<K,V>> contiene_claves_y_valores_diferentes(Map<K,V> m1, Map<K,V> m2) {
		PositionList<Entry<K,V>> list = new DoubleLinkedList<Entry<K,V>>();
		Iterable<Entry<K,V>> entries_m2 = m2.entries();

			for (Entry<K,V> e1 : m1.entries()) {
				
				Entry<K,V> e2 = null;
				Iterator<Entry<K,V>> it2 = entries_m2.iterator();
				boolean encontre = false;
				
				while (!encontre && it2.hasNext()) {
					e2 = it2.next();
					encontre = e1.getKey().equals(e2.getKey());
				}
				
				if (encontre && !e1.getValue().equals(e2.getValue()))
					list.addLast(e1);
					list.addLast(e2);
				
			}

		return list;
	}
	
	public static <K,V> void acomodar(Map<K,V> m1) {
		Map<V,K> compactador = new OpenHashMap<V,K>();
		
		try {
			
			//Traslada todas las claves al compactador
			for (Entry<K,V> e : m1.entries()) {
				compactador.put(e.getValue(), e.getKey());
				m1.remove(e.getKey());
			}
			
			//Reinserta todas las claves en m1, acomodadas.
			for (Entry<V,K> e : compactador.entries())
				m1.put(e.getValue(), e.getKey());
			
		} catch (TDAMapeo_PR2.InvalidKeyException e) {e.printStackTrace();}
		
	}
	
	public static Dictionary<Integer, String> convertir_a_diccionario(Map<String, Integer> m1) {
		Dictionary<Integer, String> d1 = new DictionaryOpenHash<Integer, String>();
		
		try {
			for (Entry<String, Integer> e : m1.entries())
				d1.insert(e.getValue(), e.getKey());
		} catch (InvalidKeyException e) {e.printStackTrace();}
			
		return d1;
	}

}