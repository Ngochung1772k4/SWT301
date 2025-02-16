<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css">
        <%@ include file="header.jsp" %>
    </head>
    <body>
        <section class="pb-5">
            <div class="container-lg">
                <div class="row">
                    <div class="col-md-12">
                        <div class="section-header d-flex flex-wrap justify-content-between my-4">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <c:set var="pageSize" value="10" />
                            <c:set var="totalProducts" value="${fn:length(products)}" />
                            <c:set var="totalPages" value="${(totalProducts + pageSize - 1) / pageSize}" />
                            <c:set var="currentPage" value="${param.page != null ? param.page : 1}" />

                            <div class="product-grid row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5">
                                <c:forEach items="${products}" var="p" begin="${(currentPage - 1) * pageSize}" end="${currentPage * pageSize - 1}" varStatus="status">
                                    <div class="col product-item">
                                        <div class="product-item">
                                            <figure>
                                                <a href="ProductDetail?productId=${p.productId}" title="${p.name}">
                                                    <img src="${not empty productImages[p.productId] ? productImages[p.productId][0].url : 'placeholder.jpg'}" alt="${p.name}" class="tab-image img-fluid">
                                                </a>
                                            </figure>
                                            <div class="d-flex flex-column text-center">
                                                <h3 class="fs-6 fw-normal">${p.name}</h3>
                                                <div class="d-flex justify-content-center align-items-center">
                                                    <c:choose>
                                                        <c:when test="${p.discount > 0}">
                                                            <span class="old-price text-muted" style="text-decoration: line-through;">
                                                                <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true" />đ
                                                            </span>
                                                            <span class="price-text text-success fw-bold ms-2">
                                                                <fmt:formatNumber value="${p.price - (p.price * p.discount / 100)}" type="number" groupingUsed="true" />đ
                                                            </span>
                                                            <span class="discount-badge text-white bg-danger fw-bold rounded ms-2 px-2 py-1">
                                                                -${p.discount}%
                                                            </span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="price-text text-dark fw-bold">
                                                                <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true" />đ
                                                            </span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <c:if test="${p.quantity > 0}">
                                                    <small class="text-muted">Còn lại: ${p.quantity} sản phẩm</small>
                                                </c:if>
                                                <c:if test="${p.quantity <= 0}">
                                                    <small class="text-danger">Tạm hết hàng</small>
                                                </c:if>
                                            </div>
                                            <div class="button-area p-3 pt-0">
                                                <form id="addToCartForm_${p.productId}" name="f${p.productId}" action="addtocart" method="post" class="add-to-cart-form">
                                                    <input type="hidden" name="id" value="${p.productId}">
                                                    <div class="row g-1 mt-2">
                                                        <c:choose>
                                                            <c:when test="${p.quantity > 0}">
                                                                <div class="col-3">
                                                                    <input type="number" name="num" class="form-control border-dark-subtle input-number quantity" value="1" min="1" max="${p.quantity}">
                                                                </div>
                                                                <div class="col-7">
                                                                    <button type="submit" class="btn btn-primary rounded-1 p-2 fs-7 add-to-cart-btn">Add to Cart</button>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="col-10">
                                                                    <button type="button" class="btn btn-secondary rounded-1 p-2 fs-7" disabled>Hết hàng</button>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div class="col-2">
                                                            <a href="#" class="btn btn-outline-dark rounded-1 p-2 fs-6">
                                                                <svg width="18" height="18"><use xlink:href="#heart"></use></svg>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <nav>
                                <ul class="pagination justify-content-center mt-4">
                                    <c:if test="${currentPage > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                                        </li>
                                    </c:if>

                                    <c:forEach begin="1" end="${totalPages}" var="page">
                                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                                            <a class="page-link" href="?page=${page}">${page}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
<script type="text/javascript">
    $(document).ready(function () {
        $(".add-to-cart-form").submit(function (event) {
            event.preventDefault(); // Ngăn chặn form submit mặc định

            var form = $(this);
            var productId = form.find("input[name='id']").val();
            var quantityInput = form.find("input[name='num']");
            var quantity = parseInt(quantityInput.val());
            var maxQuantity = parseInt(quantityInput.attr("max"));

            // Kiểm tra số lượng hợp lệ
            if (quantity > maxQuantity) {
                alert("Số lượng vượt quá hàng tồn kho!");
                return;
            }

            if (maxQuantity <= 0) {
                alert("Sản phẩm đã hết hàng!");
                return;
            }

            $.ajax({
                url: 'addtocart',
                type: 'POST',
                data: { id: productId, num: quantity },
                success: function (response) {
                    // Cập nhật badge giỏ hàng
                    $(".badge").text(response.size);

                    // Tính số lượng tồn kho mới
                    var newQuantity = maxQuantity - quantity;

                    // Cập nhật giao diện
                    updateProductStatusUI(form, newQuantity);

                    alert("Sản phẩm đã được thêm vào giỏ hàng!");
                },
                error: function (xhr) {
                    if (xhr.responseJSON && xhr.responseJSON.message) {
                        alert(xhr.responseJSON.message); // Hiển thị thông báo lỗi từ server
                    } else {
                        alert("Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.");
                    }
                }
            });
        });
    });

    function updateProductStatusUI(form, newQuantity) {
        // Cập nhật số lượng tồn kho hiển thị
        var quantityText = form.closest(".product-item").find(".text-muted");
        if (newQuantity > 0) {
            quantityText.text("Còn lại: " + newQuantity + " sản phẩm");
        } else {
            quantityText.removeClass("text-muted").addClass("text-danger").text("Tạm hết hàng");
        }

        // Cập nhật input số lượng
        var quantityInput = form.find("input[name='num']");
        quantityInput.attr("max", newQuantity);
        if (newQuantity <= 0) {
            quantityInput.val(0).prop("disabled", true); // Vô hiệu hóa input
        }

        // Cập nhật nút thêm vào giỏ hàng
        var addToCartButton = form.find(".add-to-cart-btn");
        if (newQuantity <= 0) {
            addToCartButton
                .prop("disabled", true)
                .removeClass("btn-primary")
                .addClass("btn-secondary")
                .text("Hết hàng");
        }
    }
</script>


</html>