<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 007 07.05.20
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${count>0}">
    <p style="color: green">${count} phones successfully added to cart</p>
</c:if>
<form:form method="post" id="form" modelAttribute="quickOrderForm">
    <table class="table table-striped table-hover table-sm">
        <thead>
        <tr>
            <th>Product model</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach begin="0" end="5" varStatus="status">
            <c:set var="i" value="${status.index}"/>
            <tr>
                <th>
                    <form:input class="form-control" path="quickOrderEntityList[${i}].model"/>
                    <form:errors path="quickOrderEntityList[${i}].model" cssClass="error"/>
                </th>
                <th>
                    <form:input class="form-control" path="quickOrderEntityList[${i}].quantity"/>
                    <form:errors path="quickOrderEntityList[${i}].quantity" cssClass="error"/>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input class="btn btn-primary mb-2" type="submit" value="Add to Cart"/>
</form:form>
</body>
</html>
