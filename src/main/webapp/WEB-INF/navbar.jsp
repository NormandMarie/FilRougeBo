<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <%-- CATEGORIES --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"
                       href="${pageContext.request.contextPath}/secured/list-category"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Categories
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/list-category">
                                Categories List
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/add-category">
                                Add Category
                            </a>
                        </li>
                    </ul>
                </li>

                <%-- PRODUCTS --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"
                       href="${pageContext.request.contextPath}/secured/list-product"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Products
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/list-category">
                                Products list
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/add-category">
                                Add Product
                            </a>
                        </li>
                    </ul>
                </li>

                <%-- ADMINS --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"
                       href="${pageContext.request.contextPath}/SuperAdmin/ListAdmin"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Admins
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/SuperAdmin/ListAdmin">
                                Admins list
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/SuperAdmin/add-admin">
                                Add Admin
                            </a>
                        </li>
                    </ul>
                </li>

            </ul>

            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>

        </div>
    </div>
</nav>