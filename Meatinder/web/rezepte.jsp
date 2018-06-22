<%-- 
    Document   : rezepte
    Created on : 21.06.2018, 12:46:28
    Author     : 103098
--%>

<%@include file="WEB-INF/header.jspf" %>
<c:forEach items="${rezepte}" var="rezept">
        <table>
            <tr>
                <td>${rezept.rezeptname}</a></td>
            </tr> 
        </table>
</c:forEach>
    </body>
</html>
