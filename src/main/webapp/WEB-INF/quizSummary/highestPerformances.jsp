<%@ page import="DTOs.QuizHistoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.FrontHelpers" %>


<div class="performance_container">
    <p class="performance_table_header itim-regular">Highest Performances Of All Time</p>
    <%List<QuizHistoryDTO> highestPerformances = (List<QuizHistoryDTO>)request.getAttribute("highestPerformances");%>

    <%if(highestPerformances.isEmpty()){%>
        <p class="itim-regular no_data_p">No Data</p>
    <%} else {%>
        <table class="performance_table">
            <tr>
                <th class="itim-regular">Username</th>
                <th class="itim-regular">Score</th>
                <th class="itim-regular">Time</th>
            </tr>
            <%for(QuizHistoryDTO p : highestPerformances){%>
            <tr>
                <td class="itim-regular"><%=p.getUserName()%></td>
                <td class="itim-regular"><%=p.getScore()%></td>
                <td class="itim-regular"><%=FrontHelpers.formatSeconds(p.getTime())%></td>
            </tr>
            <%}%>
        </table>
    <%}%>
</div>