package com.sma.model;

public class User {
	
	private int id;
	private int schoolId;
	private String name;
	private int age;
	private String email;
	private String password;
	private String phoneNumber;
	private Address address;
	
	public User(int id, String name, int age, String phoneNumber, String email, String password, Address address, int schoolId) {
		this.id = id;
		this.schoolId = schoolId;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public int getId() {
		return this.id;
	}

	public int getSchoolId() {
		return this.schoolId;
	}
	
	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public Address getAddressObj() {
		return this.address;
	}

	public String toString() {
		return this.id + " --- " + this.name + " --- " + this.age + " --- "+ this.phoneNumber + " --- " + this.email + " --- " + this.address;
	}
	
	
	
}
