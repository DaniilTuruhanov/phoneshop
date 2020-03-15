<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form action="${pageContext.servletContext.contextPath}">
    <input class="btn btn-primary mb-2" type="submit" value="Back to Product list"/>
</form>
<c:if test="${not empty cart.cartEntityList}">
    <p>
    <h4>
       <span class="badge badge-light">
        Found
            <c:out value="${cart.cartEntityList.size()}"/> phones.
        </span>
    </h4>
    <form action="${pageContext.servletContext.contextPath}/order" method="get">
        <button class="btn btn-primary mb-2">
            Order
        </button>
    </form>
    <form:form method="PUT" id="updateCart">

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
                <th>Action</th>
            </tr>
            </thead>
            <c:forEach var="cartEntity" items="${cart.cartEntityList}">
                <tr>
                    <th>${cartEntity.brand}</th>
                    <th>${cartEntity.model}</th>

                    <th><c:forEach var="color" items="${cartEntity.colors}">
                        <div> ${color.code}</div>
                    </c:forEach></th>

                    <th> ${cartEntity.displaySizeInches}"</th>
                    <th>$ ${cartEntity.price}</th>
                    <th>
                        <input class="form-control" name="quantity" value="${cartEntity.quantity}"/>
                        <input class="form-control" type="hidden" name="phonesId"
                               value="${cartEntity.id}"/>
                        <c:forEach items="${errors}" var="error">
                            <c:if test="${error.code.equals(cartEntity.id.toString())}">
                                <p style="color: red">${error.defaultMessage}</p>
                            </c:if>
                        </c:forEach>
                    </th>
                    <th>
                        <button class="btn btn-primary mb-2" value="${cartEntity.id}" name="phoneId"
                                form="deleteCartItem">Delete
                        </button>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <div style="display:flex">
        <div>
            <button class="btn btn-primary mb-2">
                Update
            </button>
        </div>
    </form:form>
    <form:form id="deleteCartItem" method="DELETE"/>
    <div style="margin-left: 3%">
        <form action="${pageContext.servletContext.contextPath}/order" method="get">
            <button class="btn btn-primary mb-2">
                Order
            </button>
        </form>
    </div>
    </div>
    </p>
</c:if>
<c:if test="${empty cart.cartEntityList}">
    <h1>
        <span class="badge badge-secondary">YOUR CART IS EMPTY</span>
    </h1>
</c:if>
