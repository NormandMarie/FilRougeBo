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

<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
<%--<a href="${pageContext.request.contextPath}/">home</a>--%>
    <form  class="d-flex justify-content-around align-items-center my-3 p-3"  style="background-color: rgb(242,242,242);" action="${pageContext.request.contextPath}/SuperAdmin/add-admin" method="post">
        <h3>Add new admin</h3>
        <div class="d-flex">
            <input  class="form-control mx-2"type="text" name="username" placeholder="username">
            <input  class="form-control" type="password" name="password" placeholder="password">
            <input type="hidden" name="superAdmin" value="false">
        </div>

        <button  class="btn btn-success p-2" type="submit">new Admin </button>
    </form>
</div>
<h1 class="d-flex justify-content-around align-items-center  my-5 "><strong>List of Admins</strong></h1>
<table class="table table-striped">
    <c:forEach items="${AdminDtos}" var="AdminDto">

        <tr>
            <td ><h3 style="margin-inline:4rem; ">Admin</h3></td>
            <td>
                <div class="d-flex"  style="margin-inline:4rem; ">
                    <p class="mx-5">Username: </p>
                    <p><strong>${AdminDto.username} </strong></p>
                </div>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/SuperAdmin/delete-admin?id=${AdminDto.idAdmin}" class="btn btn-danger px-3 mb-2"  style="margin-inline:4rem; ">delete admin</a>
            </td>
        </tr>
    </c:forEach>
</table>

<%--<c:forEach items="${AdminDtos}" var="AdminDto">--%>
<%--    <div class="d-flex justify-content-around align-items-center my-3 p-3 table-striped">--%>
<%--    <h3>Admin</h3>--%>
<%--    <div class="d-flex mx-3">--%>
<%--                <p class="mx-3">Username: </p>--%>

<%--                <p style="margin-bottom: 0.5em;"><strong>${AdminDto.username} </strong></p>--%>
<%--    </div>--%>

<%--        <a href="${pageContext.request.contextPath}/SuperAdmin/delete-admin?id=${AdminDto.idAdmin}" class="btn btn-danger  px-3 mb-2">delete admin</a>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</c:forEach>--%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
