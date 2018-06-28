<%@include file="WEB-INF/personalheader.jspf" %>
<table><c:forEach items="${profiluser}" var="profiluser">
        <td><h2>Username: ${profiluser.username}</h2></td>
    </c:forEach></table>
<table>
    <tr><h3>Suchergebnisse für Rezepte:</h3></tr>
    <c:forEach items="${profilrezepte}" var="profilrezept">
    <tr>
        <td><a href="rezeptfinden.do?rn=${profilrezept.rezeptname}" id="rezeptname">${profilrezept.rezeptname}</a></td>
    </tr> 
</c:forEach>
</table>
<table>
    <tr><h3>Suchergebnisse für Benutzer</h3></tr>
    <c:forEach items="${profiluser}" var="user">
    <tr>
        <td><a href="ctrluserprofil.do?username=${user.username}" id="rezeptname">${user.username}</a></td>
    </tr> 
</c:forEach>
</table>
<%@include file="WEB-INF/footer.jspf" %>