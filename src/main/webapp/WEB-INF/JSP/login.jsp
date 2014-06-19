
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    </head>
    <body>
        <div align="right">
            <c:choose>
                <c:when test="${not empty (customer.name)}">
                    <form:form action="logout" method="post" modelAttribute="">
                        <h5>Welcome, ${customer.name} ${customer.lastName}!
                            <button type="submit" value="logout" name="logout"
                            style="background-color:transparent; border-color:transparent;">
                                <img src="./resources/images/logout_button_0.png" height="20" width="80"/>
                            </button>
                        </h5>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form:form action="login" method="post" commandName="customer">
                        <table border="0">
                            <tr>
                                <td>Email:</td>
                                <td><form:input path="email" /></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><form:password path="pwdHash" /></td>
                            </tr>
                            <tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="login" name="login" /></td>
                            </tr>
                        </table>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>