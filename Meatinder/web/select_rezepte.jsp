<%@include file="WEB-INF/header.jspf" %>

        <h1>Rezeptefinder</h1>
            <h2>Kreuzen Sie die Zutaten an, die Sie besitzen.</h2>
        <table>
               <c:forEach items="${artikels}" var="artikel">
                <tr>
                    <td>${artikel.artname}</td>
                    <td>Hallo</td>
                </tr>
                </c:forEach>
            
        </table>
    </body>
</html>
