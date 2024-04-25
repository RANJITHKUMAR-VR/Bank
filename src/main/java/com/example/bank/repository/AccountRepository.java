package com.example.bank.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.bank.model.Account;

import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveMongoRepository<Account,String>{
	@Query("{ 'accountNumber' : ?0 }")
    Mono<Account> findByAccountNumber(String accountNumber);
}
