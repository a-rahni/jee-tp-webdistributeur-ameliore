<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Créer un utilisateur</h1>
        <form action="/webdistributeur/admin/AddUserServlet" method="POST">  
            Email: <input type="text" name="email"><br>
            Mot de passe: <input type="text" name="password"><br>
            Roles:
            <select name="role">
                <c:forEach var="r" items="${applicationScope.roles}">
                    <option value="${r.toString()}">${r.toString()}</option>
                </c:forEach>
            </select>
            <br />
            <input type="submit" value="Créer">
        </form>
        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>

    </body>
</html>
