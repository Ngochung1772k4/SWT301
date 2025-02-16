/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ngoch
 */
import java.util.Date;

public class Coupon {

    private int couponId;
    private String code;
    private int discountPercentage;
    private int maxDiscountAmount;
    private Date expirationDate;
    private int usageLimit;
    private boolean applicableToAllCategories;
     private int createdBy;

    public Coupon() {
    }

    public Coupon(int couponId, String code, int discountPercentage, int maxDiscountAmount, Date expirationDate, int usageLimit, boolean applicableToAllCategories, int createdBy) {
        this.couponId = couponId;
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.maxDiscountAmount = maxDiscountAmount;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.applicableToAllCategories = applicableToAllCategories;
        this.createdBy = createdBy;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public void setMaxDiscountAmount(int maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public boolean isApplicableToAllCategories() {
        return applicableToAllCategories;
    }

    public void setApplicableToAllCategories(boolean applicableToAllCategories) {
        this.applicableToAllCategories = applicableToAllCategories;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    
        public boolean isValid() {
        Date now = new Date();
        return now.before(expirationDate) && usageLimit > 0;
    }

    @Override
    public String toString() {
        return "Coupon{" + "couponId=" + couponId + ", code=" + code + ", discountPercentage=" + discountPercentage + ", maxDiscountAmount=" + maxDiscountAmount + ", expirationDate=" + expirationDate + ", usageLimit=" + usageLimit + ", applicableToAllCategories=" + applicableToAllCategories + ", createdBy=" + createdBy + '}';
    }

    

}
