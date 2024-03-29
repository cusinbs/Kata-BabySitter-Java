package main;

public class NightlyChargeCalculator { //Keep the class to calculate nightly charge separate from BabySitter class to follow the SOLID approach (Open-Closed Principle)
	
	private static int startTime;
	private static int bedTime;
	private static int midNight;
	private static int endTime;
	
	private static final String MID_NIGHT = "00:00";
	
	private static final double BEFORE_BED_RATE = 12;
	private static final double BED_TO_MID_RATE = 8;
	private static final double MID_TO_END_RATE = 16;
	
	public static double calculate(String startTime, String bedTime, String endTime) {
		NightlyChargeCalculator.startTime = TimeOperations.convertHoursToIntegerValue(startTime);
		NightlyChargeCalculator.bedTime = TimeOperations.convertHoursToIntegerValue(bedTime);
		NightlyChargeCalculator.midNight = TimeOperations.convertHoursToIntegerValue(MID_NIGHT);
		NightlyChargeCalculator.endTime = TimeOperations.convertHoursToIntegerValue(endTime);
		return calculateTimeFromStartToBed() * BEFORE_BED_RATE + calculateTimeFromBedToMidNight() * BED_TO_MID_RATE + calculateTimeFromMidNightToEnd() * MID_TO_END_RATE;
	}
	
	private static Integer calculateTimeFromStartToBed() {
		if(endTime < bedTime) {
			if(startTime < endTime) {
				return endTime - startTime;
			}else {
				return 0;
			}
		}else {
			if(startTime < bedTime) {
				return bedTime - startTime;
			}else {
				return 0;
			}
		}
	}
	
	private static Integer calculateTimeFromBedToMidNight() {
		if(endTime <= bedTime) {
			return 0;
		}else if(endTime <= midNight) {
			if (startTime < bedTime){
				return endTime - bedTime;
			}else if(startTime < midNight){
				return endTime - startTime;
			}else {
				return 0;
			}
		}else {
			if(startTime < bedTime) {
				return midNight- bedTime;
			}else if(startTime < midNight) {
				return midNight - startTime;
			}else {
				return 0;
			}
		}
	}
	
	private static Integer calculateTimeFromMidNightToEnd() {
		if(endTime > midNight) {
			if(startTime < midNight) {
				return endTime - midNight;
			}else {
				return endTime - startTime;
			}
		}else {
			return 0;
		}
	}
}
