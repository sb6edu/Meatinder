<%@include file="WEB-INF/personalheader.jspf" %>
<h1>Userverwaltung</h1>
<table>
    <th>Username</th>
    <th>Berechtigung</th>
    <th></th>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.rechte}</td>
            <td><form method="post" action="ctrluserverwaltung.do"><input type="submit" value="Löschen" name="${user.username}loeschen"/></form></td>
        </tr> 
    </c:forEach>
</table>
<%@include file="WEB-INF/footer.jspf" %>

