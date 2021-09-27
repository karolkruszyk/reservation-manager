<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit users</title>
</head>
<body>
<h1>Edit users</h1>
<hr>
<table>
    <tr>
        <th><a>Username</a></th>
        <th><a>First name</a></th>
        <th><a>Last name</a></th>
        <th><a>Phone number</a></th>
        <th><a>Roles</a></th>
        <th><a>Give/Take Manager Role</a></th>
    </tr>
    <c:forEach var="tempUser" items="${users}">
        <c:url var="giveManagerRole" value="/admin/giveManagerRole">
            <c:param name="theId" value="${tempUser.id}"/>
        </c:url>

        <tr>
            <td>${tempUser.userName}</td>
            <td>${tempUser.firstName}</td>
            <td>${tempUser.lastName}</td>
            <td>${tempUser.phoneNumber}</td>
            <td>
                ${tempUser.roles}
            </td>
            <td>
            <a href="${giveManagerRole}"
               onclick="if (!(confirm('Are you sure?'))) return false"><button>Give / Take</button></a></td>
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
