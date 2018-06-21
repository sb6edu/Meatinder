<%@include file="WEB-INF/header.jspf" %>

        <h1>Rezeptefinder</h1>
            <h3>Kreuzen Sie die Zutaten an, die Sie besitzen.</h3>
        <table>
            <% int i = 0; %> 
            <tr>
            <c:forEach items="${artikels}" var="artikel">
                <% if (i < 5) { %>
                <label>
                    <td><input type="checkbox" name="zutat" value="${artikel.artname}">${artikel.artname}</td>
                </label>
                <% i++; } else {%>
                </tr>
                <label>

                    <tr>
                    <td><input type="checkbox" name="zutat" value="${artikel.artname}">${artikel.artname}</td>

                </label>
                     <% i = 1; } %>  
        </c:forEach>
        </table>
            <br />
            <br />
            <h3>Kreuzen Sie Ihre vorhandenen Küchengeräte an.</h3>
        <table>  
                <c:forEach items="${geraete}" var="geraet">
                    
                    <label>
                        
                            <td><input type="checkbox" name="kuechengeraet" value="${geraet.geraetebezeichnung}">${geraet.geraetebezeichnung}</td>
                        
                    </label>
                </c:forEach>  
        </table>    
    </body>
</html>
