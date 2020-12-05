<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookingForm</title>
</head>
<body>

	<div align="center">
		<h1>Book Ticket</h1>
		<br>
		<table>
			<spring:form action="validateTicket.htm" modelAttribute="ticketBean">
				<tr>
					<td>Number of Tickets:</td>
					<td><spring:input path="ticketBooked" /></td>
					<td><spring:errors path="ticketBooked" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Passenger Name:</td>
					<td><spring:input path="name" /></td>
					<td><spring:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Train Name:</td>
					<td><spring:select path="trainId">
							<spring:options items="${trainDetails}" />
						</spring:select></td>
				</tr>
				<tr>
					<td>Citizen Status:</td>
					<td>Senior-Citizen <spring:radiobutton path="citizen"
							value="Senior-Citizen" /> General <spring:radiobutton
							path="citizen" value="General" checked="checked" />
					</td>
				</tr>
				<tr>
					<td><a href="index.jsp">Home</a></td>
					<td><input type="submit" value="Book Ticket" />
			</spring:form>
		</table>
	</div>
</body>
</html>