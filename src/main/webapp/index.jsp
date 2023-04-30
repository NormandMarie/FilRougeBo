<%@ page import="com.m2i.filrougebo.service.Authentication" %><%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 26/04/2023
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Holà!</h1>
<% if(request.getSession().getAttribute("username") != null) { %>
<% if (Authentication.isSuperAdmin((String) session.getAttribute("username"))) { %>
<a href="${pageContext.request.contextPath}/SuperAdmin/ListAdmin">List Admins</a>
<% } %>
<a href="${pageContext.request.contextPath}/secured/logout">Déconnexion</a>
<% } else { %>
<a href="${pageContext.request.contextPath}/login">connexion</a>
<% } %>


<%--<% if(request.getSession().getAttribute("username") != null) { %>--%>
<%--<a href="${pageContext.request.contextPath}/secured/logout">Déconnexion</a>--%>
<%--<% } else { %>--%>
<%--<a href="${pageContext.request.contextPath}/login">connexion</a>--%>
<%--<%--%>
<%--    if (session != null) {--%>
<%--        String username = (String) session.getAttribute("username");--%>
<%--        if (Authentication.isSuperAdmin(username)) {--%>
<%--%>--%>
<%--<button>Accéder à la page spéciale</button>--%>
<%--<%--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>
<%--<% } %>--%>
<%--<%--%>
<%--    if (session != null) {--%>
<%--        String username = (String) session.getAttribute("username");--%>
<%--        if (Authentication.isSuperAdmin(username)) {--%>
<%--%>--%>
<%--<button>Accéder à la page spéciale</button>--%>
<%--<%--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>
</body>
</html>