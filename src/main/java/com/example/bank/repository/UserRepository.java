package com.example.bank.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.bank.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
	@Query("{ 'customerId' : ?0 }")
    Mono<User> findByCustomerId(String customerId);
	Mono<User>findByPhoneNumber(String phoneNumber);
}
