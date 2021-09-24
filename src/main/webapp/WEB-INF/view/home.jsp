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
		<input type="button" value="Your reservations"
			   onclick="window.location.href='reserve/userReservations'; return false;"
			   class="add-button"
		/>
	</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<h3>Manager options</h3>

		<input type="button" value="Edit service list"
			   onclick="window.location.href='services/editServiceList'; return false;"
			   class="add-button"
		/>
		<input type="button" value="Reservations"
			   onclick="window.location.href='reserve/reservationList'; return false;"
			   class="add-button"
		/>
		<hr>
	</security:authorize>

	
	<security:authorize access="hasRole('ADMIN')">
		<h3>Admin options</h3>

		<input type="button" value="Edit service list"
			   onclick="window.location.href='services/editServiceList'; return false;"
			   class="add-button"
		/>

		<input type="button" value="Edit accounts"
			   onclick="window.location.href='user/list'; return false;"
			   class="add-button"
		/>
		<hr>
	</security:authorize>
	

		<h3>Service List</h3>
		<table>
			<tr>
				<th><a>Service</a></th>
				<th><a>Price</a></th>
				<th><a>Duration</a></th>
				<th><a>Choose service</a></th>
			</tr>

		<c:forEach var="tempService" items="${services}">
			<c:url var="chooseService" value="/reserve/booking">
				<c:param name="theId" value="${tempService.id}"/>
			</c:url>

			<tr>
				<td>${tempService.name}</td>
				<td>${tempService.price} zł</td>
				<td>${tempService.duration} minut</td>
				<td><a href="${chooseService}" ><></a></td>

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









