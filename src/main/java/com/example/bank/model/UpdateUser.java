package com.example.bank.model;

import java.util.Date;

import com.example.bank.contants.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUser {
	@NotBlank(message="customerId is required")
	private String customerId; 
	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;
	 
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
	private Status isActive;
	public UpdateUser() {
	}
	
	
	public UpdateUser(@NotBlank(message = "customerId is required") String customerId,
			@NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format") String phoneNumber,
			@Past(message = "Date of birth must be in the past") @NotNull(message = "Date of birth is required") Date dateOfBirth,
			@Valid Address address, @NotBlank(message = "Username is required") String username,
			@Size(min = 6, message = "Password must be at least 6 characters long") @NotBlank(message = "Password is required") String password,
			Status isActive) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}


	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Status getIsActive() {
		return isActive;
	}
	public void setIsActive(Status isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UpdateUser [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", username=" + username
				+ ", password=" + password + ", isActive=" + isActive + "]";
	}
	
}
