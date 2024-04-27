package com.example.bank.service;

import org.springframework.http.ResponseEntity;

import com.example.bank.model.DepositRequest;
import com.example.bank.model.WithdrawResponse;
import com.example.bank.model.WithdrawalRequest;

import reactor.core.publisher.Mono;

public interface TransactionService {
	public Mono<ResponseEntity<String>> depositCash(DepositRequest depositRequest);
	public Mono<WithdrawResponse> withdrawal(WithdrawalRequest withdrawalRequest);
}
