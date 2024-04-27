package com.example.bank.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.model.Account;
import com.example.bank.model.AccountNumber;
import com.example.bank.model.AccountResponse;
import com.example.bank.model.CreateAccountRequest;
import com.example.bank.model.CreateUser;
import com.example.bank.model.UpdateUser;
import com.example.bank.model.UserResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;

//first time creating a account 
	@PostMapping("/new-account")
	public Mono<UserResponse> createAccount(@Valid @RequestBody CreateUser createUser) {
		return userService.createAccount(createUser);
	}

//update the profile
	@PutMapping("/update")
	public Mono<String> updateAccount(@Valid @RequestBody UpdateUser updateUser) {
		return userService.updateAccount(updateUser);
	}

//deactivate the account
	@DeleteMapping("/deactivate")
	public Mono<String> deactivatAccount(@RequestBody AccountNumber accountNumber) {
		return userService.deactivateAccount(accountNumber);
	}
//activate the account
	@PutMapping("/activate")
	public Mono<String> activateAccount(@RequestBody AccountNumber accountNumber) {
		return accountService.activateAccount(accountNumber);
	}
//create a account 
	@PostMapping("/create-account")
	public Mono<UserResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
		Mono<Account> account = accountService.createAccount(createAccountRequest.getCustomerId(),
				createAccountRequest.getAccountType());
		UserResponse userResponse = new UserResponse();
		return account.flatMap((existingAccount) -> {
			userResponse.setAccountNO(existingAccount.getAccountNumber());
			userResponse.setCustomerId(existingAccount.getCustomerId());
			return Mono.just(userResponse);
		});
	}

//give cutomerId get all active account
	@GetMapping("/getAllAccount/{customerId}")
	public Flux<AccountResponse> allAccountDetails(@PathVariable String customerId) {
		return userService.allAcountDetails(customerId);
	}
}
