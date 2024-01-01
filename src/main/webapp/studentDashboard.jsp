<%@page import="com.sma.model.ExtraCurricularActivity"%>
<%@page import="com.sma.model.Query"%>
<%@page import="com.sma.service.UserService"%>
<%@page import="com.sma.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
	<h1>STUDENT DASHBOARD</h1>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(session.getAttribute("user") == null){
    	response.sendRedirect("studentLogin.jsp");
    }else{
    	String message=(String)request.getAttribute("message");  
    	if(message != null){
        	out.println("<font color=red size=4px>"+message+"</font>");
    	}
    	Student student = (Student)request.getSession().getAttribute("user");
    %>
    	<h3>Welcome <%=student.getName()%>, ID = <%=student.getId()%> </h3>
    <%
    }
	%>
	<hr>
	<a href="studentEnrollECA.jsp">Enroll into extra curricular activity</a>
	<a href="studentAddQuery.jsp">Add a new query</a>
	<a href="studentLogoutServlet">Logout</a>
	<hr>
	<h3>YOUR DETAILS</h3>
	<table>
		<tr>
			<th>ID</th> <th>SCHOOL ID</th> <th>NAME</th> <th>AGE</th> <th>EMAIL</th> <th>PHONE NO</th> <th>ADDRESS</th>
		</tr>
		<%Student student = (Student)request.getSession().getAttribute("user");
		  if(student != null){
		%>	
		<tr>
			<td><%=student.getId()%></td> <td><%=student.getSchoolId()%> <td><%=student.getName()%></td> <td><%=student.getAge()%> <td><%=student.getEmail()%> <td><%=student.getPhoneNumber()%></td> <td><%=student.getAddressObj()%></td>
		</tr>
		
	</table>
	<hr>
	<h3>YOUR ATTENDANCE</h3>
	<table>
		<tr>
			<th>TOTAL DAYS</th> <th>PRESENT DAYS</th> <th>ABSENT DAYS</th>
		</tr>
		<tr>
			<td><%=student.getAttendance().getTotalDays()%></td> <td><%=student.getAttendance().getPresentDays()%> <td><%=student.getAttendance().getAbsentDays()%></td>
		</tr>
	</table>
	<hr>
	<h3>YOUR MARKS</h3>
	<table>
		<tr>
			<th>ENGLISH</th> <th>TAMIL</th> <th>MATHS</th> <th>SCIENCE</th> <th>SOCIAL</th> <th>COMPUTER SCIENCE</th> <th>TOTAL</th>
		</tr>
		<tr>
			<td><%=student.getMarkObj().getEnglish()%></td> <td><%=student.getMarkObj().getTamil()%></td> <td><%=student.getMarkObj().getMaths()%></td> <td><%=student.getMarkObj().getScience()%></td><td><%=student.getMarkObj().getSocial()%></td><td><%=student.getMarkObj().getComSci()%></td><td><%=student.getMarkObj().getTotal()%></td>
		</tr>
	</table>
	<hr>
	<h3>YOUR FEE DETAILS</h3>
	<table>
		<tr>
			<th>TUTION FEE</th> <th>EXTRACURRICULAR FEE</th> <th>EXAM FEE</th> <th>BREAKAGE FEE</th> <th>STATIONERY FEE</th> <th>TOTAL</th> <th>STATUS</th>
		</tr>
		<tr>
			<td><%=student.getFeeObj().getTutionFee()%></td> <td><%=student.getFeeObj().getExtraCurrFee()%></td> <td><%=student.getFeeObj().getExamFee()%></td> <td><%=student.getFeeObj().getBreakageFee()%></td><td><%=student.getFeeObj().getStationeryFee()%></td><td><%=student.getFeeObj().getTotal()%></td> <td><%=student.getFeeObj().getFeeStatus() %></td>
		</tr>
		
	</table>
	<form action="studentPayFees">
		<input type="submit" value="Pay Fees">
	</form>
	<hr>
	<h3>EXTRA CURRICULAR ACTIVITIES YOU HAVE ENROLLED INTO:</h3>
	<table>
	<tr>
		<th>ACTIVITY CODE</th> <th>ACTIVITY NAME</th> <th>COST</th>
	</tr>
		<%
			for(ExtraCurricularActivity e : UserService.getInstance().getEcActivityList(student.getId())){
		%>		
			<tr>
				<td><%=e.name()%></td> <td><%=e%></td> <td><%=e.getFee()%></td> 
			</tr>
		<%
			}
		%>
	
	</table>
	
	<hr>
	<h3>YOUR QUERIES</h3>
	<table>
		<tr>
			<th>QUERY ID</th> <th>QUERY SUBJECT</th> <th>QUERY</th> <th>REPLY FROM ADMIN</th> <th>QUERY STATUS</th>
		</tr>
		<%
			for(Query q : UserService.getInstance().getMyQueires(student)){
		%>		
			<tr>
				<td><%=q.getId()%></td> <td><%=q.getSubject()%></td> <td><%=q.getStatement()%></td> <td><%=q.getReply()%></td> <td><%=q.getStatus()%></td>
			</tr>
		<%
			}
		%>
	</table>	
	
	
		<%
		  }
		%>
	<hr>
</body>
</html>