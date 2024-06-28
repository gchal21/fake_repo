<%@ page import="entities.User" %>
<%@ page import="entities.Achievement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <style><%@include file="./styles/user.css"%></style>
</head>
<body>

    <button class="home_button itim-regular" onclick="redirectToHome()">Home</button>

    <div class="banner">
        <%User profile = (User)request.getAttribute("profile");%>
        <div class="basic_info">
            <p class="username itim-regular"><%=profile.getUsername()%></p>
            <p class="email itim-regular"><%=profile.getEmail()%></p>
        </div>

        <div class="profile_achievements">
            <%List<Achievement> achievements = (List<Achievement>)request.getAttribute("achievements");%>
            <p class="profile_achievements_header itim-regular">Achievements</p>

            <%for(Achievement a : achievements){%>
                <div class="user_achievement">
                    <p class="itim-regular"><%=a.getName()%></p>
                    <div class="user_achievement_data">
                        <img class="user_achievement_icon" alt="achievement icon" src="../../resources<%=a.getImageUrl()%>"/>
                        <p class="itim-regular achievement_text"><%=a.getDescription()%></p>
                    </div>
                </div>
            <%}%>
        </div>
    </div>
</body>
<script>
    function redirectToHome() {
        window.location.href = '/home';
    }
</script>
</html>
