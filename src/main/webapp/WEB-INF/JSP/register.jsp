<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>User Lastname:</td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="pwdHash" /></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Credit Card Type:</td>
                <td><form:select path="creditCardByCreditCardId" items="${cardList}" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>