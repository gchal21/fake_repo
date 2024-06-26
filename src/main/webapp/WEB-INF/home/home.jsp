<%@ page import="entities.Achievement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="./css/home.css"%></style>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <title>home</title>

    <script>
        function changeSection(section) {
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "home", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const response = JSON.parse(xhr.responseText);

                    const links = document.querySelectorAll(".navbar_wrapper a");
                    links.forEach(function (link) {
                        if (link.textContent.trim() === response.currentSection) {
                            link.classList.add("decorate_underline");
                        } else {
                            link.classList.remove("decorate_underline");
                        }
                    });
                }
            };
            xhr.send("section=" + section);
        }

        document.addEventListener("DOMContentLoaded", function () {
            const links = document.querySelectorAll(".navbar_wrapper a");
            links.forEach(function (link) {
                link.addEventListener("click", function (e) {
                    e.preventDefault();
                    const section = link.textContent.trim();
                    changeSection(section);
                });
            });
        });
    </script>

</head>
<body>
    <div class="wrapper">
        <section class="content_wrapper">
            <div class="title_wrapper">
                <p class="title itim-regular">QUIZIFY</p>
            </div>

            <div class="navbar_wrapper  itim-regular">
                <div>
                    <a href="#" class="<%= "Announcements".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Announcements</a>
                    <a href="#" class="<%= "Popular Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Popular Quizzes</a>
                    <a href="#" class="<%= "Friends Activities".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Friends Activities</a>
                    <a href="#" class="<%= "Recently Created Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Recently Created Quizzes</a>
                </div>
                <div>
                    <a href="#" class="<%= "My Recently taken Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">My Recently taken Quizzes</a>
                    <a href="#" class="<%= "My Recently Created Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">My Recently Created Quizzes</a>
                </div>
            </div>

        </section>
        <section class="sidebar">
            <div class="sidebar_header">
                <p class="itim-regular"><%=request.getAttribute("userName")%></p>
            </div>

            <div class="achievements_wrapper">


                <%
                    List<Achievement> achievements = (List<Achievement>)request.getAttribute("achievements");
                    if(achievements.isEmpty()){%>
                        <p class="no_achievement_p itim-regular"> No Achievements </p>
                <%  }else {
                        %>
                            <div class="icons_container">
                        <%
                        for(Achievement achievement : achievements){%>
                            <img class="achievement_icon" alt="achievement icon" src="../../resources<%=achievement.getImageUrl()%>">
                        <%
                        }
                        %>
                            </div>
                        <%
                    }
                %>
            </div>

            <jsp:include page="./messages.jsp" />

        </section>
    </div>
</body>
</html>
