<%@ page import="entities.User" %>
<%@ page import="entities.Achievement" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Quiz" %>
<%@ page import="DTOs.QuizHistoryDTO" %>
<%@ page import="utils.FrontHelpers" %>
<%@ page import="DTOs.QuizStatsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <style><%@include file="./styles/quizLanding.css"%></style>
</head>
<body>

<button class="home_button itim-regular" onclick="redirectToHome()">Home</button>

<div class="banner">

    <div class="basic_info">
        <%User author = (User)request.getAttribute("author");%>
        <%Quiz quiz = (Quiz)request.getAttribute("currentQuiz");%>
        <p class="quiz_name itim-regular"><%=quiz.getTitle()%></p>
        <a class="author itim-regular"><%=author.getUsername()%></a>
        <p class="description itim-regular"><%=quiz.getDescription()%></p>
    </div>

    <div class="quiz_statistics">
        <%QuizStatsDTO stats = (QuizStatsDTO) request.getAttribute("quizStats");%>
        <p class="quiz_statistics_header itim-regular">Quiz Statistics</p>
        <div class="stats_wrapper">
            <p class="itim-regular">Number Of Takes: <%=stats.getUserCount()%></p>
            <p class="itim-regular">Average Score: <%=stats.getAverageScore()%></p>
        </div>
    </div>

    <jsp:include page="myPerformance.jsp"/>
    <jsp:include page="highestPerformances.jsp"/>
    <jsp:include page="lastDayHighestPerformers.jsp"/>
    <jsp:include page="mostRecentPerformances.jsp"/>

    <div class="start_quiz_button">
        <button class="itim-regular button_green">Start Quiz</button>
    </div>

</div>
</body>
<script>
    function redirectToHome() {
        window.location.href = '/home';
    }

</script>
</html>
