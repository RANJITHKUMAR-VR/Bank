package com.example.bank.model;

import java.time.LocalDateTime;

import com.example.bank.constants.TransactionType;

public class Transaction {
	 private String accountNumber;
	    private double amount;
	    private TransactionType type;
	    private LocalDateTime timestamp;

	    public Transaction() {
		}
		public Transaction(String accountNumber, double amount, TransactionType type) {
	        this.accountNumber = accountNumber;
	        this.amount = amount;
	        this.type = type;
	        this.timestamp = LocalDateTime.now();
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
		public TransactionType getType() {
			return type;
		}
		public void setType(TransactionType type) {
			this.type = type;
		}
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		
}
