<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1{
text-transform: uppercase;
text-decoration: blink;
}
</style>
<meta charset="ISO-8859-1">
<title>ReportPage</title>
</head>
<body>
	<div align="center">
		<h1>Booked Ticket Details</h1>
		<table border="1">
			<thead>
				<th>Name of the Passenger</th>
				<th>Tickets Booked</th>
				<th>Citizenship</th>
			</thead>
			<tbody>
				<c:forEach items="${AllTicketDetails}" var="ticket">
					<tr>
						<td>${ticket.name}</td>
						<td>${ticket.ticketBooked}</td>
						<td>${ticket.citizen}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="index.jsp">Home</a>
	</div>
</body>
</html>