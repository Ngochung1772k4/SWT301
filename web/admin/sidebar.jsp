<%-- 
    Document   : sidebar
    Created on : 6 thg 2, 2025, 18:43:34
    Author     : BT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/admin_style.css">
    </head>
    <body>
        <aside id="sidebar">
            <div class="d-flex">
                <button class="toggle-btn" type="button">
                    <i class="lni lni-grid-alt"></i>
                </button>
                <div class="sidebar-logo">
                    <img src="../images/logo.svg" width="50%" height="20%" style="margin-left:  10px;" />
                </div>
            </div>
            <ul class="sidebar-nav">
                <li class="sidebar-item">
                    <a href="dashboardadmin.jsp" class="sidebar-link">
                        <i class="lni lni-dashboard"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="userlist" class="sidebar-link">
                        <i class="lni lni-user"></i>
                        <span>User Management</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="units" class="sidebar-link">
                        <i class="lni lni-chevron-down-circle"></i>
                        <span>Unit Management</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="suppliers" class="sidebar-link">
                        <i class="lni lni-stamp"></i>
                        <span>Supplier Management</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                       data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                        <i class="lni lni-cart-full"></i>
                        <span>Order Management</span>
                    </a>
                    <ul id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a href="listOrderAdmin" class="sidebar-link">All order</a>
                        </li>
                        <li class="sidebar-item">
                            <a href="listOrderAdmin?od_status=0" class="sidebar-link">Processing</a>
                        </li>
                        <li class="sidebar-item">
                            <a href="listOrderAdmin?od_status=1" class="sidebar-link">Transporting</a>
                        </li>
                        <li class="sidebar-item">
                            <a href="listOrderAdmin?od_status=2" class="sidebar-link">Completed</a>
                        </li>
                        <li class="sidebar-item">
                            <a href="listOrderAdmin?od_status=3" class="sidebar-link">Cancelled</a>
                        </li>
                    </ul>
                </li>
                <li class="sidebar-item">
                    <a href="categoryAdmin" class="sidebar-link">
                        <i class="lni lni-archive"></i>
                        <span>Category Management</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="listproductadmin" class="sidebar-link">
                        <i class="lni lni-package"></i>
                        <span>Product Management</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <i class="lni lni-popup"></i>
                        <span>Notification</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <i class="lni lni-cog"></i>
                        <span>Setting</span>
                    </a>
                </li>
            </ul>
            <div class="sidebar-footer">
                <a href="logoutAd" class="sidebar-link">
                    <i class="lni lni-exit"></i>
                    <span>Logout</span>
                </a>
            </div>
        </aside>
    </body>
</html>

