<%-- 
    Document   : dashboardadmin
    Created on : 6 thg 2, 2025, 18:43:20
    Author     : BT
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Supplier Manager</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="../css/admin_style.css">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="main">
                    <nav class="navbar navbar-expand px-4 py-3" aria-label="Main navigation">
                        <div class="navbar-collapse collapse">
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item dropdown">
                                    <a href="#" data-bs-toggle="dropdown" class="nav-icon pe-md-0">
                                        <img src="../../images/account.png" class="avatar img-fluid" alt="">
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end rounded">

                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <main class="content px-3 py-4">
                        <div class="container-fluid">
                            <div class="mb-3">
                                <h3 class="fw-bold fs-1 mb-3">Supplier Manager</h3>
                                <h5 class="fw-light fs-3 mt-2">Search Condition</h5>
                                <form method="get" action="suppliers">
                                    <div class="fw-bold fs-3 mt-3 mb-3 ">
                                        <label>Name</label>
                                        <label>:</label>
                                        <input class="ms-3 w-25" type="text" name="name" placeholder="Input name of supplier" value="${name}"/>
                                </div>
                                <div class="fw-bold fs-3 mt-3 mb-3">
                                    <label>Contact info</label>
                                    <label>:</label>
                                    <input class="ms-3 w-25" type="text" name="contact_info" placeholder="Input contact of supplier" value="${contact_info}"/>
                                </div>
                                <div class="fw-bold fs-3 mt-3 mb-3">
                                    <label>Address</label>
                                    <label>:</label>
                                    <input class="ms-3 w-25" type="text" name="address" placeholder="Input address of supplier" value="${address}"/>
                                </div>
                                <button class="btn btn-primary btn-lg ms-2 mb-2">Search</button>
                                <input type="hidden" name="index" value="${index}"/>
                            </form>

                            <div class="roset w-25">
                                <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#addSupplierModal">Add new supplier</button>
                            </div>
                            <h3 class="fw-bold fs-4 my-3">Supplier Manage Table
                            </h3>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">#</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Contact info</th>
                                                <th scope="col">Address</th>
                                                <th scope="col">Create at</th>
                                                <th scope="col">Action</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${suppliers}" var="s" varStatus="loop">
                                                <tr>
                                                    <th scope="row">${loop.index}</th>
                                                    <td>${s.name}</td>
                                                    <td>${s.contactInfo}</td>
                                                    <td>${s.address}</td>
                                                    <td>
                                                        <fmt:formatDate value="${s.createdAt}" pattern="dd-MM-yyyy" />
                                                    </td>
                                                    <td>
                                                        <a href="#">
                                                            <i class="fa fa-pencil-square-o fa-lg m-2" aria-hidden="true" 
                                                               data-bs-toggle="modal" data-bs-target="#updateSupplierModal" onclick="openUpdateSupplierModal(${s.supplierId})"></i>
                                                        </a>
                                                        <a href="#">
                                                            <i class="fa fa-trash fa-lg m-2" aria-hidden="true"
                                                               data-bs-toggle="modal" data-bs-target="#deleteSupplierModal" onclick="openDeleteSupplierModal(${s.supplierId})"></i>
                                                        </a> 
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                    <nav class="d-flex justify-content-center" aria-label="navigation">
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link <c:if test="${index == 1}">disabled</c:if>" href="suppliers?index=${index-1}">Previous</a></li>
                                                <c:choose>
                                                    <c:when test="${index <= 4}">
                                                        <c:choose>
                                                            <c:when test="${total <= 5}">
                                                                <c:forEach begin="1" end="${total}" step="1" var="i">
                                                                <li class="page-item <c:if test="${i == index}">active</c:if>">
                                                                    <a class="page-link" href="suppliers?index=${i}">
                                                                        ${i}
                                                                    </a>
                                                                </li>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:when test="${total > 5}">
                                                            <c:forEach begin="1" end="${5}" step="1" var="i">
                                                                <li class="page-item <c:if test="${i == index}">active</c:if>">
                                                                    <a class="page-link" href="suppliers?index=${i}">
                                                                        ${i}
                                                                    </a>
                                                                </li>
                                                            </c:forEach>
                                                            <li class="page-item"><a class="page-link" href="#">...</a></li>
                                                            <li class="page-item"><a class="page-link" href="units?index=${total}">${total}</a></li>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:when test="${total > 5 and index > 4 and index < (total-3)}">
                                                    <li class="page-item"><a class="page-link" href="units?index=${1}">${1}</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">...</a></li>
                                                        <c:forEach begin="${index-2}" end="${index+2}" step="1" var="i">
                                                        <li class="page-item <c:if test="${i == index}">active</c:if>">
                                                            <a class="page-link" href="suppliers?index=${i}">
                                                                ${i}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item"><a class="page-link" href="#">...</a></li>
                                                    <li class="page-item"><a class="page-link" href="suppliers?index=${total}">${total}</a></li>
                                                    </c:when>
                                                    <c:when test="${total > 5 and index >= (total-3)}">
                                                    <li class="page-item"><a class="page-link" href="suppliers?index=${1}">${1}</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">...</a></li>
                                                        <c:forEach begin="${total - 4}" end="${total}" step="1" var="i">
                                                        <li class="page-item <c:if test="${i == index}">active</c:if>">
                                                            <a class="page-link" href="suppliers?index=${i}">
                                                                ${i}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>

                                            <li class="page-item"><a class="page-link <c:if test="${index == total}">disabled</c:if>" href="suppliers?index=${total}">Next</a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="footer">

                </footer>
            </div>
        </div>

        <!--Add supplier modal-->
        <div id="addSupplierModal" class="modal fade">
            <div class="modal-dialog" style="margin-left: 450px;">
                <div class="modal-content modal-add" style="width: 150%;">
                    <form action="addSupplier" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title" data-bs-toggle="modal" data-bs-target="#addSuppliertModal">Add Supplier</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Contact info</label>
                                <textarea name="contactInfo" type="text" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <textarea name="address" type="text" class="form-control" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Add Supplier">
                            <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!--Update supplier modal-->
        <div id="updateSupplierModal" class="modal fade">
            <div class="modal-dialog" style="margin-left: 450px;">
                <div class="modal-content modal-add" style="width: 150%;">
                    <form action="updateSupplier" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title" data-bs-toggle="modal" data-bs-target="#updateSupplierModal">Update Supplier</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input id="supplierName" name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Contact info</label>
                                <textarea id="supplierContact_info" name="contact_info" type="text" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <textarea id="supplierAddress" name="address" type="text" class="form-control" required></textarea>
                            </div>
                            <input id="supplierId" type="hidden" name="id"/>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Update Supplier">
                            <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--Delete supplier modal-->
        <div id="deleteSupplierModal" class="modal fade">
            <div class="modal-dialog" style="margin-left: 450px;">
                <div class="modal-content modal-add" style="width: 150%;">
                    <form action="deleteSupplier" method="get">
                        <div class="modal-header">						
                            <h4 class="modal-title" data-bs-toggle="modal" data-bs-target="#deleteSupplierModal">Delete Unit</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <p id="textDeleteModal">Do you want to delete this supplier ?</p>
                        </div>
                        <input id="deleteSupplierId" type="hidden" name="id"/>
                        <div class="modal-footer">
                            <input id="confirmDelButton" type="submit" class="btn btn-danger" value="Yes" onclick="confirmDelete()">
                            <input id="cancelDelButton" type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <script>
            function openUpdateSupplierModal(id) {
                $("#supplierId").val(id);
                $.get("updateSupplier?id=" + id, function (data) {
                    $("#supplierName").val(data.name);
                    $("#supplierContact_info").val(data.contactInfo);
                    $("#supplierAddress").val(data.address);
                });
            }

            function openDeleteSupplierModal(id) {
                $("#deleteSupplierId").val(id);


                $.get("checkDeleteSupplier?id=" + id, function (data, status) {
                }).fail(function (err) {
                    console.log(err);
                });

            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/hamburger.js"></script>
        <script type="text/javascript" src="../js/admin/modal.js"></script>
    </body>

</html>


