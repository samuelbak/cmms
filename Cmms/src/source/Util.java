package source;

import java.util.Calendar;

public class Util {

	public static String getCurrentDateInString(){
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		int month = today.get(Calendar.MONTH)+1;
		String monthInString;
		if (month<10)
			monthInString = "0"+String.valueOf(month);
		else
			monthInString = String.valueOf(month);
		int day = today.get(Calendar.DAY_OF_MONTH);
		String dayInString;
		if (day<10)
			dayInString = "0"+String.valueOf(day);
		else
			dayInString = String.valueOf(day);		
		return (yearInString+"-"+monthInString+"-"+dayInString);
	}
}
