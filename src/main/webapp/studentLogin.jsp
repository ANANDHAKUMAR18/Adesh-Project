<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Login</title>
</head>
<body>
	<h1>STUDENT LOGIN PAGE</h1>

	<form action="studentLoginServlet" method="post">
		<%
			String message=(String)request.getAttribute("message");  
			if(message != null)
			out.println("<font color=red size=4px>"+message+"</font>");
		%>  
		<p>School ID</p>
        <input type="number" name="schoolId" required="required">
		<p>Email</p>
        <input type="text" name="email" required="required">
        <p>Password</p>
        <input type="password" name="password" required="required">
        <input type="submit" value="Login">
        <a href="mainPage.jsp">Back</a>
	</form>
</body>
</html>