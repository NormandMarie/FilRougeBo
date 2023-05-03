<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 01/05/2023
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Admin</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">home</a>
<form action="${pageContext.request.contextPath}/SuperAdmin/add-admin" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="hidden" name="superAdmin" value="false">
    <button type="submit">creation </button>
</form>
</body>
</html>
