<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 007 07.05.20
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${count>0}">
    <p style="color: green">${count} phones successfully added to cart</p>
</c:if>
<form method="post">
<table class="table table-striped table-hover table-sm">
    <thead>
    <tr>
        <th>Product model</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <% for(int i = 0; i < 10; i++) { %>
    <tr>
        <th> <input class="form-control" name="phoneModel"/></th>
        <th> <input class="form-control" name="phoneQuantity"/></th>
    </tr>
    <% } %>

    <c:forEach items="${errors}" var="error">
        <p style="color: red">
            Phone with model ${error.code} - error ${error.defaultMessage}
        </p>
    </c:forEach>
    </tbody>
</table>
    <input class="btn btn-primary mb-2" type="submit" value="Add to Cart"/>
</form>
</body>
</html>
