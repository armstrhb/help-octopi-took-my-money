package org.armstrhb.finances.model;

public class Balance extends Bill {
	private int balanceId;
	private float initialBalance;
	
	public int getId() {
		return balanceId;
	}
	
	public void setBalanceId(int id) {
		this.balanceId = id;
	}
	
	public float getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(float initialBalance) {
		this.initialBalance = initialBalance;
	}
}
