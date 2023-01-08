package mycollection.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mycollection.LinkedList;

public class LinkedListTest extends ListTest {

	LinkedList<Integer> llist;
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
		llist = (LinkedList<Integer>) collection;
	}
	
	
	@Test
	void loopTest (){
		assertFalse(llist.hasLoop());
		assertThrowsExactly(IllegalArgumentException.class, ()->llist.setNext(1,4));
		llist.setNext(4,1);
		assertTrue(llist.hasLoop());
	}
	
	
}
