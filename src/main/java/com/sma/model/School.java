package com.sma.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class School {
	private int id;
	private String name;
	private Address address;
	private String phoneNumber;
	
	private static int schoolCounter = 0;
	
	private Map<String,User> userMap;
	
	public School(String name, Address address, String phoneNumber) {
		this.id = ++schoolCounter;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userMap = new HashMap<>();
	}
	
	public School(int id, String name, Address address, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userMap = new HashMap<>();
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String toString() {
		return this.id+" --- "+this.name+" ----- "+this.address+" ----- "+this.phoneNumber;
	}

	public void addUserIntoUserMap(User user) {
		this.userMap.put(user.getEmail(), user);
	}
	
	public boolean removeUserFromUserMap(String email) {
		return (this.userMap.remove(email) != null);
	}
	
	public User getUserFromUserMap(String email) {
		return this.userMap.get(email);
	}
	
	public Map<String, User> getUserMap(){
		return Collections.unmodifiableMap(userMap);
	}
}
