<%@include file="WEB-INF/header.jspf" %>

        <h1>Rezeptefinder</h1>
            <h3>Kreuzen Sie die Zutaten an, die Sie besitzen.</h3>
            
            <form method="post" action="ctrlfinder.do">
                <fieldset>
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
        </fieldset>  
            <br />
            <h3>Kreuzen Sie Ihre vorhandenen Küchengeräte an.</h3>
            <fieldset>
        <table>  
                <c:forEach items="${geraete}" var="geraet">
                    
                    <label>
                        
                            <td><input type="checkbox" name="${geraet.geraetebezeichnung}" value="${geraet.geraetebezeichnung}" id="${geraet.geraetebezeichnung}">${geraet.geraetebezeichnung}</td>
                        
                    </label>
                </c:forEach>  
                       
        </table>  
            <br />
            <input type="submit" value="Rezepte finden!" />
            </fieldset>
            </form>
            
    </body>
</html>
