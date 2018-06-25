
<%@include file="WEB-INF/header.jspf" %>
<table>
<c:forEach items="${rezeptes}" var="rezept">
    <table>    
        <tr><h2>${rezept.rezeptname}</h2></tr>
            <h3>Benötigte Zutaten und Geräte: </h3>
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
    </table>
</c:forEach>

</table>
<%@include file="WEB-INF/footer.jspf" %>
