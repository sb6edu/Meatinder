<%@include file="WEB-INF/personalheader.jspf" %>
<h1>User Bearbeiten</h1>
<form method="post" action="ctrluserbearbeiten.do">
    <td>Welche Rechte soll der User haben?</td>
    <select name="rechte">
    <option value="user">User</option>
    <option value="admin">Admin</option>
        </select>
  <br><br>
  <input type="submit" value="�nderung speichern">
  <br />
  <a href="ctrluserverwaltung.do">Zur�ck zur Userverwaltung</a>
</form>
<%@include file="WEB-INF/footer.jspf" %>