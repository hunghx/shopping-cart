<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16/5/2023
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

</head>
<body>
<h1>
  Danh sách giỏ hàng
</h1>
<table class="table table-bordered">
  <thead>
  <tr>
    <th scope="col">STT</th>
    <th scope="col">Product</th>
    <th scope="col">Image</th>
    <th scope="col">Price</th>
    <th scope="col">Quantity</th>
    <th scope="col">Total Amount</th>
    <th scope="col" colspan="2">Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${listcart}" var="cart">
    <form action="/cartController/update" method="get">
    <tr>
      <input type="hidden" name="odId" value="${cart.id}"/>
      <td>${loop.count}</td>
      <td>${cart.productName}</td>
      <td><img src="${cart.imageUrl}" alt="${cart.productName}" width="100px"></td>
      <td>${cart.price}</td>
      <td><input type="number" name="quantity" value="${cart.quantity}"></td>
      <td>${cart.price* cart.quantity}</td>
      <td><button type="submit" class="btn btn-warning">Update</button></td>
      <td><a class="btn btn-danger" href="/cartController/delete/${cart.id}">Delete</a></td>
    </tr>
    </form>
  </c:forEach>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

  </tbody>
</table>
</body>
</html>
