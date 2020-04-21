<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
</head>

<body>
<div class="container" style="width: 300px;">

    <form action="${pageContext.servletContext.contextPath}/registration" method="post">
        <h2 class="form-signin-heading">Registration</h2>
        <input type="text" class="form-control" name="username" placeholder="Email address" >
        <input type="password" class="form-control" name="firstPassword" placeholder="Password" >
        <input type="password" class="form-control" name="secondPassword" placeholder="Repeat password" >
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>
    <form action="${pageContext.servletContext.contextPath}/products">
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Back"/>
    </form>
    <c:if test="${not empty errors}">
        <c:forEach var="error" items="${errors}">
            <h5 style="color: red">${error.defaultMessage}</h5>
        </c:forEach>
    </c:if>
</div>

</body>
</html>