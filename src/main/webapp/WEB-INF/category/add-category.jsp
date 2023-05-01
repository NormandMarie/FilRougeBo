<%--
  Created by IntelliJ IDEA.
  User: Abdallah
  Date: 01/05/2023
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add-category</title>
</head>
<body>
    <form action=${pageContext.request.contextPath}/secured/add-category" method="post">
        <p>Category:</p>
        <input name="name" type="text" placeholder="category name..">
        <button type="submit">ADD</button>
    </form>
</body>
</html>
