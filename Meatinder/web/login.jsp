<%@include file="WEB-INF/header.jspf" %>
<h2>Login</h2>
<form method="post" action="ctrllogin.do">
    <fieldset>
                <legend>Welcome</legend>
                <table>
                    <tr>
                        <td><label for="username">Username:</label></td>
                        <td><input type="text" name="uname" id="uname"></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password::</label></td>
                        <td><input type="password" name="psw" id="psw"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login" /></td>
                    </tr>

                </table>
            </fieldset>
                            
</form>
<a href="ctrlselect.do">Continue as Guest</a>
<%@include file="WEB-INF/footer.jspf" %>
