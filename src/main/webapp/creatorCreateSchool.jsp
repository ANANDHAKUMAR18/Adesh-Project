<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create School</title>
</head>
<body>

	<h1>CREATE SCHOOL PAGE</h1>
	<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
	<form action="creatorCreateSchool">
		<p>School Name</p>
		<input type="text" name = "name" required="required">
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


