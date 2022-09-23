package com.training.model;

import java.time.LocalDate;

public class Employee {
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private long phoneNumber;
	private LocalDate dateOfBirth;
	private LocalDate weddingDate;

	public Employee() {
		super();
	}

	public Employee(String firstName, long phoneNumber) {
		super();
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}

	public Employee(String firstName, String email) {
		super();
		this.firstName = firstName;
		this.email = email;
	}

	public Employee(String firstName, String lastName, String address, String email, long phoneNumber,
			LocalDate dateOfBirth, LocalDate weddingDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.weddingDate = weddingDate;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailAddress) {
		this.email = emailAddress;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(LocalDate weddingDate) {
		this.weddingDate = weddingDate;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", emailAddress=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth
				+ ", weddingDate=" + weddingDate + "]";
	}



}
