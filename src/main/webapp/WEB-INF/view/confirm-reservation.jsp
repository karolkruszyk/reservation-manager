<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
Date: ${date}
<br>
Time: ${time}

<c:url var = "process" value="/reserve/processReservation">
    <c:param name="service" value="${service}"/>
    <c:param name="date" value="${date}"/>
    <c:param name="time" value="${time}"/>
</c:url>


</body>
</html>
