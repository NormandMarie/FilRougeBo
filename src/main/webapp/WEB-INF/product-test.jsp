<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mhe
  Date: 24/04/2023
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${!empty products}">
  <c:forEach var="product" items="${products}">

    <p>${product}</p>

  </c:forEach>
</c:if>

</body>
</html>
