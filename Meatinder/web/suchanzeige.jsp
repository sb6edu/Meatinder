<%@include file="WEB-INF/personalheader.jspf" %>
<p>User- und rezeptname m�ssen in Gro�- und Kleinschreibung mit den gew�nschten Suchergebnissen �bereinstimmen.</p>
<p>Rechtschreibfehler f�hren leider auch zur Nichtanzeige.</p>
<!--<h2>Suchergebnisse:</h2>
<table><c:forEach items="${profiluser}" var="profiluser">
        <td><h2>Username: ${profiluser.username}</h2></td>
    </c:forEach></table>-->
<table>
    <tr><h3>Suchergebnisse f�r Rezepte:</h3></tr>
    <c:forEach items="${profilrezepte}" var="profilrezept">
    <tr>
        <td><a href="rezeptfinden.do?rn=${profilrezept.rezeptname}" id="rezeptname">${profilrezept.rezeptname}</a></td>
    </tr> 
</c:forEach>
</table>
<table>
    <tr><h3>Suchergebnisse f�r Benutzer</h3></tr>
    <c:forEach items="${profiluser}" var="profiluser">
    <tr>
        <td><a href="ctrluserprofil.do?username=${profiluser.username}" id="rezeptname">${profiluser.username}</a></td>
    </tr> 
</c:forEach>
</table>
<%@include file="WEB-INF/footer.jspf" %>