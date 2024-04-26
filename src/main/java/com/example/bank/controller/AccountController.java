package com.example.bank.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.constants.AccountType;
import com.example.bank.model.BalanceResponse;
import com.example.bank.model.CreateUser;
import com.example.bank.model.Transaction;
import com.example.bank.model.UpdateUser;
import com.example.bank.model.UserResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class AccountController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	@PostMapping("/create/{accountType}")
	 public Mono<UserResponse> createAccount(@Valid @RequestBody CreateUser createUser,@PathVariable AccountType accountType){
        return userService.createAccount(createUser,accountType);
    }
	@PutMapping("/update")
	public Mono<String>updateAccount(@Valid @RequestBody UpdateUser updateUser ){
		return userService.updateAccount(updateUser);		
	}
	@DeleteMapping("/deactivate/{accountId}")
	public Mono<String> deactivatAccount(@PathVariable String accountId){
		return userService.deactivateAccount(accountId);
	}
	@PutMapping("/activate/{accountNumber}")
	public Mono<String>activateAccount(@PathVariable String accountNumber){
		return userService.activateAccount(accountNumber);
	}
	@GetMapping("/statement/{customerId}")
	public Flux<Transaction>getStatment(@PathVariable String customerId){
		return accountService.getStatement(customerId);
	}
	@GetMapping("/balance/{accountNumber}")
	public Mono<BalanceResponse>getBalance(@PathVariable String accountNumber){
		return accountService.getBalance(accountNumber);
	}
}
