package org.armstrhb.finances.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	private Date lastPaymentDate;
	private PaymentCycle cycle;
	private List<Event> events;
	
	public Bill() {
		events = new ArrayList<Event>();
	}
	
	public boolean isNoteAvailable() {
		return notes != null && notes.length() > 0;
	}
	
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
	
	public boolean doesPaymentPlanExist() {
		return getPaymentPlanAmount() != NO_PAYMENY_PLAN_AMOUNT;
	}
	
	public boolean isBalanceAvailable() {
		 return getBalance() != null && getBalance().getInitialBalance() >= 0;
	}
	
	public Balance getBalance() {
		return balance;
	}
	
	public void setBalance(Balance inBalance) {
		balance = inBalance;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
		
		if (cycle == null) {
			cycle = PaymentCycle.fromDate(lastPaymentDate);
		}
	}

	public PaymentCycle getCycle() {
		return cycle;
	}

	public void setCycle(PaymentCycle cycle) {
		this.cycle = cycle;
	}
	
	public void setCycle(int month, int year) {
		this.cycle = new PaymentCycle(month, year);
	}
	
	public Date getDueDate() {
		Calendar calendar = Calendar.getInstance();
		
		if (lastPaymentDate == null) {
			Date now = new Date();
			calendar.set(Calendar.DATE, dayOfMonthDue);
			
			if (calendar.getTime().before(now)) {
				calendar.add(Calendar.MONTH, 1);
			}
		} else {
			PaymentCycle nextCycle = cycle.getNext();
			
			calendar.set(Calendar.MONTH, nextCycle.getMonth() - 1);
			calendar.set(Calendar.DATE, dayOfMonthDue);
			calendar.set(Calendar.YEAR, nextCycle.getYear());
		}
		
		return calendar.getTime();
	}
	
	public boolean isOverDue() {
		return getDueDate().before(new Date());
	}
	
	public boolean hasHistory() {
		return events != null && events.size() > 0;
	}
	
	public void setEvents(List<Event> inEvents) {
		events = inEvents;
	}
	
	public List<Event> getEvents() {
		return events;
	}
}
