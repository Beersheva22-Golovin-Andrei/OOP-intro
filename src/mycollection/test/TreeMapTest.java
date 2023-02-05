package mycollection.test;

import org.junit.jupiter.api.BeforeEach;

import mycollection.TreeMap;

public class TreeMapTest extends MapTest {

	@BeforeEach
	@Override
	void setUp() throws Exception {
	 map = new TreeMap<>();
	 super.setUp();
	 
 }
}
