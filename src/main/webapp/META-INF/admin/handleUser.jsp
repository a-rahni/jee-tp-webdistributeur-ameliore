<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Gestion des utilisateurs</h1>
        <table>
            <caption>Liste des produits</caption>
            <tr>
                <th>Numéro d'utilisateur</th>
                <th>email</th>
                <th>role</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <c:set var="u" value ="${user}" />

                <tr>
                    <td><c:out value="${u.getId()}"/></td>
                    <td><c:out value="${u.getEmail()}"/></td>
                    <td><c:out value="${u.getRole().toString()}"/></td>
                </tr>
            </c:forEach>
        </table>

        <br />
        <hr />

        <form action="/webdistributeur/admin/UpdateUserServlet" method="GET">
            <h3>Modifier l'utilisateur</h3>
            <label>Numero de utilisateur: </label>
            <input type="number" name="id" />
            <input type="submit" name="modify" value="Modifier" />
            <input type="submit" name="delete" value="Supprimer" />
        </form>

        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>

        <br />
        <hr />
        <a href="/webdistributeur/admin/AddUserServlet">Créer un utilisateur</a>

    </body>
</html>
