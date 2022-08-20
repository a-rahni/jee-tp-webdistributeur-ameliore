<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Modification d'un utilisateur</h1>
        <form action="/webdistributeur/admin/UpdateUserServlet" method="POST">  
            Numéro d'utilisateur: <input type="text" name="id" value="${u.getId()}" readonly ><br>
            Email: <input type="text" name="email" value="${u.getEmail()}"><br>
            Mot de passe: <input type="text" name="password" value="${u.getPassword()}"><br>
            Roles:
            <select name="role">
                <c:forEach var="r" items="${applicationScope.roles}">
                    <option value="${r.toString()}" ${u.getRole().equals(r) ? selected : ''}>${r.toString()}</option>
                </c:forEach>
            </select>
            <br />
            <input type="submit" value="Modifier">
        </form>
        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>

    </body>
</html>
