package com.example.bank.error;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message){
		super(message);
	}
}
