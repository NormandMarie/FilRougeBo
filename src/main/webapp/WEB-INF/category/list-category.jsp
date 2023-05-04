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

  <c:forEach var="category" items="${categories}">
      <div class="card" style="width: 18rem;">
          <p style="margin-bottom: 0.5em;"><strong>${category.name} </strong></p>
          <p>${category.idCategory}</p>
          <form action="${pageContext.request.contextPath}/secured/delete-category" method="post">
              <button name="deleteBtn" value="${category.idCategory}">delete</button>
          </form>
          <form action="${pageContext.request.contextPath}/secured/edit-category" method="post">
              <button name="editBtn" value="${category.idCategory}">edit</button>
          </form>
      </div>
  </c:forEach>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
