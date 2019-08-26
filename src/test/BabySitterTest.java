package test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.BabySitter;

class BabySitterTest {
	
	private BabySitter babysitter;
	
	@BeforeEach
	public void initialized() {
		babysitter = new BabySitter();
	}
	
	@Test
	public void testValidateStartTime() { //start time should be in the range [17:00 - 23:59] and [0:00 - 4:00]
		babysitter.setStartTime("6:00 PM");
		assertTrue(babysitter.getStartTime().equals("18:00")); //test if 12 hrs time format is converted to 24 hours time format
		babysitter.setStartTime("21:00");
		assertTrue(babysitter.getStartTime().equals("21:00")); //test if set method works
		babysitter.setStartTime("16:00"); 
		assertNull(babysitter.getStartTime()); // test input outside the range, should return null
	}
	
	@Test
	public void testValidateEndTime() { //end time should be in the range [17:00 - 23:59] and [0:00 - 4:00]
		babysitter.setEndTime("9:00 PM");
		assertTrue(babysitter.getEndTime().equals("21:00")); //test if 12 hrs time format is converted to 24 hours time format
		babysitter.setEndTime("03:00");
		assertTrue(babysitter.getEndTime().equals("03:00")); //test if set method works
		babysitter.setEndTime("5:00 AM"); 
		assertNull(babysitter.getEndTime()); // test input outside the range, should return null
	}
	
	@Test
	public void testValidateBedTime() { //Bed time should be in the range [21:00-23:59]
		babysitter.setBedTime("9:00 PM");
		assertTrue(babysitter.getBedTime().equals("21:00")); //test if 12 hrs time format is converted to 24 hours time format
		babysitter.setBedTime("23:59");
		assertTrue(babysitter.getBedTime().equals("23:59")); //test if set method works
		babysitter.setBedTime("1:00 AM"); 
		assertNull(babysitter.getBedTime()); // test input outside the range, should return null
		babysitter.setBedTime("20:00"); 
		assertNull(babysitter.getBedTime()); // test input outside the range, should return null
	}
	
	@Test
	public void testCalculateBeforeBedShift() {
		babysitter.setStartTime("6:00 PM");
		babysitter.setBedTime("9:00 PM");
		assertEquals(132, babysitter.calculateBeforeBedShift());
	}

}
