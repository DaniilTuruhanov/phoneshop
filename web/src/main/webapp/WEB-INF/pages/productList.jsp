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
    <span class="badge badge-light">
    Welcome to Expert-Soft!
    </span>
</h4>
<form name="cartButton" action="${pageContext.servletContext.contextPath}/cart">
    <button class="btn btn-primary mb-2">Cart ${sessionScope.cart.totalCost} $</button>
</form>
<h4>
   <span class="badge badge-light">
    Hello from product list!
    </span>
</h4>
<form class="form-inline" action="products?query=${param.query}&sort=&order=&page=0">
    <div class="form-group mx-sm-3 mb-2">
        <input class="form-control" name="query" value="${param.query}">
    </div>
    <button class="btn btn-primary mb-2">Search</button>
</form>
<c:if test="${not empty phones}">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link"
                   href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:0}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:param.page}">${param.page>1? param.page:1}</a>
            </li>
            <li class="page-item "><a class="page-link"
                                      href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page:1}">${param.page>1? param.page+1:2}</a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):2}">${param.page>1? param.page+2:3}</a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):1}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
    <p>
    <h4>
     <span class="badge badge-light">
    Found
    <c:out value="${phones.size()}"/> phones.
    </span>
    </h4>
    <table class="table table-striped table-hover table-sm">
        <thead>
        <tr>
            <th>Image</th>
            <th>Brand
                <a href="products?query=${param.query}&sort=asc&order=brand">↑</a>
                <a href="products?query=${param.query}&sort=desc&order=brand">↓</a>
            </th>
            <th>Model
                <a href="products?query=${param.query}&sort=asc&order=model">↑</a>
                <a href="products?query=${param.query}&sort=desc&order=model">↓</a>
            </th>
            <th>Color</th>
            <th>Display size
                <a href="products?query=${param.query}&sort=asc&order=displaySizeInches">↑</a>
                <a href="products?query=${param.query}&sort=desc&order=displaySizeInches">↓</a>
            </th>
            <th>Price
                <a href="products?query=${param.query}&sort=asc&order=price">↑</a>
                <a href="products?query=${param.query}&sort=desc&order=price">↓</a>
            </th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        </thead>
        <c:forEach var="phone" items="${phones}">
            <tr>
                <th>
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                </th>
                <th>${phone.brand}</th>
                <th><a href="products/${phone.id}">${phone.model}</a></th>

                <th><c:forEach var="color" items="${phone.colors}">
                    <div> ${color.code}</div>
                </c:forEach></th>

                <th> ${phone.displaySizeInches}"</th>
                <th>$ ${phone.price}</th>
                <th>
                    <div>
                        <input class="form-control" id="quantity${phone.id}" name="quantity${phone.id}" value="1">
                    </div>
                    <div name="error${phone.id}" class="error"></div>
                </th>
                <td>
                    <input class="btn btn-primary mb-2" type="submit" id="ajaxButton${phone.id}"
                           onclick="addAjax(${phone.id})" value="Add to">
                </td>
            </tr>

        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link"
                   href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:0}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:param.page}">${param.page>1? param.page:1}</a>
            </li>
            <li class="page-item "><a class="page-link"
                                      href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page:1}">${param.page>1? param.page+1:2}</a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):2}">${param.page>1? param.page+2:3}</a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="products?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):1}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
<c:if test="${empty phones}">
    <h1>
        <span class="badge badge-secondary">Phones not found</span>
    </h1>
</c:if>
<script>
    function addAjax(id) {
        var quantity = '#quantity' + id;
        $(document).ready(function () {
            $('#ajaxButton' + id).prop('disabled', true);
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/products/add",
                data: {quantity: $(quantity).val(), phoneId: id},
                success: function (msg, textStatus, xhr) {
                    $('#ajaxButton' + id).prop('disabled', false);
                    var tbody = document.getElementsByName('cartButton')[0];
                    tbody.innerHTML = '';
                    var node = document.createElement('button');
                    node.className = "btn btn-primary mb-2";
                    node.innerHTML = 'Cart ' + xhr.responseText + ' $';
                    tbody.appendChild(node);
                    Array.from(document.getElementsByClassName('error')).forEach(function (element) {
                        element.innerHTML = '';
                    });
                    document.getElementsByName('quantity' + id)[0].value = '1';
                },
                error: function (msg) {
                    $('#ajaxButton' + id).prop('disabled', false);
                    var tbody = document.getElementsByName('error' + id)[0];
                    tbody.innerHTML = '';
                    var node = document.createElement('span');
                    node.innerHTML = msg.responseText;
                    node.style.color = "red";
                    tbody.appendChild(node);
                }
            });
        })
    }
</script>
</p>