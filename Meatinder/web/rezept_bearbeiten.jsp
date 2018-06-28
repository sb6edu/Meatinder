<%@include file="WEB-INF/personalheader.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Bearbeiten von eigenem Rezept</h1>

<form method="post" action="artikelhinzufuegen.do">
    <tr><td><input type="text" size="10" name="artikel" id="artikel"  placeholder="Neue Zutat" /></td>
        <td><input type="submit" value="Neue Zutat" /></td>
    </tr>
</form>    
<form method="post" action="ctrlupdate.do">
    <input type="hidden" name="id" value="${rezept.id}" id="id"/>
    <h3>Geben Sie den Rezeptnamen ein</h3>
    <input type="text" size="100" maxlength="100" name="rezeptname" id="rezeptname" value="${rezept.rezeptname}"/> 
    <h3>Kreuzen Sie die Zutaten an, die Sie benötigen.</h3>
    <table>
        
        
            <c:forEach items="${artikels}" var="artikel">
         <tr>
            <label>
                
                <c:if test="${rezept.artid.contains(artikel.artid) == true}">
                    <td><input type="checkbox" name="${artikel.artname}" value="${artikel.artname}" id="${artikel.artname}" checked="checked">${artikel.artname}</td>
                    <td><input type="text" size="5" name="${artikel.artname}-Menge"  id="${artikel.artname}-Menge" value="${rezept.menge.get(rezept.artid.indexOf(artikel.artid))}"></td>
                    <td><input type="text" size="5" name="${artikel.artname}-Einheit"  id="${artikel.artname}-Einheit" value="${rezept.einheit.get(rezept.artid.indexOf(artikel.artid))}"></td>
                    
                    </c:if>
                    <c:if test="${rezept.artid.contains(artikel.artid) == false}">
                    <td><input type="checkbox" name="${artikel.artname}" value="${artikel.artname}" id="${artikel.artname}">${artikel.artname}</td>
                    <td><input type="text" size="5" name="${artikel.artname}-Menge"  id="${artikel.artname}-Menge" placeholder="Menge"></td>
                    <td><input type="text" size="5" name="${artikel.artname}-Einheit"  id="${artikel.artname}-Einheit" placeholder="Einheit"></td>
                    </c:if>
                
            </label>
           
           </tr>
        </c:forEach>
        
    </table> 
    <br />
    <h3>Kreuzen Sie die Küchengeräte an, welche benötigt werden</h3>
    <table>  
        <c:forEach items="${geraetes}" var="geraet">
            <label>
                <c:if test="${rezept.geraetebezeichnung.contains(geraet.geraetebezeichnung) == true}">
                <td><input type="checkbox" name="${geraet.geraetebezeichnung}" value="${geraet.geraetebezeichnung}" id="${geraet.geraetebezeichnung}" checked="checked">${geraet.geraetebezeichnung}</td>
                </c:if>
                <c:if test="${rezept.geraetebezeichnung.contains(geraet.geraetebezeichnung) == false}">
                <td><input type="checkbox" name="${geraet.geraetebezeichnung}" value="${geraet.geraetebezeichnung}" id="${geraet.geraetebezeichnung}">${geraet.geraetebezeichnung}</td>
                </c:if>
            </label>
        </c:forEach>  

    </table>  
    <br />

    <h3>Geben sie die Zubereitungsbeschreibung ein</h3>
    <input type="text" size="100" maxlength="1500" name="rezeptbeschreibung" id="rezeptbeschreibung" value="${rezept.rezeptbeschreibung}"/> 
    <h3>Geben sie die Dauer ein</h3>
    <input type="text" size="100" name="zubereitungszeit" id ="zubereitungszeit" value="${rezept.zubereitungszeit}"/>
    <br />
    <input type="submit" value="Rezept bearbeiten!" />
</form>
<%@include file="WEB-INF/footer.jspf" %>