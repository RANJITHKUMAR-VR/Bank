package com.example.bank.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.bank.constants.AccountType;
import com.example.bank.constants.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "Account")
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
	private AccountType type;
	private LocalDate dateOpened;
	@NotBlank
	private Status status;
	private List<Transaction> depositTransactions;
	private List<Transaction> withdrawTransactions;

	public Account() {
		this.depositTransactions = new ArrayList<>();
		this.withdrawTransactions = new ArrayList<>();
	}

	public Account(String id, @NotBlank String accountNumber, @NotBlank String customerId, @NotNull double balance,
			@NotBlank AccountType type, LocalDate dateOpened, @NotBlank Status status,
			List<Transaction> depositTransactions, List<Transaction> withdrawTransactions) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.balance = balance;
		this.type = type;
		this.dateOpened = dateOpened;
		this.status = status;
		this.depositTransactions = depositTransactions;
		this.withdrawTransactions = withdrawTransactions;
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

	public List<Transaction> getDepositTransactions() {
		return depositTransactions;
	}

	public void setDepositTransactions(Transaction depositTransaction) {
		this.depositTransactions.add(depositTransaction);
	}

	public List<Transaction> getWithdrawTransactions() {
		return withdrawTransactions;
	}

	public void setWithdrawTransactions(Transaction withdrawTransaction) {
		this.withdrawTransactions.add(withdrawTransaction);
	}
}