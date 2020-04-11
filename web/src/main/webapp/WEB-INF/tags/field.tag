<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="errors" required="true" type="java.util.List" %>

<c:forEach items="${errors}" var="error">
    <c:if test="${error.code.equals(name)}">
        <span style="color: red">${error.defaultMessage}</span>
    </c:if>
</c:forEach>
