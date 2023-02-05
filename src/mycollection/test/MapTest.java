package mycollection.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mycollection.LinkedList;
import mycollection.List;
import mycollection.Map;
import mycollection.Map.Entry;
import mycollection.Set;

public abstract class MapTest {
Map<Integer, String> map;
	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}
	@Test
	void putTest( ) {
		assertEquals("One", map.put(1, "àçã"));
		assertEquals("àçã", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}
	
	@Test
	void putIfAbsentTest() {
		assertNull(map.putIfAbsent(4, "Four"));
		assertEquals(map.get(4), map.putIfAbsent(4, "tttt"));
	}
	
	@Test
	void getOrDefaultTest() {
		assertEquals("Four", map.getOrDefault(4, "Four"));
		map.put(4, "Four_other");
		assertEquals("Four_other", map.getOrDefault(4, "Four_other"));
	}
	
	@Test
	void containsTest() {
		assertFalse(map.containsKey(4));
		map.put(4, "Four");
		assertTrue(map.containsKey(4));
		assertTrue(map.containsKey(1));
	}
	
	@Test
	void collectionsValueTest() {
		String[] expected = {"One", "Two", "Three","Seven"};
		map.put(7, "Seven");
		String[] array = {"s"};
		assertArrayEquals(expected, map.values().toArray(array));
	}
	
	@Test
	void containsValueTest() {
		assertFalse(map.containsValue("Seven"));
		assertTrue(map.containsValue("Two"));
		map.put(7, "Seven");
		assertTrue(map.containsValue("Seven"));
	}
	
	@Test
	void removeTest() {	
		String[] expected = {"One", "Three"};
		String[] array = {"s"};
		assertNull(map.remove(7));
		map.put(7, "Seven");
		assertEquals("Two",map.remove(2));
		assertEquals("Seven",map.remove(7));
		assertArrayEquals(expected, map.values().toArray(array));
	}
	
	@Test
	void setKeyTest() {
		Integer[] expected = {1, 2, 3};
		Integer[] array = {0};
		Set<Integer> set = map.keySet();
		assertArrayEquals(expected, set.toArray(array));
	}

	@Test
	void setEntryTest() {
		Set<Entry<Integer,String>> set = map.entrySet();
		List<Integer> setKeys = new LinkedList<>();
		List<String> setValues = new LinkedList<>();
			for ( Entry<Integer,String> entry: set) {
				setKeys.add(entry.getKey());
				setValues.add(entry.getValue());
			}
		Integer[] expectedKey = {1, 2, 3};
		Integer[] arrayKey = {0};
		String[] expectedValues = {"One", "Two", "Three"};
		String[] arrayVal = {"s"};
		assertArrayEquals(expectedKey, setKeys.toArray(arrayKey));
		assertArrayEquals(expectedValues, setValues.toArray(arrayVal));
		
	}
}
