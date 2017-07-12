package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormater {
	
	private static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getTime(){
		return DateFormater.DateToString(new Date());
	}
	
	public static String DateToString(Date date){
		return formater.format(date);
	}
	
	public static Date StringToDate(String string) throws ParseException{
		return formater.parse(string);
	}
}
