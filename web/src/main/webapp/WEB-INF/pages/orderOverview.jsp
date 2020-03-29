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
<h4>
    <span class="badge badge-light">
    Thank you for your order
    </span>
</h4>
    <p>
    <h4>
       <span class="badge badge-light">
        Order number: ${order.number}
        </span>
    </h4>

    <table class="table table-striped table-hover table-sm">
        <thead>
        <tr>
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
        </tr>
        </thead>
        <c:forEach var="cartEntity" items="${order.orderEntityList}">
            <tr>
                <th>${cartEntity.brand}</th>
                <th>${cartEntity.model}</th>

                <th><c:forEach var="color" items="${cartEntity.colors}">
                    <div> ${color.code}</div>
                </c:forEach></th>

                <th> ${cartEntity.displaySizeInches}"</th>
                <th>$ ${cartEntity.price}</th>
                <th>
                        ${cartEntity.quantity}
                </th>
            </tr>

        </c:forEach>
        <tr>
            <th>Subtotal cost: </th>
            <th>
                ${order.subtotalCost}
            </th>
        </tr>
        <tr>
            <th>Delivery cost: </th>
            <th>
                ${order.deliveryCost}
            </th>
        </tr>

        <tr>
            <th>Total cost: </th>
            <th>
                ${order.totalCost}
            </th>
        </tr>
    </table>
    <br/>
        <table class="table" style="width: 50%">
            <tr>
                <th>
                    First name
                </th>
                <th>
                    ${order.firstName}
                </th>
            </tr>
            <tr>
                <th>
                    Last name
                </th>
                <th>
                    ${order.lastName}
                </th>
            </tr>
            <tr>
                <th>
                    Address
                </th>
                <th>
                    ${order.address}
                </th>
            </tr>
            <tr>
                <th>
                    Phone
                </th>
                <th>
                    ${order.phone}
                </th>
            </tr>
        </table>
    <br/>
    <p style="width: 35%;word-break: break-word;margin-left: 1%"><b>${order.description}</b></p>
<br/>
<form action="${pageContext.servletContext.contextPath}">
    <input class="btn btn-primary mb-2" type="submit" value="Back to Shopping"/>
</form>
