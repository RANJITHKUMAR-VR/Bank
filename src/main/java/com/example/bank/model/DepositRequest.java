package com.example.bank.model;

public class DepositRequest {
	private String accountNumber;
	private double amount;

	public DepositRequest() {
	}

	public DepositRequest(String accountNumber, double amount) {
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
