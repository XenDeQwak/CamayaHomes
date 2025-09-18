<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label>Email:</label>
    <input type="text" name="customerEmail"/><br/><br/>
    <label>Password:</label>
    <input type="password" name="customerPassword"/><br/><br/>
    <input type="submit" value="Login"/>
</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body>
</html>
