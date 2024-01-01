package com.sma.model;

public enum ExtraCurricularActivity {
	
	E1("Cricket",20000), E2("Chess",7500), E3("Carrom",5000), E4("Hockey",15000), E5("Tennis",25000),
	E6("Table Tennis",12500), E7("Yoga",10000), E8("Karate",7500), E9("General Fitness",5000);
	
	private String name;
	private int fee;
	
	ExtraCurricularActivity(String name, int fee) {
		this.name = name;
		this.fee = fee;
	}
	
	public int getFee() {
		return this.fee;
	}
	
	public String toString() {
		return this.name;
	}
}
