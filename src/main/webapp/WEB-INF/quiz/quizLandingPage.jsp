<%@ page import="entities.Quiz" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <h1><%= ((Quiz)request.getAttribute("currentQuiz")).getTitle() %></h1>
    <p><%= ((Quiz)request.getAttribute("currentQuiz")).getDescription() %></p>
    <!-- Display other quiz information here -->
    <button type="button">Start Quiz</button>
</body>
</html>
