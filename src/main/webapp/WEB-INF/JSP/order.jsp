<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<c:choose>
    <c:when test="${empty (customer.name)}">
        <c:out value="Please login to create order" />
        <c:out value="${order}" />
    </c:when>
    <c:otherwise>
        <c:out value="Later here you order should be displayed" />
        <c:forEach var="meals" items="${order.orderMealsesById}">
            <c:out value="Later here you order should be displayed" />
        </c:forEach>
    </c:otherwise>
</c:choose>