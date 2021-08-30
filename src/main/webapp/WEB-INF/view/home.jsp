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
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
		<br><br>
		First name: ${user.firstName}, Last name: ${user.lastName}, Phone number: ${user.phoneNumber}
	</p>

	<security:authorize access="hasRole('MANAGER')">
		<input type="button" value="Add service"
			   onclick="window.location.href='showServiceForm'; return false;"
			   class="add-button"
		/>
	</security:authorize>

	
	<security:authorize access="hasRole('ADMIN')">  

	</security:authorize>
	
	<hr>
		<h3>Choose the service</h3>
		<table>
			<tr>
				<th><a>Usługa</a></th>
				<th><a>Cena</a></th>
				<th><a>Czas trwania</a></th>
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









