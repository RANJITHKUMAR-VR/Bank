package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.AccountNumber;
import com.example.bank.model.BalanceResponse;
import com.example.bank.model.Transaction;
import com.example.bank.service.StatementService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class StatementController {
	@Autowired
	StatementService statementService; 
	@GetMapping("/statement")
	public Flux<Transaction>getStatment(@RequestBody AccountNumber accountNumber){
		return statementService.getStatement(accountNumber);
	}
	@GetMapping("/balance")
	public Mono<BalanceResponse>getBalance(@RequestBody AccountNumber accountNumber){
		return statementService.getBalance(accountNumber);
	}
}
