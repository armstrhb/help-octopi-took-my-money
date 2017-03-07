package org.armstrhb.finances.model;

import java.util.Date;

public class Bill {
	public static final float NO_PAYMENY_PLAN_AMOUNT = -1.0f;
	
	private int id;
	private String name;
	private String accountNumber;
	private Date created;
	private Date updated;
	private boolean active;
	private String notes;
	private float paymentPlanAmount;
	private int dayOfMonthDue;
	private String website;
	private String phoneNumber;
	private Balance balance;
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public float getPaymentPlanAmount() {
		return paymentPlanAmount;
	}

	public void setPaymentPlanAmount(float paymentPlanAmount) {
		this.paymentPlanAmount = paymentPlanAmount;
	}

	public int getDayOfMonthDue() {
		return dayOfMonthDue;
	}

	public void setDayOfMonthDue(int dayOfMonthDue) {
		this.dayOfMonthDue = dayOfMonthDue;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getUpdated() {
		return updated;
	}
	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean hasPaymentPlan() {
		return getPaymentPlanAmount() != NO_PAYMENY_PLAN_AMOUNT;
	}
	
	public boolean hasBalance() {
		 return getBalance() != null || getBalance().getInitialBalance() >= 0;
	}
	
	public Balance getBalance() {
		return balance;
	}
	
	public void setBalance(Balance inBalance) {
		balance = inBalance;
	}
}
