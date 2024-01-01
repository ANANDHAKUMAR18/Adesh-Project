<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Student</title>
</head>

<body>
<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
<h1>ADMIN DELETE STUDENT PAGE</h1>
	<form action="adminDeleteStudent">
		<p>Student ID</p>
		<input type="number" name = "id" required="required">
		<input type="submit" value = "Submit">
		<a href="adminDashboard.jsp">Back</a>
	</form>
</body>
</html>