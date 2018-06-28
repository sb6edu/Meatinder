<%-- 
    Document   : alleRezepte
    Created on : 26.06.2018, 07:41:06
    Author     : 103098
--%>
<%@include file="WEB-INF/personalheader.jspf" %>



<h2>Rezept finden!</h2>
<table >
<c:forEach items="${rezepte}" var="rezept">
    
            <tr>
                <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
            
            </tr> 
            
</c:forEach>
            <hr />
</table>
<%@include file="WEB-INF/footer.jspf" %>
