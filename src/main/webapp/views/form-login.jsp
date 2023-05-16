<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/5/2023
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>
<form action="<%=request.getContextPath()%>/userController/login" method="post">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <td>username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">Login</button></td>
        </tr>
    </table>
    <a href="/userController/form-register">Đăng ký</a>
</form>
</body>
</html>
