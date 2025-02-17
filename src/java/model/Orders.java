package model;

import java.util.Date;
import java.util.List;

public class Orders {
    private int orderId;
    private int accountId;
    private Integer couponId; 
    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Date orderDate;
    private int status;
    private int totalMoney;
    private List<OrderDetails> list;
    
    private Payment payment;

    public Orders() {
    }

    public Orders(int orderId, int accountId, Integer couponId, String fullname, String email, String phoneNumber, String address, String note, Date orderDate, int status, int totalMoney) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.couponId = couponId;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.note = note;
        this.orderDate = orderDate;
        this.status = status;
        this.totalMoney = totalMoney;
    }
    public Orders(int accountId, String fullname, String email, String phoneNumber, String address, String note, Date orderDate, int status, int totalMoney) {
    this.accountId = accountId;
    this.fullname = fullname;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.note = note;
    this.orderDate = orderDate;
    this.status = status;
    this.totalMoney = totalMoney;
}


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<OrderDetails> getList() {
        return list;
    }

    public void setList(List<OrderDetails> list) {
        this.list = list;
    }
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
 


}