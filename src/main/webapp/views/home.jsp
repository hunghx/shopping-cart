
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/5/2023
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<c:if test="${not empty message}">
    <script>
        window.alert("${message}")
    </script>
</c:if>

<h1>Đây là trang chủ</h1>
<a href="${userLogin==null?'/userController/form-login':'/cartController'}">đi đến giỏ hàng</a>
<p>Chào mừng khách hàng ${userLogin.username} đến trang web </p>
<h2>Danh sách sản phẩm</h2>

<div class="container text-center">
    <div class="row">
        <c:forEach items="${listProduct}" var="pro">
            <div class="col-3">
                <div class="card">
                    <img src="${pro.imageUrl}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${pro.productName}</h5>
                        <p class="card-text">${pro.productId}</p>
                        <p>
                           ${pro.productPrice} $
                        </p>
                        <c:set var="cart_url" value="/cartController/add-to-cart/${pro.productId}"></c:set>
                        <a href="${userLogin!=null?cart_url:'/userController/form-login'}" class="btn btn-primary">Add to cart</a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
