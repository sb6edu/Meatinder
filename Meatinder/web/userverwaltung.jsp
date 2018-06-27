<%@include file="WEB-INF/personalheader.jspf" %>
<h1>Userverwaltung</h1>
<table>
    <th>Username</th>
    <th>Berechtigung</th>
<c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.rechte}</td>
            </tr> 
</c:forEach>
</table>
<%@include file="WEB-INF/footer.jspf" %>

