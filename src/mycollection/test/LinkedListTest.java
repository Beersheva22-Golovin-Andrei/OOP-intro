package mycollection.test;

import org.junit.jupiter.api.BeforeEach;

import mycollection.LinkedList;

public class LinkedListTest extends ListTest {

	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	
	
}
