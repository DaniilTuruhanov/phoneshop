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
    Hello from product page!
    </span>
</h4>
<p>
<form action="${pageContext.servletContext.contextPath}">
    <input class="btn btn-primary mb-2" type="submit" value="Back to Product list"/>
</form>
<div style="display: flex">
    <div style="width: 70%; margin-left: 3%">
        <h2>${phone.model}</h2>
        <p>
            <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
        </p>
        <p style="width: 50%">${phone.description}</p>
        <table class="table table-sm" style="width: 50%">
            <tr>
                <th>
                    Price : ${phone.price}
                </th>
            </tr>
            <tr>
                <th>
                    <input id="quantity${phone.id}" name="quantity${phone.id}" value="1"> <input
                        class="btn btn-primary mb-2"
                        type="submit"
                        id="ajaxButton${phone.id}"
                        onclick="addAjax(${phone.id})"
                        value="Add to">
                    <p name="error${phone.id}" class="error"></p>
                </th>
            </tr>

        </table>
    </div>
    <div>
        <h6>Display</h6>
        <table class="table table-striped table-hover table-sm">
            <tr>
                <th>Size</th>
                <th>${phone.displaySizeInches} "</th>
            </tr>
            <tr>
                <th>Resolution</th>
                <th>${phone.displayResolution}</th>
            </tr>
            <tr>
                <th>Technology</th>
                <th>${phone.displayTechnology}</th>
            </tr>
            <tr>
                <th>Pixel density</th>
                <th>${phone.pixelDensity}</th>
            </tr>
        </table>
        <h6>Dimensions and weight</h6>
        <table class="table table-striped table-hover table-sm">
            <tr>
                <th>Length</th>
                <th>${phone.lengthMm} mm</th>
            </tr>
            <tr>
                <th>Width</th>
                <th>${phone.widthMm} mm</th>
            </tr>
            <tr>
                <th>Weight</th>
                <th>${phone.weightGr} gr</th>
            </tr>
        </table>
        <h6>Camera</h6>
        <table class="table table-striped table-hover table-sm">
            <tr>
                <th>Front</th>
                <th>${phone.frontCameraMegapixels} megapixels</th>
            </tr>
            <tr>
                <th>Back</th>
                <th>${phone.backCameraMegapixels} megapixels</th>
            </tr>
        </table>
        <h6>Battery</h6>
        <table class="table table-striped table-hover table-sm">
            <tr>
                <th>Talk time</th>
                <th>${phone.talkTimeHours} hours</th>
            </tr>
            <tr>
                <th>Stand by time</th>
                <th>${phone.standByTimeHours} hours</th>
            </tr>
            <tr>
                <th>Battery capacity</th>
                <th>${phone.batteryCapacityMah} mAh</th>
            </tr>
        </table>
        <h6>Other</h6>
        <table class="table table-striped table-hover table-sm">
            <tr>
                <th>Colors</th>
                <th><c:forEach var="color" items="${phone.colors}">
                    <div> ${color.code}</div>
                </c:forEach></th>
            </tr>
            <tr>
                <th>Device type</th>
                <th>${phone.deviceType}</th>
            </tr>
            <tr>
                <th>Bluetooth</th>
                <th>${phone.bluetooth}</th>
            </tr>
        </table>
    </div>
</div>
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