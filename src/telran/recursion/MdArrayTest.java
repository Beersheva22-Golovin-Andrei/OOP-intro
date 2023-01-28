package telran.recursion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MdArrayTest {

	MdArray<Integer> array;
	
	@BeforeEach
	void setUp() {
		array = new MdArray<>(new int[]{10,5,7}, 50); 
	}
	
	@Test
	void forEachTestInt () {
		Integer[] sum = {0}; 
		array.forEach(num -> sum[0]+=num);
		assertEquals(17500, sum[0]);
	}
	
	@Test
	void forEachTestString () {
		MdArray<String> strings = new MdArray<>(new int[]{3, 15,7, 2, 10}, "hello"); 
		Integer[] length = {0}; 
		strings.forEach(str -> length[0]+=str.length());
		assertEquals(31500, length[0]);
	}
	
	@Test
	void toArrayTest() {
		Integer[]exp = new Integer[350];
		for (int i =0; i<exp.length; i++) {
			exp[i] = 50;
		}
		assertArrayEquals(exp, array.toArray(new Integer[1]));
	}
	
	@Test
	void getValueTest() {
		assertThrowsExactly(IllegalStateException.class, ()->array.getValue(new int[] {8,4}));
		assertThrowsExactly(NoSuchElementException.class, ()->array.getValue(new int[] {7,1,1,1}));
		assertThrowsExactly(IndexOutOfBoundsException.class, ()->array.getValue(new int[] {8,6,1}));
		assertEquals(50, array.getValue(new int[] {6,4,3}));
		assertEquals(50, array.getValue(new int[] {0,0,0}));
		
	}
	
	@Test
	void setValueTest() {
		int[]indexes = new int[] {1,4,2};
		array.setValue(indexes, 10);
		assertEquals(10, array.getValue(indexes));	
	}

}
