package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.DepositRequest;
import com.example.bank.model.WithdrawResponse;
import com.example.bank.model.WithdrawalRequest;
import com.example.bank.service.TransactionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	@PostMapping("/deposit")
	public Mono<ResponseEntity<String>> depositCash(@RequestBody DepositRequest depositRequest){
		return transactionService.depositCash(depositRequest);
	}
	
	@PostMapping("/withdrawal")
	public Mono<WithdrawResponse> withdrawal(@RequestBody WithdrawalRequest withdrawalRequest) {
		return transactionService.withdrawal(withdrawalRequest);
	}
}
