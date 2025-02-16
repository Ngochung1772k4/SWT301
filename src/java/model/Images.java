package model;

import java.sql.Timestamp;

public class Images {
    private int imageId;
    private Integer productId; 
    private Integer feedbackId; 
    private Integer blogId;     
    private String url;
    private String altText;    
    private Timestamp createdAt;

    public Images() {
    }

    public Images(int imageId, Integer productId, Integer feedbackId, Integer blogId, String url, String altText, Timestamp createdAt) {
        this.imageId = imageId;
        this.productId = productId;
        this.feedbackId = feedbackId;
        this.blogId = blogId;
        this.url = url;
        this.altText = altText;
        this.createdAt = createdAt;
    }
   

  
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

  
    @Override
    public String toString() {
        return "Images{" +
                "imageId=" + imageId +
                ", productId=" + productId +
                ", feedbackId=" + feedbackId +
                ", blogId=" + blogId +
                ", url='" + url + '\'' +
                ", altText='" + altText + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
