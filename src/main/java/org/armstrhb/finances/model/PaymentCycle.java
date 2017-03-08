package org.armstrhb.finances.model;

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
}
