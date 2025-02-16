package controller.user;

import DAL.CategoryDAO;
import DAL.ImagesDAO;
import DAL.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Images;
import model.Product;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        // Khởi tạo DAO
        ProductDAO productDAO = new ProductDAO();
        ImagesDAO imagesDAO = new ImagesDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            // 1. Lấy danh sách danh mục
            List<Category> listCate = categoryDAO.getAllCategories();
            request.setAttribute("listCate", listCate);

            // 2. Lấy sản phẩm giảm giá
            List<Product> discountedProducts = productDAO.getDiscountedProducts();
            request.setAttribute("discountedProducts", discountedProducts);

            // 3. Lấy sản phẩm mới
            List<Product> newArrivalProducts = productDAO.getNewArrivalProducts();
            request.setAttribute("newArrivalProducts", newArrivalProducts);

            // 4. Tạo map hình ảnh cho sản phẩm
            Map<Integer, List<Images>> productImages = new HashMap<>();
            addProductImages(productImages, discountedProducts, imagesDAO);
            addProductImages(productImages, newArrivalProducts, imagesDAO);
            request.setAttribute("productImages", productImages);

            // Forward to JSP
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    private void addProductImages(Map<Integer, List<Images>> productImages, 
                                 List<Product> products, 
                                 ImagesDAO imagesDAO) {
        for (Product product : products) {
            List<Images> images = imagesDAO.getImagesByProductId(product.getProductId());
            productImages.put(product.getProductId(), images);
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