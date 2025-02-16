package model;

public class OrderDetails {
    private int orderId;
    private Product product;
    private int price;
    private int amount;
      private String name, imageUrl;
    
    public OrderDetails() {
    }

    public OrderDetails(int orderId, int price, int amount, String name, String imageUrl) {
        this.orderId = orderId;
        this.price = price;
        this.amount = amount;
        this.name = name;
        this.imageUrl = imageUrl;
    }
            

    public OrderDetails(int orderId, Product product, int price, int amount) {
        this.orderId = orderId;
        this.product = product;
        this.price = price;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderId=" + orderId + ", product=" + product + ", price=" + price + ", amount=" + amount + '}';
    }
    

    
}