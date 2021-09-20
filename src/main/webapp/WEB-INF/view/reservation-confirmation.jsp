<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation Confirmation</title>
</head>
<body>
    Succesfully made a reservation
    <br>
    ${crmReservation.date}
    <br>
    ${crmReservation.time}
    <br>
    <input type="button" value="Back to the Home Page"
           onclick="window.location.href='${pageContext.request.contextPath}/'; return false;"
           class="add-button"
    />
</body>
</html>
