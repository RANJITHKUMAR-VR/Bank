package com.example.bank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.constants.AccountType;
import com.example.bank.constants.Status;
import com.example.bank.constants.TransactionType;
import com.example.bank.error.CustomException;
import com.example.bank.model.Account;
import com.example.bank.model.BalanceResponse;
import com.example.bank.model.DepositRequest;
import com.example.bank.model.Transaction;
import com.example.bank.model.WithdrawResponse;
import com.example.bank.model.WithdrawalRequest;
import com.example.bank.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	public Mono<Account> createAccount(String customerId, AccountType accountType) {
		Account account = new Account();
		account.setAccountNumber(generateAccountNumber());
		account.setCustomerId(customerId);
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
	public Mono<ResponseEntity<String>> depositCash(DepositRequest depositRequest) {
			return accountRepository.findByAccountNumber(depositRequest.getAccountNumber())
				    .flatMap(account -> {
				    	if(account.getStatus().equals(Status.CLOSED)) {
				    		return Mono.just(ResponseEntity.badRequest().body("Unable To Deposit Your Account Was Blocked Contact The Bank"));
				    	}
				        double amount = depositRequest.getAmount();
				        if (amount <= 0) {
				            return Mono.just(ResponseEntity.badRequest().body("Invalid deposit amount "+amount));
				        }
				        double newBalance = account.getBalance() + amount;
				        account.setBalance(newBalance);
				        Transaction transaction = new Transaction(depositRequest.getAccountNumber(), amount, TransactionType.DEPOSIT);
				        account.setDepositTransactions(transaction);
				        return accountRepository.save(account)
				            .flatMap(updatedAccount -> Mono.just(ResponseEntity.ok("Deposit successful. New balance: " + updatedAccount.getBalance())));
				    }).switchIfEmpty(Mono.just(ResponseEntity.ok("Invalid Account Number! "+depositRequest.getAccountNumber())));
	}
	public Mono<WithdrawResponse> withdrawal(WithdrawalRequest withdrawalRequest) {
		return accountRepository.findByAccountNumber(withdrawalRequest.getAccountNumber()).flatMap(account->{
			if(account.getStatus().equals(Status.CLOSED)) {
				return Mono.error(new CustomException("Unable To Withdra Your Account Was Blocked Contact The Bank"));
	    	}
			if(account.getBalance()<withdrawalRequest.getAmount()) {
				return Mono.error( new CustomException("You Have Insufficient Balance "+account.getBalance()));
			}
			double amount = withdrawalRequest.getAmount();
			amount=account.getBalance()-amount;
			account.setBalance(amount);
			WithdrawResponse withdrawResponse=new WithdrawResponse();
			withdrawResponse.setAccountId(withdrawalRequest.getAccountNumber());
			withdrawResponse.setAmount(withdrawalRequest.getAmount());
			withdrawResponse.setNewBalance(account.getBalance());
			withdrawResponse.setTimestamp(LocalDateTime.now());
			if(account.getBalance()==500) {
				withdrawResponse.setMessage("You Reached The Minimum Balance");
				Transaction transaction = new Transaction(withdrawalRequest.getAccountNumber(),withdrawalRequest.getAmount(), TransactionType.WITHDRAWAL);
			    account.setWithdrawTransactions(transaction);
				return accountRepository.save(account).thenReturn(withdrawResponse);
			}
			Transaction transaction = new Transaction(withdrawalRequest.getAccountNumber(),withdrawalRequest.getAmount(), TransactionType.WITHDRAWAL);
		    account.setWithdrawTransactions(transaction);
			withdrawResponse.setMessage("You Successfully Withdraw Amount");
			return accountRepository.save(account).thenReturn(withdrawResponse);
		}).switchIfEmpty(Mono.error(new CustomException("Invalid AccountNumber")));
	}

	public Flux<Transaction> getStatement(String customerId) {
	    return accountRepository.findByCustomerId(customerId)
	            .flatMapMany(account -> {
	                Flux<Transaction> depositTransactions = Flux.fromIterable(account.getDepositTransactions());
	                Flux<Transaction> withdrawTransactions = Flux.fromIterable(account.getWithdrawTransactions());
	                return Flux.concat(depositTransactions, withdrawTransactions);
	            })
	            .switchIfEmpty(Flux.error(new CustomException("No user with this customer id")));
	}

	public Mono<BalanceResponse> getBalance(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber).flatMap((account)->{
			BalanceResponse balance=new BalanceResponse();
			balance.setAccountNumber(accountNumber);
			balance.setBalance(account.getBalance());
			return Mono.just(balance);
		}).switchIfEmpty(Mono.error(new CustomException("There is No AccountNumber")));
	}
}