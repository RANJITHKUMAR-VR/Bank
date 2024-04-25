package com.example.bank.dto;

import com.example.bank.contants.Status;
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
	user.setUsername(createUser.getUsername());
	user.setPhoneNumber(createUser.getPhoneNumber());
	user.setPassword(createUser.getPassword());
	user.setActive(Status.ACTIVE);
	return Mono.just(user);
}
public static Mono<User> updateUserDtoToUser(UpdateUser updateUser) {
	User user=new User();
	user.setCustomerId(updateUser.getCustomerId());
	user.setFirstName(updateUser.getFirstName());
	user.setLastName(updateUser.getLastName());
	user.setDateOfBirth(updateUser.getDateOfBirth());
	user.setAddress(updateUser.getAddress());
	user.setPhoneNumber(updateUser.getPhoneNumber());
	user.setPassword(updateUser.getPassword());
	user.setActive(updateUser.getIsActive());
	return Mono.just(user);
}
}
