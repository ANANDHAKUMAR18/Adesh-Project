package com.sma.model;

public enum FeeStatus {
	
	PAID("PAID"), NOT_PAID("NOT_PAID");
	
	private String status;
	
	FeeStatus(String status){
		this.status = status;
	}
	
	public String toString() {
		return this.status;
	}
	
}
