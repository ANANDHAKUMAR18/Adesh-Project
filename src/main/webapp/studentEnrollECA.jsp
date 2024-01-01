<%@page import="com.sma.model.ExtraCurricularActivity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enroll into activity</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
	<body>
	<h1>STUDENT EXTRA CURRICULAR ACTIVITY ENROLLMENT PAGE</h1>
	<%
			String message=(String)request.getAttribute("message");  
			if(message != null)
			out.println("<font color=red size=4px>"+message+"</font>");
	%>  
		
	<h3>EXTRA CURRICULAR ACTIVITIES AVAILABLE</h3>
	<table>
	<tr>
		<th>ACTIVITY CODE</th> <th>ACTIVITY NAME</th> <th>COST</th>
	</tr>
		<%
			for(ExtraCurricularActivity e : ExtraCurricularActivity.values()){
		%>		
			<tr>
				<td><%=e.name()%></td> <td><%=e%></td> <td><%=e.getFee()%></td> 
			</tr>
		<%
			}
		%>
	
	</table>
	<form action="studentEnrollECA">
	<p>Enter the activity code</p>
	<input type="text" name = "code" required="required">
	<input type="submit" value="Submit">
	<a href="studentDashboard.jsp">Back</a>
	</form>
		
		  
		

</body>
</html>