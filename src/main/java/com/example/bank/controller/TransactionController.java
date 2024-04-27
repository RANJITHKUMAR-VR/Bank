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
import com.example.bank.service.AccountService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	AccountService accountService;
	@PostMapping("/deposit")
	public Mono<ResponseEntity<String>> depositCash(@RequestBody DepositRequest depositRequest){
		return accountService.depositCash(depositRequest);
	}
	
	@PostMapping("/withdrawal")
	public Mono<WithdrawResponse> withdrawal(@RequestBody WithdrawalRequest withdrawalRequest) {
		return accountService.withdrawal(withdrawalRequest);
	}
}
