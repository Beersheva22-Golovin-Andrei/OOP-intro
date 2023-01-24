package telran.recursion;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

public class LinearRecursionTest {
	
	@Test
	void fTest() {
		f(6);
	}
	void f(int a) {
		if (a > 5) {
			f(a - 1);
		}
	}
	@Test
	public void factorialTest() {
		assertEquals(24, LinearRecursion.factorial(4));
		assertEquals(24 * 5 * 6, LinearRecursion.factorial(6));
		assertEquals(24, LinearRecursion.factorial(-4));
	}
	@Test
	void powerTest() {
		
		  assertEquals(1, LinearRecursion.power(1000, 0));
		  assertThrowsExactly(IllegalArgumentException.class, ()->LinearRecursion.power(1000, -1)); 
		  assertEquals(1000, LinearRecursion.power(10, 3)); 
		  assertEquals(-1000, LinearRecursion.power(-10, 3));
	}
	@Test
	void sumArrTest() {
		int ar[] = {1, 2, 3, 4, 5, 6};
		assertEquals(21, LinearRecursion.sumArr(ar));
		assertEquals(0, LinearRecursion.sumArr(new int[0]));
	}
	@Test
	void reverseTest() {
		int ar[] = {1, 2, 3, 4, 5, 6};
		int expected[] = {6, 5, 4, 3, 2, 1};
		int ar1[] = {1, 2, 3, 4, 5, 6, 7};
		int expected1[] = {7, 6, 5, 4, 3, 2, 1};
		LinearRecursion.reverse(ar);
		LinearRecursion.reverse (ar1);
		assertArrayEquals(expected, ar);
		assertArrayEquals(expected1, ar1);
		
		
	}
	
	@Test
	void squareTest() {
		
		  assertEquals(4, LinearRecursion.square(2));
		  assertEquals(1, LinearRecursion.square(1));
		  assertEquals(9, LinearRecursion.square(3));
		  assertEquals(0, LinearRecursion.square(0));
		  
	}

}
