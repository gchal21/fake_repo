<%@ page import="entities.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2024
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="./styles/user.css"%></style>
</head>
<body>
    <%
        User user = (User)request.getAttribute("currentUser");
        String name = user.getUsername();
    %>

    <h1><%=name%> </h1>
</body>
</html>
