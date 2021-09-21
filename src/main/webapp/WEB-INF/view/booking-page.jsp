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
                <th>${tempDay.dayOfWeek} ${tempDay.dayOfMonth} ${tempDay.month} | </th>
            </c:forEach>

        </tr>

        <c:forEach var = "tempHour" items="${workingHours}">
        <tr>
            <c:forEach var="tempDay" items="${datesToDisplay}">
                <c:set var="printed" value="false"/>
                <c:url var = "reserve" value="/reserve/makeReservation">
                    <c:param name="date" value="${tempDay}"/>
                    <c:param name="hour" value="${tempHour}"/>
                    <c:param name="serviceId" value="${service.id}"/>
                </c:url>

                <c:forEach var="tempDetailedReservation" items="${detailedReservations}">
                    <c:if test="${tempDetailedReservation.reservation.dateTime.toLocalDate() == tempDay && tempHour.isBefore(tempDetailedReservation.reservation.dateTime.toLocalTime().plusMinutes(tempDetailedReservation.service.duration + 1)) && tempHour.isAfter(tempDetailedReservation.reservation.dateTime.toLocalTime().minusMinutes(1))}">
                        <td><a>--:--<a/></td>
                        <c:set var="printed" value="true"/>
                    </c:if>
                </c:forEach>

                <c:if test="${printed != true}">
                    <c:if test="${tempDay == today && tempHour.isBefore(now)}">
                        <td><a>--:--<a/></td>
                    </c:if>

                    <c:if test="${tempDay != today || tempHour.isAfter(now)}">
                        <td><a href="${reserve}">${tempHour}<a/></td>
                    </c:if>
                </c:if>


            </c:forEach>
        </tr>
        </c:forEach>

    </table>

</body>
</html>
