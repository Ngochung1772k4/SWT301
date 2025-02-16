/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Account {
     private int account_id, role_id;
    private String fullname, email, phone_number, address, account_name, password;
    private Timestamp created_at, updated_at;
    private int deleted;
    private Date date_of_birth;
    private int gender;
    private String avatar;

    public Account() {
    }

    public Account(int account_id, int role_id, String fullname, String email, String phone_number, String address, String account_name, String password, Timestamp created_at, Timestamp updated_at, int deleted, Date date_of_birth, int gender, String avatar) {
        this.account_id = account_id;
        this.role_id = role_id;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.account_name = account_name;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted = deleted;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.avatar = avatar;
    }
    public Account(int account_id, String fullname, String email, String phone_number, String address) {
    this.account_id = account_id;
    this.fullname = fullname;
    this.email = email;
    this.phone_number = phone_number;
    this.address = address;
}


    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Account{" + "account_id=" + account_id + ", role_id=" + role_id + ", fullname=" + fullname + ", email=" + email + ", phone_number=" + phone_number + ", address=" + address + ", account_name=" + account_name + ", password=" + password + ", created_at=" + created_at + ", updated_at=" + updated_at + ", deleted=" + deleted + ", date_of_birth=" + date_of_birth + ", gender=" + gender + ", avatar=" + avatar + '}';
    }
    
}
