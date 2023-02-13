package mycollection.test;

import org.junit.jupiter.api.BeforeEach;

import mycollection.LinkedHashSet;

public class LinkedHashSetTest extends SetTest {
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedHashSet<>();
		super.setUp();
	}
}
