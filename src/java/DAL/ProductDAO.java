/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Timestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Images;
import model.Product;
import model.Suppliers;
import model.Units;

/**
 *
 * @author ngoch
 */
public class ProductDAO extends DBcontext {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {

            Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Product WHERE deleted = 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getObject("supplier_id") != null ? rs.getInt("supplier_id") : null);
                product.setUnitId(rs.getObject("unit_id") != null ? rs.getInt("unit_id") : null);
                product.setName(rs.getString("name"));
                product.setDiscount(rs.getInt("discount"));
                product.setDescription(rs.getString("description"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                product.setDeleted(rs.getInt("deleted"));
                product.setPrice(rs.getInt("price"));
                product.setWeight(rs.getObject("weight") != null ? rs.getInt("weight") : null);
                product.setQuantity(rs.getInt("quantity"));
                CategoryDAO categoryDAO = new CategoryDAO();
                SuppliersDAO supplierDAO = new SuppliersDAO();
                UnitsDAO unitDAO = new UnitsDAO();

                Category category = categoryDAO.getCategoryById(product.getCategoryId());
                if (category != null) {
                    product.setCategoryName(category.getName());
                }

                Suppliers supplier = supplierDAO.getSupplierById(product.getSupplierId());
                if (supplier != null) {
                    product.setSupplierName(supplier.getName());
                }

                Units unit = unitDAO.getUnitById(product.getUnitId());
                if (unit != null) {
                    product.setUnitName(unit.getName());
                }
                products.add(product);
                ImagesDAO imageDao = new ImagesDAO();
                List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                if (!productImages.isEmpty()) {
                    product.setImageUrl(productImages.get(0).getUrl());
                } else {
                    product.setImageUrl("/images/placeholder.jpg");
                }
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        try {

            Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Product WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getObject("supplier_id") != null ? rs.getInt("supplier_id") : null);
                product.setUnitId(rs.getObject("unit_id") != null ? rs.getInt("unit_id") : null);
                product.setName(rs.getString("name"));
                product.setDiscount(rs.getInt("discount"));
                product.setDescription(rs.getString("description"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                product.setDeleted(rs.getInt("deleted"));
                product.setPrice(rs.getInt("price"));
                product.setWeight(rs.getObject("weight") != null ? rs.getInt("weight") : null);
                product.setQuantity(rs.getInt("quantity"));
                ImagesDAO imageDao = new ImagesDAO();
                List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                if (!productImages.isEmpty()) {
                    product.setImageUrl(productImages.get(0).getUrl());
                } else {
                    product.setImageUrl("/images/placeholder.jpg");
                }
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProductsByCategory(int categotyId) {
        List<Product> pro = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE category_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categotyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    product.setDescription(rs.getString("description"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setDiscount(rs.getInt("discount"));
                    ImagesDAO imageDao = new ImagesDAO();
                    List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                    if (!productImages.isEmpty()) {
                        product.setImageUrl(productImages.get(0).getUrl());
                    } else {
                        product.setImageUrl("/images/placeholder.jpg");
                    }
                    pro.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    public List<Product> getProductsBySupplierId(int supplierId) {
        List<Product> pro = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE supplier_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    product.setDescription(rs.getString("description"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setDiscount(rs.getInt("discount"));
                    pro.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    // Phương thức thêm sản phẩm và ảnh
    public boolean insertProduct(Product product, List<Images> images) {
        Connection conn = null;
        PreparedStatement psProduct = null;
        PreparedStatement psImage = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            conn = DBcontext.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Thêm sản phẩm vào bảng Product
            String sqlProduct = "INSERT INTO Product (category_id, supplier_id, unit_id, name, discount, description, price, weight, quantity, created_at, updated_at, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psProduct = conn.prepareStatement(sqlProduct, PreparedStatement.RETURN_GENERATED_KEYS);
            psProduct.setInt(1, product.getCategoryId());
            psProduct.setObject(2, product.getSupplierId());
            psProduct.setObject(3, product.getUnitId());
            psProduct.setString(4, product.getName());
            psProduct.setInt(5, product.getDiscount());
            psProduct.setString(6, product.getDescription());
            psProduct.setInt(7, product.getPrice());
            psProduct.setObject(8, product.getWeight());
            psProduct.setInt(9, product.getQuantity());
            psProduct.setTimestamp(10, new java.sql.Timestamp(product.getCreatedAt().getTime()));
            psProduct.setTimestamp(11, new java.sql.Timestamp(product.getUpdatedAt().getTime()));
            psProduct.setInt(12, product.getDeleted());

            int rowsAffected = psProduct.executeUpdate();
            if (rowsAffected > 0) {
                rs = psProduct.getGeneratedKeys();
                if (rs.next()) {
                    int productId = rs.getInt(1); // Lấy ID của sản phẩm vừa thêm

                    // Thêm ảnh vào bảng Images
                    String sqlImage = "INSERT INTO Images (product_id, url, alt_text, created_at) VALUES (?, ?, ?, ?)";
                    psImage = conn.prepareStatement(sqlImage);

                    for (Images image : images) {
                        psImage.setInt(1, productId);
                        psImage.setString(2, image.getUrl());
                        psImage.setString(3, image.getAltText());
                        psImage.setTimestamp(4, new java.sql.Timestamp(image.getCreatedAt().getTime()));
                        psImage.addBatch();
                    }

                    psImage.executeBatch(); // Thực thi batch insert
                    conn.commit(); // Commit transaction
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback nếu có lỗi
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psProduct != null) {
                    psProduct.close();
                }
                if (psImage != null) {
                    psImage.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean updateProduct(Product product, List<Images> images) {
        Connection conn = null;
        PreparedStatement psProduct = null;
        PreparedStatement psImage = null;
        boolean result = false;

        try {
            conn = DBcontext.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Cập nhật thông tin sản phẩm trong bảng Product
            String sqlProduct = "UPDATE Product SET category_id = ?, supplier_id = ?, unit_id = ?, name = ?, discount = ?, description = ?, price = ?, weight = ?, quantity = ?, updated_at = ? WHERE product_id = ?";
            psProduct = conn.prepareStatement(sqlProduct);
            psProduct.setInt(1, product.getCategoryId());
            psProduct.setObject(2, product.getSupplierId());
            psProduct.setObject(3, product.getUnitId());
            psProduct.setString(4, product.getName());
            psProduct.setInt(5, product.getDiscount());
            psProduct.setString(6, product.getDescription());
            psProduct.setInt(7, product.getPrice());
            psProduct.setObject(8, product.getWeight());
            psProduct.setInt(9, product.getQuantity());
            psProduct.setTimestamp(10, new java.sql.Timestamp(product.getUpdatedAt().getTime()));
            psProduct.setInt(11, product.getProductId());

            int rowsAffected = psProduct.executeUpdate();
            if (rowsAffected > 0) {
                // Xóa các ảnh cũ của sản phẩm
                String sqlDeleteImages = "DELETE FROM Images WHERE product_id = ?";
                psImage = conn.prepareStatement(sqlDeleteImages);
                psImage.setInt(1, product.getProductId());
                psImage.executeUpdate();

                // Thêm các ảnh mới vào bảng Images
                String sqlInsertImage = "INSERT INTO Images (product_id, url, alt_text, created_at) VALUES (?, ?, ?, ?)";
                psImage = conn.prepareStatement(sqlInsertImage);

                for (Images image : images) {
                    psImage.setInt(1, product.getProductId());
                    psImage.setString(2, image.getUrl());
                    psImage.setString(3, image.getAltText());
                    psImage.setTimestamp(4, new java.sql.Timestamp(image.getCreatedAt().getTime()));
                    psImage.addBatch();
                }

                psImage.executeBatch(); // Thực thi batch insert
                conn.commit(); // Commit transaction
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback nếu có lỗi
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (psProduct != null) {
                    psProduct.close();
                }
                if (psImage != null) {
                    psImage.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean deleteProduct(int productId) {
        Connection conn = null;
        PreparedStatement psProduct = null;
        PreparedStatement psImage = null;
        boolean result = false;

        try {
            conn = DBcontext.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Xóa các ảnh liên quan đến sản phẩm
            String sqlDeleteImages = "DELETE FROM Images WHERE product_id = ?";
            psImage = conn.prepareStatement(sqlDeleteImages);
            psImage.setInt(1, productId);
            psImage.executeUpdate();

            // Xóa sản phẩm khỏi bảng Product
            String sqlDeleteProduct = "DELETE FROM Product WHERE product_id = ?";
            psProduct = conn.prepareStatement(sqlDeleteProduct);
            psProduct.setInt(1, productId);

            int rowsAffected = psProduct.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit(); // Commit transaction
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback nếu có lỗi
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (psProduct != null) {
                    psProduct.close();
                }
                if (psImage != null) {
                    psImage.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Tạo đối tượng Product mẫu
        Product product = new Product();
        product.setProductId(19); // Giả sử sản phẩm có ID là 1
        product.setCategoryId(2);
        product.setSupplierId(3);
        product.setUnitId(1);
        product.setName("Sản phẩm mới");
        product.setDiscount(10);
        product.setDescription("Mô tả sản phẩm mới");
        product.setPrice(500000);
        product.setWeight(12);
        product.setQuantity(100);
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        List<Images> images = new ArrayList<>();

        // Tạo danh sách ảnh mẫu
        images.add(new Images(1, null, null, null, "https://example.com/image1.jpg", "Hình 1", new Timestamp(System.currentTimeMillis())));
        images.add(new Images(2, null, null, null, "https://example.com/image2.jpg", "Hình 2", new Timestamp(System.currentTimeMillis())));

        // Tạo đối tượng DAO và gọi phương thức updateProduct
        ProductDAO productDAO = new ProductDAO();
        boolean isUpdated = productDAO.updateProduct(product, images);

        // Kiểm tra kết quả
        if (isUpdated) {
            System.out.println("Cập nhật sản phẩm thành công!");
        } else {
            System.out.println("Cập nhật sản phẩm thất bại!");
        }
    }

    public List<Product> getDiscountedProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT Top 5* FROM Product WHERE discount > 0 AND deleted = 0 ORDER BY discount DESC";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setDiscount(rs.getInt("discount"));
                 ImagesDAO imageDao = new ImagesDAO();
                   product.setQuantity(rs.getInt("quantity"));
                List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                if (!productImages.isEmpty()) {
                    product.setImageUrl(productImages.get(0).getUrl());
                } else {
                    product.setImageUrl("/images/placeholder.jpg");
                }
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getNewArrivalProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP 5 * FROM Product WHERE deleted = 0 ORDER BY created_at DESC";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setDiscount(rs.getInt("discount"));
                 product.setQuantity(rs.getInt("quantity"));
                product.setCreatedAt(rs.getTimestamp("created_at")); // Nếu có trường này trong Product
                  ImagesDAO imageDao = new ImagesDAO();
                List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                if (!productImages.isEmpty()) {
                    product.setImageUrl(productImages.get(0).getUrl());
                } else {
                    product.setImageUrl("/images/placeholder.jpg");
                }
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    
    public List<Product> searchProductsByNameAndCategory(String productName, int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE [name] LIKE ? AND category_id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + productName + "%");
            ps.setInt(2, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    product.setDescription(rs.getString("description"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setDiscount(rs.getInt("discount"));
                      ImagesDAO imageDao = new ImagesDAO();
                List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                
                if (!productImages.isEmpty()) {
                    product.setImageUrl(productImages.get(0).getUrl()); 
                } else {
                    product.setImageUrl("/images/placeholder.jpg"); 
                }
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> searchProductsByName(String searchText) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE [name] LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + searchText + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    product.setDescription(rs.getString("description"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setDiscount(rs.getInt("discount"));
                    ImagesDAO imageDao = new ImagesDAO();
                    List<Images> productImages = imageDao.getImagesByProductId(product.getProductId());

                    if (!productImages.isEmpty()) {
                        product.setImageUrl(productImages.get(0).getUrl());
                    } else {
                        product.setImageUrl("/images/placeholder.jpg");
                    }
                    productList.add(product);
                }
            }

            if (productList.isEmpty()) {
                System.out.println("No products found matching the search text: " + searchText);
            }

        } catch (Exception e) {
            System.err.println("Error while fetching products: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }

    public int getQuantityByProductId(int productId) {
        int quantity = 0;
        String sql = "SELECT quantity FROM Product WHERE product_id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public boolean updateProductQuantity(int productId, int quantity) {
        String sql = "UPDATE product SET quantity = quantity - ? WHERE product_id = ? AND quantity >= ?";
        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, quantity); // Chỉ trừ khi số lượng hiện tại >= số lượng yêu cầu
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
   
     public boolean restoreProductQuantity(int productId, int quantity) {
    String sql = "UPDATE product SET quantity = quantity + ? WHERE product_id = ?";
    try (Connection conn = DBcontext.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, quantity);
        ps.setInt(2, productId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}
