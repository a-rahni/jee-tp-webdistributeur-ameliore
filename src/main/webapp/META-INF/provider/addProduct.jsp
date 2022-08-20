<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Ajouter un produit</h1>
        <form action="/webdistributeur/provider/AddProductServlet" method="POST">  
            Nom: <input type="text" name="name"><br>
            Quantité: <input type="number" name="quantity"><br>
            Prix: <input type="number" name="price"><br>
            <input type="submit" value="Ajouter">
        </form>
        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>
    </body>
</html>
