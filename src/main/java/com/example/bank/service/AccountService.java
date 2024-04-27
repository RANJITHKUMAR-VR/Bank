package com.example.bank.service;

import com.example.bank.constants.AccountType;
import com.example.bank.model.Account;
import com.example.bank.model.AccountNumber;

import reactor.core.publisher.Mono;

public interface AccountService {
	public Mono<Account> createAccount(String customerId, AccountType accountType);

	public Mono<String> activateAccount(AccountNumber accountNumber);
}
