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

<img src="https://source.unsplash.com/random/900×700/?vegetables" style="max-height: 400px;max-width: 400px;">
<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username" placeholder="username"
        value="superadmin">
    <input type="password" name="password" placeholder="password"
           value="superadmin">
    <button type="submit">Login</button>
</form>

<c:if test="${isError == true}">
    <p>Bad credentials.</p>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
