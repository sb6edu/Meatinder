
<%@include file="WEB-INF/personalheader.jspf" %>
<table>
<c:forEach items="${rezeptes}" var="rezept">
    <table>    
        <tr><h2>${rezept.rezeptname}</h2></tr>
            <h3>Ben�tigte Zutaten und Ger�te: </h3>
            <c:forEach items="${zutaten}" var="zutat" >
                <tr>
               <td>${zutat.artname}, ${zutat.menge} ${zutat.einheit}</td>
                </tr>
            </c:forEach> 
            <tr>
                <td>${rezept.geraetebezeichnung}</td>
            </tr>
    </table>
            <table>
            <tr><h3>Zubereitungszeit:</h3></tr>
            <tr>
                <td>${rezept.zubereitungszeit}</td>
            </tr>
            </table>
    <table>
            <tr><h3>Rezeptzubereitung: </h3></tr>
            <tr>
                <td>${rezept.rezeptbeschreibung}</td>
            </tr>
            
            <% if(CtrlLogin.eingeloggt(request, response, getServletContext())){%>
            <tr><td><a href="ctrlfavoriten.do?id=${rezept.id}">Zu Favoriten hinzuf�gen</a></td></tr>
            <% } %>
    </table>
</c:forEach>
    
</table>
<%@include file="WEB-INF/footer.jspf" %>
