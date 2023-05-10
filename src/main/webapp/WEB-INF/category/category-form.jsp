<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<div class="container-fluid p-3">

    <form action="${pageContext.request.contextPath}/secured/add-category" method="post" class="m-3">

        <div class="row row-cols-2 d-flex align-items-end mx-auto">

            <div class="col-7 mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control"
                       id="name" name="name"
                       placeholder="Category name"
                       value="${empty category.name ? '' : category.name }">
            </div>

            <div class="col-4">
                <c:choose>
                    <c:when test="${! empty category}">
                        <button class="btn btn-primary my-3" type="submit"
                                name="id" value="${category.idCategory}"
                                formaction="${pageContext.request.contextPath}/secured/edit-category?id=${category.idCategory}">
                            <i class="bi bi-pencil-square d-inline"></i>
                            <span class="d-none d-md-inline-block">Edit</span>
                        </button>

                        <a role="button" class="btn btn-danger mx-1"
                           href="${pageContext.request.contextPath}/secured/delete-category?id=${category.idCategory}">
                            <i class="bi bi-trash3-fill"></i>
                            <span class="d-none d-md-inline-block">Delete</span>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-primary my-3" type="submit">
                            <i class="bi bi-check-square-fill"></i>
                            <span class="d-none d-md-inline-block">Create</span>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>

    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
