<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 26/04/2023
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ello</title>
</head>
<body>
<h1>Holà!</h1>

<c:if test="${sessionScope.isSuperAdmin}">
<a href="${pageContext.request.contextPath}/secured/logout">Déconnexion</a>
<% } else { %>
<a href="${pageContext.request.contextPath}/login">connexion</a>
<% } %>
<c:if test="${sessionScope.isSuperAdmin}">
    <button>Mon bouton superAdmin</button>
</c:if>

</body>
</html>
