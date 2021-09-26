<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>
	<title>Access Denied</title>
	<spring:url value="/resources/css/styles.css" var="styles" />
	<link href="${styles}" rel="stylesheet" />

</head>

<body>


	<div class="text-courier-font">
		<h2 class="title">Access Denied</h2>
		<p>You are not authorized to access this resource.</p>
		<img src="${pageContext.request.contextPath}/resources/img/lock.png" width="300" height="300" alt="lock-img">
		<form action="${pageContext.request.contextPath}/">
			<button type="submit" class="button-courier-font">Go back</button>
		</form>
	</div>
	
</body>

</html>