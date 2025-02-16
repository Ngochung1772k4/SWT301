<%-- 
    Document   : loginadmin
    Created on : 6 thg 2, 2025, 18:09:39
    Author     : BT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Admin | Login</title>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"><link rel="stylesheet" href="../css/login_style.css">
    </head>
    <body>
        <!-- partial:index.partial.html -->
        <div class="box-form" style="margin-top: 60px">
            <div class="right" style="margin: 30px 100px">
                <form action="loginAd" method="post">
                    <h5>Login for Admin</h5>
                    <div class="inputs">
                        <input name="account_name" type="text" placeholder="User name" >
                        <br>
                        <input name="password" type="password" placeholder="Password" >
                    </div>
                    <br>
                    <%
                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null) {
                    %>
                    <div style="color: red" class="error-message">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                    <br><br>
                    <br>
                    <button type="submit">Đăng nhập</button>
                </form>
            </div>
        </div>
    </body>
</html>
