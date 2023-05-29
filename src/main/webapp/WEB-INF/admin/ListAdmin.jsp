<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

<div class="container-fluid p-4">

    <form  class="d-flex justify-content-around align-items-center my-3 p-3 background-add-Admin" action="${pageContext.request.contextPath}/SuperAdmin/add-admin" method="post">
        <div class="grid-for-admin">
        <p class="my-2 d-none d-sm-table-cell"><strong>administrateur·rice</strong></p>
            <div class=" mx-1">
                <input  class="form-control mx-2"type="text" name="username" placeholder="nom d'utilisateur">
                <c:if test="${not empty requestScope.errors.username}">
                    <c:out value="${requestScope.errors.username}"/>
                </c:if>
            </div>

            <div class=" mx-1">
                <input  class="form-control mx-2" type="password" name="password" placeholder="Mots de passe">
                <c:if test="${not empty requestScope.errors.password}">
                    <c:out value="${requestScope.errors.password}" />
                </c:if>
            </div>

            <div class=" mx-1">
                <input  class="form-control mx-2 "type="text" name="firstName" placeholder="Prénom">
                <c:if test="${not empty requestScope.errors.firstName}">
                    <c:out value="${requestScope.errors.firstName}" />
                </c:if>
            </div>

            <div class=" mx-1">
                <input  class="form-control mx-2" type="text" name="lastName" placeholder="Nom">
                <c:if test="${not empty requestScope.errors.lastName}">
                    <c:out value="${requestScope.errors.lastName}" />
                </c:if>
            </div>

            <div class=" mx-1">
                <input  class="form-control mx-2 " type="email" name="email" placeholder="email">
                <c:if test="${not empty requestScope.errors.email}">
                    <c:out value="${requestScope.errors.email}" />
                </c:if>
            </div>
            <input type="hidden" name="superAdmin" value="false">
            <button  class="btn btn-success p-2 mx-1 mx-2" type="submit">Ajouter</button>
        </div>
    </form>
</div>
<h1 class="d-flex justify-content-around align-items-center  my-5 mx-3 "><strong>Liste des administrateur·rices</strong></h1>
<div class="container-fluid p-4">
    <c:choose>
        <c:when test="${!empty AdminDtos}">

            <table class="table table-responsive table-striped table-hover p-3 align-middle ">
                <thead>
                <tr>
                    <th></th>
                    <th  class="ml-5 d-none d-sm-table-cell" >ID</th>
                    <th>nom d'utilisateur</th>
                    <th class="d-none d-sm-table-cell">Nom</th>
                    <th>Email</th>
                    <th>Supprimer</th>
                </tr>
                </thead>

                <tbody class="table-group-divider">

                <c:forEach items="${AdminDtos}" var="AdminDto">

                    <tr>
                        <td><h5 class="d-none d-sm-inline-block">administrateur·rice</h5></td>
                        <td class="d-none d-sm-table-cell">&num;${AdminDto.idAdmin}</td>
                        <td >${AdminDto.username}</td>
                        <td class="d-none d-sm-table-cell">${AdminDto.firstName} ${AdminDto.lastName}</td>
                        <td ><span class="truncate" onclick="showEmailMobile(this)">${AdminDto.email}</span>
                            <span class="email-hover-mobile" onclick="hideEmailMobile(this)">${AdminDto.email}</span>
                        </td>


                        <td>
                            <a class="btn btn-danger btn-sm col-9"
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
<script>
    if (window.innerWidth < 850) {
        // Sélectionnez tous les éléments avec la classe "truncate"
        var truncateElements = document.getElementsByClassName('truncate');
        console.log('truncate element', truncateElements);
        for (var i = 0; i < truncateElements.length; i++) {
            var element = truncateElements[i];
            var text = element.textContent;

            if (text.length > 5) {
                // Tronquez le texte et ajoutez des points de suspension
                var truncatedText = text.substring(0, 5) + '...';
                element.textContent = truncatedText;
            }
        }
        function showEmailMobile(element) {
            var siblingElement = element.nextElementSibling;
            console.log('siblingElement', siblingElement)
            siblingElement.style.display = "flex";
        }

        function hideEmailMobile(element) {
            element.style.display = "none";
        }

    }


</script>
</body>
</html>
