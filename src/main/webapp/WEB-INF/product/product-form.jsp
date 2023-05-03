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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<form action="${pageContext.request.contextPath}/create-product" method="post"
      class="m-5">


    <div class="mb-3">
        <label for="name" class="form-label">Product Name :</label>
        <input type="text" class="form-control"
               id="name" name="name"
               value="${empty product.name ? '' : product.name }">
    </div>

    <div class="mb-3">
        <label for="category" class="form-label">Category:</label>

        <select class="form-select" id="category" name="category">

            <c:choose>
                <c:when test="${! empty product.category}">
                    <option value="${product.category.idCategory}" selected>${product.category.name}</option>
                </c:when>
                <c:otherwise>
                    <option selected>Please select a Category:</option>
                </c:otherwise>
            </c:choose>

            <c:forEach var="cat" items="${categoryList}">
                <option value="${cat.idCategory}">${cat.name}</option>
            </c:forEach>

        </select>
    </div>

    <div class="mb-3">
        <label for="unit" class="form-label">Product Unit :</label>
        <input type="text" class="form-control"
               id="unit" name="unit"
               value="${empty product.unit ? '' : product.unit }">
    </div>

    <div class="mb-3">
        <label for="pricePerUnit" class="form-label">Price per unit</label>
        <input type="text" class="form-control"
               id="pricePerUnit" name="pricePerUnit"
               value="${empty product.pricePerUnit ? '' : product.pricePerUnit }">
    </div>

    <div class="mb-3">
        <label for="imgUrl" class="form-label">Product imgUrl :</label>
        <input type="text" class="form-control"
               id="imgUrl" name="imgUrl"
               value="${empty product.imgUrl ? '' : product.imgUrl }">
    </div>

    <div class="mb-3">
        <label for="vat" class="form-label">VAT (in digits) :</label>
        <input type="text" class="form-control"
               id="vat" name="vat"
               value="${empty product.vat ? '' : product.vat }">
    </div>

    <label for="description" class="form-control-sm">Description:</label>
    <textarea id="description" class="form-control"
              name="description" rows="10">${empty product.description ? 'Some description' : product.description}</textarea>

    <div class="mb-3">
        <label for="stock" class="form-label">Stock :</label>
        <input type="text" class="form-control"
               id="stock" name="stock"
               value="${empty product.stock ? '' : product.stock }">
    </div>


<%--    MONTHS    --%>
    <div>

        <c:forEach items="${monthList}" var="month">

            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" ${product.seasonalMonths.contains(month) ? 'checked' : '' }
                       name="months" id="${month}" value="${month}">
                <label class="form-check-label" for="${month}">${month}</label>
            </div>

        </c:forEach>

    </div>

    <c:choose>
        <c:when test="${! empty product}">
            <button class="btn btn-primary my-3" type="submit" name="id"
                    value=${product.id} formaction="/edit-product?id=${product.id}">Edit</button>

            <a class="btn btn-danger" role="button" href="/delete-product?id=${product.id}">Delete</a>
        </c:when>
        <c:otherwise>
            <button class="btn btn-primary my-3" type="submit">Create</button>
        </c:otherwise>
    </c:choose>



</form>
