package genericUtilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class JavaUtils {
	
	/**
	 * This method is used create random integer values
	 * @return
	 */
	public int getRandomNo() {
		
		Random random = new Random();
		int rand = random.nextInt(1000);
		return rand;
	}
	
	/**
	 * This method is used to get the system date (only date not timings) of any format which we want
	 * @return
	 */
	public String systemDate() {
		
		LocalDate date = LocalDate.now();
		
		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String systemDate = d.format(date);
		return systemDate;
	}

	/**
	 * This method is used get both date and time of any format which we want
	 * @return
	 */
	public String sysDate() {
		
		Date date = new Date();
		
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String systemDateInFormat = sim.format(date);
		return systemDateInFormat;
	}
	
	/**
	 * This method is used get the future date by adding number of months for the current date
	 * @param futureMon
	 * @return
	 */
	public String futureDateInTermsOfMonths(long futureMon) {
		
		LocalDate date = LocalDate.now();
		LocalDate future = date.plusMonths(futureMon);
		
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String futureMonth = d.format(future);
		return futureMonth;
	}
	
}
