package mycollection.test;

import org.junit.jupiter.api.BeforeEach;

import mycollection.HashMap;

public class HashMapTest extends MapTest {
	 @BeforeEach
		@Override
		void setUp() throws Exception {
		 map = new HashMap<>();
		 super.setUp();
		 
	 }
}
