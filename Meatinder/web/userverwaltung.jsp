<%@include file="WEB-INF/personalheader.jspf" %>
<h1>Userverwaltung</h1>
<table>
    <th>Username</th>
    <th>Berechtigung</th>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.rechte}</td>
            <td><a href="ctrluserloeschen.do?username=${user.username}">Löschen</a></td>
            <td style="color:white">${user.bearbeiten}${user.username}${user.a}${user.rechte}${user.b}</td>
        </tr> 
    </c:forEach>
</table>
<a href="useradden.jsp">Neuen Benutzer hinzufügen</a>
<%@include file="WEB-INF/footer.jspf" %>

