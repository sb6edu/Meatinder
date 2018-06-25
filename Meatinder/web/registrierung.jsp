<%@include file="WEB-INF/anonymusheader.jspf" %>
<h1>Registrierung</h1>
<form method="post" action="ctrlregister.do">
    <fieldset>
                <legend>Wilkommen</legend>
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
                        <td><input type="submit" value="Registrieren" /></td>
                    </tr>

                </table>
            </fieldset>
                            
</form>
<%@include file="WEB-INF/footer.jspf" %>