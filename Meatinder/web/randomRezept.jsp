<%-- 
    Document   : randomRezept
    Created on : 26.06.2018, 08:21:01
    Author     : 103098
--%>


<%@include file="WEB-INF/anonymusheader.jspf" %>

<h3>Zufallsrezept</h3>
<table>
<c:forEach items="${zufallsrezept}" var="rezept">
    
            <tr>
                <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
            </tr> 
</c:forEach>
</table>
Hallo ${cookie.User.value}


<%@include file="WEB-INF/footer.jspf" %>
