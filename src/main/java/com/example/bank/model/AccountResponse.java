package com.example.bank.model;

import java.time.LocalDate;

import com.example.bank.constants.AccountType;
import com.example.bank.constants.Status;

public class AccountResponse {
	private String accountNumber;
	private String customerId;
	private double balance;
	private AccountType accountType;
	private LocalDate dateOpened;
	private Status status;
	public AccountResponse() {
	}
	public AccountResponse(String accountNumber, String customerId, double balance, AccountType accountType,
			LocalDate dateOpened, Status status) {
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.balance = balance;
		this.accountType = accountType;
		this.dateOpened = dateOpened;
		this.status = status;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public LocalDate getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(LocalDate dateOpened) {
		this.dateOpened = dateOpened;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
