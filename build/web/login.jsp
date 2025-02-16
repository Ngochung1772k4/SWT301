<%-- 
    Document   : login
    Created on : Feb 26, 2024, 9:48:41 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Organic Food | Login</title>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"><link rel="stylesheet" href="css/login_style.css">
    </head>
    <body>
        <div class="box-form" style="margin-top: 60px">
            <div class="left">
                <div class="overlay">
                    <img src="images/logo.svg" width="30%" height="15%" />
                    <h1>Organic Food</h1>
                   
                    <a style="text-decoration: none; color: #842029" href="home">← Back to home</a>
                </div>
            </div>
            <div class="right">
                <form action="login" method="post">
                    <h5>Login</h5>
                    <p style="color: gray">Need an account? <a href="register.jsp">Register</a>here!</p>
                    <div class="inputs">
                        <input name="account_name" type="text" placeholder="User name" value="${account_name}" >
                        <br>
                        <input name="password" type="password" placeholder="Password" value="${password}" >
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
                    <div class="remember-me--forget-password">
                        <div>
                            <input type="checkbox" name="remember" value="1" ${remember =='1' ?'checked':''}/><span>Remember password</span>
                        </div>
                                           </div>
                    <br>
                    <button type="submit">LOGIN</button>
                </form>
            </div>
        </div>
    </body>
</html>
