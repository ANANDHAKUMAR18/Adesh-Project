package com.sma.service.compare;

import java.util.Comparator;

import com.sma.model.Student;

public class SocialMarkCompare implements Comparator<Student>{
	public int compare(Student s1, Student s2) {
		if(s2.getMarkObj().getSocial() < s1.getMarkObj().getSocial()) {
			return -1;
		}
		if(s2.getMarkObj().getSocial() > s1.getMarkObj().getSocial()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
