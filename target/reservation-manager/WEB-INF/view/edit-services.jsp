<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Edit Service List</title>
</head>
<body>
    <h1>Edit service list</h1>
    <hr>
    <input type="button" value="Add new service"
           onclick="window.location.href='showServiceForm'; return false;"
           class="add-button"
    />
    <hr>
    <table>
        <tr>
            <th><a>Service</a></th>
            <th><a>Price</a></th>
            <th><a>Duration</a></th>
            <th><a>Action</a></th>
        </tr>

        <c:forEach var="tempService" items="${services}">
            <c:url var="updateLink" value="/services/showUpdateForm">
                <c:param name="serviceId" value="${tempService.id}"/>
            </c:url>

            <c:url var="deleteLink" value="/services/delete">
                <c:param name="serviceId" value="${tempService.id}"/>
            </c:url>
            <tr>
                <td>${tempService.name}</td>
                <td>${tempService.price} zł</td>
                <td>${tempService.duration} minut</td>
                <td><a href="${updateLink}">Update</a>
                |
                <td><a href="${deleteLink}"
                   onclick="if (!(confirm('Are you sure you want to delete this service?'))) return false">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" value="Back to the Home Page"
           onclick="window.location.href='${pageContext.request.contextPath}/'; return false;"
           class="add-button"
    />
    <hr>
</body>
</html>
