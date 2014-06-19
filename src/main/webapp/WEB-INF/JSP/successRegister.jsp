<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Caffee</title>
</head>
<body>
    <div align="center">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Registration Succeeded!</h2></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <h3>Thank you for registering! Here's the review of your details:</h3>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><h2>Customer Registration</h2></td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td>${userForm.name} </td>
            </tr>
            <tr>
                <td>User Lastname:</td>
                <td>${userForm.lastName}</td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td>${userForm.email}</td>
            </tr>
            <tr>
                <td>Credit Card Type:</td>
                <td>${userForm.creditCard.creditCardType.type}</tr>
            <tr>
                <td>Credit card number:</td>
                <td>${userForm.creditCard.numHash}</td>
            </tr>
            <tr>
                <td>Expires:</td>
                <td><fmt:formatDate value="${userForm.creditCard.expDate}" pattern="dd-MM-yyyy" /></td>
            </tr>

        </table>
    </div>
</body>
</html>