package MyTesters;

import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.HeapPriorityQueue;
import TDAColaCP.InvalidKeyException;
import TDAColaCP.PriorityQueue;

public class MyHeapPriorityQueue {

	public static void main(String[] args) {
		test_remove();
	}

	private static void test_remove() {
		PriorityQueue<Integer, Character> q = new HeapPriorityQueue<Integer, Character>();
		int [] ins;
		int [] config1 = {9,8,7,6,5,1,2,3,4};
		int [] config2 = {1,2,3,4,5,6,7,8,9};
		int [] config3 = {9,1,8,2,7,3,6,4,5};
		int [] config4 = {9,8,7,6,5,4,3,2,1};
		
		ins = config4;
		try {
			for (int i = 0; i < ins.length; i++) {
				q.insert(ins[i], (char)('a'+i));
				System.out.println("Inserting :: " + ins[i] + " - now min key is :: " + q.min().getKey());
			}
			
			while (!q.isEmpty()) {
				System.out.println("min :: " + q.min().getKey());
				System.out.println("elem removed :: " + q.removeMin().getKey());
			}
			
		} catch (InvalidKeyException | EmptyPriorityQueueException e) {e.printStackTrace();}
	}

}
