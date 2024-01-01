package com.sma.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Fee {
	
	private int tutionFee;
	private int extraCurrFee;
	private int examFee;
	private int stationeryFee;
	private int breakageFee;
	private Set <ExtraCurricularActivity> ecActivitySet;
	private FeeStatus feeStatus;
	
	public Fee(int examFee, int breakageFee, int tutionFee, int stationeryFee) {
		this.examFee = examFee;
		this.breakageFee = breakageFee;
		this.tutionFee = tutionFee;
		this.extraCurrFee = 0;
		this.stationeryFee = stationeryFee;
		this.ecActivitySet = new HashSet<>();
		feeStatus = FeeStatus.NOT_PAID;
	}
	
	public Fee(int tutionFee, int extraCurrFee, int examFee, int stationeryFee, int breakageFee, String feeStatus) {
		this.examFee = examFee;
		this.breakageFee = breakageFee;
		this.tutionFee = tutionFee;
		this.extraCurrFee = extraCurrFee;
		this.stationeryFee = stationeryFee;
		this.ecActivitySet = new HashSet<>();
		this.feeStatus = FeeStatus.valueOf(feeStatus);
	}

	public int getTutionFee() {
		return this.tutionFee;
	}
	
	public int getExtraCurrFee() {
		return this.extraCurrFee;
	}
	
	public int getBreakageFee() {
		return this.breakageFee;
	}
	
	public int getExamFee() {
		return this.examFee;
	}
	
	public int getStationeryFee() {
		return this.stationeryFee;
	}

	public Set<ExtraCurricularActivity> getEcActivitySet() {
		return Collections.unmodifiableSet(ecActivitySet);
	}

	public FeeStatus getFeeStatus() {
		return feeStatus;
	}

	public int getTotal() {
		return this.tutionFee + this.extraCurrFee + this.examFee + this.breakageFee + this.stationeryFee;
	}
	
	public String toString(){
		System.out.println("TUTION FEE -- EXTRACURR FEE -- EXAM FEE -- BREAKAGE FEE -- STATIONERY FEE -- TOTAL");
		return this.tutionFee+" ----- "+this.extraCurrFee+" ----- "+this.examFee+" ----- "+this.breakageFee+" ----- "+this.stationeryFee+" ----- "+this.getTotal();
	}

	public void switchFeeStatus() {
		this.feeStatus = FeeStatus.PAID;	
	}

	public boolean addIntoECActivitySet(ExtraCurricularActivity activity) {
		return this.ecActivitySet.add(activity);
	}
	
	public void addExtraCurrFee(int fee) {
		this.extraCurrFee += fee;
	}

}
