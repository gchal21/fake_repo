<%@ page import="DTOs.ChallengeDTO" %>
<%@ page import="java.util.List" %>
<style><%@include file="./styles/challenges.css"%></style>

<%List<ChallengeDTO> challenges = (List<ChallengeDTO>)request.getAttribute("challenges");%>

<div class="challenges_container">
    <p class="challenges_header itim-regular">Challenges</p>

    <div class="challenges">
        <%for(ChallengeDTO c : challenges){%>
            <div class="challenge">
                <div class="challenge_data">
                    <p class="itim-regular">From: <%=c.getSenderName()%></p>
                    <p class="itim-regular">Quiz: <%=c.getQuizName()%></p>
                    <p class="itim-regular">I Scored: <%=c.getSenderMaxScore()%></p>
                </div>

                <div class="challenge_buttons">
                    <button class="button_green">Accept</button>
                    <button class="button_red">Reject</button>
                </div>
            </div>
        <%}%>
    </div>
</div>