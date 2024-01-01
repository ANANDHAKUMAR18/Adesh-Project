package com.sma.model;

public class Attendance {
	
	private int presentDays;
	private int absentDays;
	
	private static final int TOTAL_DAYS = 100;
	private static final int DEFAULT = -1;
	
	public Attendance() {
		this.presentDays = DEFAULT;
		this.absentDays = DEFAULT;
	}
	
	public Attendance(int presentDays) {
		this.presentDays = presentDays;
		this.absentDays = TOTAL_DAYS - presentDays;
	}

	public int getPresentDays() {
		return this.presentDays;
	}
	
	public int getAbsentDays() {
		return this.absentDays;
	}
	
	public int getTotalDays() {
		return TOTAL_DAYS;
	}

	public void setAttendance(int presentDays) {
		this.presentDays = presentDays;
		this.absentDays = TOTAL_DAYS - this.presentDays;
	}
	
	public String toString() {
		System.out.println("TOTAL DAYS = "+ TOTAL_DAYS);
		System.out.println("PRESENT -- ABSENT");
		return this.presentDays+ " ----- "+ this.absentDays;
	}
}
