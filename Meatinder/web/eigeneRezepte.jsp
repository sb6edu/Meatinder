<%@include file="WEB-INF/personalheader.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Erstellen von eigenen Rezepten</h1>
            <h3>Kreuzen Sie die Zutaten an, die Sie benötigen.</h3>
            
            <form method="post" action="CtrlEinfuegen.do">
        <table>
            <% int i = 0; %> 
            <tr>
            <c:forEach items="${artikels}" var="artikel">
                <% if ((i++ % 5) != 0) { %>
                <label>
                    <td><input type="checkbox" name="${artikel.artname}" value="${artikel.artname}" id="${artikel.artname}">${artikel.artname}</td>
                </label>
                <%  } else {%>
                </tr>
                <label>
                    <tr>
                    <td><input type="checkbox" name="${artikel.artname}" value="${artikel.artname}" id="${artikel.artname}">${artikel.artname}</td>
                </label>
                <%  } %>  
        </c:forEach>
        </tr>
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
                <input type="text" size="100" maxlength="1000" name="rezeptbeschreibung" id="rezeptbeschreibung"/> 
            <h3>Geben sie die Dauer ein</h3>
                <input type="text" size="100" name="zubereitungszeit" id ="zubereitungszeit"/>
            <br />
                <input type="submit" value="Rezept einfügen!" />
            </form>
<%@include file="WEB-INF/footer.jspf" %>
 
