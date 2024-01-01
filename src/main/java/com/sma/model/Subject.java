package com.sma.model;

import java.util.Comparator;

import com.sma.service.compare.ComScienceMarkCompare;
import com.sma.service.compare.EnglishMarkCompare;
import com.sma.service.compare.MathsMarkCompare;
import com.sma.service.compare.ScienceMarkCompare;
import com.sma.service.compare.SocialMarkCompare;
import com.sma.service.compare.TamilMarkCompare;

public enum Subject {
	
	S1("ENGLISH") {

		@Override
		public Comparator<Student> getComparator() {
			return new EnglishMarkCompare();
		}
		
	}, S2("TAMIL") {
		@Override
		public Comparator<Student> getComparator() {
			return new TamilMarkCompare();
		}
	}, S3("MATHS") {
		@Override
		public Comparator<Student> getComparator() {
			return new MathsMarkCompare();
		}
	}, S4("SCIENCE") {
		@Override
		public Comparator<Student> getComparator() {
			return new ScienceMarkCompare();
		}
	}, S5("SOCIAL") {
		@Override
		public Comparator<Student> getComparator() {
			return new SocialMarkCompare();
		}
	}, S6("COMPUTER SCIENCE") {
		@Override
		public Comparator<Student> getComparator() {
			return new ComScienceMarkCompare();
		}
	};
	
	public abstract Comparator<Student> getComparator();
	
	private String name;
	
	Subject(String name){
		this.name = name;
	}
	
	public String getSubjectName() {
		return this.name;
	}
	
}
