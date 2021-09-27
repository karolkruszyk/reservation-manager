<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservations</title>
</head>
<body>
    <h1>Reservations</h1>
    <c:url var="waitingStatus" value="/manager/reservationList">
        <c:param name="status" value="WAITING" />
    </c:url>
    <c:url var="confirmedStatus" value="/manager/reservationList">
        <c:param name="status" value="CONFIRMED" />
    </c:url>
    <c:url var="all" value="/manager/reservationList">
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

            <th><a>Name</a></th>
            <th><a>Phone number</a></th>
            <th><a>Service</a></th>
            <th><a>Price</a></th>
            <th><a>Duration</a></th>
            <th><a>Date</a></th>
            <th><a>Status</a></th>
            <th><a>Action</a></th>
            <th><a>Reservation by</a></th>
        </tr>
        <c:forEach var="tempReservation" items="${detailedReservations}">

            <c:url var="confirmReservation" value="/manager/confirmReservation">
                <c:param name="reservationId" value="${tempReservation.reservation.reservationId}"/>
            </c:url>
            <c:url var="rejectReservation" value="/manager/rejectReservation">
                <c:param name="reservationId" value="${tempReservation.reservation.reservationId}"/>
            </c:url>
            <tr>
                <c:if test="${tempReservation.reservation.noAccountName == null}">
                    <td>${tempReservation.user.firstName} ${tempReservation.user.lastName}</td>
                </c:if>
                <c:if test="${tempReservation.reservation.noAccountName != null}">
                    <td>${tempReservation.reservation.noAccountName}</td>
                </c:if>
                <c:if test="${tempReservation.reservation.noAccountPhone != null}">
                    <td>${tempReservation.reservation.noAccountPhone}</td>
                </c:if>
                <c:if test="${tempReservation.reservation.noAccountName == null}">
                    <td>${tempReservation.user.phoneNumber}</td>
                </c:if>
                <td>${tempReservation.service.name}</td>
                <td>${tempReservation.service.price}</td>
                <td>${tempReservation.service.duration}</td>
                <td>${tempReservation.reservation.dateTime.dayOfMonth}.${tempReservation.reservation.dateTime.monthValue}.${tempReservation.reservation.dateTime.year} ${tempReservation.reservation.dateTime.toLocalTime()}</td>
                <td>${tempReservation.reservation.status}</td>
                <td><a href="${confirmReservation}"
                       onclick="if (!(confirm('Are you sure?'))) return false">Accept</a> |
                    <a href="${rejectReservation}"
                       onclick="if (!(confirm('Are you sure?'))) return false">Reject</a></td>
                <c:if test="${tempReservation.reservation.noAccountName != null}">
                    <td>${tempReservation.user.firstName} ${tempReservation.user.lastName}</td>
                </c:if>
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
