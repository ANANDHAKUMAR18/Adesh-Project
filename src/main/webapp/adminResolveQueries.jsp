<%@page import="com.sma.model.QueryStatus"%>
<%@page import="com.sma.service.UserService"%>
<%@page import="com.sma.model.Query"%>
<%@page import="com.sma.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resolve Queries</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
<h1>ADMIN RESOLVE QUERIES PAGE</h1>
		<%
		String message=(String)request.getAttribute("message");  
    	if(message != null){
        	out.println("<font color=red size=4px>"+message+"</font>");
    	}
    	%>
<h3>Pending Queries</h3>
	<table>
		<tr>
			<th>QUERY ID</th> <th>STUDENT ID</th> <th>QUERY SUBJECT</th> <th>QUERY</th> <th>REPLY FROM ADMIN</th> <th>QUERY STATUS</th>
		</tr>
		<%
			for(Query q : UserService.getInstance().getQueryListFromTable()){
				if(QueryStatus.PENDING.equals(q.getStatus())){
					%>		
					<tr>
						<td><%=q.getId()%></td> <td><%=q.getStudentId()%></td> <td><%=q.getSubject()%></td> <td><%=q.getStatement()%></td> <td><%=q.getReply()%></td> <td><%=q.getStatus()%></td>
					</tr>
					<%
				}
			}
		%>
	</table>	
<h3>Resolved Queries</h3>
<table>
		<tr>
			<th>QUERY ID</th> <th>STUDENT ID</th> <th>QUERY SUBJECT</th> <th>QUERY</th> <th>REPLY FROM ADMIN</th> <th>QUERY STATUS</th>
		</tr>
		<%
			for(Query q : UserService.getInstance().getQueryListFromTable()){
				if(QueryStatus.RESOLVED.equals(q.getStatus())){
					%>		
					<tr>
						<td><%=q.getId()%></td> <td><%=q.getStudentId()%></td> <td><%=q.getSubject()%></td> <td><%=q.getStatement()%></td> <td><%=q.getReply()%></td> <td><%=q.getStatus()%></td>
					</tr>
					<%
				}
			}
		%>
</table>	
<form action="adminResolveQuery">
	<p>Query id</p>
	<input type="number" name="qid" required="required">
	<p>Reply </p>
	<input type="text" name="reply" required="required">
	<input type="submit" value="Submit">
	<a href="adminDashboard.jsp">Back</a>
</form>

</body>
</html>