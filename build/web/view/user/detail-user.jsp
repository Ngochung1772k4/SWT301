<%-- 
    Document   : dashboard-admin
    Created on : Mar 2, 2024, 9:01:01 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Organic Shop</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="../../css/admin_style.css">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar-user.jsp"></jsp:include>

                <div class="main">
                    <nav class="navbar navbar-expand px-4 py-3">
                        <form action="#" class="d-none d-sm-inline-block">
                        </form>
                    </nav>
                    <main class="content px-3 py-4">
                        <div class="container-fluid">
                            <div class="mb-3">
                                <h3 class="fw-bold fs-4 mb-3">Thông tin người dùng </h3>
                                <div class="add-action mb-2">
                                    <a onclick="UserInfo('${sessionScope.account.account_id}', '${sessionScope.account.fullname}', '${sessionScope.account.email}', '${sessionScope.account.phone_number}', '${sessionScope.account.address}')" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateUserModal">
                                    <span>Thay đổi thông tin</span></a>
                            </div>
                            <div class="add-action mb-2">
                                <a onclick="changPassword('${sessionScope.account.account_id}')" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#changePasswordModal">
                                    <span>Thay đổi mật khẩu</span></a>
                            </div>
                            <c:if test="${sessionScope.errorMessage != null}">
                                <span style="color: red" class="mb-2">
                                    ${sessionScope.errorMessage}
                                </span>
                                <%
                                    session.removeAttribute("errorMessage");
                                %>
                            </c:if>
                            <div class="row">
                                <div class="card border-0">
                                    <div class="card-body py-4">
                                        <c:if test="${sessionScope.account != null}">
                                            <p class="mb-2 ">
                                                User ID: ${sessionScope.account.account_id}
                                            </p>
                                            <p class="mb-2 ">
                                                Họ và tên: ${sessionScope.account.fullname}
                                            </p>
                                            <p class="mb-2 ">
                                                Tên tài khoản: ${sessionScope.account.account_name}
                                            </p>
                                            <p class="mb-2 ">
                                                Giới tính: 
                                                <c:choose>
                                                    <c:when test="${sessionScope.account.gender == 0}">Nam</c:when>
                                                    <c:when test="${sessionScope.account.gender == 1}">Nữ</c:when>
                                                    <c:otherwise>Unknown</c:otherwise>
                                                </c:choose>
                                            </p>
                                            <p class="mb-2 ">
                                                Ngày tháng năm sinh: ${sessionScope.account.date_of_birth}
                                            </p>
                                            <p class="mb-2 ">
                                                Số điện thoại: ${sessionScope.account.phone_number}
                                            </p>
                                            <p class="mb-2 ">
                                                Email: ${sessionScope.account.email}
                                            </p>
                                            <p class="mb-2 ">
                                                Địa chỉ: ${sessionScope.account.address}
                                            </p>
                                            <p class="mb-2 ">
                                                Ngày tạo tài khoản: ${sessionScope.account.created_at}
                                            </p>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <!-- Update user modal -->
        <div id="updateUserModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${pageContext.request.contextPath}/view/user/updateUser" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Category</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" name="user_id" id="user_idInput"/>
                                <label>Họ và tên</label>
                                <input name="user_name" type="text" class="form-control" id="user_nameInput" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input name="user_email" type="text" class="form-control" id="user_emailInput" required>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <input name="user_phonenumber" type="text" class="form-control" id="user_phonenumberInput" required>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <input name="user_address" type="text" class="form-control" id="user_addressInput" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Update">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Change password modal -->
        <div id="changePasswordModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="changepassword" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Thay đổi mật khẩu</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" name="user_id" id="user_idInput2"/>
                                <label>Mật khẩu cũ</label>
                                <input name="old_passwordInput" type="password" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu mới</label>
                                <input name="new_password" id="new_password" type="password" class="form-control" oninput="checkPasswordLength()" required>
                                <div style="margin-top: 5px" id="passwordLengthMessage"></div>
                            </div>
                            <div class="form-group">
                                <label>Xác nhận mật khẩu mới</label>
                                <input name="re_new_password" id="re_new_password" type="password" class="form-control" oninput="checkPasswordMatch()" required>
                                <div style="margin-top: 5px" id="passwordMatchMessage"></div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Change">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function checkPasswordMatch() {
                var passwordInput = document.getElementById("new_password");
                var confirmPasswordInput = document.getElementById("re_new_password");
                var passwordMatchMessage = document.getElementById("passwordMatchMessage");
                if (passwordInput.value === confirmPasswordInput.value) {
                    passwordMatchMessage.textContent = "Mật khẩu nhập lại khớp.";
                    passwordMatchMessage.style.color = "green";
                } else {
                    passwordMatchMessage.textContent = "Mật khẩu nhập lại không khớp.";
                    passwordMatchMessage.style.color = "red";
                }
            }

            function checkPasswordLength() {
                var passwordInput = document.getElementById("new_password");
                var passwordLengthMessage = document.getElementById("passwordLengthMessage");
                if (passwordInput.value.length < 6) {
                    passwordLengthMessage.textContent = "Mật khẩu phải có ít nhất 6 ký tự.";
                    passwordLengthMessage.style.color = "red";
                } else {
                    passwordLengthMessage.remove();
                }
            }
            function UserInfo(user_id, user_name, user_email, user_phonenumber, user_address) {
                document.getElementById('user_idInput').value = user_id;
                document.getElementById('user_nameInput').value = user_name;
                document.getElementById('user_emailInput').value = user_email;
                document.getElementById('user_phonenumberInput').value = user_phonenumber;
                document.getElementById('user_addressInput').value = user_address;
            }

            function changPassword(user_id) {
                document.getElementById('user_idInput2').value = user_id;
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="../../js/hamburger.js"></script>
    </body>

</html>