package com.example.bank.service;

import com.example.bank.model.AccountNumber;
import com.example.bank.model.AccountResponse;
import com.example.bank.model.CreateUser;
import com.example.bank.model.UpdateUser;
import com.example.bank.model.UserResponse;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	 public Mono<UserResponse> createAccount(CreateUser createUser);
	 public Mono<String> updateAccount(@Valid UpdateUser updateUser);
	 public Mono<String> deactivateAccount(AccountNumber accountNumber);
	 public Flux<AccountResponse> allAcountDetails(String customerId);
}
