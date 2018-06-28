<%-- 
    Document   : favoriten
    Created on : 28.06.2018, 10:04:15
    Author     : 103098
--%>

<%@include file="WEB-INF/personalheader.jspf" %>



<h2>Favoriten</h2>
<table>
<c:forEach items="${rezepte}" var="rezept">
    
            <tr>
                <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
                <td><a  href="ctrlfavoritendelete.do?id=${rezept.id}"><i class="fa fa-trash"></i></a></td>
                
            </tr> 
</c:forEach>
</table>

<%@include file="WEB-INF/footer.jspf" %>

