package mycollection.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mycollection.Set;

public class SetTest extends CollectionTest {
	
	Set<Integer> set ;
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));

	}

	@Override
	@Test
	void testIterator() {
		
		Integer[] ar = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(ar);
		Integer[]exp = new Integer[ar.length];
		set.toArray(exp);
		Arrays.sort(exp);
		assertArrayEquals(ar, exp);
		Integer[] ar2 = { -5,10,15, 134, 280 };
		Iterator<Integer> itr = set.iterator(); 
		itr.next();
		itr.remove();
		for (int i = 0; i<4; i++) itr.next();
		itr.remove();
		Integer[]exp2 = new Integer[ar.length-2];
		set.toArray(exp2);
		Arrays.sort(exp2);
		assertArrayEquals(ar2, exp2);
	}


}
