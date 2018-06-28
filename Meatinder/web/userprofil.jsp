<%@include file="WEB-INF/personalheader.jspf" %>
<h2>${user.username}</h2>
<table>
<c:forEach items="${rezepte}" var="rezept">
            <tr>
                <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
            </tr> 
</c:forEach>
</table>
<%@include file="WEB-INF/footer.jspf" %>