<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.02.2018
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<body>
<h1>Welcome, to continue please press Start</h1>
<form action="${pageContext.request.contextPath}/crud" method="post">
    <input type="hidden" name="command" value="showAll">
    <input type="submit" value="Start">
</form>
</body>
</html>
