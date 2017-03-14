package org.armstrhb.finances.model;

import java.util.Calendar;
import java.util.Date;

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
	
	public void add(int monthsToAdd) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, month -1);
		now.set(Calendar.YEAR, year);
		
		now.add(Calendar.MONTH, monthsToAdd);
		
		month = now.get(Calendar.MONTH) + 1;
		year = now.get(Calendar.YEAR);
	}
	
	public PaymentCycle getNext() {
		PaymentCycle next = new PaymentCycle(this.month, this.year);
		next.add(1);
		
		return next;
	}
	
	public static PaymentCycle fromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		PaymentCycle cycle = new PaymentCycle();
		cycle.setMonth(calendar.get(Calendar.MONTH) + 1);
		cycle.setYear(calendar.get(Calendar.YEAR));
		
		return cycle;
	}
}
