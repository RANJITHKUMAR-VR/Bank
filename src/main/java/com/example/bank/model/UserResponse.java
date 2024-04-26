package com.example.bank.model;

public class UserResponse {
	private String accountNO;
	private String customerId;
	public UserResponse() {
	}
	public UserResponse(String accountNO, String customerId) {
		this.accountNO = accountNO;
		this.customerId = customerId;
	}
	public String getAccountNO() {
		return accountNO;
	}
	public void setAccountNO(String accountNO) {
		this.accountNO = accountNO;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
