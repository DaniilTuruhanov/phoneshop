<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<head>
</head>
<p>
    Welcome to Expert-Soft!
</p>
<p>
    Found
    <c:out value="${sessionScope.cart.phoneStocks.size()}"/> phones.
<table border="1px">
    <thead>
    <tr>
        <td>Image</td>
        <td>Brand
        </td>
        <td>Model
        </td>
        <td>Color</td>
        <td>Display size
        </td>
        <td>Price
        </td>
        <td>Quantity</td>
        <td>Stock</td>
    </tr>
    </thead>
    <c:forEach var="stock" items="${sessionScope.cart.phoneStocks}">
        <tr>
            <td>
                <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${stock.phone.imageUrl}">
            </td>
            <td>${stock.phone.brand}</td>
            <td>${stock.phone.model}</td>

            <td><c:forEach var="color" items="${stock.phone.colors}">
                <div> ${color.code}</div>
            </c:forEach></td>

            <td> ${stock.phone.displaySizeInches}"</td>
            <td>$ ${stock.phone.price}</td>
            <td>${stock.reserved}</td>
            <td>${stock.stock}</td>
        </tr>
    </c:forEach>
</table>
</p>