package com.example.bank.service;
import com.example.bank.model.ErrorResponse;
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
import com.example.bank.model.AccountNumber;
import com.example.bank.model.AccountResponse;
import com.example.bank.model.BalanceResponse;
import com.example.bank.model.DepositRequest;
import com.example.bank.model.Transaction;
import com.example.bank.model.WithdrawResponse;
import com.example.bank.model.WithdrawalRequest;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	public Mono<Account> createAccount(String customerId, AccountType accountType) {
		return userRepository.findByCustomerId(customerId).flatMap((existingUser)->{
			Account account = new Account();
			account.setAccountNumber(generateAccountNumber());
			account.setCustomerId(customerId);
			account.setBalance(0.0);
			account.setDateOpened(LocalDate.now());
			account.setType(accountType);
			account.setStatus(Status.ACTIVE);
			return accountRepository.save(account);
		}).switchIfEmpty(Mono.error(new CustomException(new ErrorResponse(404,"Invalid CustomerId"))));
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
				return Mono.error(new CustomException(new ErrorResponse(403,"Unable To Withdra Your Account Was Blocked Contact The Bank")));
	    	}
			if(account.getBalance()<withdrawalRequest.getAmount()) {
				return Mono.error( new CustomException(new ErrorResponse(400,"You Have Insufficient Balance "+account.getBalance())));
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
		}).switchIfEmpty(Mono.error(new CustomException(new ErrorResponse(404,"Invalid AccountNumber"))));
	}

	public Flux<Transaction> getStatement(AccountNumber accountNumber) {
	    return accountRepository.findByAccountNumber(accountNumber.getAccountNumber())
	            .flatMapMany(account -> {
	                Flux<Transaction> depositTransactions = Flux.fromIterable(account.getDepositTransactions());
	                Flux<Transaction> withdrawTransactions = Flux.fromIterable(account.getWithdrawTransactions());
	                return Flux.concat(depositTransactions, withdrawTransactions).switchIfEmpty(Flux.error(new CustomException(new ErrorResponse(404,"You Did Not Do Any Transaction"))));
	            })
	            .switchIfEmpty(Flux.error(new CustomException(new ErrorResponse(404,"No User With This AccountNumber id"))));
	}

	public Mono<BalanceResponse> getBalance(AccountNumber accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber.getAccountNumber()).flatMap((account)->{
			BalanceResponse balance=new BalanceResponse();
			balance.setAccountNumber(accountNumber.getAccountNumber());
			balance.setBalance(account.getBalance());
			return Mono.just(balance);
		}).switchIfEmpty(Mono.error(new CustomException(new ErrorResponse(404,"There is No AccountNumber"))));
	}

	public Flux<AccountResponse> allAcountDetails(String customerId) {
		return accountRepository.findByCustomerId(customerId)
				.flatMap((account)->{
					if(account.getStatus().equals(Status.ACTIVE)) {
					AccountResponse accountResponse=new AccountResponse();
					accountResponse.setAccountNumber(account.getAccountNumber());
					accountResponse.setCustomerId(account.getCustomerId());
					accountResponse.setBalance(account.getBalance());
					accountResponse.setAccountType(account.getType());
					accountResponse.setDateOpened(account.getDateOpened());
					accountResponse.setStatus(account.getStatus());
					return Mono.just(accountResponse);
					}
					return Mono.empty();
				})
				.switchIfEmpty(Mono.error(new CustomException(new ErrorResponse(404,"Invalid CustomerId"))));
	}
}