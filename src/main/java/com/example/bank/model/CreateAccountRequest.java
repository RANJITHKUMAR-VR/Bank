package com.example.bank.model;

import com.example.bank.constants.AccountType;

public class CreateAccountRequest {
	private String customerId;
	private AccountType accountType;	
	public CreateAccountRequest() {
	}
	public CreateAccountRequest(String customerId, AccountType accountType) {
		this.customerId = customerId;
		this.accountType = accountType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
