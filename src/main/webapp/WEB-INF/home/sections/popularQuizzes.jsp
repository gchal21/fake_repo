<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<%@ page import="entities.Quiz" %>
<%@ page import="entities.Category" %>
<%@ page import="utils.FrontHelpers" %>

<style><%@include file="./styles/quizShared.css"%></style>
<style><%@include file="styles/quizTable.css"%></style>

<%List<Quiz> popularQuizzes= (List<Quiz>) request.getAttribute("popularQuizzesData");%>
<%List<User> users = (List<User>) request.getAttribute("usersData");%>
<%List<Category> categories = (List<Category>) request.getAttribute("categoriesData");%>

<div class="table_container">
    <table>
        <tr class="fixed_header">
            <th>Title</th>
            <th class="table_width_limit_column">Description</th>
            <th>Created By</th>
            <th>Category</th>
            <th>Max Score</th>
            <th>Date</th>
        </tr>
        <%for(Quiz quiz: popularQuizzes){%>
        <tr>
            <td><%=quiz.getTitle()%></td>
            <td class="table_width_limit_column"><%=quiz.getDescription()%></td>
            <td><%=FrontHelpers.getUsernameById(quiz.getCreatorId(), users)%></td>
            <td><%=FrontHelpers.getCategoryById(quiz.getCategoryId(), categories)%></td>
            <td><%=quiz.getMaxScore()%></td>
            <td><%=FrontHelpers.formatLocalDateTime(quiz.getCreateDate())%></td>
        </tr>
        <%}%>

    </table>
</div>




