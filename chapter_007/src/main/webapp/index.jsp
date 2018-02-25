<%@ page import="ru.job4j.crud.dao.UserStore" %>
<%@ page import="ru.job4j.crud.model.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User crud</h1>
<br>
<form action="<%=request.getContextPath()%>/add" method="post">
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
    <% for (Users user : UserStore.getInstance().getAll()) { %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreateDate()%></td>
            <td><a href="<%=request.getContextPath()%>/update?id=<%=user.getId()%>">update</a></td>
            <td><a href="<%=request.getContextPath()%>/del?id=<%=user.getId()%>">delete</a></td>
        </tr>
    <% } %>
</table>
</body>
</html>
