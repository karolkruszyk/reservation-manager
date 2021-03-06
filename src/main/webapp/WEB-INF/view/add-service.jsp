<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<html>
<head>
    <title>Add new service</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .error {color:red}
    </style>
</head>
<body>
<div>
    <div id="loginbox" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Add new service</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <form:form action="processServiceForm" modelAttribute="crmService" method="POST" class="form-horizontal">

                    <div class="form-group">
                        <div class="col-xs-15">
                            <div>

                                <c:if test="${serviceError != null}">

                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                            ${serviceError}
                                    </div>

                                </c:if>

                            </div>
                        </div>
                    </div>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-font"></i></span>
                        <form:errors path="name" cssClass="error" />
                        <form:input path="name" placeholder="name" class="form-control" />
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span>
                        <form:errors path="price" cssClass="error" />
                        <form:input path="price" placeholder="price" class="form-control" />
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                        <form:errors path="duration" cssClass="error" />
                        <form:input path="duration" placeholder="duration" class="form-control" />
                    </div>


                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-primary">Add service</button>
                        </div>
                    </div>

                </form:form>

            </div>

        </div>

    </div>

</div>

</body>

</html>
