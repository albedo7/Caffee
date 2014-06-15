<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Caffee</title>
        <link rel="stylesheet" href="./resources/CSS/caffee_menu.css" type="text/css" />
        <link rel="stylesheet" href="./resources/CSS/all_pages.css" type="text/css" />
        <c:import url="login.jsp"/>
    </head>

    <body>
            <ul id="caffee_menu">
            <li><a href="#"><span>Главная</span></a></li>
            <li><a href="#"><span>Мои заказы</span></a></li>
            <li><a href="#"><span>Сделать заказ</span></a></li>
            <li><a href="#"><span>Регистрация</span></a></li>
            <li><a href="#"><span>Отзывы</span></a></li>
        </ul>
    </body>
</html>