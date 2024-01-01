<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete School</title>
</head>
<body>

<h1>DELETE SCHOOL PAGE</h1>
<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
	<form action="creatorDeleteSchool">
		<p>School ID</p>
		<input type="number" name = "id" required="required">
		<input type="submit" value = "Submit">
		<a href="creatorDashboard.jsp">Back</a>
	</form>
</body>
</html>