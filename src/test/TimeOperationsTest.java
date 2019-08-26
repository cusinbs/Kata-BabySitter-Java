package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.TimeOperations;

class TimeOperationsTest {
	
	@Test
	public void shouldConvert12HoursTimeTo24HoursTime() {
		assertTrue("22:30".equals(TimeOperations.convertTo24HoursFormat("10:30 PM")));
		assertTrue("10:30".equals(TimeOperations.convertTo24HoursFormat("10:30 AM")));
		assertTrue("00:00".equals(TimeOperations.convertTo24HoursFormat("12:00 AM")));
		assertTrue("12:00".equals(TimeOperations.convertTo24HoursFormat("12:00 PM")));
		assertTrue("04:00".equals(TimeOperations.convertTo24HoursFormat("4:00 AM")));
		assertTrue("04:00".equals(TimeOperations.convertTo24HoursFormat("4:0 AM")));
	}
	
	@Test
	public void validateHoursInputFormat() {
		assertTrue("".equals(TimeOperations.convertTo24HoursFormat("ab:cd PM")));
		assertTrue("".equals(TimeOperations.convertTo24HoursFormat("12:34 CM")));
		assertTrue("".equals(TimeOperations.convertTo24HoursFormat("100:200 AD")));
	}
	
	/**
	 * Testing of validating input in between time range.
	 * Two cases: 1) From afternoon to next day morning (i.e: 17:00 - 4:00) 
	 * 			  2) In the same day (i.e: 12:00 - 16:00)
	 * */
	
	@Test
	public void testInBetweenTimeRange() { //all inclusive
		//Case 1: start time and end time in the same day
		assertTrue(TimeOperations.inBetween("17:00", "22:00", "19:00")); //test inside of range
		assertTrue(TimeOperations.inBetween("17:00", "23:00", "17:00")); //test inclusive
		assertTrue(TimeOperations.inBetween("17:00", "23:00", "23:00")); //test inclusive
		assertFalse(TimeOperations.inBetween("17:00", "23:00", "00:00")); //test outside of range
		
		//Case 2: start time and end time in different days
		assertTrue(TimeOperations.inBetween("17:00", "04:00", "23:00")); //test inside of range
		assertTrue(TimeOperations.inBetween("17:00", "04:00", "03:00")); //test inside of range
		assertTrue(TimeOperations.inBetween("17:00", "04:00", "23:59")); //test inside of range
		assertTrue(TimeOperations.inBetween("17:00", "04:00", "00:00")); //test inside of range
		assertTrue(TimeOperations.inBetween("17:00", "04:00", "17:00")); //test inclusive
		assertTrue(TimeOperations.inBetween("17:00", "04:00", "04:00")); //test inclusive
		assertFalse(TimeOperations.inBetween("17:00", "04:00", "15:00")); //test outside of range
		assertFalse(TimeOperations.inBetween("17:00", "04:00", "05:00")); //test outside of range
	}
	
	@Test
	public void testTimeDifference() {
		//test start time and end time are in same days
		assertEquals(4, TimeOperations.timeDifference("17:00", "21:00")); //test non-odd full-hour
		assertEquals(5, TimeOperations.timeDifference("17:00", "21:20")); //test odd full-hour
		assertEquals(5, TimeOperations.timeDifference("17:00", "21:30")); //test odd full-hour
		
		//test start time and end time are in different days
		assertEquals(2, TimeOperations.timeDifference("23:00", "01:00")); //test non-odd hour
		assertEquals(1, TimeOperations.timeDifference("23:00", "00:00")); //test non-odd hour inclusive
		assertEquals(3, TimeOperations.timeDifference("23:00", "01:30")); //test odd hour inclusive
	}
	
	@Test
	public void testAdjustHoursForCalculationForHoursAfterMidNight() {
		assertEquals(26, TimeOperations.convertHoursToIntegerValue("02:00"));
	}
	
	@Test
	public void testAdjustHoursForCalculationForHoursBeforeMidNight() {
		assertEquals(23, TimeOperations.convertHoursToIntegerValue("23:00"));
	}
}
