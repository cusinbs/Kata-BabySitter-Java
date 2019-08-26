package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class BabySitter {

	private TimeOperations timeOps;
	private String startTime;
	private String bedTime;
	private String endTime;
	private static final String MIN_START_TIME = "17:00";
	private static final String MAX_END_TIME = "04:00";
	private static final double BEFORE_BED_RATE = 12;
	private static final double BED_TO_MID_RATE = 8;
	private static final double MID_TO_END_RATE = 16;

	public BabySitter() { //constructor
		timeOps = new TimeOperations();
	}
	
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime.contains("AM") || startTime.contains("am") || startTime.contains("PM") || startTime.contains("pm")) {
			startTime = timeOps.convertTo24HoursFormat(startTime);
		}
		
		if(!timeOps.inBetween(MIN_START_TIME, MAX_END_TIME, startTime)) {
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
			endTime = timeOps.convertTo24HoursFormat(endTime);
		}
		
		if(!timeOps.inBetween(MIN_START_TIME, MAX_END_TIME, endTime)) {
			System.out.println("End time is outside of time range");
			this.endTime = null;
			return;
		}
		this.endTime = endTime;
	}
	
	public String getBedTime() {
		return this.bedTime;
	}
	
	public void setBedTime(String bedTime) {
		if(bedTime.contains("AM") || bedTime.contains("am") || bedTime.contains("PM") || bedTime.contains("pm")) {
			bedTime = timeOps.convertTo24HoursFormat(bedTime);
		}
		
		if(!timeOps.inBetween("21:00", "23:59", bedTime)) {
			System.out.println("Bed time is outside of time range");
			this.bedTime = null;
			return;
		}
		this.bedTime = bedTime;
	}
	
	public double calculateBeforeBedShift() {
		return 0;
	}
}
