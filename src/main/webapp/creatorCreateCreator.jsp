<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Creator</title>
</head>
<body>

	<h1>CREATE CREATOR PAGE</h1>
	<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
	<form action="creatorCreateCreator">
		<p>Name</p>
		<input type="text" name = "name" required="required">
		<p>Age</p>
		<input type="number" name = "age" required="required">
		<p>Email</p>
		<input type="text" name = "email" required="required">
		<p>Password</p>
		<input type="text" name = "password" required="required">
		<p>Phone Number</p>
		<input type="text" name = "phoneNo" required="required">
		<input type="submit" value = "Submit">
		<a href="creatorDashboard.jsp">Back</a>
	</form>
</body>
</html>