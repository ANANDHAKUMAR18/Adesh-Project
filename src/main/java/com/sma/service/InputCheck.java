package com.sma.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCheck {
	
	private static InputCheck instance = null;
	
	public static InputCheck getInstance() {	
		if(instance == null) {
			instance = new InputCheck();
		}
		return instance;
	}
	private InputCheck() {}
	
	public int validateAge(Scanner sc) {
		boolean validAge;
		String age;
		String regex = "[1-9][0-9]?";
		Pattern p = Pattern.compile(regex);
		do {
			validAge = false;
			age = sc.nextLine();
			Matcher m = p.matcher(age);
			if(m.matches()) {
				validAge=true;
			}
			else {
				System.out.println("Enter valid age (should be greater than zero)");
			}
		}while(!validAge);
		return Integer.parseInt(age);
	}
	
	public String validateName(Scanner sc) {
		boolean validName;
		String name;
		String regex = "[A-Za-z\\s]+";
		Pattern p = Pattern.compile(regex);
		do {
			validName = false;
			name = sc.nextLine();
			Matcher m = p.matcher(name);
			if(m.matches()) {
				validName=true;
			}
			else {
				System.out.println("Enter valid name (only alphabets)");
			}
		}while(!validName);
		return name;
	}
		
	public String validateEmail(Scanner sc) {
		boolean validEmail;
		String email;
		String regex = "[0-9a-zA-Z][0-9a-zA-Z._]*@[a-z]+[.]com";
		Pattern p = Pattern.compile(regex);
		do {
			validEmail = false;
			email = sc.nextLine();
			Matcher m = p.matcher(email);
			if(m.matches()) {
				validEmail=true;
			}
			else {
				System.out.println("Enter proper email (must include @ and end with .com) ");
			}
		}while(!validEmail);
		return email;
	}
	
	public String validatePassword(Scanner sc) {
		boolean validPsd;
		String psd;
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$*])(?=\\S+$).{8,20}";
		Pattern p = Pattern.compile(regex);
		do {
			validPsd = false;
			psd = sc.nextLine();
			Matcher m = p.matcher(psd);
			if(m.matches()) {
				validPsd = true;
			}
			else {
				System.out.println("Enter valid password (must be from 8 to 20 characters long, should not contain spaces, can include @#$*, numbers,atleast one uppercase and one lowercase alphabet)");
			}
		}while(!validPsd);
		return psd;
	}
	
	public String validatePhoneNumber(Scanner sc) {
		boolean validPhoneNumber;
		String phoneNumber;
		String regex = "[7-9][0-9]{9}";
		Pattern p = Pattern.compile(regex);
		do {
			validPhoneNumber = false;
			phoneNumber = sc.nextLine();
			Matcher m = p.matcher(phoneNumber);
			if(m.matches()) {
				validPhoneNumber = true;
			}
			else {
				System.out.println("Enter valid phone number");
			}
		}while(!validPhoneNumber);
		return phoneNumber;
	}
	
	public int validateMark(Scanner sc) {
		boolean validMark;
		String mark;
		String regex = "[0-9][0-9]*";
		Pattern p = Pattern.compile(regex);
		do{
			validMark = false;
			mark = sc.nextLine();
			Matcher m = p.matcher(mark);
			if(m.matches()) {
				validMark = true;
			}
			else {
				System.out.println("Enter valid mark (0 - 100)");
			}
		}while(!validMark);
		return Integer.parseInt(mark);
	}
	
	public int validateAttendance(Scanner sc) {
		return validateMark(sc);
	}
	public int validateYear(Scanner sc) {
		boolean validYear;
		String year;
		String regex = "[1-2][0-9]{3}";
		Pattern p = Pattern.compile(regex);
		do{
			validYear = false;
			year = sc.nextLine();
			Matcher m = p.matcher(year);
			if(m.matches()) {
				validYear = true;
			}
			else {
				System.out.println("Enter valid year: (1900 - 2999)");
			}
		}while(!validYear);
		return Integer.parseInt(year);
	}
	
	public int validateInteger(Scanner sc) {
		boolean validSalary;
		String salary;
		String regex = "[1-9][0-9]*";
		Pattern p = Pattern.compile(regex);
		do {
			validSalary = false;
			salary = sc.nextLine();
			Matcher m = p.matcher(salary);
			if(m.matches()) {
				validSalary=true;
			}
			else {
				System.out.println("Enter valid integer");
			}
		}while(!validSalary);
		return Integer.parseInt(salary);
	}	
}
