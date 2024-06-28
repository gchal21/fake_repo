<%@ page import="entities.User" %>
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
