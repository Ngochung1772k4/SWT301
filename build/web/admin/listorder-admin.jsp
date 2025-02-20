<%-- 
    Document   : listorder-admin
    Created on : Mar 7, 2024, 12:49:06 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi" xml:lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="../../css/admin_style.css">
        <link rel="stylesheet" href="../../css/manager.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <style>
            .datatable-table td, th, p {
                padding: 7px; /* Adjust the value to set the desired spacing */
            }
            .scrollable-table {
                max-height: 600px; /* Đặt chiều cao tối đa của bảng */
                overflow: auto; /* Tạo thanh cuộn nếu bảng vượt quá kích thước tối đa */
            }
        </style>
        <script src="../../js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="main" id="layoutSidenav_content" >
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Danh sách đơn hàng</h1>
                        <div class="card mb-4" style="display: flex; justify-content: space-between">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Đơn hàng 
                            <c:if test="${sessionScope.errorMessage != null}">
                                <span style="color: red" class="mb-2">
                                    ${sessionScope.errorMessage}
                                </span>
                                <%
                                    session.removeAttribute("errorMessage");
                                %>
                            </c:if>
                        </div>
                        <div class="card-body scrollable-table">
                            <table id="datatablesSimple" class="datatable-table" >
                                <thead>
                                    <tr style="font-size: 13px">
                                        <th>Order ID</th>
                                        <th style="width: 20%">Họ và tên</th>
                                        <th style="width: 15%">Email</th>
                                        <th style="width: 7%">Số điện thoại</th>
                                        <th style="width: 25%">Địa chỉ</th>
                                        <th style="width: 20%">Ghi chú</th>
                                        <th style="width: 10%">Created at</th>
                                        <th>Tổng tiền</th>
                                        <th style="width: 10%">Tình trạng</th>
                                        <th>Actions</th>
                                        <th>Chi tiết</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOrder}" var = "order">
                                        <tr style="font-size: 13px">
                                            <td>${order.orderId}</td>
                                            <td>${order.fullname}</td>
                                            <td>
                                                ${order.email}
                                            </td>
                                            <td>${order.phoneNumber}</td>
                                            <td>${order.address}</td>
                                            <td>${order.note}</td>
                                            <td>${order.orderDate}</td>
                                            <td>${order.totalMoney}</td>
                                            <td><c:choose>
                                                    <c:when test="${order.status eq 3}">
                                                        <span style="color: red">Đã hủy</span>
                                                    </c:when>
                                                    <c:when test="${order.status eq 2}">
                                                        <span style="color: green">Đã hoàn thành</span>
                                                    </c:when>
                                                    <c:when test="${order.status eq 1}">
                                                        <span style="color: #FFC107">Đang vận chuyển</span>
                                                    </c:when>
                                                    <c:when test="${order.status eq 0}">
                                                        <span style="color: #FFC107">Đang xử lý</span>
                                                    </c:when>
                                                </c:choose></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status != 3 && order.status !=2}">
                                                        <a onclick="UpdateStatus('${order.orderId}')" style=" font-size: 15px; color: green" 
                                                           data-bs-toggle="modal" data-bs-target="#changeStatusModal" title="Edit"> <i class="lni lni-pencil"></i></a>
                                                        </c:when>
                                                    </c:choose>
                                            </td>
                                            <td>
                                                <a href="orderdetailAdmin?orderId=${order.orderId}" >Chi tiết</a>
                                            </td>
                                        </tr>   
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Change status Order -->
        <div id="changeStatusModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="updateStatusOrderAdmin" method="post">
                        <div class="modal-header">						
                            <h5 class="modal-title">Cập nhật trạng thái cho đơn hàng ID: <span style="color: blue" id="orderIdInput2"></span></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <input type="hidden" name="orderId" id="orderIdInput3"/>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="status" value="0" >
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Đang xử lý
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="status" value="1">
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Đang vận chuyển
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="status" value="2">
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Đã hoàn thành
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="status" value="3">
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Đã hủy
                                    </label>
                                </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Xác nhận">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function UpdateStatus(orderId) {
                document.getElementById('orderIdInput3').value = orderId;
                document.getElementById('orderIdInput2').textContent = orderId;

            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="../../js/hamburger.js"></script>
        <script type="text/javascript" src="../../js/admin/modal.js"></script>
    </body>
</html>
