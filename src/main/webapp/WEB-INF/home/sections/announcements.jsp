<%@ page import="entities.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Duration" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.ZoneId" %>

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


    public static String formatTimestamp(Timestamp timestamp) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timestampDateTime = convertToLocalDateTime(timestamp);

        System.out.println(now);
        System.out.println(timestampDateTime);

        Duration duration = Duration.between(timestampDateTime, now);

        if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " minutes ago";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            return hours + " hours and " + minutes + " minutes ago";
        } else if (duration.toDays() < 10) {
            return duration.toDays() + " days ago";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return timestampDateTime.format(formatter);
        }
    }

    private static LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
            <td><%=getUsernameById(a.getUserId(), users)%></td>
            <td class="table_middle_column"><%=a.getContent()%></td>
            <td><%=formatTimestamp(a.getCreateDate())%></td>
        </tr>
        <%}%>

    </table>
</div>




