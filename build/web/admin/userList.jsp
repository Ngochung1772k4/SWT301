<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <style>
            .table-hover tbody tr:hover {
                background-color: #FFF5F5;
            }
            .table thead th {
                background-color: #FFC1C1;
                color: #4B4B4B;
            }
            .action-btn {
                background: none;
                border: none;
                color: #555;
                font-size: 20px;
                cursor: pointer;
            }
            .btn-custom {
                background-color: #D2691E;
                color: white;
                padding: 10px 20px;
                font-size: 16px;
                font-weight: bold;
                border-radius: 5px;
                text-decoration: none;
                display: inline-block;
            }
            .btn-custom:hover {
                background-color: #A0522D;
                color: white;
            }
        </style>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure you want to delete user ID = " + id + "?")) {
                    window.location = "delete-account?id=" + id;
                }
            }
        </script>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="container" style="padding: 30px;">
                    <h2 class="text-center">User Management</h2>
                    <div class="text-right mb-3">
                        <a href="userlist?action=add" class="btn btn-custom">+ Add New User</a>
                    </div>
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>UserID</th>
                                <th>Username</th>
                                <th>Fullname</th>
                                <th>Email</th>
                                <th>Gender</th>
                                <th>Address</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${listRe == null}">
                            <tr>
                                <td colspan="8" class="text-center"><strong>No sellers found</strong></td>
                            </tr>
                        </c:if>
                        <c:if test="${listRe != null}">
                            <c:forEach items="${listRe}" var="c">
                                <tr>
                                    <td>${c.getAccount_id()}</td>
                                    <td>${c.getAccount_name()}</td>
                                    <td>${c.getFullname()}</td>
                                    <td>${c.getEmail()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${c.getGender() == 0}">Nam</c:when>
                                            <c:otherwise>Nữ</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${c.getAddress()}</td>
                                    <td>${roleMap[c.getRole_id()]}</td>
                                    <td>
                                        <div class="dropdown">
                                            <button class="action-btn" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="fas fa-ellipsis-v"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="user_update?id=${c.getAccount_id()}"><i class="fas fa-edit"></i> Edit</a>
                                                <a class="dropdown-item text-danger" href="#" onclick="doDelete(${c.getAccount_id()})"><i class="fas fa-trash-alt"></i> Delete</a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>

        </div>


        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
