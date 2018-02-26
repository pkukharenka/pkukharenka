<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User crud</h1>
<br>
<form action="${pageContext.request.contextPath}/add" method="post">
    Name: <input type="text" name="name"><br>
    Login: <input type="text" name="login"><br>
    Email: <input type="text" name="email"><br>
    <input type="submit" value="Add User">
</form>
<br>
<h2>List of users</h2>
<table>
    <tr>
        <th>Id</th>
        <th>User name</th>
        <th>User login</th>
        <th>User email</th>
        <th>Create date</th>
        <th>Updating</th>
        <th>Deleting</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.createDate}"/></td>
            <td><a href="${pageContext.request.contextPath}/update?id=<c:out value="${user.id}"/>">update</a></td>
            <td><a href="${pageContext.request.contextPath}/del?id=<c:out value="${user.id}"/>">delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
