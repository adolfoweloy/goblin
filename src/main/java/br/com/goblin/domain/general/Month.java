package br.com.goblin.domain.general;

import java.util.Calendar;
import java.util.Date;

public enum Month {

	JAN(1, "January"), 
	FEB(2, "February"), 
	MAR(3, "March"), 
	APR(4, "April"), 
	MAY(5, "May"), 
	JUN(6, "June"), 
	JUL(7, "July"), 
	AUG(8, "August"), 
	SEP(9, "September"), 
	OCT(10, "October"), 
	NOV(11, "November"),
	DEC(12, "December");
	
	private int yearMonth;
	
	private String name;
	
	private Month(int yearMonth, String name) {
		this.yearMonth = yearMonth;
		this.name = name;
	}
	
	public int getYearMonth() {
		return yearMonth;
	}

	public String getName() {
		return name;
	}

	public static Month fromString(String value) {
		int yearMonth = Integer.parseInt(value);
		
		return fromValue(yearMonth);
	}

	public static Month fromValue(int value) {
		
		for (Month month : Month.values()) {
			if (month.getYearMonth() == value) {
				return month;
			}
		}

		return null;
	}
	
	public static Month currentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH);
		
		return Month.fromValue(month + 1);
	}
	
}
