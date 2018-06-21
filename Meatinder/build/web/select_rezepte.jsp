<%@include file="WEB-INF/header.jspf" %>

        <h1>Rezeptefinder</h1>
            <h3>Kreuzen Sie die Zutaten an, die Sie besitzen.</h3>
        <table>
            
            <fieldset> 
            
                <c:forEach items="${artikels}" var="artikel">
                    <label>
                        <tr>
                            <td><input type="checkbox" name="zutat" value="${artikel.artname}">${artikel.artname}</td>
                        </tr>
                    </label>
                </c:forEach>
            </fieldset>
        </table>
        <table>
                
        </table>    
    </body>
</html>
