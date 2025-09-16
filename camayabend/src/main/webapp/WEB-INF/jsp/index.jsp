<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Customer List</title></head>
<body>
<h1>Customer List</h1>
<ul>
    <c:forEach var="cust" items="${customers}">
        <li>${cust.customerName} (${cust.customerEmail})</li>
    </c:forEach>
</ul>
</body>
</html>
