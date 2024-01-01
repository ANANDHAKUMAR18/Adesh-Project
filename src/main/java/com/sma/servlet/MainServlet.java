package com.sma.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sma.model.Address;
import com.sma.model.Admin;
import com.sma.model.Creator;
import com.sma.model.Fee;
import com.sma.model.FeeStatus;
import com.sma.model.Mark;
import com.sma.model.Student;
import com.sma.model.Subject;
import com.sma.service.UserService;


@WebServlet("/")
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		switch(request.getServletPath()) {
			case "/creatorLoginServlet":
				creatorLoginServlet(request,response);
				break;
			case "/creatorLogoutServlet":
				creatorLogoutServlet(request,response);
				break;
			case "/creatorCreateSchool":
				creatorCreateSchool(request,response);
				break;
			case "/creatorDeleteSchool":
				creatorDeleteSchool(request,response);
				break;
			case "/creatorCreateAdmin":
				creatorCreateAdmin(request,response);
				break;
			case "/creatorDeleteAdmin":
				creatorDeleteAdmin(request,response);
				break;
			case "/creatorCreateCreator":
				creatorCreateCreator(request,response);
				break;
			case "/creatorDeleteCreator":
				creatorDeleteCreator(request,response);
				break;
			case "/studentLoginServlet":
				studentLoginServlet(request,response);
				break;
			case "/studentLogoutServlet":
				studentLogoutServlet(request,response);
				break;
			case "/studentPayFees":
				studentPayFees(request,response);
				break;
			case "/studentAddQuery":
				studentAddQuery(request,response);
				break;
			case "/studentEnrollECA":
				studentEnrollECA(request,response);
				break;
			case "/adminLoginServlet":
				adminLoginServlet(request,response);
				break;
			case "/adminLogoutServlet":
				adminLogoutServlet(request,response);
				break;
			case "/adminCreateStudent":
				adminCreateStudent(request,response);
				break;
			case "/adminDeleteStudent":
				adminDeleteStudent(request,response);
				break;
			case "/adminUpdateMarks":
				adminUpdateMarks(request,response);
				break;
			case "/adminUpdateAttendance":
				adminUpdateAttendance(request,response);
				break;
			case "/adminResolveQuery":
				adminResolveQuery(request,response);
				break;
			case "/adminSortByIdAndName":
				adminSortByIdAndName(request,response);
				break;
			case "/adminSortByMarks":
				adminSortByMarks(request,response);
				break;
			case "/adminSortByIdAndAttendance":
				adminSortByIdAndAttendance(request,response);
			default:
				break;
		}
	}

