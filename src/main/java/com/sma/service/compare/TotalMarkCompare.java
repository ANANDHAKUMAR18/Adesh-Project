package com.sma.service.compare;

import java.util.Comparator;

import com.sma.model.Student;

public class TotalMarkCompare implements Comparator<Student>{
	public int compare(Student s1, Student s2) {
		if(s2.getMarkObj().getTotal() < s1.getMarkObj().getTotal()) {
			return -1;
		}
		if(s2.getMarkObj().getTotal() > s1.getMarkObj().getTotal()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
