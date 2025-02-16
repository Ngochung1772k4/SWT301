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
                padding: 8px; /* Adjust the value to set the desired spacing */
            }

            .modal-add {
                font-size: 12px;
            }

            .form-group input,
            .form-group textarea {
                font-size: 11px; /* You can adjust the font size as needed */
            }

            .scrollable-table {
                max-height: 600px; /* Đặt chiều cao tối đa của bảng */
                overflow: auto; /* Tạo thanh cuộn nếu bảng vượt quá kích thước tối đa */
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar-user.jsp"></jsp:include>
           
                <div style="margin-top: 50px " class="main" id="layoutSidenav_content">
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Chi tiết đơn hàng</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Danh sánh sản phẩm 
                            </div>
                            <div class="card-body scrollable-table">
                                <table id="datatablesSimple" class="datatable-table" >
                                    <thead>
                                        <tr style="font-size: 19px">
                                            <th>Tên sản phẩm</th>
                                            <th style="width: 15%">Hình ảnh</th>
                                            <th style="width: 15%">Số lượng</th>
                                            <th style="width: 15%">Đơn giá</th>
                                            <th style="width: 25%">Số tiền</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.listDetail}" var = "Detail">
                                        <tr style="font-size: 15px">
                                            <td>${Detail.name}</td>
                                            <td><img style="width: 50%" src="${Detail.imageUrl}"></td>

                                            <td>${Detail.amount}</td>

                                            <td>${Detail.price}đ</td>
                                            <td>${Detail.price * Detail.amount}đ</td>
                                        </tr>  
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <p>Tổng số tiền của đơn hàng: ${Order.totalMoney}đ</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="../../js/hamburger.js"></script>
    </body>
</html>
