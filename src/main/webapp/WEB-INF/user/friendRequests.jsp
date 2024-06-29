<%@ page import="entities.Friendship" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<style><%@include file="./styles/friendRequests.css"%></style>

<div class="friend_requests_container">
    <p class="itim-regular friend_requests_header">Friend Requests</p>

    <div class="friend_requests_table">
        <%List<Friendship> requests = (List<Friendship>) request.getAttribute("friendRequests");%>

        <%for(Friendship f : requests){%>
            <div class="request">
                <p class="itim-regular request_author"><%=f.getSenderUsername()%></p>

                <div class="request_buttons">
                    <button class="itim-regular button_green">Accept</button>
                    <button class="itim-regular button_red">Reject</button>
                </div>
            </div>
        <%}%>
    </div>
</div>