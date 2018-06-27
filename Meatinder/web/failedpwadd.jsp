<%@include file="WEB-INF/personalheader.jspf" %>
<h1>Neuen User erstellen</h1>
<form method="post" action="ctrladduser.do">
    <fieldset>
        <legend>Wilkommen</legend>
        <h3 style="color:rgb(255,0,0)">Passwörter stimmen nicht überein!</h3>
        <table>
            <tr>
                <td><label for="vorname">Vorname:</label></td>
                <td><input type="text" name="vorname" id="vorname"></td>
            </tr>
            <tr>
                <td><label for="nachname">Nachname:</label></td>
                <td><input type="text" name="nachname" id="nachname"></td>
            </tr>
            <tr>
                <td><label for="uname">Username:</label></td>
                <td><input type="text" name="uname" id="uname"></td>
            </tr>
            <tr>
                <td><label for="psw1">Passwort:</label></td>
                <td><input type="password" name="psw1" id="psw1"></td>
            </tr>
            <tr>
                <td><label for="psw2">Passwort erneut eingeben:</label></td>
                <td><input type="password" name="psw2" id="psw2"></td>
            </tr>
            <tr>
                <td><label for="email">Mailadresse:</label></td>
                <td><input type="email" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Welche Rechte soll der User haben?</td>
                <td><select name="rechte">
                        <option value="user">User</option>
                        <option value="admin">Admin</option>
                    </select></td>
            </tr>
            <tr>
                <td><input type="submit" value="User erstellen" /></td>
            </tr>
        </table>
    </fieldset>                        
</form>
<%@include file="WEB-INF/footer.jspf" %>