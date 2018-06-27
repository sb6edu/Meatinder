<%-- 
    Document   : history
    Created on : 26.06.2018, 13:56:09
    Author     : 103098
--%>

<%@include file="WEB-INF/personalheader.jspf" %>
        <h1>History</h1>
        
        <table>
            <c:forEach items="${rezepte}" var="rezept">
    
            <tr>
                <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
            </tr> 
            </c:forEach>
        </table>



<%@include file="WEB-INF/footer.jspf" %>