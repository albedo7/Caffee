<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<c:choose>
    <c:when test="${empty (customer.name)}">
        <c:out value="Please login to create order" />
    </c:when>
    <c:otherwise>
        <c:out value="Your meals in current order" />
        <c:if test="${not empty (order)}" >
            <c:forEach var="meal" items="${order.orderMealsesById}">
                </br>
                <c:out value = "${meal.meal.name} Price = ${meal.meal.price}'$'" />
            </c:forEach>
            </br>
            <c:out value = "Sum of your order is  = ${order.summ}'$'" />
        </c:if>
    </c:otherwise>
</c:choose>