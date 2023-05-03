<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 28/04/2023
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<html>
<head>
    <title>List Admins</title>
</head>
<body>
<c:forEach items="${AdminDtos}" var="AdminDto">
    <div class="card" style="width: 18rem;">
                <p style="margin-bottom: 0.5em;"><strong>${AdminDto.username} </strong></p>
                <p>${AdminDto.idAdmin}</p>
        <a href="delete-admin?id=${AdminDto.idAdmin}" class="btn btn-danger mb-2">delete</a>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
