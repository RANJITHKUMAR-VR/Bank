package com.example.bank.model;

import java.time.LocalDateTime;

public class WithdrawResponse {
	  private String accountId;
	  private String message;
	  private double amount;
	  private double newBalance;
	  private LocalDateTime timestamp;
	  
	public WithdrawResponse() {
	}
	public WithdrawResponse(String accountId, String message, double amount, double newBalance,
			LocalDateTime timestamp) {
		super();
		this.accountId = accountId;
		this.message = message;
		this.amount = amount;
		this.newBalance = newBalance;
		this.timestamp = timestamp;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	  
}
