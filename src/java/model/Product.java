package model;

import java.util.Date;

public class Product {
    private int productId;
    private int categoryId;
    private Integer supplierId; 
    private Integer unitId;     
    private String name;
    private int discount;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private int deleted;
    private int price;
    private Integer weight;     
    private int quantity;
    private String imageUrl;
    private String unitName;
    private String supplierName;
    private String categoryName;

    
    public Product() {
    }

    public Product(int productId, int categoryId, Integer supplierId, Integer unitId, String name, int discount, String description, Date createdAt, Date updatedAt, int deleted, int price, Integer weight, int quantity, String imageUrl) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.unitId = unitId;
        this.name = name;
        this.discount = discount;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
   
    
    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", unitId=" + unitId + ", name=" + name + ", discount=" + discount + ", description=" + description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted + ", price=" + price + ", weight=" + weight + ", quantity=" + quantity + ", imageUrl=" + imageUrl + '}';
    }


    public int getDiscountPrice() {
    if (discount > 0) {
        return price - (price * discount / 100);
    }
    return price;
}
}