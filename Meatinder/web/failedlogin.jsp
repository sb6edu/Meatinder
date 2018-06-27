<%@include file="WEB-INF/personalheader.jspf" %>

<form method="post" action="ctrllogin.do">
    <fieldset>

        <h2>Wilkommen</h2>
        <h3 style="color:rgb(255,0,0)">Falsches Passwort du Depp!</h3>
        <table>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><input type="text" name="uname" id="uname"></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" name="psw" id="psw"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Login" /></td>
            </tr>

        </table>
    </fieldset>

</form>
<a href="ctrlselect.do">Weiter als Gast</a>
<a href="registrierung.jsp">Registrieren</a>
<%@include file="WEB-INF/footer.jspf" %>