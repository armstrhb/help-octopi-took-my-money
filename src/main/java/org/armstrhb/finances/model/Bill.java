package org.armstrhb.finances.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bill {
	private static final Logger log = LoggerFactory.getLogger(Bill.class);
	public static final float NO_PAYMENT_PLAN_AMOUNT = -1.0f;
	public static final float NO_BALANCE_AMOUNT = -1.0f;
	
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
	private float initialBalance;
	private Date lastPaymentDate;
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
	
	public boolean hasPaymentPlan() {
		return paymentPlanAmount > 0 && paymentPlanAmount != NO_PAYMENT_PLAN_AMOUNT;
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
		return getPaymentPlanAmount() != NO_PAYMENT_PLAN_AMOUNT;
	}
	
	public boolean isBalanceAvailable() {
		 return getCurrentBalance() != NO_BALANCE_AMOUNT;
	}
	
	public float getCurrentBalance() {
		float balance = initialBalance;
		
		if (hasHistory()) {
			for (Event event : events) {
				balance -= event.getAmountPaid();
			}
		}
		
		return balance;
	}
	
	public void setInitialBalance(float inBalance) {
		initialBalance = inBalance;
	}
	
	public float getInitialBalance() {
		return initialBalance;
	}
	
	public float getAveragePayment() {
		float averagePayment = 0.0f;
		
		if (hasHistory()) {
			averagePayment = calculateAveragePayment(getEvents(false)); // sort by chronological
		} else {
			averagePayment = paymentPlanAmount;
		}
		
		return averagePayment;
	}
	
	private float calculateAveragePayment(List<Event> events) {
		float total = 0.0f;
		int sum = 0;
		int index = 0;
		
		log.debug("calculating weighted average from previous payments");
		
		for (Event event : events) {
			index += 1;
			total += event.getAmountPaid() * index;
			
			sum += index;
			
			if (log.isDebugEnabled()) {
				log.debug("step: " + index + ", total: " + total + ", sum: " + sum);
			}
		}
		
		if (log.isDebugEnabled()) {
			log.debug("average: " + total + " / " + sum + " = " + (total / sum));
		}
		
		return total / sum;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	
	public boolean isOverDue() {
		return false; //TODO: fill this in
	}
	
	public boolean hasHistory() {
		return events != null && events.size() > 0;
	}
	
	public void setEvents(List<Event> inEvents) {
		events = inEvents;
	}
	
	public List<Event> getEvents() {
		return getEvents(true);
	}
	
	public List<Event> getEvents(boolean sortNewer) {
		if (hasHistory()) {
			for (Event event : events) {
				log.debug("event: " + event.getId() + ", created: " + event.getCreated());
			}
			
			if (sortNewer) {
				Collections.sort(events, (a, b) -> a.getCreated() != null && a.getCreated().before(b.getCreated()) ? 1 : -1);
			} else {
				Collections.sort(events, (a, b) -> a.getCreated() != null && a.getCreated().before(b.getCreated()) ? -1 : 1);
			}
		}
		
		return events;
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
}
