<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 28/04/2023
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Admins</title>
</head>
<body>
<c:forEach items="${AdminDtos}" var="AdminDto">
    <div class="card" style="width: 18rem;">
                <p style="margin-bottom: 0.5em;"><strong>${AdminDto.username} </strong></p>
                <p>${AdminDto.idAdmin}</p>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
