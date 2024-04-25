package com.example.bank.model;

import java.util.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.ToString;
@ToString
public class CreateUser {
@NotBlank(message = "First name is required")
private String firstName;

@NotBlank(message = "Last name is required")
private String lastName;
 
@Email(message = "Invalid email format")
@NotBlank(message = "Email is required")
private String email;

@Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format")
private String phoneNumber;

@Past(message = "Date of birth must be in the past")
@NotNull(message = "Date of birth is required")
private Date dateOfBirth;
@Valid
private Address address;

@NotBlank(message = "Username is required")
private String username;

@Size(min = 6, message = "Password must be at least 6 characters long")
@NotBlank(message = "Password is required")
private String password;


public CreateUser() {
}

public CreateUser(@NotBlank(message = "First name is required") String firstName,
		@NotBlank(message = "Last name is required") String lastName,
		@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
		@Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format") String phoneNumber,
		@Past(message = "Date of birth must be in the past") @NotNull(message = "Date of birth is required") Date dateOfBirth,
		@Valid Address address, @NotBlank(message = "Username is required") String username,
		@Size(min = 6, message = "Password must be at least 6 characters long") @NotBlank(message = "Password is required") String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.dateOfBirth = dateOfBirth;
	this.address = address;
	this.username = username;
	this.password = password;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public Date getDateOfBirth() {
	return dateOfBirth;
}

public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}