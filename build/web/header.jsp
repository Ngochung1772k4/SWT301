<!-- header.jsp -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <defs>
        <!-- Menu Icon -->
        <symbol xmlns="http://www.w3.org/2000/svg" id="menu" viewBox="0 0 24 24">
            <path fill="currentColor" d="M2 6a1 1 0 0 1 1-1h18a1 1 0 1 1 0 2H3a1 1 0 0 1-1-1m0 6.032a1 1 0 0 1 1-1h18a1 1 0 1 1 0 2H3a1 1 0 0 1-1-1m1 5.033a1 1 0 1 0 0 2h18a1 1 0 0 0 0-2z"/>
        </symbol>

        <!-- Search Icon -->
        <symbol xmlns="http://www.w3.org/2000/svg" id="search" viewBox="0 0 24 24">
            <path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z"/>
        </symbol>

        <!-- User Icon -->
        <symbol xmlns="http://www.w3.org/2000/svg" id="user" viewBox="0 0 24 24">
            <g fill="none" stroke="currentColor" stroke-width="1.5">
                <circle cx="12" cy="9" r="3"/>
                <circle cx="12" cy="12" r="10"/>
                <path stroke-linecap="round" d="M17.97 20c-.16-2.892-1.045-5-5.97-5s-5.81 2.108-5.97 5"/>
            </g>
        </symbol>

        <!-- Wishlist Icon -->
        <symbol xmlns="http://www.w3.org/2000/svg" id="wishlist" viewBox="0 0 24 24">
            <g fill="none" stroke="currentColor" stroke-width="1.5">
                <path d="M21 16.09v-4.992c0-4.29 0-6.433-1.318-7.766C18.364 2 16.242 2 12 2C7.757 2 5.636 2 4.318 3.332C3 4.665 3 6.81 3 11.098v4.993c0 3.096 0 4.645.734 5.321c.35.323.792.526 1.263.58c.987.113 2.14-.907 4.445-2.946c1.02-.901 1.529-1.352 2.118-1.47c.29-.06.59-.06.88 0c.59.118 1.099.569 2.118 1.47c2.305 2.039 3.458 3.059 4.445 2.945c.47-.053.913-.256 1.263-.579c.734-.676.734-2.224.734-5.321Z"/>
                <path stroke-linecap="round" d="M15 6H9"/>
            </g>
        </symbol>

        <!-- Shopping Bag Icon -->
        <symbol xmlns="http://www.w3.org/2000/svg" id="shopping-bag" viewBox="0 0 24 24">
            <g fill="none" stroke="currentColor" stroke-width="1.5">
                <path d="M3.864 16.455c-.858-3.432-1.287-5.147-.386-6.301C4.378 9 6.148 9 9.685 9h4.63c3.538 0 5.306 0 6.207 1.154c.901 1.153.472 2.87-.386 6.301c-.546 2.183-.818 3.274-1.632 3.91c-.814.635-1.939.635-4.189.635h-4.63c-2.25 0-3.375 0-4.189-.635c-.814-.636-1.087-1.727-1.632-3.91Z"/>
                <path d="m19.5 9.5l-.71-2.605c-.274-1.005-.411-1.507-.692-1.886A2.5 2.5 0 0 0 17 4.172C16.56 4 16.04 4 15 4M4.5 9.5l.71-2.605c.274-1.005.411-1.507.692-1.886A2.5 2.5 0 0 1 7 4.172C7.44 4 7.96 4 9 4"/>
                <path d="M9 4a1 1 0 0 1 1-1h4a1 1 0 1 1 0 2h-4a1 1 0 0 1-1-1Z"/>
                <path stroke-linecap="round" stroke-linejoin="round" d="M8 13v4m8-4v4m-4-4v4"/>
            </g>
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="heart" viewBox="0 0 24 24"><path fill="currentColor" d="M20.16 4.61A6.27 6.27 0 0 0 12 4a6.27 6.27 0 0 0-8.16 9.48l7.45 7.45a1 1 0 0 0 1.42 0l7.45-7.45a6.27 6.27 0 0 0 0-8.87Zm-1.41 7.46L12 18.81l-6.75-6.74a4.28 4.28 0 0 1 3-7.3a4.25 4.25 0 0 1 3 1.25a1 1 0 0 0 1.42 0a4.27 4.27 0 0 1 6 6.05Z"/></symbol>
    </defs>
