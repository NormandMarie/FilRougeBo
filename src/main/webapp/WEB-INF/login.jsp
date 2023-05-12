<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FilRougeBO</title>
    <jsp:include page="/WEB-INF/head.jsp"></jsp:include>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
<div class=" d-flex align-items-center" style="height: 90vh">
    <div class=" d-flex justify-content-center container m-auto login-img" >
        <div class="login-img-div">
            <img src="https://source.unsplash.com/random/900×700/?vegetables" class="img-fluid  h-100 w-100" style="object-fit: cover;" >
        </div>

        <div class=" m-2 bg-light-custome p-3 " >
            <form  class="d-flex flex-column m-1 mt-2 login-champ" action="${pageContext.request.contextPath}/login" method="post">
                <div>
                    <h2> Welcome to </h2>
                    <h3 >Panier Primeur </h3>
                    <h4>Log into your account </h4>
                </div>
                <div class="d-flex  flex-column ">
                    <div class="d-flex flex-column login-champ-use " >
                        <label> Username:</label>
                        <input   type="text" name="username" placeholder="username"
                                 value="superadmin" >
                    </div>
                    <div class="d-flex flex-column mb-1 login-champ-pwd" >
                        <label> Password:</label>
                        <input type="password" name="password" placeholder="password"
                               value="superadmin">
                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Login</button>
            </form>

            <c:if test="${isError == true}">
                <p>Bad credentials.</p>
            </c:if>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
