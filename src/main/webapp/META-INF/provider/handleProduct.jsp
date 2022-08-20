<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Gestion des produits</h1>
        <table>
            <caption>Liste des produits</caption>
            <tr>
                <th>Numéro de produit</th>
                <th>Nom</th>
                <th>Quantité</th>
                <th>Prix</th>
            </tr>
            <c:forEach var="product" items="${stock}">
                <c:set var="p" value ="${product}" />

                <tr>
                    <td><c:out value="${p.getId()}"/></td>
                    <td><c:out value="${p.getName()}"/></td>
                    <td><c:out value="${p.getQuantity()}"/></td>
                    <td><c:out value="${p.getPrice()}"/></td>
                </tr>
            </c:forEach>
        </table>

        <br />
        <hr />

        <form action="/webdistributeur/provider/UpdateProductServlet" method="GET">
            <h3>Modifier le produit</h3>
            <label>Numero de produit: </label>
            <input type="number" name="id" />
            <input type="submit" name="modify" value="Modifier" />
            <input type="submit" name="delete" value="Supprimer" />
        </form>

        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>

        <br />
        <hr />
        <a href="/webdistributeur/provider/AddProductServlet">ajouter un produit</a>

    </body>
</html>
