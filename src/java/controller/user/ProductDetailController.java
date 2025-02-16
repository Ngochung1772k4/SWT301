package controller.user;

import DAL.ImagesDAO;
import DAL.ProductDAO;
import DAL.SuppliersDAO;
import DAL.UnitsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Images;
import model.Product;
import model.Suppliers;
import model.Units;

@WebServlet(name = "ProductDetailController", urlPatterns = {"/ProductDetail"})
public class ProductDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");

        try {
            // 1. Validate productId
            int productId = Integer.parseInt(productIdStr);
            
            // 2. Khởi tạo DAO
            ProductDAO productDAO = new ProductDAO();
            SuppliersDAO supplierDAO = new SuppliersDAO();
            ImagesDAO imagesDAO = new ImagesDAO();
            UnitsDAO unitsDAO = new UnitsDAO();

            // 3. Lấy thông tin sản phẩm
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại");
                return;
            }

            // 4. Lấy dữ liệu liên quan
            Suppliers supplier = product.getSupplierId() != null 
                                ? supplierDAO.getSupplierById(product.getSupplierId()) 
                                : null;
            List<Images> images = imagesDAO.getImagesByProductId(productId);
            Units unit = product.getUnitId() != null 
                        ? unitsDAO.getUnitById(product.getUnitId()) 
                        : null;

            // 5. Set attributes
            request.setAttribute("product", product);
            request.setAttribute("supplier", supplier);
            request.setAttribute("images", images);
            request.setAttribute("unit", unit);

            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}