</svg>
<header>
    <div class="container-fluid">
        <div class="row py-3 border-bottom align-items-center">

            <!-- Logo and Menu Toggle -->
            <div class="col-sm-4 col-lg-2 text-center text-sm-start d-flex gap-3 justify-content-center justify-content-md-start">
                <a href="home" class="d-flex align-items-center">
                    <img src="images/logo.svg" alt="logo" class="img-fluid">
                </a>
            </div>

            <!-- Search Bar -->
            <div class="col-sm-6 col-lg-4">
                <form id="search-form" class="d-flex flex-grow-1 gap-2" action="productController" method="get">
                    <!-- Dropdown ch?n danh m?c -->
                    <select name="categoryId" class="form-select border-0 bg-transparent" style="width: 150px;">
                        <option value="">All Categories</option>
                        <c:forEach items="${listCate}" var="cate">
                            <option value="${cate.categoryId}" 
                                    ${param.categoryId == cate.categoryId ? 'selected' : ''}>
                                ${cate.name}
                            </option>
                        </c:forEach>
                    </select>

                    <!-- Ô nh?p t? khóa -->
                 
<input name="txt" type="text" class="form-control border-0 bg-transparent" 
       placeholder="Search products..." value="<c:out value="${param.txt}" />">

                        <!-- Nút Submit -->
                        <button type="submit" class="btn border-0 bg-transparent p-0">
                            <svg width="24" height="24"><use xlink:href="#search"></use></svg>
                        </button>
                </form>
            </div>

            <!-- Navigation Links -->
            <div class="col-lg-4">
                <ul class="navbar-nav list-unstyled d-flex flex-row gap-4 justify-content-center align-items-center mb-0 fw-bold text-uppercase text-dark">
                    <li class="nav-item active">
                        <a href="home" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item active">
                        <a href="productController" class="nav-link">Product</a>
                    </li>
                    <c:if test="${sessionScope.account == null}">
                        <li class="nav-item">
                            <a class="nav-link me-4" href="login.jsp">Login</a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.account != null}">
                        <a class="nav-link">
                            Hello ${sessionScope.account.account_name}
                        </a>
                    </c:if>
                </ul>
            </div>

            <!-- User Actions -->
            <div class="col-sm-8 col-lg-2 d-flex gap-4 align-items-center justify-content-center justify-content-sm-end">
                <ul class="d-flex justify-content-end list-unstyled m-0 align-items-center">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle p-2" role="button" id="userMenu" data-bs-toggle="dropdown" aria-expanded="false">
                            <svg width="24" height="24">
                                <use xlink:href="#user"></use>
                            </svg>
                        </a>
                        <ul class="dropdown-menu border-0 p-3 rounded-0 shadow" aria-labelledby="userMenu">
                            <c:if test="${sessionScope.account == null}">
                                <li><a href="login.jsp" class="dropdown-item">Login</a></li>
                                <li><a href="register.jsp" class="dropdown-item">Register</a></li>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                                <li><a href="view/user/detail-user.jsp" class="dropdown-item">Profile</a></li>
                                <li><a href="cart.jsp" class="dropdown-item">My Cart</a></li>
                                <li><a href="logout" class="dropdown-item">Logout</a></li>
                            </c:if>
                        </ul>
                    </li>

                    <li>
                        <a href="#" class="p-2">
                            <svg width="24" height="24">
                                <use xlink:href="#wishlist"></use>
                            </svg>
                        </a>
                    </li>

                    <li class="position-relative">
                        <a class="p-2" href="cart.jsp">
                            <svg width="24" height="24">
                                <use xlink:href="#shopping-bag"></use>
                            </svg>
                            <span class="position-absolute top-0 start-100 translate-middle badge bg-danger small">
                                ${size}
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>