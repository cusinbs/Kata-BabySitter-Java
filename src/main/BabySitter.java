package main;

public class BabySitter {

	private String startTime;
	private String bedTime;
	private String endTime;
	private static final String MIN_START_TIME = "17:00";
	private static final String MAX_END_TIME = "04:00";
	private static final String MIN_BED_TIME = "21:00";
	private static final String MAX_BED_TIME = "23:59";
	
	
	public BabySitter() { //constructor
		
	}
	
	public String getStartTime() { //getter and setter of startTime
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = validateInput(MIN_START_TIME, MAX_END_TIME, startTime);
	}

	public String getEndTime() { //getter and setter of endTime
		return this.endTime; 
	}

	public void setEndTime(String endTime) {
		this.endTime = validateInput(MIN_START_TIME, MAX_END_TIME, endTime);
	}
	
	public String getBedTime() { //getter and setter of bedTime
		return this.bedTime;
	}
	
	public void setBedTime(String bedTime) { //Bed time should be in the range [21:00-23:59]
		this.bedTime = validateInput(MIN_BED_TIME, MAX_BED_TIME, bedTime);
	}
	
	public double getNightlyCharge() {
		return NightlyChargeCalculator.calculate(startTime, bedTime, endTime);
	}
	
	private String validateInput(String start, String end, String input) { //validate if input is at the right format and is in the desired range
		if(input.contains("AM") || input.contains("am") || input.contains("PM") || input.contains("pm")) {
			 input = TimeOperations.convertTo24HoursFormat(input);
		}
		
		if(!TimeOperations.inBetween(start, end, input)) {
			System.out.println("Input is out of range");
			return null;
		}
		return input;
	}
}
