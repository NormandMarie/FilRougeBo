<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<div class="container-fluid p-3">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center">
            <h1 class="mt-4"> <i class="bi bi-bug-fill"></i> Erreur 404</h1>
            <p>La page que vous avez demandée est introuvable.</p>
            <a href="${pageContext.request.contextPath}/secured/list-product" class="btn btn-primary">Accueil</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>