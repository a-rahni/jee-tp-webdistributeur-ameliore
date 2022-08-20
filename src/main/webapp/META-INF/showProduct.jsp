<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
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
        <hr />
        <a href="/webdistributeur/customer/BuyProductServlet">Acheter un produit</a>
    </body>
</html>
