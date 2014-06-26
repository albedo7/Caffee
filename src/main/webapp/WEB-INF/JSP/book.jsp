<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" language="javascript" src="./resources/js/book.js"></script>
<script type="text/javascript" language="javascript" src="./resources/js/orderAjax.js"></script>

<div class="book_wrapper">
    <a id="next_page_button"></a>
    <a id="prev_page_button"></a>
    <div id="loading" class="loading">Loading menu...</div>
    <div id="mybook" style="display:none;">
        <div class="b-load">
            <c:forEach var="meal" items="${mealList}">
                <div>
                    <h1>${meal.name}</h1>
                    <img src="<c:url value="/book/image">
                        <c:param name="id" value="${meal.id}"/>
                     </c:url>" alt=""/>
                    <p style="color: black">${meal.description}</p>
                    <p style="color: black" class="article">Price: ${meal.price}</p>
                    <a class="demo" onclick="addMealToOrder('${meal.id}')">Add to order</a>
                    <a href="/add" class="demo">Add to order</a>
                </div>
            </c:forEach>
         </div>
    </div>
</div>
