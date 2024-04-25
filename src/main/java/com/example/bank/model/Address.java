package com.example.bank.model;

import jakarta.validation.constraints.NotBlank;

public class Address {
	@NotBlank(message="Street Must Not Be Blank")
    private String street;
	@NotBlank(message="City Must Not Be Blank")
    private String city;
	@NotBlank(message="State Must Not Be Blank")
    private String state;
	@NotBlank(message="PostalCode Must Not Be Blank")
    private String postalCode;
	public Address() {
	}
	public Address(@NotBlank(message = "Street Must Not Be Blank") String street,
			@NotBlank(message = "City Must Not Be Blank") String city,
			@NotBlank(message = "State Must Not Be Blank") String state,
			@NotBlank(message = "PostalCode Must Not Be Blank") String postalCode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}
