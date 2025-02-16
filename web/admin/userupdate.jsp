<%-- 
    Document   : userupdate
    Created on : Feb 8, 2025, 3:47:01 PM
    Author     : ASUS
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container" style="padding: 30px;">
            <h2 class="text-center">Update User</h2>
            <form action="user_update" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="account_id">Account ID</label>
                            <input type="text" class="form-control" id="account_id" name="account_id" value="${user.account_id}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="fullname">Full Name</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" value="${user.fullname}" required>
                        </div>
                        <div class="form-group">
                            <label for="account_name">Username</label>
                            <input type="text" class="form-control" id="account_name" name="account_name" value="${user.account_name}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="phone_number">Phone Number</label>
                            <input type="text" class="form-control" id="phone_number" name="phone_number" value="${user.phone_number}" required>
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" id="address" name="address" value="${user.address}" required>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select class="form-control" id="gender" name="gender">
                                <option value="0" ${user.gender == 0 ? 'selected' : ''}>Male</option>
                                <option value="1" ${user.gender == 1 ? 'selected' : ''}>Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="role_id">Role</label>
                            <select class="form-control" id="role_id" name="role_id">
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.role_id}" ${user.role_id == role.role_id ? 'selected' : ''}>${role.role_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Update</button>
                    <a href="userlist.jsp" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </body>
</html>
