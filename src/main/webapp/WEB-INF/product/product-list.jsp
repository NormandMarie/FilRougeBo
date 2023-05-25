<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<div class="container-fluid p-4">

    <div class="row row-cols-1 row-cols-sm-2 p-3 m-3">

            <form class="d-flex col-12 col-sm-7 my-2"
                  role="search" method="post"
                  action="${pageContext.request.contextPath}/secured/search-product">
                <input class="form-control me-2" type="search" placeholder="chercher un produit" aria-label="Search"
                    value="${!empty searchQuery ? searchQuery : ''}"  name="search">
                <button class="btn btn-outline-success" type="submit">Rechercher</button>
            </form>

            <a class="btn btn-primary col-12 col-sm-5 my-2"
               href="${pageContext.request.contextPath}/secured/add-product"
               role="button">Créer un produit</a>


    </div>

<c:choose>
    <c:when test="${!empty products}">

        <table class="table table-responsive table-striped table-hover p-3 align-middle">

    <thead class="">
        <tr>
            <th>ID</th>
            <th class="d-none d-md-table-cell">Image</th>
            <th>Nom</th>
            <th class="d-none d-md-table-cell">Categories</th>
            <th class="">Prix</th>
            <th class="">Stock</th>
            <th></th> <!--  buttons -->
        </tr>
    </thead>

    <tbody class="table-group-divider">
        <c:forEach var="product" items="${products}">
            <tr>
                <td>&num;${product.id}</td>

                <td class="d-none d-md-table-cell">
                    <img src="data:image/jpeg;base64,${product.imgUrl}" alt="Product Image"
                         class="table-img rounded border">
                </td>

                <td>${product.name}</td>
                <td class="d-none d-md-table-cell">${product.category.name}</td>
                <td class="font-monospace">&euro;&nbsp${product.pricePerUnit}/${product.unit}</td>
                <td class="font-monospace">${product.stock}&nbsp${product.unit}</td>

                <td class="row-cols-1 row-cols-xs-3">
                    <a class="btn btn-outline-secondary btn-sm m-1 col-9 col-md-3"
                       href="${pageContext.request.contextPath}/secured/details-product?id=${product.id}"
                       role="button">
                        <i class="bi bi-card-heading"></i>
                        <span class="d-none d-sm-inline-block">Détails</span>
                    </a>

                    <a class="btn btn-primary btn-sm m-1 col-9 col-md-3"
                       href="${pageContext.request.contextPath}/secured/edit-product?id=${product.id}"
                       role="button">
                        <i class="bi bi-pencil-square d-inline"></i>
                        <span class="d-none d-sm-inline-block">Éditer</span>
                    </a>

                    <a class="btn btn-danger btn-sm m-1 col-9 col-md-3"
                       href="${pageContext.request.contextPath}/secured/delete-product?id=${product.id}"
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

    <c:when test="${empty products && empty searchQuery}">
        <p>No products to display</p>
    </c:when>

    <c:when test="${empty products && !empty searchQuery}">
        <p>No products found for : "${searchQuery}"</p>
    </c:when>
</c:choose>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
