<%-- 
    Document   : order-user.jsp
    Created on : Mar 6, 2024, 9:27:40 PM
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
        <title>Organic Food</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="../../css/admin_style.css">

        <style>
            .datatable-table td, th {
                padding: 8px; 
            }

            .modal-add {
                font-size: 12px;
            }

            .form-group input,
            .form-group textarea {
                font-size: 13px;
            }

            .scrollable-table {
                max-height: 600px; 
                overflow: auto; 
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar-user.jsp"></jsp:include>

                <div class="main" id="layoutSidenav_content" style="margin-top: 50px ">
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Danh sách đơn hàng</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Đơn hàng đang đặt
                            </div>
                            <div class="card-body scrollable-table">
                                <table id="datatablesSimple" class="datatable-table">
                                    <thead>
                                        <tr style="font-size: 13px">
                                            <th>Order ID</th>
                                            <th style="width: 20%">Họ và tên</th>
                                            <th style="width: 15%">Email</th>
                                            <th style="width: 7%">Số điện thoại</th>
                                            <th style="width: 25%">Địa chỉ</th>
                                            <th>Phương thức TT</th>
                                            
                                            <th style="width: 25%">Ghi chú</th>

                                            <th>Created at</th>
                                            <th>Tổng tiền</th>
                                            <th style="width: 10%">Tình trạng</th>
                                            <th>Actions</th>
                                            <th>Chi tiết</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listOrder}" var="order">
                                        <tr style="font-size: 13px">
                                            <td>${order.orderId}</td>
                                            <td>${order.fullname}</td>
                                            <td>${order.email}</td>
                                            <td>${order.phoneNumber}</td>
                                            <td>${order.address}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty order.payment}">
                                                        ${order.payment.paymentMethod}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                           
                                            <td>${order.note}</td>
                                            <td>${order.orderDate}</td>
                                            <td>${order.totalMoney}</td>
                                            <td>
                                                <c:choose>
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
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status eq 0}">
                                                        <a onclick="ChangeInfo('${order.orderId}', '${order.fullname}', '${order.phoneNumber}', '${order.email}', '${order.address}', '${order.note}')" 
                                                           style="font-size: 15px; color: green" 
                                                           data-bs-toggle="modal" data-bs-target="#changeInfoModal" title="Edit"> 
                                                            <i class="lni lni-pencil"></i>
                                                        </a>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${order.status eq 0}">
                                                        <a onclick="CancelOrder('${order.orderId}')" 
                                                           style="font-size: 15px; color: red"
                                                           data-bs-toggle="modal" data-bs-target="#cancelOrderModal" title="Cancel"> 
                                                            <i class="lni lni-trash-can"></i>
                                                        </a>    
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a style="color: #0d6efd" href="orderdetail?order_id=${order.orderId}">Chi tiết</a>
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

     
        <div id="changeInfoModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="changeorderInfo" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Confirm order information</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <input type="hidden" name="order_id" id="order_idInput"/>
                                <label>Họ và tên</label>
                                <input name="fullname" type="text" class="form-control" id="fullnameInput" required>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <input name="phone_number" type="text" class="form-control" id="phone_numberInput" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input name="email" type="text" class="form-control" id="emailInput" required>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <input name="address" type="text" class="form-control" id="addressInput" required>
                            </div>
                            <div class="form-group">
                                <label>Ghi chú</label>
                                <textarea rows="5" name="note" type="text" class="form-control" id="noteInput" ></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Xác nhận">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div id="cancelOrderModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="cancelorder" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Xác nhận hủy đơn hàng</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <input type="hidden" name="order_id" id="order_idInput3"/>
                                <span>Bạn có chắc chắn muốn hủy đơn hàng ID:<span style="color: blue" id="order_idInput2"></span> không?</span>
                                <p class="text-warning"><small>Hành động này không thể được hoàn tác.</small></p>
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
            function ChangeInfo(order_id, fullname, phone_number, email, address, note) {
                document.getElementById('order_idInput').value = order_id;
                document.getElementById('phone_numberInput').value = phone_number;
                document.getElementById('fullnameInput').value = fullname;
                document.getElementById('emailInput').value = email;
                document.getElementById('addressInput').value = address;
                document.getElementById('noteInput').value = note;
            }

            function CancelOrder(order_id) {
                document.getElementById('order_idInput3').value = order_id;
                document.getElementById('order_idInput2').textContent = order_id;

            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="../../js/hamburger.js"></script>
    </body>
</html>
