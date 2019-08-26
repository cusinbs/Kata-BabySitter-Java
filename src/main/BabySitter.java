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
	
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime.contains("AM") || startTime.contains("am") || startTime.contains("PM") || startTime.contains("pm")) {
			startTime = TimeOperations.convertTo24HoursFormat(startTime);
		}
		
		if(!TimeOperations.inBetween(MIN_START_TIME, MAX_END_TIME, startTime)) {
			System.out.println("Start time is outside of time range");
			this.startTime = null;
			return;
		}
			
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		if(endTime.contains("AM") || endTime.contains("am") || endTime.contains("PM") || endTime.contains("pm")) {
			endTime = TimeOperations.convertTo24HoursFormat(endTime);
		}
		
		if(!TimeOperations.inBetween(MIN_START_TIME, MAX_END_TIME, endTime)) {
			System.out.println("End time is outside of time range");
			this.endTime = null;
			return;
		}
		this.endTime = endTime;
	}
	
	public String getBedTime() {
		return this.bedTime;
	}
	
	public void setBedTime(String bedTime) { //Bed time should be in the range [21:00-23:59]
		if(bedTime.contains("AM") || bedTime.contains("am") || bedTime.contains("PM") || bedTime.contains("pm")) {
			bedTime = TimeOperations.convertTo24HoursFormat(bedTime);
		}
		
		if(!TimeOperations.inBetween(MIN_BED_TIME, MAX_BED_TIME, bedTime)) {
			System.out.println("Bed time is outside of time range");
			this.bedTime = null;
			return;
		}
		this.bedTime = bedTime;
	}
	
	public double getNightlyCharge() {
		NightlyChargeCalculator calculator = new NightlyChargeCalculator(startTime, bedTime, endTime);
		return calculator.calculate();
	}
}
