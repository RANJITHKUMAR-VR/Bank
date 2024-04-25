package com.example.bank.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.UserResponse;
import com.example.bank.contants.AccountType;
import com.example.bank.contants.Status;
import com.example.bank.dto.CreateUserDto;
import com.example.bank.error.UserNotFoundException;
import com.example.bank.model.CreateUser;
import com.example.bank.model.UpdateUser;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.UserRepository;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountService accountService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

	public Mono<UserResponse> createAccount(CreateUser createUser,AccountType accountType) {
		return CreateUserDto.createUserDtoToUser(createUser).flatMap(user -> {
	        return userRepository.findByEmail(user.getEmail()).flatMap(existingUser -> {
	            if (existingUser == null) {
	                user.setCustomerId(generateCustomerId());
	                System.out.println("Generated Customer ID: " + user.getCustomerId());
	                return userRepository.save(user).flatMap(savedUser -> {
	                    return accountService.createAccount(savedUser.getCustomerId(), accountType).map(account -> {
	                        UserResponse userResponse = new UserResponse();
	                        userResponse.setAccountNO(account.getAccountNumber());
	                        userResponse.setCustomerId(savedUser.getCustomerId());
	                        return userResponse;
	                    });
	                });
	            } else {
	                return Mono.error(new UserNotFoundException("User with email " + user.getEmail() + " already exists."));
	            }
	        });
	    });
			
	}
	public static String generateCustomerId() {
		return UUID.randomUUID().toString().substring(0, 7);
	}

	public Mono<String> updateAccount(@Valid UpdateUser updateUser) {
		return CreateUserDto.updateUserDtoToUser(updateUser)
                .flatMap(user -> {
                    logger.info("Updating user with customer ID: {}", user.getCustomerId());
                    return userRepository.findByCustomerId(user.getCustomerId())
                            .flatMap(existingUser -> {
                                if (existingUser != null) {
                                    logger.info("Existing user found: {}", existingUser);
                                    existingUser.setFirstName(updateUser.getFirstName());
                                    existingUser.setLastName(updateUser.getLastName());
                                    existingUser.setDateOfBirth(updateUser.getDateOfBirth());
                                    existingUser.setPhoneNumber(updateUser.getPhoneNumber());
                                    existingUser.setAddress(updateUser.getAddress());
                                    existingUser.setPassword(updateUser.getPassword());
                                    existingUser.setActive(updateUser.getIsActive());
                                    return userRepository.save(existingUser)
                                            .thenReturn("Successfully updated");
                                } else {
                                    logger.warn("User with customer ID {} not found.", user.getCustomerId());
                                    return Mono.error(new UserNotFoundException("User with customer ID " + user.getCustomerId() + " not found."));
                                  }
                            })
                            .switchIfEmpty(Mono.error(new UserNotFoundException("User with customer ID " + user.getCustomerId() + " not found.")));
                })
                .onErrorResume(UserNotFoundException.class,error -> {
                    logger.error("Failed to update user", error);
                    return Mono.error(error);
                });
	}
	public Mono<String> deactivateAccount(String accountId) {
		return accountRepository.findByAccountNumber(accountId)
			.flatMap(existingAccount->{
			existingAccount.setStatus(Status.CLOSED);
			return accountRepository.save(existingAccount).thenReturn("successfully deactivate");
			}).switchIfEmpty(Mono.just("Invalid AccountNumber "));
	}
}
