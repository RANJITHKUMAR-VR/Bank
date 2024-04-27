package com.example.bank.service;
import com.example.bank.model.ErrorResponse;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.constants.AccountType;
import com.example.bank.constants.Status;
import com.example.bank.error.CustomException;
import com.example.bank.model.Account;
import com.example.bank.model.AccountNumber;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.UserRepository;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService{
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
	
	public Mono<String> activateAccount(AccountNumber accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber.getAccountNumber()).flatMap((account)->{
			if(account.getStatus().equals(Status.ACTIVE)) {
				return Mono.just("This Account is Already Activated");
			}
			account.setStatus(Status.ACTIVE);
			return accountRepository.save(account).thenReturn("Successfully Activate");
		}).switchIfEmpty(Mono.just("Ivalid AccountNumber"));
	}
	
}