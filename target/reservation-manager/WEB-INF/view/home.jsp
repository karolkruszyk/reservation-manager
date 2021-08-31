<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>

<head>
	<title>Reservation Manager - Home Page</title>
</head>

<body>
	<h2>Reservation Manager - Home Page</h2>
	<hr>
	
	<p>
		<h3>Your account details</h3>
		User: <security:authentication property="principal.username" />
		<br>
		Role(s): <security:authentication property="principal.authorities" />
		<br>
		First name: ${user.firstName}
		<br>
		Last name: ${user.lastName}
		<br>
		Phone number: ${user.phoneNumber}
		<br>
	</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<h3>Options</h3>
		<br>
		<input type="button" value="Edit service list"
			   onclick="window.location.href='services/editServiceList'; return false;"
			   class="add-button"
		/>
	</security:authorize>

	
	<security:authorize access="hasRole('ADMIN')">
		
	</security:authorize>
	
	<hr>
		<h3>Service List</h3>
		<table>
			<tr>
				<th><a>Service</a></th>
				<th><a>Price</a></th>
				<th><a>Duration</a></th>
			</tr>

		<c:forEach var="tempService" items="${services}">

			<tr>
				<td>${tempService.name}</td>
				<td>${tempService.price} zł</td>
				<td>${tempService.duration} minut</td>

			</tr>
		</c:forEach>
		</table>
	
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