//response.getWriter().println("From student enroll eca servlet");

	private void creatorLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(UserService.getInstance().creatorLogin(email, password)){
			HttpSession session = request.getSession();
			session.setAttribute("user", UserService.getInstance().getCreatorObjFromTable(email));
			response.sendRedirect("creatorDashboard.jsp");
		}
		else {
			request.setAttribute("message","Invalid Username or Password");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorLogin.jsp");            
			rd.include(request, response);
		}		
	}
	
	private void creatorLogoutServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		response.sendRedirect("creatorLogin.jsp");
	}
	
	private void creatorCreateSchool(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");
		String line1 = request.getParameter("line1");
		String line2 = request.getParameter("line2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String pincode = request.getParameter("pincode");
		if(UserService.getInstance().createSchool(name, phoneNo, new Address(line1,line2,city,state,country,pincode))) {
			request.setAttribute("message","School created successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateSchool.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateSchool.jsp");            
			rd.include(request, response);
		}
	}
	

	private void creatorDeleteSchool(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(UserService.getInstance().deleteSchool(id)) {
			request.setAttribute("message","School deleted successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorDeleteSchool.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","School not found");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorDeleteSchool.jsp");            
			rd.include(request, response);
		}
	}
	
	private void creatorCreateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		if(UserService.getInstance().getSchoolObjFromTable(schoolId) != null) {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("phoneNo");
			String line1 = request.getParameter("line1");
			String line2 = request.getParameter("line2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String pincode = request.getParameter("pincode");
			if(UserService.getInstance().createAdmin(name, age, email, password, phoneNo, new Address(line1,line2,city,state,country,pincode), schoolId)) {
				request.setAttribute("message","Admin created successfully");
				RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateAdmin.jsp");            
				rd.include(request, response);
			}
			else {
				request.setAttribute("message","Invalid input");
				RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateAdmin.jsp");            
				rd.include(request, response);
			}
		}
		else {
			request.setAttribute("message","School not found");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateAdmin.jsp");            
			rd.include(request, response);
		}
		
	}
	
	private void creatorDeleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(UserService.getInstance().deleteAdmin(id)) {
			request.setAttribute("message","Admin deleted successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorDeleteAdmin.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Admin not found");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorDeleteAdmin.jsp");            
			rd.include(request, response);
		}
	}
	
	private void creatorCreateCreator(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNo = request.getParameter("phoneNo");
		if(UserService.getInstance().createCreator(name, age, email, password, phoneNo)) {
			request.setAttribute("message","Creator created successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateCreator.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorCreateCreator.jsp");            
			rd.include(request, response);
		}
	}
	
	private void creatorDeleteCreator(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Creator creator = (Creator) request.getSession().getAttribute("user");
		if(UserService.getInstance().deleteCreator(id, creator.getId())) {
			request.setAttribute("message","Creator deleted successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorDeleteCreator.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Creator not deleted");
			RequestDispatcher rd = request.getRequestDispatcher("/creatorDeleteCreator.jsp");            
			rd.include(request, response);
		}
	}

	private void studentLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(UserService.getInstance().studentLogin(schoolId, email, password)){
			HttpSession session = request.getSession();
			session.setAttribute("user", UserService.getInstance().getStudentObjFromTable(email));
			response.sendRedirect("studentDashboard.jsp");
		}
		else {
			request.setAttribute("message","Invalid Username or Password");
			RequestDispatcher rd = request.getRequestDispatcher("/studentLogin.jsp");            
			rd.include(request, response);
		}		
	}
	
	private void studentLogoutServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		response.sendRedirect("studentLogin.jsp");
	}

	private void studentEnrollECA(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Student student = (Student) request.getSession().getAttribute("user");
		String code = request.getParameter("code");
		if(FeeStatus.NOT_PAID.equals(student.getFeeObj().getFeeStatus()) && UserService.getInstance().addExtraCurricularActivity(code, student)) {
			request.setAttribute("message","Enrolled successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/studentEnrollECA.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input or you have paid fees already");
			RequestDispatcher rd = request.getRequestDispatcher("/studentEnrollECA.jsp");            
			rd.include(request, response);
		}
	}
	
	private void studentPayFees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = (Student) request.getSession().getAttribute("user");
		if(FeeStatus.NOT_PAID.equals(student.getFeeObj().getFeeStatus())){
			UserService.getInstance().switchFeeStatus(student);
			request.setAttribute("message","Fees paid successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/studentDashboard.jsp");            
			rd.include(request, response);
		}
		else {
			UserService.getInstance().switchFeeStatus(student);
			request.setAttribute("message","Fees already paid");
			RequestDispatcher rd = request.getRequestDispatcher("/studentDashboard.jsp");            
			rd.include(request, response);
		}
	}
	
	private void studentAddQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = (Student) request.getSession().getAttribute("user");
		String subject = request.getParameter("subject");
		String statement = request.getParameter("statement");
		if(UserService.getInstance().addNewQuery(subject, statement, student)) {
			request.setAttribute("message","Query added successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/studentAddQuery.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input");
			RequestDispatcher rd = request.getRequestDispatcher("/studentAddQuery.jsp");            
			rd.include(request, response);
		}
	}
	
	private void adminLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(UserService.getInstance().adminLogin(email, password)){
			HttpSession session = request.getSession();
			session.setAttribute("user", UserService.getInstance().getAdminObjFromTable(email));
			response.sendRedirect("adminDashboard.jsp");
		}
		else {
			request.setAttribute("message","Invalid Username or Password");
			RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");            
			rd.include(request, response);
		}		
	}
	
	private void adminLogoutServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		response.sendRedirect("adminLogin.jsp");
	}
	
	private void adminCreateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("user");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNo = request.getParameter("phoneNo");
		String line1 = request.getParameter("line1");
		String line2 = request.getParameter("line2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String pincode = request.getParameter("pincode");
		int tutionFee = Integer.parseInt(request.getParameter("tutionFee"));
		int breakageFee = Integer.parseInt(request.getParameter("breakageFee"));
		int examFee = Integer.parseInt(request.getParameter("examFee"));
		int stationeryFee = Integer.parseInt(request.getParameter("stationeryFee"));
		if(UserService.getInstance().createNewStudent(admin.getSchoolId(), name, age, email, password, phoneNo, new Address(line1,line2,city,state,country,pincode), new Fee(examFee, breakageFee, tutionFee, stationeryFee))) {
			request.setAttribute("message","Student created successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/adminCreateStudent.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input");
			RequestDispatcher rd = request.getRequestDispatcher("/adminCreateStudent.jsp");            
			rd.include(request, response);
		}
	}

	private void adminDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("id"));
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(UserService.getInstance().removeStudent(admin.getSchoolId(), studentId)) {
			request.setAttribute("message","Student deleted successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/adminDeleteStudent.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input");
			RequestDispatcher rd = request.getRequestDispatcher("/adminDeleteStudent.jsp");            
			rd.include(request, response);
		}
	}
	
	private void adminUpdateMarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("id"));
		int english = Integer.parseInt(request.getParameter("english"));
		int tamil = Integer.parseInt(request.getParameter("tamil"));
		int maths = Integer.parseInt(request.getParameter("maths"));
		int science = Integer.parseInt(request.getParameter("science"));
		int social = Integer.parseInt(request.getParameter("social"));
		int comSci = Integer.parseInt(request.getParameter("comSci"));
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(UserService.getInstance().enterMark(admin.getSchoolId(), studentId, new Mark(english,tamil,maths,science,social,comSci))) {
			request.setAttribute("message","Marks updated successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/adminUpdateMarks.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Student not found");
			RequestDispatcher rd = request.getRequestDispatcher("/adminUpdateMarks.jsp");            
			rd.include(request, response);
		}
	}
	
	private void adminUpdateAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("user");
		int studentId = Integer.parseInt(request.getParameter("id"));
		int presentDays = Integer.parseInt(request.getParameter("presentDays"));
		if(UserService.getInstance().enterAttendance(admin.getSchoolId(), studentId, presentDays)) {
			request.setAttribute("message","Attendance updated successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/adminUpdateAttendance.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Student not found");
			RequestDispatcher rd = request.getRequestDispatcher("/adminUpdateAttendance.jsp");            
			rd.include(request, response);
		}
	}

	private void adminResolveQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qid = Integer.parseInt(request.getParameter("qid"));
		String reply = request.getParameter("reply");
		if(UserService.getInstance().resolveQuery(qid,reply)) {
			request.setAttribute("message","Query resolved successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/adminResolveQueries.jsp");            
			rd.include(request, response);
		}
		else {
			request.setAttribute("message","Invalid input or query already resolved");
			RequestDispatcher rd = request.getRequestDispatcher("/adminResolveQueries.jsp");            
			rd.include(request, response);
		}
	}
	
	private void adminSortByIdAndName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = request.getParameter("choice"); 
		Admin admin = (Admin) request.getSession().getAttribute("user");
		switch(choice) {
			case "id":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsById(admin.getSchoolId()));
				break;
			case "name":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByName(admin.getSchoolId()));
				break;
			default:
				request.setAttribute("studentList", UserService.getInstance().sortStudentsById(admin.getSchoolId()));
				break;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/adminDashboard.jsp");            
		rd.include(request, response);		
	}
	
	private void adminSortByMarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = request.getParameter("choice"); 
		Admin admin = (Admin) request.getSession().getAttribute("user");
		switch(choice) {
			case "Id":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsById(admin.getSchoolId()));
				break;
			case "S1":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByMark(admin.getSchoolId(),Subject.S1));
				break;
			case "S2":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByMark(admin.getSchoolId(),Subject.S2));
				break;
			case "S3":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByMark(admin.getSchoolId(),Subject.S3));
				break;
			case "S4":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByMark(admin.getSchoolId(),Subject.S4));
				break;
			case "S5":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByMark(admin.getSchoolId(),Subject.S5));
				break;
			case "S6":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByMark(admin.getSchoolId(),Subject.S6));
				break;
			case "total":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByTotalMark(admin.getSchoolId()));
				break;
			default:
				request.setAttribute("studentList", UserService.getInstance().sortStudentsById(admin.getSchoolId()));
				break;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/adminUpdateMarks.jsp");            
		rd.include(request, response);		
	}
	
	private void adminSortByIdAndAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = request.getParameter("choice"); 
		Admin admin = (Admin) request.getSession().getAttribute("user");
		switch(choice) {
			case "id":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsById(admin.getSchoolId()));
				break;
			case "attendance":
				request.setAttribute("studentList", UserService.getInstance().sortStudentsByAttendance(admin.getSchoolId()));
				break;
			default:
				request.setAttribute("studentList", UserService.getInstance().sortStudentsById(admin.getSchoolId()));
				break;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/adminUpdateAttendance.jsp");            
		rd.include(request, response);	
	}

}
