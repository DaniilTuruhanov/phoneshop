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
<form name="cartButton" action="${pageContext.servletContext.contextPath}/cart">
    <button>Cart ${sessionScope.cart.totalCost}</button>
</form>
</p>
<p>
    Hello from product list!
</p>
<form action="productList?query=${param.query}&sort=&order=&page=0">
    <input name="query" value="${param.query}">
    <button>Search</button>
</form>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link"
               href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:0}"
               aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:param.page}">${param.page>1? param.page:1}</a>
        </li>
        <li class="page-item "><a class="page-link"
                                  href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page:1}">${param.page>1? param.page+1:2}</a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):2}">${param.page>1? param.page+2:3}</a>
        </li>
        <li class="page-item">
            <a class="page-link"
               href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):1}"
               aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>
<p>
    Found
    <c:out value="${phones.size()}"/> phones.
<table border="1px">
    <thead>
    <tr>
        <td>Image</td>
        <td>Brand
            <a href="productList?query=${param.query}&sort=asc&order=brand">↑</a>
            <a href="productList?query=${param.query}&sort=desc&order=brand">↓</a>
        </td>
        <td>Model
            <a href="productList?query=${param.query}&sort=asc&order=model">↑</a>
            <a href="productList?query=${param.query}&sort=desc&order=model">↓</a>
        </td>
        <td>Color</td>
        <td>Display size
            <a href="productList?query=${param.query}&sort=asc&order=displaySizeInches">↑</a>
            <a href="productList?query=${param.query}&sort=desc&order=displaySizeInches">↓</a>
        </td>
        <td>Price
            <a href="productList?query=${param.query}&sort=asc&order=price">↑</a>
            <a href="productList?query=${param.query}&sort=desc&order=price">↓</a>
        </td>
        <td>Quantity</td>
        <td>Action</td>
    </tr>
    </thead>
    <c:forEach var="phone" items="${phones}">
        <tr>
            <td>
                <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
            </td>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>

            <td><c:forEach var="color" items="${phone.colors}">
                <div> ${color.code}</div>
            </c:forEach></td>

            <td> ${phone.displaySizeInches}"</td>
            <td>$ ${phone.price}</td>
            <td>
                <input id="quantity${phone.id}" name="quantity${phone.id}" value="1">
                <div name="error${phone.id}" class="error">
                </div>
            </td>
            <td>
                <input type="submit" class="ajaxButton${phone.id}" onclick="addAjax(${phone.id})" value="Add to">
            </td>
        </tr>

    </c:forEach>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link"
               href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:0}"
               aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page-1:param.page}">${param.page>1? param.page:1}</a>
        </li>
        <li class="page-item "><a class="page-link"
                                  href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?param.page:1}">${param.page>1? param.page+1:2}</a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):2}">${param.page>1? param.page+2:3}</a>
        </li>
        <li class="page-item">
            <a class="page-link"
               href="productList?query=${param.query}&sort=${param.sort}&order=${param.order}&page=${param.page>0?(param.page<countPage-1?param.page+1:param.page):1}"
               aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>
<script>
    function addAjax(id) {
        var quantity = '#quantity' + id;
        $(document).ready(function () {
            $('.ajaxButton' + id).prop('disabled', true);
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/ajaxCart",
                data: {quantity: $(quantity).val(), phoneId: id},
                success: function (msg, textStatus, xhr) {
                    $('.ajaxButton' + id).prop('disabled', false);
                    var tbody = document.getElementsByName('cartButton')[0];
                    tbody.innerHTML = '';
                    var node = document.createElement('button');
                    node.innerHTML = 'Cart ' + xhr.responseText;
                    tbody.appendChild(node);
                    Array.from(document.getElementsByClassName('error')).forEach(function (element) {
                        element.innerHTML = '';
                    });
                    document.getElementsByName('quantity' + id)[0].value = '1';
                },
                error: function (msg) {
                    $('.ajaxButton' + id).prop('disabled', false);
                    var tbody = document.getElementsByName('error' + id)[0];
                    tbody.innerHTML = '';
                    var node = document.createElement('span');
                    node.innerHTML = msg.responseText;
                    tbody.appendChild(node);
                }
            });
        })
    }
</script>
</p>