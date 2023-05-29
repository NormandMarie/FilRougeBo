<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/head.jsp"></jsp:include>
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
    <form  class="d-flex justify-content-around align-items-center my-3 p-3 background-add-Admin" action="${pageContext.request.contextPath}/SuperAdmin/add-admin" method="post">
        <p class="my-2"><strong>administrateur·rice</strong></p>
        <div class="d-flex">
            <div class="col mx-1">
                <input  class="form-control mx-2"type="text" name="username" placeholder="nom d'utilisateur">
                <c:if test="${not empty requestScope.errors.username}">
                    <c:out value="${requestScope.errors.username}"/>
                </c:if>
            </div>

            <div class="col mx-1">
                <input  class="form-control mx-2" type="password" name="password" placeholder="Mots de passe">
                <c:if test="${not empty requestScope.errors.password}">
                    <c:out value="${requestScope.errors.password}" />
                </c:if>
            </div>

            <div class="col mx-1">
                <input  class="form-control mx-2"type="text" name="firstName" placeholder="Prénom">
                <c:if test="${not empty requestScope.errors.firstName}">
                    <c:out value="${requestScope.errors.firstName}" />
                </c:if>
            </div>

            <div class="col mx-1">
                <input  class="form-control mx-2" type="text" name="lastName" placeholder="Nom">
                <c:if test="${not empty requestScope.errors.lastName}">
                    <c:out value="${requestScope.errors.lastName}" />
                </c:if>
            </div>

            <div class="col mx-1">
                <input  class="form-control mx-2 " type="email" name="email" placeholder="email">
                <c:if test="${not empty requestScope.errors.email}">
                    <c:out value="${requestScope.errors.email}" />
                </c:if>
            </div>
            <input type="hidden" name="superAdmin" value="false">
        </div>

        <button  class="btn btn-success p-2" type="submit">Ajouter</button>
    </form>
</div>
<h1 class="d-flex justify-content-around align-items-center  my-5 "><strong>Liste des administrateur·rices</strong></h1>
<div class="container-fluid p-4">
    <c:choose>
        <c:when test="${!empty AdminDtos}">

            <table class="table table-responsive table-striped table-hover p-3 align-middle ">
                <thead>
                <tr>
                    <th></th>
                    <th  class="ml-5" >ID</th>
                    <th>nom d'utilisateur</th>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Boutons</th>
                </tr>
                </thead>

                <tbody class="table-group-divider">

                <c:forEach items="${AdminDtos}" var="AdminDto">

                    <tr>
                        <td><h5>administrateur·rice</h5></td>
                        <td>&num;${AdminDto.idAdmin}</td>
                        <td >${AdminDto.username}</td>
                        <td >${AdminDto.firstName} ${AdminDto.lastName}</td>
                        <td >${AdminDto.email}</td>
                        <td>
                            <a class="btn btn-danger btn-sm col-9 "
                               href="${pageContext.request.contextPath}/SuperAdmin/delete-admin?id=${AdminDto.idAdmin}"
                               role="button">
                                <i class="bi bi-trash3-fill"></i>
                                <span class="d-none d-sm-inline-block">Supprimer</span>
                            </a>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>

        </c:when>
    </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
