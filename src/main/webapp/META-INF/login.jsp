<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Page de connexion</h1>
        <form action="/webdistributeur/LoginServlet" method="POST">
            Email: <input type="text" name="email"><br>  
            Mot de passe: <input type="password" name="motPasse"><br>  
            <input type="submit" value="login">
        </form>
        <c:if test="${error != null}">
            <p style="color:red"><c:out value="${error}" /></p>
        </c:if>

    </body>
</html>
