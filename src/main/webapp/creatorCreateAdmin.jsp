<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Admin</title>
</head>
<body>
	<h1>CREATE ADMIN PAGE</h1>
<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
	<form action="creatorCreateAdmin">
		<p>School ID</p>
		<input type="number" name = "schoolId" required="required">
		<p>Admin Name</p>
		<input type="text" name = "name" required="required">
		<p>Admin Age</p>
		<input type="number" name = "age" required="required">
		<p>Email</p>
		<input type="text" name = "email" required="required">
		<p>Password</p>
		<input type="text" name = "password" required="required">
		<p>Phone Number</p>
		<input type="text" name = "phoneNo" required="required">
		<p>Address (Line 1)</p>
		<input type="text" name = "line1" required="required">
		<p>Address (Line 2)</p>
		<input type="text" name = "line2" required="required">
		<p>Address (City)</p>
		<input type="text" name = "city" required="required">
		<p>Address (State)</p>
		<input type="text" name = "state" required="required">
		<p>Address (Country)</p>
		<input type="text" name = "country" required="required">
		<p>Address (Pincode)</p>
		<input type="text" name = "pincode" required="required">
		<input type="submit" value = "Submit">
		<a href="creatorDashboard.jsp">Back</a>
	</form>

</body>
</html>