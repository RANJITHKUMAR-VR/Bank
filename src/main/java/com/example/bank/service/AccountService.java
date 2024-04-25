package com.example.bank.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.contants.AccountType;
import com.example.bank.contants.Status;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;

import reactor.core.publisher.Mono;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	public Mono<Account>createAccount(String customerId,AccountType accountType){
		Account account =new Account();
		account.setAccountNumber(generateAccountNumber());
		account.setCustomerId(customerId);
		account.setCurrency(0.0);
		account.setBalance(0.0);
		account.setDateOpened(LocalDate.now());
		account.setType(accountType);
		account.setStatus(Status.ACTIVE);
		return accountRepository.save(account);
	}
	public static String generateAccountNumber() {
	    long min = 1000000000000000L; 
	    long max = 9999999999999999L; 
	    long randomNumber = (long) (Math.random() * (max - min + 1) + min);
	    String accountNumber = String.valueOf(randomNumber);
	    return accountNumber;
	}
}
