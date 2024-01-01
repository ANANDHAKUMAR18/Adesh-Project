<%@page import="com.sma.model.FeeStatus"%>
<%@page import="com.sma.model.Admin"%>
<%@page import="com.sma.service.UserService"%>
<%@page import="com.sma.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students fee status</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 15px;
  text-align:center;
}
</style>
</head>
<body>
<h3>Students yet to pay fees</h3>

		
	<table>
		<tr>
			<th>STUDENT ID</th> <th>STUDENT NAME</th> <th>TUTION FEE</th> <th>EXTRACURRICULAR FEE</th> <th>EXAM FEE</th> <th>BREAKAGE FEE</th> <th>STATIONERY FEE</th> <th>TOTAL</th> <th>STATUS</th>
		</tr>
		<%
		Admin admin = (Admin)request.getSession().getAttribute("user");
		for(Student student : UserService.getInstance().getStudentList(admin.getSchoolId())){
			if(FeeStatus.NOT_PAID.equals(student.getFeeObj().getFeeStatus())){	
		%>
		<tr>
			<td><%=student.getId()%></td> <td><%=student.getName()%></td> <td><%=student.getFeeObj().getTutionFee()%></td> <td><%=student.getFeeObj().getExtraCurrFee()%></td> <td><%=student.getFeeObj().getExamFee()%></td> <td><%=student.getFeeObj().getBreakageFee()%></td><td><%=student.getFeeObj().getStationeryFee()%></td><td><%=student.getFeeObj().getTotal()%></td> <td><%=student.getFeeObj().getFeeStatus() %></td>
		</tr>
		<%
			}
		}
		%>
	
	</table>
		
		<h3>Students who have paid fees</h3>
		<table>
		
		<tr>
			<th>STUDENT ID</th> <th>STUDENT NAME</th> <th>TUTION FEE</th> <th>EXTRACURRICULAR FEE</th> <th>EXAM FEE</th> <th>BREAKAGE FEE</th> <th>STATIONERY FEE</th> <th>TOTAL</th> <th>STATUS</th>
		</tr>
		<% 
		for(Student student : UserService.getInstance().getStudentList(admin.getSchoolId())){
			if(FeeStatus.PAID.equals(student.getFeeObj().getFeeStatus())){			
		%>
		<tr>
			<td><%=student.getId()%></td> <td><%=student.getName()%></td> <td><%=student.getFeeObj().getTutionFee()%></td> <td><%=student.getFeeObj().getExtraCurrFee()%></td> <td><%=student.getFeeObj().getExamFee()%></td> <td><%=student.getFeeObj().getBreakageFee()%></td><td><%=student.getFeeObj().getStationeryFee()%></td><td><%=student.getFeeObj().getTotal()%></td> <td><%=student.getFeeObj().getFeeStatus() %></td>
		</tr>
		<%
			}
		}
		%>
	</table>
		
</body>
</html>