package com.sma.model;

public class Address {

	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	public Address(String line1, String line2, String city, String state, String country, String pincode) {
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public String getLine1() {
		return this.line1;
	}
	
	public String getLine2() {
		return this.line2;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getPincode() {
		return this.pincode;
	}
	
	public String toString() {
		return this.line1 + "," + this.line2 + "," + this.city + "," + state + ","
				+ country + "," + pincode;
	}
	
	
	
}
