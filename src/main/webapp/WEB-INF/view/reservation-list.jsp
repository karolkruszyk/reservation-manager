<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservations</title>
</head>
<body>
    <h1>Reservations</h1>
    <c:url var="waitingStatus" value="/reserve/reservationList">
        <c:param name="status" value="WAITING" />
    </c:url>
    <c:url var="confirmedStatus" value="/reserve/reservationList">
        <c:param name="status" value="CONFIRMED" />
    </c:url>
    <c:url var="all" value="/reserve/reservationList">
        <c:param name="status" value="" />
    </c:url>


    <input type="button" value="Waiting"
           onclick="window.location.href='${waitingStatus}'; return false;"
           class="add-button"
    />
    <input type="button" value="Confirmed"
           onclick="window.location.href='${confirmedStatus}'; return false;"
           class="add-button"
    />
    <input type="button" value="All"
           onclick="window.location.href='${all}'; return false;"
           class="add-button"
    />
    <br>
    <table>

        <tr>

            <th><a>first name</a></th>
            <th><a>last name</a></th>
            <th><a>phone number</a></th>
            <th><a>service</a></th>
            <th><a>price</a></th>
            <th><a>duration</a></th>
            <th><a>date</a></th>
            <th><a>status</a></th>
            <th><a>action</a></th>

        </tr>
        <c:forEach var="tempReservation" items="${detailedReservations}">

            <c:url var="confirmReservation" value="/reserve/confirm">
                <c:param name="reservationId" value="${tempReservation.reservation.reservationId}"/>
            </c:url>
            <c:url var="rejectReservation" value="/reserve/reject">
                <c:param name="reservationId" value="${tempReservation.reservation.reservationId}"/>
            </c:url>
            <tr>
                <td>${tempReservation.user.firstName}</td>
                <td>${tempReservation.user.lastName}</td>
                <td>${tempReservation.user.phoneNumber}</td>
                <td>${tempReservation.service.name}</td>
                <td>${tempReservation.service.price}</td>
                <td>${tempReservation.service.duration}</td>
                <td>${tempReservation.reservation.dateTime}</td>
                <td>${tempReservation.reservation.status}</td>
                <td><a href="${confirmReservation}"
                       onclick="if (!(confirm('Are you sure?'))) return false">Accept</a></td>
                <td><a href="${rejectReservation}"
                       onclick="if (!(confirm('Are you sure?'))) return false">Reject</a></td>
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
