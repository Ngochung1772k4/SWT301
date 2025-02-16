<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Chi Tiết Sản Phẩm - Organic</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

        <link rel="stylesheet" type="text/css" href="style.css">


        <%@ include file="header.jsp" %>

        <style>
            .price {
                font-size: 1.5rem;
                font-weight: bold;
                color: green;
            }
            .old-price {
                font-size: 1.2rem;
                color: gray;
                text-decoration: line-through;
                margin-right: 10px;
            }
            .discount-badge {
                font-size: 1rem;
                color: white;
                background-color: red;
                border-radius: 5px;
                padding: 2px 5px;
                margin-left: 10px;
            }


        </style>
    </head>
    <body>

        <div class="container my-4">
            <div class="row">

                <div class="col-md-6">

                    <div class="main-image mb-3">
                        <img src="${product.imageUrl}" alt="${product.name}" id="mainImage" class="img-fluid rounded">
                    </div>

                    <div class="image-gallery d-flex flex-wrap gap-2">
                        <c:forEach var="image" items="${images}">
                            <img 
                                src="${image.url}" 
                                alt="${image.altText}" 
                                class="img-thumbnail" 
                                style="width: 100px; height: 100px; cursor: pointer;" 
                                onclick="changeMainImage('${image.url}')">
                        </c:forEach>
                    </div>
                </div>


                <div class="col-md-6">
                    <h1 class="section-title">${product.name}</h1>
                    <p class="text-muted">
                        Thương hiệu: 
                        <c:choose>
                            <c:when test="${supplier != null}">
                                <span class="text-primary fw-bold">${supplier.name}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-secondary">Không xác định</span>
                            </c:otherwise>
                        </c:choose>
                        | Tình trạng: 
                        <span class="product-status">
                            <c:choose>
                                <c:when test="${product.quantity > 0}">
                                    Còn hàng (${product.quantity} sản phẩm)
                                </c:when>
                                <c:otherwise>
                                    <span class="text-danger">Hết hàng</span>
                                </c:otherwise>
                            </c:choose>
                        </span>



                    <p class="text-muted">
                        Trọng lượng: 
                        <c:choose>
                            <c:when test="${product.weight != null}">
                                ${product.weight} 
                                <c:choose>
                                    <c:when test="${units != null}">
                                        ${units.name}
                                    </c:when>
                                    <c:otherwise>
                                        (Đơn vị không xác định)
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                Không xác định
                            </c:otherwise>
                        </c:choose>
                    </p>



                    <div class="price-details my-3">
                        <c:choose>
                            <c:when test="${product.discount > 0}">
                                <span class="old-price">
                                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" />đ
                                </span>
                                <span class="price">
                                    <fmt:formatNumber value="${product.price - (product.price * product.discount / 100)}" type="number" groupingUsed="true" />đ
                                </span>
                                <span class="discount-badge">-${product.discount}%</span>
                            </c:when>
                            <c:otherwise>
                                <span class="price">
                                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" />đ
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </div>


                    <form id="addToCartForm" action="addtocart" method="post">
                        <input type="hidden" name="id" value="${product.productId}">

                        <c:choose>
                            <c:when test="${product.quantity > 0}">
                                <div class="input-group my-3 w-50">
                                    <input type="number" class="form-control text-center" 
                                           name="num" 
                                           id="quantity" 
                                           value="1" 
                                           min="1"
                                           max="${product.quantity}"
                                           style="border: 2px solid #000; border-radius: 5px;">
                                </div>
                                <div class="d-flex gap-2">
                                    <button type="submit" class="btn btn-success rounded-1 px-4">Thêm Vào Giỏ Hàng</button>
                                   
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger mt-3">
                                    Sản phẩm tạm thời hết hàng
                                </div>
                                <div class="d-flex gap-2">
                                    <button type="button" class="btn btn-secondary rounded-1 px-4" disabled>Thêm Vào Giỏ Hàng</button>
                                  
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </form>


                    <div class="mt-4 border rounded p-3 bg-light">
                        <h5>Duy nhất tại Organic Shop</h5>
                        <ul class="list-unstyled">
                            <li><i class="bi bi-truck"></i> Free ship 5km cho đơn hàng từ 499k</li>
                            <li><i class="bi bi-clock"></i> Giao hàng trong 2h</li>   
                            <li><i class="bi bi-heart"></i> Thực phẩm hữu cơ tốt cho sức khỏe</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="mt-5">
                <h4 class="section-title">Mô tả sản phẩm</h4>
                <p>${product.description}</p>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>


                                        function changeMainImage(imageUrl) {
                                            document.getElementById('mainImage').src = imageUrl;
                                        }




        </script>
        
     <script>
    $(document).ready(function () {
        $("#addToCartForm").submit(function (event) {
            event.preventDefault();

            var form = $(this);
            var productId = form.find("input[name='id']").val();
            var quantityInput = form.find("input[name='num']");
            var quantity = parseInt(quantityInput.val());
            var maxQuantity = parseInt(quantityInput.attr('max'));

            // Kiểm tra số lượng hợp lệ
            if (quantity > maxQuantity) {
                alert("Số lượng vượt quá hàng tồn kho!");
                return;
            }

            $.ajax({
                url: 'addtocart',
                type: 'POST',
                data: {
                    id: productId,
                    num: quantity
                },
                success: function (response) {
                    // Cập nhật badge giỏ hàng
                    $(".badge").text(response.size);
                    
                    // Tính số lượng tồn kho mới
                    var newQuantity = maxQuantity - quantity;
                    
                    // Cập nhật giao diện
                    updateProductStatusUI(newQuantity);
                    
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

    function updateProductStatusUI(newQuantity) {
        // Cập nhật thông báo trạng thái
        var statusElement = $(".product-status");
        if (newQuantity > 0) {
            statusElement.html(`Còn hàng (${newQuantity} sản phẩm)`);
        } else {
            statusElement.html('<span class="text-danger">Hết hàng</span>');
        }

        // Cập nhật input số lượng
        var quantityInput = $("#quantity");
        quantityInput.attr("max", newQuantity);
        if (newQuantity <= 0) {
            quantityInput.val(0).prop("disabled", true); 
        }
        if (newQuantity <= 0) {
            $(".btn-success, .btn-primary")
                .prop("disabled", true)
                .removeClass("btn-success btn-primary")
                .addClass("btn-secondary");
        }
    }
</script>
            
             
    </body>
</html>