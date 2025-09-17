<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Customer List</h1>

<ul>
    <c:forEach var="cust" items="${customers}">
        <li>${cust.customerName} (${cust.customerEmail}) ${cust.customerContactNo}</li>
    </c:forEach>
</ul>

<h2>Add Customer</h2>
<form action="add-customer" method="post">
    Name: <input type="text" name="customerName" required><br>
    Email: <input type="email" name="customerEmail" required><br>
    Contact Number: <input type="number" name="customerContactNo" required><br>
    <input type="submit" value="Add Customer">
</form>

</body>
</html>
