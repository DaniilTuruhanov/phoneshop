<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<head>
<h4>
    <span class="badge badge-light"> Welcome to Expert-Soft!</span>
</h4>

<h4>
    <span class="badge badge-light">
    Hello from order!
    </span>
</h4>
<form action="${pageContext.servletContext.contextPath}/cart">
    <input class="btn btn-primary mb-2" type="submit" value="Back to cart"/>
</form>
<c:forEach items="${errors}" var="error">
    <c:if test="${error.code.equals('quantity')}">
        <b><span style="color: red">${error.defaultMessage}</span></b>
    </c:if>
</c:forEach>
<c:if test="${not empty order.orderEntityList}">
    <p>
    <h4>
       <span class="badge badge-light">
        Found
            <c:out value="${order.orderEntityList.size()}"/> phones.
        </span>
    </h4>
    <form method="post">
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
                    <input type="hidden" value="${cartEntity.id}" name="placeOrderId"/>
                    <input type="hidden" value="${cartEntity.quantity}" name="placeOrderQuantity"/>
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
                <th>Subtotal cost:</th>
                <th>
                        ${order.subtotalCost}
                </th>
            </tr>
            <tr>
                <th>Delivery cost:</th>
                <th>
                        ${order.deliveryCost}
                </th>
            </tr>

            <tr>
                <th>Total cost:</th>
                <th>
                        ${order.totalCost}
                </th>
            </tr>
        </table>
        </p>
        <table>
            <tr>
                <th>
                    First name
                </th>
                <th>
                    <input value="${param.firstName}" class="form-control" placeholder="First name" name="firstName"/>
                    <tags:field name="First name" errors="${errors}"/>
                </th>
            </tr>
            <tr>
                <th>
                    Last name
                </th>
                <th>
                    <input value="${param.lastName}" class="form-control" placeholder="Last name" name="lastName"/>
                    <tags:field name="Last name" errors="${errors}"/>
                </th>
            </tr>
            <tr>
                <th>
                    Address
                </th>
                <th>
                    <input value="${param.address}" class="form-control" placeholder="Address" name="address"/>
                    <tags:field name="Address" errors="${errors}"/>
                </th>
            </tr>
            <tr>
                <th>
                    Phone
                </th>
                <th>
                    <input value="${param.phone}" class="form-control" placeholder="Phone" name="phone"/>
                    <tags:field name="Phone" errors="${errors}"/>
                </th>
            </tr>
        </table>
        <div>
            <textarea style="width: 30%" class="form-control" name="description"
                      placeholder="Additional information">${param.description}</textarea>
        </div>
        <div>
            <input type="submit" class="btn btn-primary mb-2" value="Place order"/>
        </div>
    </form>

</c:if>
<c:if test="${empty order.orderEntityList}">
    <h1>
        <span class="badge badge-secondary">YOUR ORDER IS EMPTY</span>
    </h1>
</c:if>