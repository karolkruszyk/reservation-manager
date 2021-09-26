<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<input type="button" value="Back to the Home Page"
       onclick="window.location.href='${pageContext.request.contextPath}/'; return false;"/>
</body>
</html>
