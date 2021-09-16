<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking page</title>
</head>
<body>
    You are currently booking:
    <br>
    name: ${service.name}
    <br>
    price: ${service.price} zł
    <br>
    duration: ${service.duration} min
    <hr>
    <table>
        <tr>
            <c:forEach var = "tempDay" items="${datesToDisplay}">
                <th>${tempDay}</th>
            </c:forEach>
        </tr>

        <c:forEach var = "tempHour" items="${workingHours}">
        <tr>
            <c:forEach var="tempDay" items="${datesToDisplay}">
                <c:url var = "reserve" value="/reserve/makeReservation">
                    <c:param name="date" value="${tempDay}"/>
                    <c:param name="hour" value="${tempHour}"/>
                    <c:param name="serviceId" value="${service.id}"/>
                </c:url>
                <td><a href="${reserve}">${tempHour}<a/></td>
            </c:forEach>
        </tr>
        </c:forEach>

    </table>

</body>
</html>
