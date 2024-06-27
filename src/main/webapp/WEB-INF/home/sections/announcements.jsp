<%@ page import="entities.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>

<style><%@include file="./styles/announcements.css"%></style>

<%List<Announcement> announcements = (List<Announcement>) request.getAttribute("announcementsData");%>
<%List<User> users = (List<User>) request.getAttribute("userData");%>

<%!
    public String getUsernameById(long userId, List<User> userList) {

        for (User user : userList) {
            if (user.getId() == userId) {
                return user.getUsername();
            }
        }
        return "User not found";
    }
%>

<div class="table_container">
    <table>
        <tr class="fixed_header">
            <th>Author</th>
            <th class="table_middle_column">Content</th>
            <th>Date</th>
        </tr>
        <%for(Announcement a : announcements){%>
        <tr>
            <td><a href="user?id=<%= a.getUserId() %>" class="link_to_user"><%=getUsernameById(a.getUserId(), users)%></a></td>
            <td class="table_middle_column"><%=a.getContent()%></td>
            <td><%=a.getCreateDate()%></td>
        </tr>
        <%}%>

    </table>
</div>




