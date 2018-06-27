<%-- 
    Document   : erstellteRezepte
    Created on : 27.06.2018, 08:01:24
    Author     : 103098
--%>
<%@include file="WEB-INF/personalheader.jspf" %>
<h2>Ihre eingefügten Rezepte: </h2>
<table>
<c:forEach items="${rezepte}" var="rezept">
        <tr>
            <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
        </tr> 
</c:forEach>
</table>
    </body>
</html>
