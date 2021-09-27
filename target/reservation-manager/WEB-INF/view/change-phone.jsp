<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change phone number</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/customer/processPhoneNumberForm"
           modelAttribute="crmUser">
    <form:input path="phoneNumber" placeholder="${user.phoneNumber}" />
    <button type="submit">Change</button>
</form:form>
</body>
</html>
