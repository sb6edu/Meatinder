<%-- 
    Document   : rezepte
    Created on : 21.06.2018, 12:46:28
    Author     : 103098
--%>

<%@include file="WEB-INF/header.jspf" %>

<c:forEach items="${rezepte}" var="rezept">
        <table>
            <tr>
                <td>${rezept.rezeptname}</td>
                <td>${rezept.artid}</td>
                
            </tr> 
        </table>
</c:forEach>

<c:forEach items="${zinventar}" var="artikel">
        <table>
            <tr>
                <td>${artikel.artid}</td>
            </tr> 
        </table>
</c:forEach>


<%@include file="WEB-INF/footer.jspf" %>
