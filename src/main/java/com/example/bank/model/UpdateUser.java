package com.example.bank.model;

import java.util.Date;

import com.example.bank.constants.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class UpdateUser {
	@NotBlank(message = "customerId is required")
	private String customerId;
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

	private Status isActive;

	public UpdateUser() {
	}

	public UpdateUser(@NotBlank(message = "customerId is required") String customerId,
			@NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
			@Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format") String phoneNumber,
			@Past(message = "Date of birth must be in the past") @NotNull(message = "Date of birth is required") Date dateOfBirth,
			@Valid Address address, Status isActive) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
