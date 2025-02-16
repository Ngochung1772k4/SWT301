/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngoch
 */
public class Cart {

    private List<Item> items;
    private Coupon appliedCoupon;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProduct().getProductId() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getProductId()) != null) {
            Item i = getItemById(t.getProduct().getProductId());
            i.setQuantity(i.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));

            if (items.isEmpty()) {
                this.appliedCoupon = null;
            }
        }
    }

    public double getTotalMoney() {
        double total = items.stream()
                .mapToDouble(i -> i.getQuantity() * i.getProduct().getDiscountPrice())
                .sum();

        if (appliedCoupon != null) {
            double discount = total * appliedCoupon.getDiscountPercentage() / 100;
            discount = Math.min(discount, appliedCoupon.getMaxDiscountAmount());
            total -= discount;
        }
        return total;
    }

    public void setAppliedCoupon(Coupon appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

    public Coupon getAppliedCoupon() {
        return appliedCoupon;
    }
}
