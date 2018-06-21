<%@include file="WEB-INF/header.jspf" %>

        <h1>Rezeptefinder</h1>
            <h3>Kreuzen Sie die Zutaten an, die Sie besitzen.</h3>
        <table>
            

            
            
                <c:forEach items="${artikels}" var="artikel">
                    <label>
                        <tr>
                            <td><input type="checkbox" name="zutat" value="${artikel.artname}">${artikel.artname}</td>
                        </tr>
                    </label>
                </c:forEach>

          
        </table>
            <br />
            <br />
        <table>
              <c:forEach items="${geraete}" var="geraet">
                    <label>
                        
                            <td><input type="checkbox" name="kuechengeraet" value="${geraet.geraetebezeichnung}">${geraet.geraetebezeichnung}</td>
                        
                    </label>
                </c:forEach>  
        </table>    
    </body>
</html>
