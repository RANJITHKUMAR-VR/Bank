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
public class AccountController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;

	@PostMapping("/new-account")
	public Mono<UserResponse> createAccount(@Valid @RequestBody CreateUser createUser) {
		return userService.createAccount(createUser);
	}

	@PutMapping("/update")
	public Mono<String> updateAccount(@Valid @RequestBody UpdateUser updateUser) {
		return userService.updateAccount(updateUser);
	}

	@DeleteMapping("/deactivate")
	public Mono<String> deactivatAccount(@RequestBody AccountNumber accountNumber) {
		return userService.deactivateAccount(accountNumber);
	}

	@PutMapping("/activate")
	public Mono<String> activateAccount(@RequestBody AccountNumber accountNumber) {
		return userService.activateAccount(accountNumber);
	}

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
	@GetMapping("/getAllAccount/{customerId}")
	public Flux<AccountResponse> allAccountDetails(@PathVariable String customerId){
		return accountService.allAcountDetails(customerId);
	}
}
