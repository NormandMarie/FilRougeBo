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
    <%--    Bootstrap icons--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
    <%--    Custom CSS--%>
    <style>
        <%@include file="/resources/css/custom.css" %>
    </style>

</head>
<body>

<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<div class="container-fluid p-4">

    <div class="row row-cols-1 row-cols-sm-2 p-3 m-3">

            <form class="d-flex col-12 col-sm-7 my-2" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>

            <a class="btn btn-primary col-12 col-sm-5 my-2"
               href="${pageContext.request.contextPath}/secured/add-product"
               role="button">Create product</a>


    </div>



<c:if test="${!empty products}">

<table class="table table-responsive table-striped table-hover p-3 align-middle">

    <thead class="">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Stock</th>
            <th></th> <!--  buttons -->
        </tr>
    </thead>

    <tbody class="table-group-divider">
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.category.name}</td>
                <td class="font-monospace">&euro; ${product.pricePerUnit} / ${product.unit}</td>
                <td class="font-monospace">${product.stock} ${product.unit}</td>

                <td class="row-cols-1 row-cols-xs-3">
                    <a class="btn btn-outline-secondary btn-sm m-1 col-9 col-md-3"
                       href="${pageContext.request.contextPath}/secured/details-product?id=${product.id}"
                       role="button">
                        <i class="bi bi-card-heading"></i>
                        <span class="d-none d-sm-inline-block">Details</span>
                    </a>

                    <a class="btn btn-primary btn-sm m-1 col-9 col-md-3"
                       href="${pageContext.request.contextPath}/secured/edit-product?id=${product.id}"
                       role="button">
                        <i class="bi bi-pencil-square d-inline"></i>
                        <span class="d-none d-sm-inline-block">Edit</span>
                    </a>

                    <a class="btn btn-danger btn-sm m-1 col-9 col-md-3"
                       href="${pageContext.request.contextPath}/secured/delete-product?id=${product.id}"
                       role="button">
                        <i class="bi bi-trash3-fill"></i>
                        <span class="d-none d-sm-inline-block">Delete</span>
                    </a>
                </td>

            </tr>
        </c:forEach>
    </tbody>

</table>


</c:if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
