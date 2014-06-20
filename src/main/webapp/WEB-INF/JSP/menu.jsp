<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<<<<<<< HEAD
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
=======
>>>>>>> 00f6dbcb8c08d75a3b3c1fa67d29431078c3a4ac
<html>
    <head>
        <title>Caffee</title>
        <link rel="stylesheet" href="./resources/css/caffee_menu.css" type="text/css" />
        <link rel="stylesheet" href="./resources/css/all_pages.css" type="text/css" />
    </head>

    <body>
        <ul id="caffee_menu">
            <li><a href="./main"><span>Main</span></a></li>
            <li><a href="./book"><span>Menu</span></a></li>
            <li><a href="#"><span>My orders</span></a></li>
            <c:choose>
                <c:when test="${not empty (customer.email)}">
                    <li><a href="./userData"><span>User information</span></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="./register"><span>Registration</span></a></li>
                </c:otherwise>
            </c:choose>
            <li><a href="#"><span>Comments</span></a></li>
        </ul>
    </body>
</html>