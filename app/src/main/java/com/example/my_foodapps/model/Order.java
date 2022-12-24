package com.example.my_foodapps.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "order" , indices = {@Index(value = {"order_id"}, unique = true)})
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int order_id;
    public String orderName;
    public String orderAddress;
    public String orderphone;
    public String ordertotal;
    public String payment;
    public String status;



    public Order(String orderName, String orderAddress, String orderphone, String ordertotal, String payment, String status) {
        this.orderName = orderName;
        this.orderAddress = orderAddress;
        this.orderphone = orderphone;
        this.ordertotal = ordertotal;
        this.payment = payment;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderphone() {
        return orderphone;
    }

    public void setOrderphone(String orderphone) {
        this.orderphone = orderphone;
    }

    public String getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(String ordertotal) {
        this.ordertotal = ordertotal;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
