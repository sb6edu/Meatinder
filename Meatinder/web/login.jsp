<%@include file="WEB-INF/anonymusheader.jspf" %>
<h2>Login</h2>
<form method="post" action="ctrllogin.do">
    <fieldset>
                <legend>Wilkommen</legend>
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
