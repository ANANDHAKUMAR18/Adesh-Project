package com.sma.model;

public class Student extends User implements Comparable<Student>{

	private Attendance attendance;
	private Fee fee;
	private Mark mark;
	
	public Student(int id, String name, int age, String phoneNumber, String email, String password, Address address, Mark mark,Fee fee, Attendance attendance, int schoolId) {
		super(id, name, age, phoneNumber, email, password, address, schoolId);
		this.fee = fee;
		this.mark = mark;
		this.attendance = attendance;
	}

	
	public Fee getFeeObj() {
		return this.fee;
	}
	
	public Mark getMarkObj() {
		return this.mark;
	}
	
	public Attendance getAttendance() {
		return this.attendance;
	}
	
	public int compareTo(Student s) {
		return this.getId() - s.getId();
	}
	
}
