<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Confirm reservation</title>
</head>
<body>
Your reservation:
<br>
${service.name}
<br>
Price: ${service.price}
<br>
Duration: ${service.duration}
<br><br>
Date: ${crmReservation.date}
<br>
Time: ${crmReservation.time}
<br>
Your id: ${crmReservation.userId}

<form:form action="processReservation" modelAttribute="crmReservation" method="post">

    <form:hidden path="userId" />
    <form:hidden path="serviceId"/>
    <form:hidden path="status"/>
    <form:hidden path="date"/>
    <form:hidden path="time"/>
    <button type="submit">Make reservation</button>
</form:form>


</body>
</html>
