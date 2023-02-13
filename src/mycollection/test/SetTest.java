package mycollection.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.management.DescriptorKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
}
