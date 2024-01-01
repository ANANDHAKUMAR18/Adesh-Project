package com.sma.model;

public class Creator {
	
	private int id;
	private String name;
	private int age;
	private String email;
	private String password;
	private String phoneNumber;
	
//	private static int creatorCounter = 0;
	
	public Creator(int id, String name, int age, String email, String password, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
}
