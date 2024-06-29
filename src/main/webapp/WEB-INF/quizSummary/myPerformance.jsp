<%@ page import="DTOs.QuizHistoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.FrontHelpers" %>

<style><%@include file="styles/sharedTable.css"%></style>

<div class="performance_container">
    <p class="performance_table_header itim-regular">My Past Performance</p>
    <%List<QuizHistoryDTO> myPerformance = (List<QuizHistoryDTO>)request.getAttribute("myPerformance");%>

    <% if(myPerformance.isEmpty()){%>
        <p class="itim-regular no_data_p">No Data</p>
    <%} else {%>
        <table class="performance_table">
            <tr>
                <th class="itim-regular">Date</th>
                <th class="itim-regular">Score</th>
                <th class="itim-regular">Time</th>
            </tr>
            <%for(QuizHistoryDTO p : myPerformance){%>
            <tr>
                <td class="itim-regular"><%=FrontHelpers.formatDate(p.getWriteDate())%></td>
                <td class="itim-regular"><%=p.getScore()%></td>
                <td class="itim-regular"><%=FrontHelpers.formatSeconds(p.getTime())%></td>
            </tr>
            <%}%>
        </table>
    <%}%>
</div>