<%--
  Created by IntelliJ IDEA.
  User: Abdallah
  Date: 01/05/2023
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list-category</title>
</head>
<body>
  <c:forEach var="category" items="${categories}">
      <div class="card" style="width: 18rem;">
          <p style="margin-bottom: 0.5em;"><strong>${category.name} </strong></p>
          <p>${category.idCategory}</p>
      </div>
  </c:forEach>
</body>
</html>
