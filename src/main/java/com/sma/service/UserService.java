package com.sma.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.sma.model.Address;
import com.sma.model.Admin;
import com.sma.model.Attendance;
import com.sma.model.Creator;
import com.sma.model.ExtraCurricularActivity;
import com.sma.model.Fee;
import com.sma.model.FeeStatus;
import com.sma.model.Mark;
import com.sma.model.Query;
import com.sma.model.School;
import com.sma.model.Student;
import com.sma.model.Subject;
import com.sma.service.compare.AttendanceCompare;
import com.sma.service.compare.ComScienceMarkCompare;
import com.sma.service.compare.EnglishMarkCompare;
import com.sma.service.compare.MathsMarkCompare;
import com.sma.service.compare.NameCompare;
import com.sma.service.compare.ScienceMarkCompare;
import com.sma.service.compare.SocialMarkCompare;
import com.sma.service.compare.TamilMarkCompare;
import com.sma.service.compare.TotalMarkCompare;

public class UserService {
	
	private UserService() {}
	
	private static UserService instance = null;
	
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	public List<School> getExistingSchools() {
		List<School> schoolList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id from School");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//System.out.println( rs.getInt("s.id") + "---" +rs.getString("s.name") + "---" +rs.getString("s.phone_no") + "---" +rs.getString("a.line_1") + "---" +rs.getString("a.line_2") + "---" +rs.getString("a.city") + "---" +rs.getString("a.state") + "---" +rs.getString("a.country") + "---" +rs.getString("pincode"));
				schoolList.add(getSchoolObjFromTable(rs.getInt("id")));
			}
			return schoolList;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return schoolList;
	}
	
	public List<Admin> getExistingAdmins() {
		List<Admin> adminList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select email from User where type = 'ADMIN'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				adminList.add(getAdminObjFromTable(rs.getString("email")));
				//System.out.println( rs.getInt("s.id") + "---" +rs.getString("s.name") + "---" +rs.getInt("u.id")  + "---" +rs.getString("u.name") + "---" +rs.getInt("u.age") + "---" +rs.getString("u.email") + "---" +rs.getString("u.phone_no"));
			}
			return adminList;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return adminList;
	}
	
	public List<Creator> getExistingCreators() {
		List<Creator> creatorList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select email from User where type = 'CREATOR' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				creatorList.add(getCreatorObjFromTable(rs.getString("email")));
				//System.out.println( rs.getInt("id") + "---" +rs.getString("name")+ "---" +rs.getInt("age") + "---" +rs.getString("email") + "---" +rs.getString("phone_no"));
			}
			return creatorList;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return creatorList;
	}
	
	//creator 
	
	public boolean creatorLogin(String email, String password) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id from User where email = ? and password = ? and type = 'CREATOR'");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			return (rs.next());
		}catch(SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean createSchool(String name, String phoneNumber, Address address) {
		try {
			
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into School (name,phone_no) values(?,?)");
			ps.setString(1, name);
			ps.setString(2, phoneNumber);
			ps.execute();
			
			PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("select id from School where phone_no = ?");
			ps1.setString(1, phoneNumber);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			
			PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("insert into Address (school_id,line_1,line_2,city,state,country,pincode) values(?,?,?,?,?,?,?)");
			ps2.setInt(1, rs1.getInt("id"));
			ps2.setString(2, address.getLine1());
			ps2.setString(3, address.getLine2());
			ps2.setString(4, address.getCity());
			ps2.setString(5, address.getState());
			ps2.setString(6, address.getCountry());
			ps2.setString(7, address.getPincode());
			return(ps2.executeUpdate() > 0);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteSchool(int schoolId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("delete from School where id = ?");
			ps.setInt(1, schoolId);
			return (ps.executeUpdate() > 0);	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;	
	}

	public boolean createAdmin(String name, int age, String email, String password, String phoneNumber, Address address, int schoolId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into User(name,age,email,password,phone_no,type,school_id) values(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setString(5, phoneNumber);
			ps.setString(6, "ADMIN");
			ps.setInt(7, schoolId);
			ps.execute();
			PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("select id from User where email = ?");
			ps1.setString(1, email);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("insert into Address(user_id,line_1,line_2,city,state,country,pincode) values(?,?,?,?,?,?,?)");
			ps2.setInt(1, rs1.getInt("id"));
			ps2.setString(2, address.getLine1());
			ps2.setString(3, address.getLine2());
			ps2.setString(4, address.getCity());
			ps2.setString(5, address.getState());
			ps2.setString(6, address.getCountry());
			ps2.setString(7, address.getPincode());
			return(ps2.executeUpdate() > 0);	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteAdmin(int id) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("delete from User where id = ? and type = 'ADMIN'");
			ps.setInt(1, id);
			return (ps.executeUpdate() > 0);	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;	
	}
	
	public boolean createCreator(String name, int age, String email, String password, String phoneNumber) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into User(name,age,email,password,phone_no,type) values(?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setString(5, phoneNumber);
			ps.setString(6, "CREATOR");
			return(ps.executeUpdate() > 0);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteCreator(int id, int cid) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("delete from User where id != ? and id = ?");
			ps.setInt(1, cid);
			ps.setInt(2, id);
			return (ps.executeUpdate() > 0);	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;	
	}
	
	//admin
	
	public boolean adminLogin(String email, String password) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select email,password,type from User where email = ? and password = ? and type = 'ADMIN'");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			return (rs.next());
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean createNewStudent(int schoolId, String name, int age, String email, String password, String phoneNumber, Address address, Fee fee) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id from School where id = ?");
			ps.setInt(1, schoolId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs.getInt("id") > 0) {
				PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("insert into User(name,age,email,password,phone_no,type,school_id) values(?,?,?,?,?,?,?)");
				ps1.setString(1, name);
				ps1.setInt(2, age);
				ps1.setString(3, email);
				ps1.setString(4, password);
				ps1.setString(5, phoneNumber);
				ps1.setString(6, "STUDENT");
				ps1.setInt(7, schoolId);
				ps1.execute();
				PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("select id from User where email = ?");
				ps2.setString(1, email);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				PreparedStatement ps3 = DBConnection.getConnection().prepareStatement("insert into Address(user_id,line_1,line_2,city,state,country,pincode) values(?,?,?,?,?,?,?)");
				ps3.setInt(1, rs2.getInt("id"));
				ps3.setString(2, address.getLine1());
				ps3.setString(3, address.getLine2());
				ps3.setString(4, address.getCity());
				ps3.setString(5, address.getState());
				ps3.setString(6, address.getCountry());
				ps3.setString(7, address.getPincode());
				ps3.execute();
				PreparedStatement ps4 = DBConnection.getConnection().prepareStatement("insert into Fee(student_id,tution_fee,extracurr_fee,exam_fee,stationery_fee,breakage_fee,fee_status) values(?,?,?,?,?,?,?)");
				ps4.setInt(1, rs2.getInt("id"));
				ps4.setInt(2, fee.getTutionFee());
				ps4.setInt(3, fee.getExtraCurrFee());
				ps4.setInt(4, fee.getExamFee());
				ps4.setInt(5, fee.getStationeryFee());
				ps4.setInt(6, fee.getBreakageFee());
				ps4.setString(7, fee.getFeeStatus().name());
				ps4.execute();
				PreparedStatement ps5 = DBConnection.getConnection().prepareStatement("insert into Mark(student_id,english,tamil,maths,science,social,computer_science) values(?,-1,-1,-1,-1,-1,-1)");
				ps5.setInt(1, rs2.getInt("id"));
				return (ps5.executeUpdate() > 0);	
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean removeStudent(int schoolId, int studentId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("delete from User where school_id = ? and id = ?");
			ps.setInt(1, schoolId);
			ps.setInt(2, studentId);
			return (ps.executeUpdate() > 0);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean enterMark(int schoolId, int studentId, Mark mark) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id from User where id = ? and school_id = ?");
			ps.setInt(1, studentId);
			ps.setInt(2, schoolId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("update Mark set english = ?, tamil = ?, maths = ?, science = ?, social = ?, computer_science = ? where student_id = ?");
				ps1.setInt(1, mark.getEnglish());
				ps1.setInt(2, mark.getTamil());
				ps1.setInt(3, mark.getMaths());
				ps1.setInt(4, mark.getScience());
				ps1.setInt(5, mark.getSocial());
				ps1.setInt(6, mark.getComSci());
				ps1.setInt(7, studentId);
				return (ps1.executeUpdate() > 0);
			}
			return false;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public List<Student> sortStudentsById(int schoolId) {
		List<Student> studentList = getStudentList(schoolId);
		Collections.sort(studentList);
		return (studentList);
	}
	
	public List<Student> sortStudentsByName(int schoolId) {
		List<Student> studentList = getStudentList(schoolId);
		Collections.sort(studentList, new NameCompare());
		return(studentList);
	}
	
	public List<Student> getStudentList(int schoolId){
		List<Student> studentList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select email from User where school_id = ? and type = 'STUDENT'");
			ps.setInt(1, schoolId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				studentList.add(getStudentObjFromTable(rs.getString("email")));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return studentList;
	}
	
	public void printStudentDetails(List<Student> studentList) {
		System.out.println("ID ----- NAME ----- AGE ----- PHONE ----- EMAIL ----- ADDRESS");
		for(Student s : studentList) {
			System.out.println(s);
		}
	}
	
	
	
	public List<Student> sortStudentsByMark(int schoolId, Subject subject) {
		List<Student> studentList = getStudentList(schoolId);
		Collections.sort(studentList,subject.getComparator());
		return(studentList);
	}
	
	public void printStudentMarks(List<Student> studentList) {
		System.out.println("ID ----- Name ----- ENGLISH -- TAMIL -- MATHS -- SCIENCE -- SOCIAL -- COM SCIENCE -- TOTAL");
		for(Student s : studentList) {
			System.out.println(s.getId()+" ----- "+s.getName()+" ----- "+s.getMarkObj());
		}
	}
	
	public List<Student> sortStudentsByTotalMark(int schoolId) {
		List<Student> studentList = getStudentList(schoolId);
		Collections.sort(studentList,new TotalMarkCompare());
		return(studentList);
	}
	
	public List<Student> sortStudentsByAttendance(int schoolId) {
		List<Student> studentList = getStudentList(schoolId);
		Collections.sort(studentList,new AttendanceCompare());
		return(studentList);
	}
	
	public void printToppersOfEachSubject(int schoolId) {
		System.out.println("SUBJECT ----- ID ----- NAME ----- MARKS");
		List<Student> studentList = getStudentList(schoolId);
		
		Collections.sort(studentList,new EnglishMarkCompare());
		System.out.println(Subject.S1+" ----- "+studentList.get(0).getId()+" ----- "+studentList.get(0).getName()+" ----- "+studentList.get(0).getMarkObj().getEnglish());
		
		Collections.sort(studentList,new TamilMarkCompare());
		System.out.println(Subject.S2+" ----- "+studentList.get(0).getId()+" ----- "+studentList.get(0).getName()+" ----- "+studentList.get(0).getMarkObj().getTamil());
		
		Collections.sort(studentList,new MathsMarkCompare());
		System.out.println(Subject.S3+" ----- "+studentList.get(0).getId()+" ----- "+studentList.get(0).getName()+" ----- "+studentList.get(0).getMarkObj().getMaths());
		
		Collections.sort(studentList,new ScienceMarkCompare());
		System.out.println(Subject.S4+" ----- "+studentList.get(0).getId()+" ----- "+studentList.get(0).getName()+" ----- "+studentList.get(0).getMarkObj().getScience());
		
		Collections.sort(studentList,new SocialMarkCompare());
		System.out.println(Subject.S5+" ----- "+studentList.get(0).getId()+" ----- "+studentList.get(0).getName()+" ----- "+studentList.get(0).getMarkObj().getSocial());
		
		Collections.sort(studentList,new ComScienceMarkCompare());
		System.out.println(Subject.S6+" ----- "+studentList.get(0).getId()+" ----- "+studentList.get(0).getName()+" ----- "+studentList.get(0).getMarkObj().getComSci());
	}

	public boolean resolveQuery(int qid, String reply) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("update Query set reply = ?, status = 'RESOLVED' where id = ? and status = 'PENDING'");
			ps.setString(1, reply);
			ps.setInt(2, qid);
			return(ps.executeUpdate() > 0);
		}catch(SQLException e) {
			System.out.println(e);
		}
		return false;
	}
		
	public List<Query> getQueryListFromTable() {
		try {
			List<Query> queryList = new ArrayList<>();
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from Query");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				queryList.add(new Query(rs.getInt("id"),rs.getInt("student_id"),rs.getString("subject"),rs.getString("statement"),rs.getString("reply"),rs.getString("status")));
			}
			return queryList;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public boolean enterAttendance(int schoolId, int studentId, int presentDays) {
		try {
			PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("update User set present_days = ? where id = ? and school_id = ?");
			ps1.setInt(1, presentDays);
			ps1.setInt(2, studentId);
			ps1.setInt(3, schoolId);
			return(ps1.executeUpdate() > 0);
		}catch(SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public void printStudentFeeStatus(List<Student> studentList) {
		for(Student s: studentList) {
			if(s.getFeeObj().getFeeStatus().name().equals("NOT_PAID")) {
				System.out.println(s.getId() +" --- "+s.getName()+" --- "+s.getEmail()+" --- "+s.getPhoneNumber()+" --- "+s.getFeeObj().getFeeStatus());
			}
		}
	}

	//student
	
	public boolean studentLogin(int schoolId, String email, String password) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id from User where email = ? and password = ? and school_id = ? and type = 'STUDENT'");
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, schoolId);
			ResultSet rs = ps.executeQuery();
			return (rs.next());
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public void showStudentMarks(Student student) {
		System.out.println(student.getMarkObj());
	}

	public void showStudentAttendance(Student student) {
		System.out.println(student.getAttendance());
	}

	public void showStudentFees(Student student) {
		System.out.println(student.getFeeObj());
	}
	
	public void payFees(Scanner sc, Student student) {
		if(student.getFeeObj().getFeeStatus() == FeeStatus.NOT_PAID) {
			System.out.println("FEE DETAILS:");
			System.out.println("Tution Fee = "+student.getFeeObj().getTutionFee());
			System.out.println("Exam Fee = "+student.getFeeObj().getExamFee());
			System.out.println("Stationery Fee = "+student.getFeeObj().getStationeryFee());
			System.out.println("Extra Curricular fee details:");
			List<ExtraCurricularActivity> ecActivityList = getEcActivityList(student.getId());
			System.out.println("ACTIVITY ----- COST");
			if(ecActivityList.size() > 0) {
				for(ExtraCurricularActivity e : ecActivityList) {
					System.out.println(e.toString()+" ----- "+e.getFee());
				}
			}
			else {
				System.out.println("No activities enrolled");
			}
			System.out.println("Total Extra Curricular Activity fee = "+student.getFeeObj().getExtraCurrFee());
			System.out.println("Breakage Fee = "+ student.getFeeObj().getBreakageFee());
			System.out.println("TOTAL FEES TO BE PAID = "+student.getFeeObj().getTotal());
			System.out.println("Do you want to pay: (Press 1 for 'Yes', any other key for 'No')");
			String ch = sc.nextLine();
			if("1".equals(ch)) {
				try {
					PreparedStatement ps = DBConnection.getConnection().prepareStatement("update Fee set fee_status = 'PAID' where student_id = ?");
					ps.setInt(1, student.getId());
					ps.execute();
					student.getFeeObj().switchFeeStatus();
					System.out.println("Fees paid successfully");
				}catch(SQLException e) {
					System.out.println(e);
				}	
			}
		}
		else {
			System.out.println("You have already paid the fees..");
		}
	}
	
	public void switchFeeStatus(Student student) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("update Fee set fee_status = 'PAID' where student_id = ?");
			ps.setInt(1, student.getId());
			ps.execute();
			student.getFeeObj().switchFeeStatus();
			System.out.println("Fees paid successfully");
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}

	public List<Query> getMyQueires(Student student) {
		List<Query> queryList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from Query where student_id = ?");
			ps.setInt(1, student.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				queryList.add(new Query( rs.getInt("id"),rs.getInt("student_id"),rs.getString("subject"),rs.getString("statement"),rs.getString("reply"),rs.getString("status")));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return queryList;
	}
	
	public boolean addNewQuery(String subject, String statement, Student student) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into Query(student_id,subject,statement) values(?,?,?)");
			ps.setInt(1, student.getId());
			ps.setString(2, subject);
			ps.setString(3, statement);
			return (ps.executeUpdate() > 0);
		}catch(SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean addExtraCurricularActivity(String code, Student student) {
		try {
			ExtraCurricularActivity activity = ExtraCurricularActivity.valueOf(code);
			if(!getEcActivityList(student.getId()).contains(activity)) {
				PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into ExtraCurricular(student_id,activity,cost) values(?,?,?)");
				ps.setInt(1, student.getId());
				ps.setString(2, activity.name());
				ps.setInt(3, activity.getFee());
				ps.execute();
				student.getFeeObj().addExtraCurrFee(activity.getFee());
				PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("update Fee set extracurr_fee = ? where student_id = ?");
				ps1.setInt(1, student.getFeeObj().getExtraCurrFee());
				ps1.setInt(2, student.getId());
				return(ps1.executeUpdate() > 0);
			}
			else {
				System.out.println("You have enrolled into this activity already");
			}
		}catch(IllegalArgumentException | SQLException e1) {
			System.out.println("INVALID INPUT");
		}
		return false;
	}

	public List<ExtraCurricularActivity> getEcActivityList(int studentId) {
		List<ExtraCurricularActivity> activityList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select activity from ExtraCurricular where student_id = ?");
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				activityList.add(ExtraCurricularActivity.valueOf(rs.getString("activity")));
			}
			return activityList;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return activityList;
	}
	
	public Mark getMarks(Scanner sc) {
		Mark mark = new Mark();
		System.out.println("Enter English mark:");
		mark.setEnglish(sc.nextInt());
		System.out.println("Enter Tamil mark:");
		mark.setTamil(sc.nextInt());
		System.out.println("Enter Maths mark:");
		mark.setMaths(sc.nextInt());
		System.out.println("Enter Science mark:");		
		mark.setScience(sc.nextInt());
		System.out.println("Enter Social mark:");
		mark.setSocial(sc.nextInt());
		System.out.println("Enter Computer Science mark:");
		mark.setComSci(sc.nextInt());
		return mark;
	}
	
	public Fee getFeeDetails(Scanner sc) {
		System.out.println("Enter Exam Fee:");
		int examFee = InputCheck.getInstance().validateInteger(sc);
		System.out.println("Enter Breakage Fee:");
		int breakageFee = InputCheck.getInstance().validateInteger(sc); 
		System.out.println("Enter Tution Fee:");
		int tutionFee = InputCheck.getInstance().validateInteger(sc);	
		System.out.println("Enter Stationery Fee:");
		int stationeryFee = InputCheck.getInstance().validateInteger(sc);	
		return new Fee(examFee, breakageFee, tutionFee, stationeryFee);
	}

	public Address getAddressDetails(Scanner sc) {
		System.out.println("Enter the school address (line 1):");
		String line1 = sc.nextLine();
		System.out.println("Enter the school address (line 2):");
		String line2 = sc.nextLine();
		System.out.println("Enter the school address (city):");
		String city = sc.nextLine();
		System.out.println("Enter the school address (state):");
		String state = sc.nextLine();
		System.out.println("Enter the school address (country):");
		String country = sc.nextLine();
		System.out.println("Enter the school address (pincode):");
		String pincode = sc.nextLine();
		return new Address(line1, line2, city, state, country, pincode);
	}
	
	public Fee getFeeObjFromTable(int studentId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select tution_fee,extracurr_fee,exam_fee,stationery_fee,breakage_fee,fee_status from Fee where student_id = ?");
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Fee(rs.getInt("tution_fee"),rs.getInt("extracurr_fee"),rs.getInt("exam_fee"),rs.getInt("stationery_fee"),rs.getInt("breakage_fee"),rs.getString("fee_status"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Mark getMarkObjFromTable(int studentId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select english,tamil,maths,science,social,computer_science from Mark where student_id = ?");
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Mark(rs.getInt("english"),rs.getInt("tamil"),rs.getInt("maths"),rs.getInt("science"),rs.getInt("social"),rs.getInt("computer_science"));
			}
			else {
				return new Mark();
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Address getSchoolAddressObjFromTable(int schoolId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select line_1,line_2,city,state,country,pincode from Address where school_id = ?");
			ps.setInt(1, schoolId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Address(rs.getString("line_1"),rs.getString("line_2"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pincode"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Address getUserAddressObjFromTable(int userId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select line_1,line_2,city,state,country,pincode from Address where user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Address(rs.getString("line_1"),rs.getString("line_2"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pincode"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public School getSchoolObjFromTable(int schoolId) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select name,phone_no from School where id = ?");
			ps.setInt(1, schoolId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new School(schoolId,rs.getString("name"),getSchoolAddressObjFromTable(schoolId),rs.getString("phone_no"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Student getStudentObjFromTable(String email) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id,school_id,name,age,phone_no,present_days,password from User where email = ? and type = 'STUDENT'");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Student(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getString("phone_no"),email,rs.getString("password"),getUserAddressObjFromTable(rs.getInt("id")),getMarkObjFromTable(rs.getInt("id")),getFeeObjFromTable(rs.getInt("id")),new Attendance(rs.getInt("present_days")), rs.getInt("school_id"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Admin getAdminObjFromTable(String email) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id,name,age,phone_no,school_id,password from User where email = ? and type = 'ADMIN'");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Admin(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getString("phone_no"),email,rs.getString("password"),getUserAddressObjFromTable(rs.getInt("id")),rs.getInt("school_id"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Creator getCreatorObjFromTable(String email) {
		try {
			PreparedStatement ps = DBConnection.getConnection().prepareStatement("select id,name,age,phone_no,password from User where email = ? and type = 'CREATOR'");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Creator(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),email,rs.getString("password"),rs.getString("phone_no"));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	
	
}
