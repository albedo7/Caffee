<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Caffee</title>
  </head>
<body>
<div align="center">
    <form:form action="register" method="post" commandName="userForm">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Customer Registration</h2></td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td><form:input path="name" />
                    <form:errors path="name" cssclass="error" />
                </td>
            </tr>
            <tr>
                <td>User Lastname:</td>
                <td><form:input path="lastName" />
                    <form:errors path="lastName" cssclass="error" />
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="pwdHash" />
                    <form:errors path="pwdHash" cssclass="error" />
                </td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" />
                    <form:errors path="email" cssclass="error" />
                </td>
            </tr>
            <tr>
                <td>Credit Card Type:</td>
                <td><form:select path="creditCard.creditCardType.id"
                                 items="${cardList}" itemValue="id" itemLabel="type"/></td>
            </tr>
            <tr>
                <td>Credit card number:</td>
                <td><form:input path="creditCard.numHash" /></td>
            </tr>
            <tr>
                <td>Expires(dd-mm-yyyy):</td>
                <fmt:formatDate value="${form.creditCard.expDate}" var="dateString" type="date" pattern="dd-MM-yyyy" />
                <td><form:input path="creditCard.expDate" value = "${dateString}" />
                    <form:errors path="creditCard.expDate" cssclass="error" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>