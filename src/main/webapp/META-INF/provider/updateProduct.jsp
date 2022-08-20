<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Modifier un produit</h1>
        <form action="/webdistributeur/provider/UpdateProductServlet" method="POST">  
            Numéro de produit: <input type="text" name="id" value="${item.getId()}" readonly ><br>
            Nom: <input type="text" name="name" value="${item.getName()}"><br>
            Quantité: <input type="number" name="quantity" value="${item.getQuantity()}"><br>
            Prix: <input type="number" name="price" value="${item.getPrice()}"><br>
            <input type="submit" value="Valider">
        </form>
        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>

    </body>
</html>
