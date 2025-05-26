package generic.webdriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random ran=new Random();
		int ran_Num = ran.nextInt(5000);
		return ran_Num;
	}
	
	public String getSystemDateYYYYDDMM() {
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		return date;
		
	}
	public String getRequireddateYYYYDDMM(int days) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqDate = sdf.format(cal.getTime());
		return reqDate;
	}
}
