<%@include file="WEB-INF/personalheader.jspf" %>
<table><c:forEach items="${profiluser}" var="profiluser">
            <td><h2>Username: ${profiluser.username}</h2></td>
            </c:forEach></table>
<table>
    <th>Rezepte, die dieser User erstellt hat:</th>
<c:forEach items="${rezepte}" var="profilrezept">
            <tr>
                <td><a href="rezeptfinden.do?rn=${profilrezept.rezeptname}" id="rezeptname">${profilrezept.rezeptname}</a></td>
            </tr> 
</c:forEach>
</table>
<%@include file="WEB-INF/footer.jspf" %>