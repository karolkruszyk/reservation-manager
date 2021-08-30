<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<html>
<head>
    <title>Add a new service</title>
</head>
<body>
<form:form action="processServiceForm" modelAttribute="service" method="POST">
    <table>
        <tbody>
        <tr>
            <td><label>Name:</label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><label>Price:</label></td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td><label>Duration:</label></td>
            <td><form:input path="duration"/></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/> </td>
        </tr>
        </tbody>
    </table>

</form:form>
</body>
</html>
