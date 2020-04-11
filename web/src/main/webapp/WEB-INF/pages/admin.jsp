<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<head>
</head>
<c:if test="${not empty orderList}">
<h2>
    <span class="badge badge-light">
    Orders
    </span>
</h2>
    <table class="table table-striped table-hover table-sm">
        <thead>
        <tr>
            <th>Order number
            </th>
            <th>Customer
            </th>
            <th>Phone</th>
            <th>Address
            </th>
            <th>Date
            </th>
            <th>Total price</th>
            <th>Status</th>
        </tr>
        </thead>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <th><a href="${pageContext.servletContext.contextPath}/admin/${order.number}">${order.number}</a></th>
                <th>${order.customer}</th>
                <th>${order.phone}</th>
                <th>${order.address}</th>
                <th>${order.date}</th>
                <th>${order.totalPrice}</th>
                <th>${order.status}</th>
            </tr>
        </c:forEach>
    </table>
    </c:if>
<c:if test="${empty orderList}">
    <h1>
        <span class="badge badge-secondary">ORDER LIST IS EMPTY</span>
    </h1>
</c:if>
<form action="${pageContext.servletContext.contextPath}/products">
    <input class="btn btn-primary mb-2" type="submit" value="Back to Product list"/>
</form>