<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 26/04/2023
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FilRougeBO</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<h1>Holà!</h1>

<c:if test="${sessionScope.isSuperAdmin}">
<a href="${pageContext.request.contextPath}/secured/logout">Déconnexion</a>
    <a href="${pageContext.request.contextPath}/secured/ListAdmin">List Admins</a>
<% } else { %>
<a href="${pageContext.request.contextPath}/login">connexion</a>
<% } %>
<%--<c:if test="${sessionScope.isSuperAdmin}">--%>
<%--    <a href="${pageContext.request.contextPath}/secured/ListAdmin">List Admins</a>--%>
<%--</c:if>--%>

</body>
</html>
