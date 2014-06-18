<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Caffee</title>
        <link rel="stylesheet" href="./resources/css/caffee_menu.css" type="text/css" />
        <link rel="stylesheet" href="./resources/css/all_pages.css" type="text/css" />
        <%--<c:import url="login.jsp"/>--%>
    </head>

    <body>
            <ul id="caffee_menu">
            <li><a href="./main"><span>Main</span></a></li>
            <li><a href="#"><span>Menu</span></a></li>
            <li><a href="#"><span>My orders</span></a></li>
            <li><a href="./register"><span>Registration</span></a></li>
            <li><a href="#"><span>Comments</span></a></li>
        </ul>
    </body>
</html>