
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="right">
    <c:choose>
        <c:when test="${not empty (customer.name)}">
            <form:form action="logout" method="post" modelAttribute="">
                <h3>Welcome, ${customer.name} ${customer.lastName}!
                        <button type="submit" value="logout" name="logout"
                    style="background-color:transparent; border-color:transparent;">
                        <img src="./resources/images/logout_button_0.png" height="20" width="80"/>
                    </button>
                </h3>
            </form:form>
        </c:when>
        <c:otherwise>
            <form:form action="login" method="post" commandName="customer">
                <table border="0">
                    <tr>

                            <td><h3>Email:</h3></td>
                            <td><h3><form:input path="email" /></h3></td>
                    </tr>
                    <tr>
                            <td><h3>Password:</h3></td>
                            <td><h3><form:password path="pwdHash" /></h3></td>
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