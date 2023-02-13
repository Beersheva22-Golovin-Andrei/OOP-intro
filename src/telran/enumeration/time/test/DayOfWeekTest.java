package telran.enumeration.time.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.enumeration.time.DayOfWeek;

public class DayOfWeekTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void valuesTest() {
		DayOfWeek[] array = DayOfWeek.values();
		assertEquals(DayOfWeek.SUN, array[0]);
		assertEquals(7, array.length);
		
	}
	@Test
	void plusDaysTest() {
		assertEquals(DayOfWeek.TUE, DayOfWeek.TUE.plusDays(700));
		assertEquals(DayOfWeek.THU, DayOfWeek.TUE.plusDays(2));
	}
	@Test
	void minusDaysTest() {
		assertEquals(DayOfWeek.SHABBAT, DayOfWeek.TUE.minusDays(3));
		assertEquals(DayOfWeek.TUE, DayOfWeek.TUE.minusDays(700));
		
	}
	@Test
	void betweenTest() {
		assertEquals(4, DayOfWeek.between(DayOfWeek.THU,DayOfWeek.MON));
		assertEquals(3, DayOfWeek.between(DayOfWeek.MON,DayOfWeek.THU));
	}

}
