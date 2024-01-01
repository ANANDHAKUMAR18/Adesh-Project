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
<title>Admin Dashboard</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
<h1>ADMIN DASHBOARD</h1>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(session.getAttribute("user") == null){
    	response.sendRedirect("adminLogin.jsp");
    }else{
    	String message=(String)request.getAttribute("message");  
    	if(message != null){
        	out.println("<font color=red size=4px>"+message+"</font>");
    	}
    	Admin admin = (Admin)request.getSession().getAttribute("user");
    %>
    	<h3>Welcome <%=admin.getName()%>, ID = <%=admin.getId()%></h3>
    
	
	<hr>
	<a href="adminCreateStudent.jsp">Add Student</a><br>
	<a href="adminDeleteStudent.jsp">Remove Student</a><br>
	<a href="adminUpdateMarks.jsp">Update Student Marks</a><br>
	<a href="adminUpdateAttendance.jsp">Update Student Attendance</a><br>
	<a href="adminResolveQueries.jsp">Resolve Queries</a><br>
	<a href="adminStudentsYetToPayFees.jsp">View students fees status</a><br>
	<a href="adminLogoutServlet">Logout</a>
	<hr>
	
	<form action="adminSortByIdAndName">
	<p>Sort By: 
	<select name = "choice">
    <option value="id">ID</option>
    <option value="name">NAME</option>
  	</select>
  	</p>
  	<input type="submit" value="Submit">
	</form>
	
	<%
	if(request.getAttribute("studentList") != null){
		List<Student> studentList = (ArrayList<Student>)request.getAttribute("studentList");
		%>
		<h3>Student List</h3>
		<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>AGE</th> <th>EMAIL</th> <th>PHONE NO</th> <th>ADDRESS</th> 
			</tr>
			<%
				for(Student s : studentList){
			%>		
				<tr>
					<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getAge()%></td> <td><%=s.getEmail()%></td> <td><%=s.getPhoneNumber()%></td> <td><%=s.getAddressObj()%></td>
				</tr>
			<%
				}
			%>
		</table>
	<%
	}
	else{
		List<Student> studentList = UserService.getInstance().sortStudentsById(admin.getSchoolId());
		%>
		<h3>Student List</h3>
		<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>AGE</th> <th>EMAIL</th> <th>PHONE NO</th> <th>ADDRESS</th> 
			</tr>
			<%
				for(Student s : studentList){
			%>		
				<tr>
					<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getAge()%></td> <td><%=s.getEmail()%></td> <td><%=s.getPhoneNumber()%></td> <td><%=s.getAddressObj()%></td>
				</tr>
			<%
				}
			%>
		</table>
		
	<%
		}
    }
	%>
	
</body>
</html>