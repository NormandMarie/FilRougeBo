<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<div class="container-fluid p-3">

    <c:choose>
        <c:when test="${! empty product}">
            <h1>Edit product</h1>
        </c:when>
        <c:otherwise>
            <h1>Add product to the database</h1>
        </c:otherwise>
    </c:choose>


    <form action="${pageContext.request.contextPath}/secured/add-product" method="post" enctype="multipart/form-data"
      class="m-3 bg-light rounded p-2">

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control"
               id="name" name="name"
               value="${empty product.name ? '' : product.name }">
    </div>

    <div class="row row-cols-2">
        <div class="col mb-3">
            <label for="category" class="form-label">Category</label>

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

        <div class="col mb-3">
<%--            <label for="imgUrl" class="form-label">Img URL</label>--%>
<%--            <input type="text" class="form-control"--%>
<%--                   id="imgUrl" name="imgUrl"--%>
<%--                   value="${empty product.imgUrl ? '' : product.imgUrl }">--%>
            <c:if test="${!empty product.id}">
                <img src="data:image/jpeg;base64,${product.imgUrl}" alt="Product Image"
                     class="table-img rounded border">
            </c:if>
                <input type="file" name="imageFile">


        </div>



    </div>


    <div class="row row-cols-4">

            <div class="col mb-3">
                <label for="unit" class="form-label">Unit</label>
                <input type="text" class="form-control"
                       id="unit" name="unit"
                       value="${empty product.unit ? '' : product.unit }">
            </div>

            <div class="col mb-3">
                <label for="pricePerUnit" class="form-label">Price</label>
                <input type="text" class="form-control"
                       id="pricePerUnit" name="pricePerUnit"
                       value="${empty product.pricePerUnit ? '' : product.pricePerUnit }">
            </div>

            <div class="col mb-3">
                <label for="vat" class="form-label">V.A.T.</label>
                <input type="text" class="form-control"
                       id="vat" name="vat"
                       value="${empty product.vat ? '' : product.vat }">
            </div>

            <div class="col mb-3">
                <label for="stock" class="form-label">Stock</label>
                <input type="text" class="form-control"
                       id="stock" name="stock"
                       value="${empty product.stock ? '' : product.stock }">
            </div>


        </div>

    <label for="description" class="form-control-sm">Description:</label>
    <textarea id="description" class="form-control"
              name="description" rows="7">${empty product.description ? 'Some description' : product.description}</textarea>


<%--    MONTHS    --%>
    <div class="m-2 row row-cols-sm-4 row-cols-md-5 row-cols-xl-7">

        <c:forEach items="${monthList}" var="month">

            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" ${product.seasonalMonths.contains(month) ? 'checked' : '' }
                       name="months" id="${month}" value="${month}">
                <label class="form-check-label" for="${month}"> ${month.label}</label>
            </div>

        </c:forEach>

    </div>


        <c:choose>
            <c:when test="${! empty product}">
                <button class="btn btn-primary my-3" type="submit"
                        name="id" value="${product.id}"
                        formaction="${pageContext.request.contextPath}/secured/edit-product?id=${product.id}">
                    <i class="bi bi-pencil-square d-inline"></i>
                    <span class="d-none d-sm-inline-block">Edit</span>
                </button>

                <a role="button" class="btn btn-danger mx-1"
                   href="${pageContext.request.contextPath}/secured/delete-product?id=${product.id}">
                    <i class="bi bi-trash3-fill"></i>
                    <span class="d-none d-sm-inline-block">Delete</span>
                </a>
            </c:when>
            <c:otherwise>
                <button class="btn btn-primary my-3" type="submit">
                    <i class="bi bi-check-square-fill"></i>
                    <span class="d-none d-sm-inline-block">Create</span>
                </button>
            </c:otherwise>
        </c:choose>




</form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
