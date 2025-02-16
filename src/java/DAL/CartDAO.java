package DAL;

import model.Cart;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public class CartDAO extends DBcontext {

   
    public void saveCart(int accountId, Cart cart) {
       
        deleteCartByAccountId(accountId);

       
        for (Item item : cart.getItems()) {
            saveCartItem(accountId, item);
        }
    }

    
    public void deleteCartByAccountId(int accountId) {
        String sql = "DELETE FROM cart WHERE account_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void saveCartItem(int accountId, Item item) {
        String sql = "INSERT INTO cart (account_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            statement.setInt(2, item.getProduct().getProductId());
            statement.setInt(3, item.getQuantity());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
   public Cart loadCart(int accountId) {
    Cart cart = new Cart();
    String sql = "SELECT product_id, quantity FROM cart WHERE account_id = ?";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, accountId);
        ResultSet resultSet = statement.executeQuery();

    
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            int quantity = resultSet.getInt("quantity");

            
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);

    
            int price = product.getPrice();  

       
            Item item = new Item(product, quantity, price);
            cart.addItem(item);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return cart;
}


}
