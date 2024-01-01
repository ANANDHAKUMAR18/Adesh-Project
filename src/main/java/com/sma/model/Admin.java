package com.sma.model;

public class Admin extends User{
	
	public Admin(int id, String name, int age, String email, String password, String phoneNumber, Address address, int schoolId) {
		super(id, name, age, email, phoneNumber, password, address, schoolId);
	}
	
}
