package com.example.bank.service;

import com.example.bank.model.AccountNumber;
import com.example.bank.model.BalanceResponse;
import com.example.bank.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatementService {
	public Flux<Transaction> getStatement(AccountNumber accountNumber);
	public Mono<BalanceResponse> getBalance(AccountNumber accountNumber);
}
