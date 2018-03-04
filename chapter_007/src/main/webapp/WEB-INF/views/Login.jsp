<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1 align="center">Simple crud application</h1>
<br>
<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="hidden" name="command" value="login">
    Login: <input type="text" name="login">
    <br>
    Password: <input type="password" name="password">
    <br>
    <br>
    <c:out value="${error}"/>
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
