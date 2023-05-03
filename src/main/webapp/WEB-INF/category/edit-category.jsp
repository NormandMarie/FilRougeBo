<%--
  Created by IntelliJ IDEA.
  User: Abdallah
  Date: 01/05/2023
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit-category</title>
</head>
<body>
<form action="/secured/edit-category" method="post">
    <input name="name" type="text" placeholder="type new name..">
    <button name="id" type="submit" value="${toUpdateCategory.idCategory}">save</button>
</form>
</body>
</html>
