package testcases;

import java.util.Calendar;

public class TC002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Calendar calendar = Calendar.getInstance();
	      System.out.println("Current Date = " + calendar.getTime());
	      // Incrementing hours by 5
	      calendar.add(Calendar.HOUR_OF_DAY, -5);
	      System.out.println("Updated Date = " + calendar.getTime());
	}

}
