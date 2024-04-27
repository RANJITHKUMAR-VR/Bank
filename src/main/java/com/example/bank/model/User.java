package com.example.bank.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.bank.constants.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
@Document(collection="User")
public class User {
@Id
private String id;
@NotBlank(message = "First name is required")
private String firstName;
@NotBlank(message = "Last name is required")
private String lastName;
@NotBlank
private String customerId; 
@Email(message = "Invalid email format")
@NotBlank(message = "Email is required")
private String email;
@Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format")
private String phoneNumber;
@Past(message = "Date of birth must be in the past")
@NotNull(message = "Date of birth is required")
private Date dateOfBirth;
@NotBlank(message = "AadaharNumber is  required")
private String aadharNumber;
@Valid
private Address address;
private Status isActive;

public User() {
}


public User( @NotBlank(message = "First name is required") String firstName,
		@NotBlank(message = "Last name is required") String lastName, @NotBlank String customerId,
		@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
		@Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format") String phoneNumber,
		@Past(message = "Date of birth must be in the past") @NotNull(message = "Date of birth is required") Date dateOfBirth,
		@NotBlank(message = "AadaharNumber is  required") String aadharNumber, @Valid Address address,
		Status isActive) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.customerId = customerId;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.dateOfBirth = dateOfBirth;
	this.aadharNumber = aadharNumber;
	this.address = address;
	this.isActive = isActive;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
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


public String getCustomerId() {
	return customerId;
}


public void setCustomerId(String customerId) {
	this.customerId = customerId;
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


public String getAadharNumber() {
	return aadharNumber;
}


public void setAadharNumber(String aadharNumber) {
	this.aadharNumber = aadharNumber;
}


public Address getAddress() {
	return address;
}


public void setAddress(Address address) {
	this.address = address;
}

public Status getIsActive() {
	return isActive;
}

public void setIsActive(Status isActive) {
	this.isActive = isActive;
}

}
