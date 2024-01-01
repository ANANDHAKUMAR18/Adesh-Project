<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sma.service.UserService"%>
<%@page import="com.sma.model.Student"%>
<%@page import="com.sma.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Marks</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
<h1>ADMIN UPDATE MARKS PAGE</h1>
<h3>Student Attendance Details</h3>

    <form action="adminSortByMarks">
		<p>Sort By: 
		<select name = "choice">
		<option value="id">Id</option>
	    <option value="S1">English</option>
	    <option value="S2">Tamil</option>
	    <option value="S3">Maths</option>
	    <option value="S4">Science</option>
	    <option value="S5">Social</option>
	    <option value="S6">Computer Science</option>
	    <option value="total">Total</option>
	  	</select>
	  	</p>
	  	<input type="submit" value="Submit">
	</form>
	
	
	
	<%
	Admin admin = (Admin)request.getSession().getAttribute("user");
	List<Student> studentList;
	if(request.getAttribute("studentList") != null){
		studentList = (ArrayList<Student>)request.getAttribute("studentList");
		%>
		<h3>Student List</h3>
		<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>ENGLISH</th> <th>TAMIL</th> <th>MATHS</th> <th>SCIENCE</th> <th>SOCIAL</th> <th>COMPUTER SCIENCE</th> <th>TOTAL(600)</th> 
			</tr>
			<%
				for(Student s : studentList){
			%>		
				<tr>
					<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getMarkObj().getEnglish()%></td> <td><%=s.getMarkObj().getTamil()%></td> <td><%=s.getMarkObj().getMaths()%></td> <td><%=s.getMarkObj().getScience()%></td> <td><%=s.getMarkObj().getSocial()%></td> <td><%=s.getMarkObj().getComSci()%></td> <td><%=s.getMarkObj().getTotal()%></td> 
				</tr>
			<%
				}
			%>
		</table>
	<%
	}
	else{
		studentList = UserService.getInstance().sortStudentsById(admin.getSchoolId());
		%>
		<h3>Student List</h3>
		<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>ENGLISH</th> <th>TAMIL</th> <th>MATHS</th> <th>SCIENCE</th> <th>SOCIAL</th> <th>COMPUTER SCIENCE</th> <th>TOTAL(600)</th> 
			</tr>
			<%
				for(Student s : studentList){
			%>		
				<tr>
					<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getMarkObj().getEnglish()%></td> <td><%=s.getMarkObj().getTamil()%></td> <td><%=s.getMarkObj().getMaths()%></td> <td><%=s.getMarkObj().getScience()%></td> <td><%=s.getMarkObj().getSocial()%></td> <td><%=s.getMarkObj().getComSci()%></td> <td><%=s.getMarkObj().getTotal()%></td> 
				</tr>
			<%
				}
			%>
		</table>
		
	<%
		}
    
	%>
	
	<form action="adminUpdateMarks">
		<p>Student ID</p>
		<input type="number" name = "id" required="required">
		<p>English</p>
		<input type="number" name = "english" required="required">
		<p>Tamil</p>
		<input type="number" name = "tamil" required="required">
		<p>Maths</p>
		<input type="number" name = "maths" required="required">
		<p>Science</p>
		<input type="number" name = "science" required="required">
		<p>Social</p>
		<input type="number" name = "social" required="required">
		<p>Computer Science</p>
		<input type="number" name = "comSci" required="required">
		<input type="submit" value = "Submit">
		<a href="adminDashboard.jsp">Back</a>
	</form>
</body>
</html>