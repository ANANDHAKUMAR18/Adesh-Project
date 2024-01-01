<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Query</title>
</head>
<body>
<h1>STUDENT ADD NEW QUERY PAGE</h1>
<%
String message=(String)request.getAttribute("message");  
if(message != null){
	out.println("<font color=red size=4px>"+message+"</font>");
}
%>
<form action="studentAddQuery">
	<p>Enter the subject</p>
	<input type="text" name="subject" required="required">
	<p>Enter your query statement</p>
	<input type="text" name="statement" required="required"><br>
	<input type="submit" value="Submit">
</form>

</body>
</html>