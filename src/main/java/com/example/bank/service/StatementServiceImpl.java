package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.error.CustomException;
import com.example.bank.model.AccountNumber;
import com.example.bank.model.BalanceResponse;
import com.example.bank.model.ErrorResponse;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StatementServiceImpl implements StatementService{
	@Autowired
	AccountRepository accountRepository;
	public Flux<Transaction> getStatement(AccountNumber accountNumber) {
	    return accountRepository.findByAccountNumber(accountNumber.getAccountNumber())
	            .flatMapMany(account -> {
	                Flux<Transaction> depositTransactions = Flux.fromIterable(account.getDepositTransactions());
	                Flux<Transaction> withdrawTransactions = Flux.fromIterable(account.getWithdrawTransactions());
	                return Flux.concat(depositTransactions, withdrawTransactions).switchIfEmpty(Flux.error(new CustomException(new ErrorResponse(404,"You Did Not Do Any Transaction"))));
	            })
	            .switchIfEmpty(Flux.error(new CustomException(new ErrorResponse(404,"No User With This AccountNumber id"))));
	}

	public Mono<BalanceResponse> getBalance(AccountNumber accountNumber)  {
		return accountRepository.findByAccountNumber(accountNumber.getAccountNumber()).flatMap((account)->{
			BalanceResponse balance=new BalanceResponse();
			balance.setAccountNumber(accountNumber.getAccountNumber());
			balance.setBalance(account.getBalance());
			return Mono.just(balance);
		}).switchIfEmpty(Mono.error(new CustomException(new ErrorResponse(404,"There is No AccountNumber"))));
	}

}
