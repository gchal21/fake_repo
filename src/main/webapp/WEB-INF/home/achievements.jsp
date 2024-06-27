<%@ page import="entities.Achievement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<style><%@include file="./styles/achievements.css"%></style>

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