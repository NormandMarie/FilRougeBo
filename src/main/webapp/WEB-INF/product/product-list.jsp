<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: mhe
  Date: 24/04/2023
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<c:if test="${!empty products}">
  <c:forEach var="product" items="${products}">

      <p>${product}</p>

      <a class="btn btn-secondary"
         href="${pageContext.request.contextPath}/details-product?id=${product.id}"
         role="button">details id=${product.id}</a>
      <a class="btn btn-primary"
         href="${pageContext.request.contextPath}/edit-product?id=${product.id}"
         role="button">update id=${product.id}</a>
      <a class="btn btn-danger"
         href="${pageContext.request.contextPath}/delete-product?id=${product.id}" role="button">delete id=${product.id}</a>




  </c:forEach>
</c:if>

<a class="btn btn-primary" href="/create-product" role="button">create</a>

</body>
</html>
