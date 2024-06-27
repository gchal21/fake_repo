<%@ page import="entities.Achievement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="./styles/home.css"%></style>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <title>home</title>
</head>
<body>
    <div class="wrapper">
        <section class="content_wrapper">
            <div class="title_wrapper">
                <p class="title itim-regular">QUIZIFY</p>
            </div>

            <jsp:include page="./navbar.jsp"/>

            <div class="table_wrapper">
                <%String section = (String)request.getAttribute("currentSection");%>
                <%if(section.equals("Announcements")){%>
                <jsp:include page="./sections/announcements.jsp"/>
                <%} else if (section.equals("Popular Quizzes")){%>
                <jsp:include page="./sections/popularQuizzes.jsp"/>
                <%} else if (section.equals("Friends Activities")){%>
                <jsp:include page="./sections/friendsActivities.jsp"/>
                <%} else if (section.equals("My Recently Created Quizzes")){%>
                <jsp:include page="./sections/myRecentlyCreatedQuizzes.jsp"/>
                <%} else if (section.equals("My Recently Taken Quizzes")){%>
                <jsp:include page="./sections/myRecentlyTakenQuizzes.jsp"/>
                <%} else if (section.equals("Recently Created Quizzes")){%>
                <jsp:include page="./sections/recentlyCreatedQuizzes.jsp"/>
                <%}%>
            </div>

        </section>
        <section class="sidebar">
            <div class="sidebar_header">
                <p class="itim-regular"><%=request.getAttribute("userName")%></p>
            </div>

           <jsp:include page="./achievements.jsp"/>

            <jsp:include page="./messages.jsp" />

        </section>
    </div>
</body>
</html>
