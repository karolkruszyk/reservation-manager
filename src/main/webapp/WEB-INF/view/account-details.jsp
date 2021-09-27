<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account details</title>
</head>
<body>
<h3>Your account details</h3>
Username: ${user.userName}
<br>
Full name: ${user.firstName} ${user.lastName}
<br>
Phone number: ${user.phoneNumber}
<br>
<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
    Roles: ${user.roles}
</security:authorize>
<br>
<hr>

<c:url var="changePhoneNumber" value="/customer/changePhoneNumber">
    <c:param name="userId" value="${user.id}"/>
</c:url>

<a href="${changePhoneNumber}"><button>Change phone number</button></a>


<hr>
<input type="button" value="Back to the Home Page"
       onclick="window.location.href='${pageContext.request.contextPath}/'; return false;"/>
</body>
</html>
