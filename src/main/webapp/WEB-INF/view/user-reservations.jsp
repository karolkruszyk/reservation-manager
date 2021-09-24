<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your reservations</title>
</head>
<body>
<h1>Your reservations</h1>
<br>
<table>
        <tr>
            <th><a>service</a></th>
            <th><a>price</a></th>
            <th><a>duration</a></th>
            <th><a>date</a></th>
            <th><a>status</a></th>

        </tr>
        <c:forEach var="tempReservation" items="${detailedReservations}">
            <tr>

                <td>${tempReservation.service.name}</td>
                <td>${tempReservation.service.price}</td>
                <td>${tempReservation.service.duration}</td>
                <td>${tempReservation.reservation.dateTime.dayOfMonth}.${tempReservation.reservation.dateTime.monthValue}.${tempReservation.reservation.dateTime.year} ${tempReservation.reservation.dateTime.toLocalTime()}</td>
                <td>${tempReservation.reservation.status}</td>
            </tr>
        </c:forEach>


</table>
<br>
<input type="button" value="Back to the Home Page"
       onclick="window.location.href='${pageContext.request.contextPath}/'; return false;"
       class="add-button"
/>
</body>
</html>
