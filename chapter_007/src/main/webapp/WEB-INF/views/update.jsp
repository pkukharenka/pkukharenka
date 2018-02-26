<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update User info</h1>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <c:set var="user" value="${user}"/>
    <input type="text" name="id" value="<c:out value="${user.id}"/>" >
    Name: <input type="text" name="name" value="<c:out value="${user.name}"/>">
    Login: <input type="text" name="login" value="<c:out value="${user.login}"/>">
    Email: <input type="text" name="email" value="<c:out value="${user.email}"/>">
    <input type="submit">
</form>
</body>
</html>
