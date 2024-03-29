package mycollection.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mycollection.Collection;

public abstract class CollectionTest {

	protected Integer [] numbers = {10, 100, -5, 134, 280, 120, 15};
	protected Integer ar[] = new Integer[numbers.length + 100];
	protected Collection<Integer> collection;
	protected Integer [] empty = {};
	@BeforeEach
	void setUp() throws Exception {
		
		for(Integer num: numbers) {
			collection.add(num);
		}
	}

	abstract void testAdd();
	
	@Test
	void testIterator() {
		Integer actual[] = new Integer[numbers.length];
		int index = 0;
		Iterator<Integer> it = collection.iterator();
		while(it.hasNext()) {
			actual[index++] = it.next();
		}
		assertArrayEquals(numbers, actual);

	}
	

	@Test
	void testRemove() {
		Integer [] expected = {10, 100, -5,  280, 120, 15};
		assertTrue(collection.remove((Integer)134));
		Arrays.sort(expected);
		Integer [] actual = collection.toArray(empty);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
		assertFalse(collection.remove((Integer)134));
	}

	@Test
	void testRemoveIf() {
		Integer []expected = {-5, 15};
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		assertArrayEquals(expected, collection.toArray(empty));
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> true));
		assertTrue(collection.isEmpty());
		
	}

	@Test
	void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {
		
		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		Arrays.sort(ar,0, collection.size());
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(ar[i], expected[i]);
		}
		for(int i = expected.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
		
	}
	@Test
	void removeIteratorTest() {
		final Iterator <Integer> it = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		Integer num = it.next();
		assertTrue(collection.contains(num));
		it.remove();
		assertFalse(collection.contains(num));
		
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		Iterator<Integer> it1 = collection.iterator();
		
		while(it1.hasNext()) {
			num = it1.next();
		}
		assertTrue(collection.contains(num));
		it1.remove();
		assertFalse(collection.contains(num));
		
		
	}
	@Test
	void nextExceptionIteratorTest() {
		Iterator <Integer> it = collection.iterator();
		while(it.hasNext()) {
			it.next();
		}
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}
	
	@Test
	void shuffelingTest() {
		Integer [] arr1 = collection.toArrayShuffling(new Integer[collection.size()]);
		Integer [] arr2 = collection.toArrayShuffling(new Integer[collection.size()]);
		Integer [] arr3 = collection.toArrayShuffling(new Integer[collection.size()]);
		
		assertFalse(Arrays.equals(arr1, arr2));
		assertFalse(Arrays.equals(arr1, arr3));
		assertFalse(Arrays.equals(arr2, arr3));
		
	}
}
