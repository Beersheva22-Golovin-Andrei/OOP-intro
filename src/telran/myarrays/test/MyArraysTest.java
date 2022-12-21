package telran.myarrays.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import telran.myarrays.EvenOddComparator;
import telran.myarrays.MyArrays;
import telran.myarrays.StringLengthComparator;

public class MyArraysTest {

	@Test
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		Integer [] ar = {3, 2, 1};
		
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		MyArrays.sort(ar, (a, b) -> a - b);
		
	}
	
	@Test
	void evenOddTest() {
	Integer numbers[] = {13,2, -8,47, 100, 10, 7};
	Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
	MyArrays.sort(numbers, new EvenOddComparator());
	assertArrayEquals(expected, numbers);
	}
	
	@Test
	void www() {
		Integer a = 5;
		System.out.println (a instanceof Number);
	}

}
