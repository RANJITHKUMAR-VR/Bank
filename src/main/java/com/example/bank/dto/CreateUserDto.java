package com.example.bank.dto;

import com.example.bank.constants.Status;
import com.example.bank.model.CreateUser;
import com.example.bank.model.UpdateUser;
import com.example.bank.model.User;

import reactor.core.publisher.Mono;

public class CreateUserDto {
public CreateUserDto(){	
}
public static Mono<User> createUserDtoToUser(CreateUser createUser) {
	User user=new User();
	user.setFirstName(createUser.getFirstName());
	user.setLastName(createUser.getLastName());
	user.setEmail(createUser.getEmail());
	user.setDateOfBirth(createUser.getDateOfBirth());
	user.setAddress(createUser.getAddress());
	user.setAadharNumber(createUser.getAadharNumber());
	user.setPhoneNumber(createUser.getPhoneNumber());
	user.setIsActive(Status.ACTIVE);
	return Mono.just(user);
}
public static Mono<User> updateUserDtoToUser(UpdateUser updateUser) {
	User user=new User();
	user.setCustomerId(updateUser.getCustomerId());
	user.setFirstName(updateUser.getFirstName());
	user.setLastName(updateUser.getLastName());
	user.setEmail(updateUser.getEmail());
	user.setDateOfBirth(updateUser.getDateOfBirth());
	user.setAddress(updateUser.getAddress());
	user.setIsActive(updateUser.getIsActive());
	return Mono.just(user);
}
}
