<%@ page import="com.m2i.filrougebo.service.AuthenticationService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" class="img-navBar mr-3">
        <a class="navbar-brand" href="#">Panier Primeur</a>
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
                                Liste des catégories
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/add-category">
                                Ajouter une catégorie
                            </a>
                        </li>
                    </ul>
                </li>

                <%-- PRODUCTS --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"
                       href="${pageContext.request.contextPath}/secured/list-product"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Produits
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/list-product">
                                Liste des produits
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/secured/add-product">
                                Ajouter un produit
                            </a>
                        </li>
                    </ul>
                </li>

                <%-- ADMINS --%>
                    <%
                        AuthenticationService authService = new AuthenticationService();
                        String username = (String) session.getAttribute("username");
                        if (authService.isSuperAdmin(username)) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/SuperAdmin/ListAdmin"
                           role="button"  aria-expanded="false">
                            Administrateur&middot;rice
                        </a>
                    </li>
                    <%
                        }
                    %>
            </ul>

            <div class="d-flex flex-column">
                <c:choose>
                    <c:when test="${empty sessionScope.username}">
                        <a class="btn btn-outline-success btn-sm m-1"  href="${pageContext.request.contextPath}/login">Login</a>
                    </c:when>

                    <c:when test="${! empty sessionScope.username}">
                        <div class="d-flex">
                             <a class="btn btn-outline-danger btn-sm m-1 my-2 mx-2 " href="${pageContext.request.contextPath}/secured/logout">Déconnexion</a>
                            <div class="d-flex flex-column">
                              <span> <i class="bi bi-person-fill-lock"></i> ${sessionScope.username}</span>
                              <span style="white-space: nowrap;"> <i class="bi bi-calendar2-event"></i> <%= (new java.util.Date()).toLocaleString()%></span>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>

        </div>
    </div>
</nav>