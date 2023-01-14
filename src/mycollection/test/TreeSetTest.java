package mycollection.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mycollection.Set;
import mycollection.TreeSet;

public class TreeSetTest extends SetTest {

	TreeSet <Integer> set;
	  @BeforeEach
	  @Override 
	  void setUp() throws Exception { 
		  collection = new TreeSet<>();
		  super.setUp();
		  set = (TreeSet<Integer>) collection;
		  }
	  
	  @Test
	  void testAddAndIterator () {
		  
		  Set<Integer> trSet = new TreeSet<>();
		  Integer[] arr = new Integer[] {5, 40, 25, 90, 4, 31, 11, 2, 26};		  
		  for (Integer i : arr) {
			  trSet.add(i);
		  }		
		  assertFalse(trSet.add(11));
			  Arrays.sort(arr); 
			  Iterator<Integer> itr = trSet.iterator(); 
			  for (Integer i : arr) { 
				  assertEquals(i, itr.next()); 
			  }
			assertTrue(trSet.contains(25));
			assertFalse(trSet.contains(0));
			 Integer[] arr2 = new Integer[] {25,40,2,12,-6,19,0,11,62,1};
			 Set<Integer> trSet2 = new TreeSet<>();
			 for (Integer i : arr2) {
				  trSet2.add(i);
			  }
			 Arrays.sort(arr2); 
			 Iterator<Integer> itr2 = trSet2.iterator(); 
			 for (Integer i : arr2) { 
				  assertEquals(i, itr2.next()); 
			  }
	  }
	 
	  
}
