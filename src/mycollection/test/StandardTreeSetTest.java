package mycollection.test;

import org.junit.jupiter.api.BeforeEach;

import mycollection.StandardTreeSet;

public class StandardTreeSetTest extends SortedTest {
@BeforeEach
	@Override
void setUp() throws Exception {
	collection = new StandardTreeSet<Integer>();
	super.setUp();
}
}
