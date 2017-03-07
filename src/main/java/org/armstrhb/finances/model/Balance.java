package org.armstrhb.finances.model;

public class Balance extends Bill {
	private int balanceId;
	private float initialBalance;
	
	public int getDebtId() {
		return balanceId;
	}
	
	public void setBalanceId(int debtId) {
		this.balanceId = debtId;
	}
	
	public float getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(float initialBalance) {
		this.initialBalance = initialBalance;
	}
}
