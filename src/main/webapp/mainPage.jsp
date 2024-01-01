<%@page import="com.sma.model.School"%>
<%@page import="java.util.List"%>
<%@page import="com.sma.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
		<h1>MAIN PAGE</h1>
		<a href="creatorLogin.jsp">Creator login</a>
		<a href="adminLogin.jsp">Admin login</a>
		<a href="studentLogin.jsp">Student login</a>
		<p>Existing Schools</p>
		<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>PHONE NO</th> <th>ADDRESS</th>
			</tr>
		<%
			List<School> schoolList = UserService.getInstance().getExistingSchools();
			for(School s : schoolList){
		%>		
			<tr>
				<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getPhoneNumber()%></td> <td><%=s.getAddress()%></td>
			</tr>
		<%
			}
		%>
		</table>
		

</body>
</html>