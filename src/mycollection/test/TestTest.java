package mycollection.test;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestTest {


	@Test
	void testnull () {
		System.out.print(Objects.hash(Objects.hash(new String("I love you"))));
	}

}
