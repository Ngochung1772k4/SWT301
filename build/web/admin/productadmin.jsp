<%-- 
    Document   : productadmin
    Created on : 6 thg 2, 2025, 19:09:05
    Author     : BT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/admin_style.css">
        <link rel="stylesheet" href="../css/manager.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
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
                max-height: 580px; /* Đặt chiều cao tối đa của bảng */
                overflow: auto; /* Tạo thanh cuộn nếu bảng vượt quá kích thước tối đa */
            }
        </style>
        <script src="../js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="main" id="layoutSidenav_content">
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">List Product</h1>
                        <div class="add-action mb-2">
                            <a  class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addProductModal"><span>Add New Product</span></a>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Product List
                            </div>
                            <div class="card-body scrollable-table">
                                <table id="datatablesSimple" class="datatable-table" >
                                    <thead>
                                        <tr style="font-size: 13px">
                                            <th>Product ID</th>
                                            <th style="width: 8%">Category</th>
                                            <th style="width: 8%">Supplier</th>
                                            <th style="width: 20%">Image</th>
                                            <th style="width: 15%">Name</th>
                                            <th style="width: 7%">Weight</th>
                                            <th style="width: 7%">Unit</th>
                                            <th style="width: 7%">Price</th>
                                            <th style="width: 7%">Discount</th>
                                            <th>Created at</th>
                                            <th>Updated at</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listProduct}" var = "Product">
                                        <tr style="font-size: 13px">
                                            <td>${Product.productId}</td>
                                            <td>${Product.categoryName}</td>
                                            <td>${Product.supplierName}</td>
                                            <td>
                                                <img style="width: 50%" src="${not empty productImages[Product.productId] ? productImages[Product.productId][0].url : 'placeholder.jpg'}" alt="${Product.name}">
                                            </td>
                                            <td>${Product.name}</td>
                                            <td>${Product.weight}</td>
                                            <td>${Product.unitName}</td>
                                            <td>${Product.price}</td>
                                            <td>${Product.discount}</td>
                                            <td>${Product.createdAt}</td>
                                            <td>${Product.updatedAt}</td>
                                            <td>
                                                <a onclick="editProductInfo('${Product.productId}', '${Product.name}', '${Product.categoryId}', '${Product.supplierId}', '${Product.unitId}', '${Product.weight}',
                                                                '${Product.price}', '${Product.discount}', '${Product.description}', '${Product.quantity}')" style=" font-size: 15px; color: green" 
                                                   data-bs-toggle="modal" data-bs-target="#editProductModal" title="Edit"> <i class="lni lni-pencil"></i></a>
                                                <a onclick="ProductInfo('${Product.productId}', '${Product.name}')" style=" font-size: 15px; color: red"
                                                   data-bs-toggle="modal" data-bs-target="#deleteProductModal" title="Delete"> <i class="lni lni-trash-can"></i></a>
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

        <!--Add product modal-->
        <div id="addProductModal" class="modal fade">
            <div class="modal-dialog" style="margin-left: 450px;">
                <div class="modal-content modal-add" style="width: 150%;">
                    <form action="addProductAdmin" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Category:</label>
                                <select name="category_id">
                                    <c:forEach items="${listCategory}" var="Category">
                                        <option value="${Category.categoryId}">${Category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Supplier:</label>
                                <select name="supplier_id">
                                    <c:forEach items="${listSupplier}" var="Supplier">
                                        <option value="${Supplier.supplierId}">${Supplier.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Unit:</label>
                                <select name="unit_id">
                                    <c:forEach items="${listUnit}" var="Unit">
                                        <option value="${Unit.unitId}">${Unit.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Images</label>
                                <div id="imageFields">
                                    <div class="image-input mb-2">
                                        <input type="text" name="imageUrl" class="form-control mb-1" placeholder="Image URL" required>
                                        <input type="text" name="altText" class="form-control mb-1" placeholder="Alt Text" required>
                                        <button type="button" class="btn btn-danger btn-sm remove-image">Remove</button>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-primary btn-sm" id="addImage">Add Image</button>
                            </div>
                            <div class="form-group">
                                <label>Weight</label>
                                <input name="weight" type="text" class="form-control" placeholder="g" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" placeholder="VND" required>
                            </div>
                            <div class="form-group">
                                <label>Discount</label>
                                <input name="discount" type="text" class="form-control" placeholder="%" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" type="text" class="form-control" required></textarea>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Delete Product modal -->
        <div id="deleteProductModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="deleteProductAdmin" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Product</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <input type="hidden" name="product_id" id="product_idInput"/>
                                <span>Bạn có chắc chắn muốn xóa sản phẩm <span style="color: blue" id="product_nameInput"></span> không?</span>
                                <p class="text-warning"><small>Hành động này không thể được hoàn tác.</small></p>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Edit product modal-->
        <div id="editProductModal" class="modal fade">
            <div class="modal-dialog" style="margin-left: 450px;">
                <div class="modal-content modal-add" style="width: 150%;">
                    <form action="editProductAdmin" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Product</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <input type="hidden" name="product_id" id="product_idInput2"/>
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" id="nameInput2" required value="${Product.name}">
                            </div>
                            <div class="form-group">
                                <label>Category:</label>
                                <select name="category_id" id="category_idInput2">
                                    <c:forEach items="${listCategory}" var="Category">
                                        <option value="${Category.categoryId}">${Category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Supplier:</label>
                                <select name="supplier_id" id="supplier_idInput2">
                                    <c:forEach items="${listSupplier}" var="Supplier">
                                        <option value="${Supplier.supplierId}">${Supplier.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Unit:</label>
                                <select name="unit_id" id="unit_idInput2">
                                    <c:forEach items="${listUnit}" var="Unit">
                                        <option value="${Unit.unitId}">${Unit.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                             <!-- Thêm phần xử lý ảnh -->
                    <div class="form-group">
                        <label>Images</label>
                        <div id="imageFieldsEdit"> <!-- Khung chứa các ảnh -->
                            <!-- Các trường ảnh sẽ được thêm động bằng JavaScript -->
                        </div>
                        <button type="button" class="btn btn-primary btn-sm" id="addImageEdit">Add Image</button>
                    </div>
                           
                            <div class="form-group">
                                <label>Weight</label>
                                <input name="weight" type="text" class="form-control" id="weightInput2" placeholder="g" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" id="priceInput2" placeholder="VND" required>
                            </div>
                            <div class="form-group">
                                <label>Discount</label>
                                <input name="discount" type="text" class="form-control" id="discountInput2" placeholder="%" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="text" class="form-control" id="quantity2" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" type="text" class="form-control" id="description2" required></textarea>
                            </div>   
                        </div>
                        <div class="modal-footer">
                            <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Edit">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function ProductInfo(product_id, name) {
                document.getElementById('product_idInput').value = product_id;
                document.getElementById('product_nameInput').textContent = name;
            }

            function editProductInfo(product_id, name, category_id, supplier_id, unit_id, weight, price, discount, description, quantity, imagesJson) {
                // Điền thông tin cơ bản
                document.getElementById('product_idInput2').value = product_id;
                document.getElementById('nameInput2').value = name;
                document.getElementById('category_idInput2').value = category_id;
                document.getElementById('supplier_idInput2').value = supplier_id;
                document.getElementById('unit_idInput2').value = unit_id;
                document.getElementById('weightInput2').value = weight;
                document.getElementById('priceInput2').value = price;
                document.getElementById('discountInput2').value = discount;
                document.getElementById('quantity2').value = quantity;
                document.getElementById('description2').value = description;

                // Xử lý ảnh
                const imageFieldsEdit = document.getElementById('imageFieldsEdit');
                imageFieldsEdit.innerHTML = '';
                if (imagesJson) {
                    const images = JSON.parse(imagesJson);
                    images.forEach(image => {
                        const newImageField = document.createElement('div');
                        newImageField.className = 'image-input mb-2';
                        newImageField.innerHTML = `
                    <input type="text" name="imageUrl" class="form-control mb-1" value="${image.url}" placeholder="Image URL" required>
                    <input type="text" name="altText" class="form-control mb-1" value="${image.altText}" placeholder="Alt Text" required>
                    <button type="button" class="btn btn-danger btn-sm remove-image">Remove</button>
                `;
                        imageFieldsEdit.appendChild(newImageField);
                    });
                }
            }

            }
        </script>
        <script>
            // Xử lý thêm ảnh cho modal thêm sản phẩm
            document.getElementById('addImage').addEventListener('click', function () {
                const imageFields = document.getElementById('imageFields');
                const newImageField = document.createElement('div');
                newImageField.className = 'image-input mb-2';
                newImageField.innerHTML = `
            <input type="text" name="imageUrl" class="form-control mb-1" placeholder="Image URL" required>
            <input type="text" name="altText" class="form-control mb-1" placeholder="Alt Text" required>
            <button type="button" class="btn btn-danger btn-sm remove-image">Remove</button>
        `;
                imageFields.appendChild(newImageField);
            });

            // Xử lý thêm ảnh cho modal sửa sản phẩm
            document.getElementById('addImageEdit').addEventListener('click', function () {
                const imageFieldsEdit = document.getElementById('imageFieldsEdit');
                const newImageField = document.createElement('div');
                newImageField.className = 'image-input mb-2';
                newImageField.innerHTML = `
            <input type="text" name="imageUrl" class="form-control mb-1" placeholder="Image URL" required>
            <input type="text" name="altText" class="form-control mb-1" placeholder="Alt Text" required>
            <button type="button" class="btn btn-danger btn-sm remove-image">Remove</button>
        `;
                imageFieldsEdit.appendChild(newImageField);
            });

            // Xử lý xóa trường ảnh
            document.addEventListener('click', function (e) {
                if (e.target && e.target.classList.contains('remove-image')) {
                    e.target.closest('.image-input').remove();
                }
            });     
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="../js/hamburger.js"></script>
        <script type="text/javascript" src="../js/admin/modal.js"></script>
    </body>
</html>

