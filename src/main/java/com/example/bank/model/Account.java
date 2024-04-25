package com.example.bank.model;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.bank.contants.AccountType;
import com.example.bank.contants.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Document(collection="Account")
public class Account {
    @Id
    private String id; 
    @NotBlank
    private String accountNumber; 
    @NotBlank
    private String customerId; 
    @NotNull
    private double balance; 
    @NotBlank
    private double currency; 
    @NotBlank
    private AccountType type; 
    private LocalDate dateOpened; 
    @NotBlank
    private Status status;    
	public Account() {
	}

	public Account(@NotBlank String accountNumber, @NotBlank String customerId, @NotNull double balance,
			@NotBlank double currency, @NotBlank AccountType type, LocalDate dateOpened, @NotBlank Status status) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.balance = balance;
		this.currency = currency;
		this.type = type;
		this.dateOpened = dateOpened;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
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