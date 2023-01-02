package mycollection.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import mycollection.ArrayList;
import mycollection.List;

public class ArrayListTest extends ListTest {

	
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>();
		super.setUp();
	}

	
	List<Integer> testList = new ArrayList<>();
	
	void setAllZeros (int count){
		for (int i=0; i<count; i++) {
			testList.add(0);
		}
	}
	
	
	@Test
	@Disabled
	public void ListTest () {
		assertTrue(testList.isEmpty());
		setAllZeros(15);
		assertEquals(15, testList.size());
		assertTrue(testList.add(5));
		assertEquals(5, testList.get(15));
		assertTrue(testList.add(null));
		testList.add(100);
		assertEquals(18, testList.size());
		assertTrue(testList.contains(null));
		assertEquals(16, testList.indexOf(null));
		testList.set(16, 66);
		assertEquals(66, testList.get(16));
		testList.add(10, 1000);
		assertEquals(1000, testList.get(10));
		assertEquals(19, testList.size());
		//assertTrue(testList.remove(1000));
		assertEquals(18, testList.size());
		assertTrue(testList.removeIf(a->a==0));
		assertEquals(3, testList.size());
		
		assertThrows(IndexOutOfBoundsException.class, ()->testList.get(5));
		
		Object []artrr =  testList.toArray(new Integer[2]);
		assertEquals(3, artrr.length);
	}
	
	void iterTest() {
		
	}

}
