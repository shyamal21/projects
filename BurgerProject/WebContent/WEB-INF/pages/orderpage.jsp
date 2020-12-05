<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<style>
h2{
text-align:center;
}
</style>
</head>
<body>
	<h2>Order Burger</h2> 

	<spring:form action="val.htm" modelAttribute="burgerBean">
		<table style="margin-left:35%;">
			<tr>
				<td>Burger Name</td>
				<td><spring:select path="BurgerName">
						<spring:option value="select Your Burger" />
						<spring:options items="${burgerList}" />
					</spring:select></td>
			</tr>
			<tr>
				<td>Topping Name</td>
				<td><spring:select path="toppingName">
						<spring:option value="select Your Burger" />
						<spring:options items="${ toppingList}" />
					</spring:select></td>
			</tr>
			<tr>
				<td>Number of Burgers</td>
				<td><spring:input path="noOfBurgers" /></td>
				<td><spring:errors path="noOfBurgers"></spring:errors></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Order"></td>
			</tr>
		</table>
	</spring:form>
</body>
</html>