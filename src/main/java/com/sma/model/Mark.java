package com.sma.model;

public class Mark {
	
	private int english;
	private int tamil;
	private int maths;
	private int science;
	private int social;
	private int comSci;
	
	private static final int DEFAULT = -1;
	
	public Mark() {
		this.english = DEFAULT;
		this.tamil = DEFAULT;
		this.maths = DEFAULT;
		this.comSci = DEFAULT;
		this.social = DEFAULT;
		this.science = DEFAULT;
	}
	
	public Mark(int english, int tamil, int maths, int science, int social, int comSci) {
		this.english = english;
		this.tamil = tamil;
		this.maths = maths;
		this.comSci = comSci;
		this.science = science;
		this.social = social;
	}
	
	public int getEnglish() {
		return this.english;
	}
	
	public void setEnglish(int english) {
		this.english = english;
	}

	public void setTamil(int tamil) {
		this.tamil = tamil;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public void setSocial(int social) {
		this.social = social;
	}

	public void setComSci(int comSci) {
		this.comSci = comSci;
	}

	public int getTamil() {
		return this.tamil;
	}
	
	public int getMaths() {
		return this.maths;
	}
	
	public int getScience() {
		return this.science;
	}
	
	public int getSocial() {
		return this.social;
	}
	
	public int getComSci() {
		return this.comSci;
	}
	
	public int getTotal() {
		int total = this.comSci + this.social + this.science + this.maths + this.english + this.tamil;
		if(total >= 0) {
			return total;
		}
		return 0;
	}
	
	public String toString() {
		return this.english + " ----- " + this.tamil + " ----- " + this.maths + " ----- " + this.science
				+ " ----- " + this.social + " ----- " + this.comSci +" ----- "+this.getTotal();
	}
	
	
	
	

}
