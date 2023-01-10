package mycollection.test;

import org.junit.jupiter.api.BeforeEach;

import mycollection.HashSet;


public class HashSetTest extends SetTest {
	
		HashSet<Integer> set;
	  @BeforeEach
	  @Override 
	  void setUp() throws Exception { 
		  collection = new HashSet<>();
		  super.setUp();
		  set = (HashSet<Integer>) collection;
		  }
	
}
