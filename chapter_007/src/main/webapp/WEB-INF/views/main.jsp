<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User crud</h1>
<br>
<c:if test="${empty user}">
    <form action="${pageContext.request.contextPath}/crud" method="post">
        <input type="hidden" name="command" value="add">
        Name: <input type="text" name="name"><br>
        Login: <input type="text" name="login"><br>
        Email: <input type="text" name="email"><br>
        <input type="submit" value="Add User">
    </form>
</c:if>

<c:if test="${not empty user}">
    <form action="${pageContext.request.contextPath}/crud" method="post">
        <input type="hidden" name="command" value="update">
        <input type="text" name="id" value="<c:out value="${user.id}"/>" hidden>
        Updating user with id - <c:out value="${user.id}"/>
        <br>
        Name: <input type="text" name="name" value="<c:out value="${user.name}"/>"><br>
        Login: <input type="text" name="login" value="<c:out value="${user.login}"/>"><br>
        Email: <input type="text" name="email" value="<c:out value="${user.email}"/>"><br>
        <input type="submit" value="Update User">
    </form>
</c:if>
<hr>
<hr>
<h2>List of users</h2>
<table border="1">
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
            <td align="center">
                <form action="${pageContext.request.contextPath}/crud" method="post" >
                    <input type="hidden" name="command" value="fillUpdate">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                    <input type="submit" value="update">
                </form>
            </td>
            <td align="center">
                <form action="${pageContext.request.contextPath}/crud" method="post">
                    <input type="hidden" name="command" value="del">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                    <input type="submit" value="delete">
                </form>
        </tr>
    </c:forEach>

</table>
</body>
</html>
