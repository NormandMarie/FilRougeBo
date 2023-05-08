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

<div class="container-fluid p-3">

        <div class="row row-cols-1 row-cols-sm-2">


<%--            IMAGE   --%>
            <div class="col-4 col-sm-3">
                <div class="product-img-container">
                    <img src="${product.imgUrl}" class="rounded" style="object-fit: cover;">
                </div>
            </div>

            <div class="col-8 col-sm-9 p-3">

                <div class="border-bottom border-black w-100 mb-2 d-flex justify-content-between">
                    <div class="">
                        <h2 class="d-inline-block">
                            <span class="badge text-bg-secondary font-monospace align-top">&num;${product.id}</span>
                            ${product.name}
                        </h2>
                    </div>
                    <div class="my-auto">

                        <a role="button" class="btn btn-primary mx-1"
                           href="${pageContext.request.contextPath}/secured/edit-product?id=${product.id}">
                            <i class="bi bi-pencil-square d-inline"></i>
                            <span class="d-none d-sm-inline-block">Edit</span>
                        </a>

                        <a role="button" class="btn btn-danger mx-1"
                           href="${pageContext.request.contextPath}/secured/delete-product?id=${product.id}">
                            <i class="bi bi-trash3-fill"></i>
                            <span class="d-none d-sm-inline-block">Delete</span>
                        </a>

                    </div>
                </div>

            <dl class="row">
                <dt class="col-sm-3">Category</dt>
                <dd class="col-sm-9">${product.category.name}</dd>

                <dt class="col-sm-3">Stock</dt>
                <dd class="col-sm-9">${product.stock} ${product.unit}</dd>

                <dt class="col-sm-3">Price</dt>
                <dd class="col-sm-9">&euro;&nbsp${product.pricePerUnit}&nbsp/&nbsp${product.unit}</dd>

                <dt class="col-sm-3">V.A.T.</dt>
                <dd class="col-sm-9">${product.vat * 100} %</dd>

                <dt class="col-sm-3">Description</dt>
                <dd class="col-sm-9">${product.description}</dd>

                <dt class="col-sm-3">Seasonal months:</dt>
                <dd class="col-sm-9">
                    <div class="container text-center row row-cols-1 row-cols-sm-4 row-cols-md-5 row-cols-xl-7">
                        <c:forEach items="${monthList}" var="month">
                            <div class="col p-1 m-1 text-wrap badge
                            ${product.seasonalMonths.contains(month) ? 'text-bg-primary' : 'text-muted bg-light'}">${month.label}</div>
                        </c:forEach>
                    </div>
                </dd>
            </dl>

        </div>


    </div>


</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>