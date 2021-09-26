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

	<input type="button" value="My account"
		   onclick="window.location.href='customer/accountDetails'; return false;"/>

	<input type="button" value="My reservations"
			   onclick="window.location.href='customer/userReservations'; return false;"
			   class="add-button"
		/>
	</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<h3>Manager options</h3>

		<input type="button" value="Reservations"
			   onclick="window.location.href='manager/reservationList'; return false;"
			   class="add-button"
		/>
		<hr>
	</security:authorize>

	
	<security:authorize access="hasRole('ADMIN')">
		<h3>Admin options</h3>

		<input type="button" value="Edit service list"
			   onclick="window.location.href='admin/editServiceList'; return false;"
			   class="add-button"
		/>

		<input type="button" value="Edit accounts"
			   onclick="window.location.href='admin/listUsers'; return false;"
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
			<c:url var="chooseService" value="/customer/bookingPage">
				<c:param name="theId" value="${tempService.id}"/>
			</c:url>

			<tr>
				<td>${tempService.name}</td>
				<td>${tempService.price} zł</td>
				<td>${tempService.duration} minut</td>
				<td><a href="${chooseService}" ><button type="submit">choose</button></a></td>

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









