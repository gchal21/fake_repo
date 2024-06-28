<%@ page import="entities.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Duration" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="utils.FrontHelpers" %>

<style><%@include file="./styles/quizShared.css"%></style>
<style><%@include file="./styles/announcements.css"%></style>

<%List<Announcement> announcements = (List<Announcement>) request.getAttribute("announcementsData");%>
<%List<User> users = (List<User>) request.getAttribute("usersData");%>

<div class="table_container">
    <table>
        <tr class="fixed_header">
            <th>Author</th>
            <th class="table_width_limit_column">Content</th>
            <th>Date</th>
        </tr>
        <%for(Announcement a : announcements){%>
        <tr>
            <td><a href="user?id=<%= a.getUserId() %>" class="link_to_user"><%=FrontHelpers.getUsernameById(a.getUserId(), users)%></a></td>
            <td class="table_width_limit_column"><%=a.getContent()%></td>
            <td><%=FrontHelpers.formatTimestamp(a.getCreateDate())%></td>
        </tr>
        <%}%>

    </table>
</div>




