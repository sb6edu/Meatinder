<%@include file="WEB-INF/header.jspf" %>
<h2>Login</h2>
<form method="post" action="ctrllogin.do">
    <fieldset>
                <legend>Welcome</legend>
                <table>
                    <tr>
                        <td><label for="username">Username:</label></td>
                        <td><input type="text" name="username" id="username"></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password::</label></td>
                        <td><input type="password" name="password" id="password"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login" /></td>
                    </tr>

                </table>
            </fieldset>
                            <a href="ctrlselect.do/">Continue as Guest</a>
</form>
<%@include file="WEB-INF/footer.jspf" %>
