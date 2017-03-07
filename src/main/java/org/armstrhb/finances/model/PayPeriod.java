package org.armstrhb.finances.model;

import java.util.Date;

public class PayPeriod {
	private int id;
	private Date startDate;
	private Date endDate;
	private float startingAmount;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public float getStartingAmount() {
		return startingAmount;
	}
	
	public void setStartingAmount(float startingAmount) {
		this.startingAmount = startingAmount;
	}
	
}
