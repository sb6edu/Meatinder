<%-- 
    Document   : rezepte
    Created on : 21.06.2018, 12:46:28
    Author     : 103098
--%>

<%@include file="WEB-INF/header.jspf" %>

<table>
<c:forEach items="${verfuegbareRezepte}" var="rezept">
            <tr>
                <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
            </tr> 
</c:forEach>
</table>


<%@include file="WEB-INF/footer.jspf" %>
