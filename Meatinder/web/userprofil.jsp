<%@include file="WEB-INF/personalheader.jspf" %>
<table><c:forEach items="${profiluser}" var="profiluser">
        <td><h2>Username: ${profiluser.username}</h2></td>
    </c:forEach></table>
<table>
    <tr><h3>Rezepte, die dieser User erstellt hat:</h3></tr>
        <c:forEach items="${profilrezepte}" var="profilrezept">
        <tr>
            <td><a href="rezeptfinden.do?rn=${profilrezept.rezeptname}" id="rezeptname">${profilrezept.rezeptname}</a></td>
        </tr> 
    </c:forEach>
</table>
<table>
    <tr><h3>Favoriten dieses Users</h3></tr>
        <c:forEach items="${rezepte}" var="rezept">
        <tr>
            <td><a href="rezeptfinden.do?rn=${rezept.rezeptname}" id="rezeptname">${rezept.rezeptname}</a></td>
        </tr> 
    </c:forEach>   
</table>
<!--
<h1>Kommentar</h1>    
        <form method="post" action="controllerinsert.do">
            <fieldset>
                <legend>Neuer Kommentar</legend>
                <table>
                    <tr>
                        <td><label for="ersteller">Dein Name:</label></td>
                        <td><input placeholder="Dein Name:" type="text" name="ersteller" id="ersteller"></td>
                    </tr>
                    <tr>
                        <td><label for="text">Dein Kommentar:</td>
                        <td><textarea placeholder="Blablabla..." cols="20" rows="10" name="text" id="text"></textarea></td>
                    </tr>
                    <tr colspan="2">
                        <td><input type="submit" value="Speichern" /></td>
                    </tr>
                </table>
            </fieldset>
        </form>-->
<%@include file="WEB-INF/footer.jspf" %>