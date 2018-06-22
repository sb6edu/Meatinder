<%-- 
    Document   : rezeptSeite
    Created on : 21.06.2018, 13:31:07
    Author     : 103098
--%>
<%@include file="WEB-INF/header.jspf" %>
<c:forEach items="${rezepte}" var="rezept">
    <h1>${rezept.rezeptname}</h1>
    
    
</c:forEach>
<%@include file="WEB-INF/footer.jspf" %>