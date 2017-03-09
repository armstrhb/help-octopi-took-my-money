package org.armstrhb.finances.model;

import java.util.Calendar;

public class PaymentCycle {
	private int month;
	private int year;
	
	public PaymentCycle() {
		
	}
	
	public PaymentCycle(int inMonth, int inYear) {
		setMonth(inMonth);
		setYear(inYear);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isPresent() {
		Calendar now = Calendar.getInstance();
		return month == now.get(Calendar.MONTH) + 1 && year == now.get(Calendar.YEAR);
	}
	
	public boolean isBefore() {
		Calendar now = Calendar.getInstance();
		return month < now.get(Calendar.MONTH) + 1 || year < now.get(Calendar.YEAR);		
	}
	
	public boolean isAfter() {
		Calendar now = Calendar.getInstance();
		return month > now.get(Calendar.MONTH) + 1 || year > now.get(Calendar.YEAR);		
	}
}
