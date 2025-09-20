<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>

<h2>Sign Up</h2>
<form action="add-customer" method="post">
    Name: <input type="text" name="customerName" required><br>
    Email: <input type="email" name="customerEmail" required><br>
    Password; <input type="password" name="customerPassword" required><br>
    Contact Number: <input type="number" name="customerContactNo" required><br>

    <input type="submit" value="Add Customer">
</form>
<a href="${pageContext.request.contextPath}/login">Login</a>
</body>
</html>
