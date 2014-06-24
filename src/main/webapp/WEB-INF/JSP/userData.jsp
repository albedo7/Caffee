
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div align="center">
    <table border="0">
        <tr>
            <td colspan="2" align="center"><h2>User information</h2></td>
        </tr>
            <tr>
                <td><h3>User Name:</h3></td>
                <td><h3>${customer.name} </h3></td>
            </tr>
            <tr>
                <td><h3>User Lastname:</h3></td>
                <td></h3>${customer.lastName}</h3></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td>${customer.email}</td>
            </tr>
            <tr>
                <td>Credit Card Type:</td>
                <td>${customer.creditCard.creditCardType.type}</tr>
            <tr>
                <td>Credit card number:</td>
                <td>${customer.creditCard.numHash}</td>
            </tr>
            <tr>
                <td>Expires:</td>
                <td><fmt:formatDate value="${customer.creditCard.expDate}" pattern="dd-MM-yyyy" /></td>
            </tr>
    </table>
</div>