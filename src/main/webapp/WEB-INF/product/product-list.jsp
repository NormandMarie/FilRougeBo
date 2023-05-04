<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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

<c:if test="${!empty products}">
  <c:forEach var="product" items="${products}">

      <p>${product}</p>

      <a class="btn btn-secondary"
         href="${pageContext.request.contextPath}/secured/details-product?id=${product.id}"
         role="button">details id=${product.id}</a>
      <a class="btn btn-primary"
         href="${pageContext.request.contextPath}/secured/edit-product?id=${product.id}"
         role="button">update id=${product.id}</a>
      <a class="btn btn-danger"
         href="${pageContext.request.contextPath}/secured/delete-product?id=${product.id}" role="button">delete id=${product.id}</a>




  </c:forEach>
</c:if>

<a class="btn btn-primary" href="${pageContext.request.contextPath}/secured/add-product" role="button">create</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
