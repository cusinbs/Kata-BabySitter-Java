package main;

/**
 * The reason I keep Convert Time separate is in case when we need to use a better way to convert time or use a different format,
 *  we can just modify this class instead of messing with the BabySitter class
 * */

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeOperations {

	public String convertTo24HoursFormat(String input) { 
		SimpleDateFormat TwentyFourHrsFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat TweleveHrsFormat = new SimpleDateFormat("hh:mm a");
		Date date = null;
		try {
			date = (Date) TweleveHrsFormat.parse(input);
		} catch (ParseException e) {
			return ""; //if it is not convertible, return empty string
		}
		//System.out.println(TwentyFourHrsFormat.format(date));
		return TwentyFourHrsFormat.format(date);
	}
	
	public boolean inBetween(String start, String end, String input) {
		LocalTime startTime = LocalTime.parse(start); //convert from string to localtime objects
		LocalTime endTime = LocalTime.parse(end);
		LocalTime userInput = LocalTime.parse(input);
		if(startTime.isBefore(endTime)) { //when start time and end time are on the same day, simply compare them with user input
			return !userInput.isBefore(startTime) && !userInput.isAfter(endTime); //Inclusive
		}else if(startTime.isAfter(endTime)) { //when start time and end time are on different days, check if they are in the range [start, 23:59] or [0:00-end]
			return (!userInput.isBefore(startTime) && !userInput.isAfter(LocalTime.parse("23:59")) 
					|| (!userInput.isBefore(LocalTime.parse("00:00")) && !userInput.isAfter(endTime))); //inclusive
		}else { //when start time and end time are equal, throw an exception
			throw new IllegalArgumentException();
		}
	}

	public Integer timeDifference(String startTime, String endTime) {
		// TODO Auto-generated method stub
		LocalTime start = LocalTime.parse(startTime);
		LocalTime end = LocalTime.parse(endTime);
		if(start.isBefore(end)) {
			System.out.println();
			return (int) Math.round(MINUTES.between(start, end)/60.0);
		}else if(start.isAfter(end)) {
			return (int) Math.round(MINUTES.between(start, LocalTime.parse("23:59"))/60.0) + (int) Math.round(MINUTES.between(LocalTime.parse("00:00"), end)/60.0);
		}else {
			return 0;
		}
	}
}
