package org.armstrhb.finances.model;

import java.util.Date;

public class Event {
	private long id;
	private Bill bill;
	private Date created;
	private Date updated;
	private float amountPaid;
	private String reference;
	private float amountDue;
	private Date dueDate;
	private PayPeriod payPeriod;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Bill getBill() {
		return bill;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
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
	
	public float getAmountPaid() {
		return amountPaid;
	}
	
	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public float getAmountDue() {
		return amountDue;
	}
	
	public void setAmountDue(float amountDue) {
		this.amountDue = amountDue;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public PayPeriod getPayPeriod() {
		return payPeriod;
	}
	
	public void setPayPeriod(PayPeriod payPeriod) {
		this.payPeriod = payPeriod;
	}
}
