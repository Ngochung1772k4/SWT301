package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Images;

public class ImagesDAO extends DBcontext {

    public List<Images> getImagesByProductId(int productId) {
        List<Images> images = new ArrayList<>();
        try {

            Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Images WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Images image = new Images();
                image.setImageId(rs.getInt("image_id"));
                image.setProductId(rs.getObject("product_id") != null ? rs.getInt("product_id") : null);
                image.setFeedbackId(rs.getObject("feedback_id") != null ? rs.getInt("feedback_id") : null);
                image.setBlogId(rs.getObject("blog_id") != null ? rs.getInt("blog_id") : null);
                image.setUrl(rs.getString("url"));
                image.setAltText(rs.getString("alt_text"));
                image.setCreatedAt(rs.getTimestamp("created_at"));
                images.add(image);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }
    
    public List<Images> getAllImages() {
        List<Images> images = new ArrayList<>();
        try {
            java.sql.Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Images";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Images image= new Images();
                image.setImageId(rs.getInt("imageId"));
                image.setProductId(rs.getInt("productId"));
                image.setFeedbackId(rs.getInt("feedbackId"));                        
                image.setBlogId(rs.getInt("blogId"));
                image.setUrl(rs.getString("url"));
                image.setAltText(rs.getString("altText"));
                image.setCreatedAt(rs.getTimestamp("createdAt"));
                images.add(image);

            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }
}
