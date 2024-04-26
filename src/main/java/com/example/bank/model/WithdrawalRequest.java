package com.example.bank.model;

public class WithdrawalRequest {
	private String accountNumber;
    private double amount;

	public WithdrawalRequest() {
	}
	
	public WithdrawalRequest(String accountNumber, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
    
}
