package com.sma.model;

public enum QueryStatus {
	
	PENDING("PENDING"), RESOLVED("RESOLVED");
	
	private String statusName;
	
	QueryStatus(String statusName){
		this.statusName = statusName;
	}
	
	public String toString() {
		return this.statusName;
	}
}
