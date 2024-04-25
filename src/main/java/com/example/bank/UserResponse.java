package com.example.bank;

public class UserResponse {
	private String accountNO;
	private String customerId;
	public UserResponse() {
		super();
	}
	public UserResponse(String accountNO, String customerId) {
		super();
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
