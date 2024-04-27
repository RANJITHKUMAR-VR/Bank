package com.example.bank.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.model.UserResponse;
import com.example.bank.constants.Status;
import com.example.bank.dto.CreateUserDto;
import com.example.bank.error.CustomException;
import com.example.bank.model.AccountNumber;
import com.example.bank.model.CreateUser;
import com.example.bank.model.ErrorResponse;
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
                   
    public Mono<UserResponse> createAccount(CreateUser createUser) {
        return CreateUserDto.createUserDtoToUser(createUser)
            .flatMap(user -> {
                return userRepository.findByPhoneNumber(user.getPhoneNumber())
                    .flatMap(existingUser -> Mono.error(new CustomException(new ErrorResponse(409,"User exists"))))
                    .switchIfEmpty(
                        Mono.defer(() -> {
                            user.setCustomerId(generateCustomerId());
                            return userRepository.save(user)
                                .flatMap(savedUser -> {
                                    return accountService.createAccount(savedUser.getCustomerId(), createUser.getAccountType())
                                        .flatMap(account -> {
                                            UserResponse userResponse = new UserResponse();
                                            userResponse.setAccountNO(account.getAccountNumber());
                                            userResponse.setCustomerId(savedUser.getCustomerId());
                                            return Mono.just(userResponse);
                                        });
                                });
                        })
                    ).cast(UserResponse.class);
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
                                    existingUser.setEmail(updateUser.getEmail());
                                    existingUser.setAddress(updateUser.getAddress());
                                    existingUser.setIsActive(updateUser.getIsActive());
                                    return userRepository.save(existingUser)
                                            .thenReturn("Successfully updated");
                                } else {
                                    logger.warn("User with customer ID {} not found.", user.getCustomerId());
                                    return Mono.error(new CustomException(new ErrorResponse(404,"User with customer ID " + user.getCustomerId() + " not found.")));
                                  }
                            })
                            .switchIfEmpty(Mono.error(new CustomException(new ErrorResponse(404,"User with customer ID " + user.getCustomerId() + " not found."))));
                });
	}
	public Mono<String> deactivateAccount(AccountNumber accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber.getAccountNumber())
			.flatMap(existingAccount->{
				if(existingAccount.getStatus().equals(Status.CLOSED)) {
					return Mono.just("This Account is Already Deactivated");
				}
				existingAccount.setStatus(Status.CLOSED);
				return accountRepository.save(existingAccount).thenReturn("successfully deactivate");
			}).switchIfEmpty(Mono.just("Invalid AccountNumber "));
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
