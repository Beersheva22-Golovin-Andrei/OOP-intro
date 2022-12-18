package memmory.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import memmory.MemoryOperations;

public class MemoryOperationTests {

byte[] array;

@Test
void maxMemoryTest() {
	int maxMemory = MemoryOperations.getMaxAvaliableMemory();
	array = new byte[maxMemory];
	array = null;
	boolean flException = false;
	try {
			array = new byte [maxMemory + 1];			
				
	} catch (Throwable e) {
		flException = true;
	}
	assertTrue(flException);
}

}