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
<h4>
    <span class="badge badge-light"> Welcome to Expert-Soft!</span>
</h4>

<h4>
    <span class="badge badge-light">
    Hello from cart!
    </span>
</h4>
<c:if test="${not empty sessionScope.cart.cartEntityList}">
    <p>
    <h4>
       <span class="badge badge-light">
        Found
            <c:out value="${sessionScope.cart.cartEntityList.size()}"/> phones.
        </span>
    </h4>
    <table class="table table-striped table-hover table-sm">
        <thead>
        <tr>
            <th>Image</th>
            <th>Brand
            </th>
            <th>Model
            </th>
            <th>Color</th>
            <th>Display size
            </th>
            <th>Price
            </th>
            <th>Quantity</th>
            <th>Stock</th>
        </tr>
        </thead>
        <c:forEach var="cartEntity" items="${sessionScope.cart.cartEntityList}">
            <tr>
                <th>
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartEntity.phone.imageUrl}">
                </th>
                <th>${cartEntity.phone.brand}</th>
                <th>${cartEntity.phone.model}</th>

                <th><c:forEach var="color" items="${cartEntity.phone.colors}">
                    <div> ${color.code}</div>
                </c:forEach></th>

                <th> ${cartEntity.phone.displaySizeInches}"</th>
                <th>$ ${cartEntity.phone.price}</th>
                <th>${cartEntity.quantity}</th>
                <th>${cartEntity.phone.stock}</th>
            </tr>
        </c:forEach>
    </table>
    </p>
</c:if>
<c:if test="${empty sessionScope.cart.cartEntityList}">
    <h1>
        <span class="badge badge-secondary">YOUR CART IS EMPTY</span>
    </h1>
</c:if>