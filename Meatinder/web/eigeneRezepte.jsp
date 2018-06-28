<%@include file="WEB-INF/personalheader.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Erstellen von eigenen Rezepten</h1>

<form method="post" action="artikelhinzufuegen.do">
    <input type="text" size="10" name="artikel" id="artikel"  placeholder="Neue Zutat" />
    <input type="submit" value="Neue Zutat" />
</form>    
<form method="post" action="ctrleinfuegen.do">
    <h3>Geben Sie den Rezeptnamen ein</h3>
    <input type="text" size="100" maxlength="100" name="rezeptname" id="rezeptname"/> 
    <h3>Kreuzen Sie die Zutaten an, die Sie benötigen.</h3>
    <table>
        <% int i = 0; %> 
        <tr>
            <c:forEach items="${artikels}" var="artikel">
                <% if ((i++ % 5) != 0) { %>
            <label>
                <td><input type="checkbox" name="${artikel.artname}" value="${artikel.artname}" id="${artikel.artname}">${artikel.artname}</td>
                <td><input type="text" size="5" name="${artikel.artname}-Menge"  id="${artikel.artname}-Menge" placeholder="Menge"></td>
                <td><input type="text" size="5" name="${artikel.artname}-Einheit"  id="${artikel.artname}-Einheit" placeholder="Einheit"></td>
            </label>
            <%  } else {%>
            </tr>
            <label>
                <tr>
                    <td><input type="checkbox" name="${artikel.artname}" value="${artikel.artname}" id="${artikel.artname}">${artikel.artname}</td>
                    <td><input type="text" size="5" name="${artikel.artname}-Menge"  id="${artikel.artname}-Menge" placeholder="Menge"></td>
                    <td><input type="text" size="5" name="${artikel.artname}-Einheit"  id="${artikel.artname}-Einheit" placeholder="Einheit"></td>
                </tr>
            </label>
            <%  }%>  
        </c:forEach>
    </table> 
    <br />
    <h3>Kreuzen Sie die Küchengeräte an, welche benötigt werden</h3>
    <table>  
        <c:forEach items="${geraetes}" var="geraet">
            <label>
                <td><input type="checkbox" name="${geraet.geraetebezeichnung}" value="${geraet.geraetebezeichnung}" id="${geraet.geraetebezeichnung}">${geraet.geraetebezeichnung}</td>
            </label>
        </c:forEach>  

    </table>  
    <br />

    <h3>Geben sie die Zubereitungsbeschreibung ein</h3>
    <input type="text" size="100" maxlength="1500" name="rezeptbeschreibung" id="rezeptbeschreibung"/> 
    <h3>Geben sie die Dauer ein</h3>
    <input type="text" size="100" name="zubereitungszeit" id ="zubereitungszeit"/>
    <br />
    <input type="submit" value="Rezept einfügen!" />
</form>
<%@include file="WEB-INF/footer.jspf" %>