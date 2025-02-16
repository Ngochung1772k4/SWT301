<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Shopping Cart</title>
        <link rel="stylesheet" type="text/css" href="css/cart.css">

    </head>
    <body>
        <h1>Shopping Cart</h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="coupon-form">
                        <label for="coupon_codeInput">Coupon Code</label>
                        <input type="text" name="coupon_code" class="form-control" id="coupon_codeInput">
                        <button type="button" id="applyCouponBtn" class="btn btn-primary mt-2">Apply </button>
                        <span id="couponMessage" class="text-danger"></span>
                    </div>
                </div>
            </div>
        </div>
        <br>

        <table>


            <c:set var="o" value="${sessionScope.cart}"/> 
            <c:set var="t" value="0"/> 

            <c:forEach items="${o.items}" var="i"> 
                <c:set var="t" value="${t + 1}"/> 
                <tr> 

                    <td>
                        <img src="${i.product.imageUrl}" alt="${i.product.name}" class="product-image"/>
                    </td>
                    <td>${i.product.name}</td> 
                    <td> 
                        <div class="quantity-control">
                            <button><a href="updatecart?num=-1&id=${i.product.productId}">-</a></button>
                            <input type="text" readonly value="${i.quantity}"/> 
                            <button><a href="updatecart?num=1&id=${i.product.productId}">+</a></button>
                        </div>
                    </td>
                    <td><fmt:formatNumber value="${i.product.getDiscountPrice()}" pattern="#,##0 đ"/></td> 


                    <td> 
                        <form action="removefromcart" method="post"> 
                            <input type="hidden" name="id" value="${i.product.productId}"/> 
                            <input type="submit" value="Remove"/> 
                        </form> 
                    </td>
                </tr>
            </c:forEach>   
        </table>
        <div class="total-money-container">
            <strong>Total Amount:</strong> 
            <span class="total-amount">
                <fmt:formatNumber value="${cart.totalMoney}" pattern="#,##0 đ"/>
            </span>
            <c:if test="${cart.appliedCoupon != null}">
                <br>
                <small class="coupon-applied">
                    (Đã áp dụng mã <strong>${cart.appliedCoupon.code}</strong>)
                    <button type="button" id="removeCouponBtn" class="btn btn-danger btn-sm">Hủy</button>
                </small>
            </c:if> 
        </div>



        <form action="checkout" method="post">
            <input type="submit" value="Check out" class="checkout-button"/>
        </form>

        <h2>
            <a href="home" class="continue-shopping">Continue Shopping</a>
        </h2>

        <!-- Modal xác nhận thông tin đặt hàng -->
        <div id="confirmInfoModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="checkout" method="post">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirm order information</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="account_id" id="account_idInput">
                            <input type="hidden" name="total_money" id="total_moneyInput">

                            <div class="form-group">
                                <label>Họ và tên</label>
                                <input type="text" name="fullname" class="form-control" id="fullnameInput" required>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <input type="text" name="phone_number" class="form-control" id="phone_numberInput" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" name="email" class="form-control" id="emailInput" required>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <input type="text" name="address" class="form-control" id="addressInput" required>
                            </div>

                            <!-- Payment Method Section -->
                            <div class="form-group">
                                <label>Phương thức thanh toán</label>
                                <select name="payment_method" class="form-control" id="paymentMethodSelect" required>
                                    <option value="COD">Thanh toán khi nhận hàng (COD)</option>
                                    <option value="ATM">Thẻ ATM</option>
                                </select>
                            </div>

                            <!-- ATM Fields (Initially Hidden) -->
                            <div id="atmFields" style="display: none;">
                                <div class="form-group">
                                    <label>Số thẻ</label>
                                    <input type="text" name="card_number" class="form-control" placeholder="1234 5678 9012 3456">
                                </div>
                                <div class="form-group">
                                    <label>Ngày hết hạn</label>
                                    <input type="text" name="expiry_date" class="form-control" placeholder="MM/YY">
                                </div>
                                <div class="form-group">
                                    <label>CVV</label>
                                    <input type="text" name="cvv" class="form-control" placeholder="123">
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Ghi chú</label>
                                <textarea name="note" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-primary">Xác nhận đặt hàng</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            // Hiển thị trường thẻ khi chọn ATM
            $('select[name="payment_method"]').change(function () {
                if ($(this).val() === 'ATM') {
                    $('#atmFields').show();
                } else {
                    $('#atmFields').hide();
                }
            });
        </script>

        <script>
            // Hàm xác nhận thông tin
            function confirmInfo(account_id, fullname, phone_number, email, address, total_money) {
                document.getElementById('account_idInput').value = account_id;
                document.getElementById('fullnameInput').value = fullname;
                document.getElementById('phone_numberInput').value = phone_number;
                document.getElementById('emailInput').value = email;
                document.getElementById('addressInput').value = address;
                document.getElementById('total_moneyInput').value = total_money;
            }
        </script>
        <script>
                document.querySelector('.checkout-button').addEventListener('click', function (event) {
                    event.preventDefault(); // Ngăn chặn hành động mặc định của form

                    // Lấy thông tin từ session hoặc từ các biến khác
                    const account_id = ${sessionScope.account.account_id}; // Lấy account_id từ session
                    const fullname = "${sessionScope.account.fullname}";
                    const phone_number = "${sessionScope.account.phone_number}";
                    const email = "${sessionScope.account.email}";
                    const address = "${sessionScope.account.address}";
                    const total_money = ${sessionScope.cart.totalMoney};

                    // Gọi hàm confirmInfo để điền thông tin vào modal
                    confirmInfo(account_id, fullname, phone_number, email, address, total_money);

                    // Hiển thị modal
                    $('#confirmInfoModal').modal('show');
                });
        </script>
        <script>
            $(document).ready(function () {
                $('#applyCouponBtn').click(function () {
                    const code = $('#coupon_codeInput').val();
                    $.post('applycoupon', {coupon_code: code}, function (response) {
                        if (response === 'success') {
                            $('#couponMessage').text('Áp dụng mã thành công').removeClass('text-danger').addClass('text-success');
                            location.reload(); 
                        } else {
                            $('#couponMessage').text('Mã không hợp lệ').removeClass('text-success').addClass('text-danger');
                        }
                    });
                });
            });
        </script>
        <script>
    $(document).ready(function() {
        $('#removeCouponBtn').click(function() {
            $.post('applycoupon', { action: 'remove' }, function(response) {
                if (response === 'success') {
                    location.reload(); 
                } else {
                    alert('Có lỗi xảy ra');
                }
            });
        });
    });
</script>

    </body>
</html>