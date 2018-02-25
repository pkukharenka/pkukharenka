<%@ page import="ru.job4j.crud.model.Users" %>
<%@ page import="ru.job4j.crud.dao.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update User info</h1>
<form action="<%=request.getContextPath()%>/update" method="post">
    <input type="text" name="id" value="<%=request.getParameter("id")%>" hidden>

    <%Users user = UserStore.getInstance().get(Integer.parseInt(request.getParameter("id")));%>

    Name: <input type="text" name="name" value="<%=user.getName()%>">
    Login: <input type="text" name="login" value="<%=user.getLogin()%>">
    Email: <input type="text" name="email" value="<%=user.getEmail()%>">
    <input type="submit">
</form>
</body>
</html>
