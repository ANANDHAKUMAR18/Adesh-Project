<%@page import="com.sma.model.Creator"%>
<%@page import="com.sma.model.Admin"%>
<%@page import="com.sma.service.UserService"%>
<%@page import="com.sma.model.School"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creator Dashboard</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
	<h1>CREATOR DASHBOARD</h1>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(session.getAttribute("user") == null){
    	response.sendRedirect("creatorLogin.jsp");
    }else{
    	String message=(String)request.getAttribute("message");  
    	if(message != null){
        	out.println("<font color=red size=4px>"+message+"</font>");
    	}
    	Creator creator = (Creator)request.getSession().getAttribute("user");
    %>
    	<h3>Welcome <%=creator.getName()%>, ID = <%=creator.getId()%></h3>
    <%
    }
	%>
	
	<hr>
	<a href="creatorCreateSchool.jsp">Create School</a>
	<a href="creatorDeleteSchool.jsp">Delete School</a>
	<a href="creatorCreateAdmin.jsp">Create Admin</a>
	<a href="creatorDeleteAdmin.jsp">Delete Admin</a>
	<a href="creatorCreateCreator.jsp">Create Creator</a>
	<a href="creatorDeleteCreator.jsp">Delete Creator</a>
	<a href="creatorLogoutServlet">Logout</a>
	<hr>
<h3>Existing Schools</h3>
<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>PHONE NO</th> <th>ADDRESS</th>
			</tr>
		<%
			for(School s : UserService.getInstance().getExistingSchools()){
		%>		
			<tr>
				<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getPhoneNumber()%></td> <td><%=s.getAddress()%></td>
			</tr>
		<%
			}
		%>
		
</table>
<hr>
<h3>Existing Admins</h3>
<table>
			<tr>
				<th>ID</th> <th>SCHOOL ID</th> <th>NAME</th> <th>AGE</th> <th>EMAIL</th> <th>PHONE NO</th> <th>ADDRESS</th>
			</tr>
		<%
			for(Admin s : UserService.getInstance().getExistingAdmins()){
		%>		
			<tr>
				<td><%=s.getId()%></td> <td><%=s.getSchoolId()%></td> <td><%=s.getName()%></td> <td><%=s.getAge()%></td> <td><%=s.getEmail()%></td> <td><%=s.getPhoneNumber()%></td> <td><%=s.getAddressObj()%></td>
			</tr>
		<%
			}
		%>
		
</table>
<hr>
<h3>Existing Creators</h3>
<table>
			<tr>
				<th>ID</th> <th>NAME</th> <th>AGE</th> <th>EMAIL</th> <th>PHONE NO</th> 
			</tr>
		<%
			for(Creator s : UserService.getInstance().getExistingCreators()){
		%>		
			<tr>
				<td><%=s.getId()%></td> <td><%=s.getName()%></td> <td><%=s.getAge()%></td> <td><%=s.getEmail()%></td> <td><%=s.getPhoneNumber()%></td> 
			</tr>
		<%
			}
		%>
		
</table>



</body>
</html>