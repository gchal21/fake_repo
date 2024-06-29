<%@ page import="DTOs.ActivityDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="enums.ActivityType" %>
<%@ page import="utils.FrontHelpers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <style><%@include file="./styles/quizShared.css"%></style>--%>
    <style><%@include file="./styles/friendsActivities.css"%></style>
</head>
<body>

<%
    List<ActivityDTO> friendsActivities = (List<ActivityDTO>) request.getAttribute("friendsActivitiesData");
%>
<div class="content-wrapper">
    <div class="scrollable-container">
        <%
            if (friendsActivities != null) {
                for (ActivityDTO activity : friendsActivities) {
        %>
        <div class="activity-block">
            <% if ((ActivityType.QUIZ_WRITTEN).equals(activity.getType())) { %>
            <p class="itim-regular info"><%= activity.getUserName() %> has written a quiz: <%= activity.getQuiz().getName() %></p>
            <% } else if ((ActivityType.QUIZ_CREATED).equals(activity.getType())) { %>
            <p class="itim-regular info"><%= activity.getUserName() %> has created a quiz: <%= activity.getQuiz().getName() %></p>
            <% } else if ((ActivityType.ACHIEVEMENT_ACQUIRED).equals(activity.getType())) { %>
            <p class="itim-regular info"><%= activity.getUserName() %> has received an achievement: <%= activity.getAchievement().getName() %></p>
            <% } %>
            <p class="itim-regular"><%=FrontHelpers.formatDate(activity.getCreateDate()) %></p>
        </div>
        <%
            }
        } else {
        %>
        <p class="no-activities">No activities found.</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
