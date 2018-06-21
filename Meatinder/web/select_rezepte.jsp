<%@include file="WEB-INF/header.jspf" %>

        <h1>Rezeptefinder</h1>
            <h2>Kreuzen Sie die Zutaten an, die Sie besitzen.</h2>
        <table>
            
             
            
            <c:forEach items="${artikels}" var="artikel">
                
                <td>${artikel.artname}</td>
                
            </c:forEach>
            
        </table>
    </body>
</html>
