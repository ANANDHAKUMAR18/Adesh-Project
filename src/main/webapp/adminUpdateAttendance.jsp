<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sma.model.Admin"%>
<%@page import="com.sma.service.UserService"%>
<%@page import="com.sma.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Attendance</title>
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
<h1>ADMIN UPDATE STUDENT ATTENDANCE</h1>
<h3>Student Attendance Details</h3>

	<form action="adminSortByIdAndAttendance">
		<p>Sort By: 
		<select name = "choice">
	    <option value="id">ID</option>
	    <option value="attendance">Attendance</option>
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
				<th>ID</th> <th>NAME</th> <th>DAYS PRESENT</th> <th>DAYS ABSENT</th> 
			</tr>
			<%
				for(Student s : studentList){
			%>		
				<tr>
					<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getAttendance().getPresentDays()%></td> <td><%=s.getAttendance().getAbsentDays()%></td> 
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
				<th>ID</th> <th>NAME</th> <th>DAYS PRESENT</th> <th>DAYS ABSENT</th> 
			</tr>
			<%
				for(Student s : studentList){
			%>		
				<tr>
					<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getAttendance().getPresentDays()%></td> <td><%=s.getAttendance().getAbsentDays()%></td> 
				</tr>
			<%
				}
			%>
		</table>
		
	<%
		}
    
	%>
	
<form action="adminUpdateAttendance">
	<p>Student ID</p>
	<input type ="number" name = "id" required="required">
	<p>Number of days present</p>
	<input type ="number" name = "presentDays" required="required">
	<input type="submit" value ="Submit">
	<a href="adminDashboard.jsp">Back</a>
</form>
</body>
</html>
