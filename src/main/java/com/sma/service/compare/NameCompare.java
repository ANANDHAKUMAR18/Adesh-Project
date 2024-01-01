package com.sma.service.compare;

import java.util.Comparator;

import com.sma.model.Student;

public class NameCompare implements Comparator<Student>{
	public int compare(Student s1, Student s2) {
		return s1.getName().compareTo(s2.getName());
	}
}
