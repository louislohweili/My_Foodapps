package com.example.my_foodapps.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "carts")
public class Cart {
    // Variables
    @PrimaryKey(autoGenerate = true)
    public int cart_id;
    @ColumnInfo(name = "cartFoodName")
    public String cartFoodName;

    @ColumnInfo(name = "cartqty")
    public String cartqty;
    @ColumnInfo(name = "carttPrice")
    public String  carttPrice;
    public  String remark ;


    @ColumnInfo(name = "typeservice")
    public String typeservice;
    @ColumnInfo(name = "itemid")
    public int itemid;

    public Cart(String cartFoodName, String cartqty, String carttPrice, String remark, String typeservice, int itemid) {
        this.cartFoodName = cartFoodName;
        this.cartqty = cartqty;
        this.carttPrice = carttPrice;
        this.remark = remark;

        this.typeservice = typeservice;
        this.itemid = itemid;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getCartFoodName() {
        return cartFoodName;
    }

    public void setCartFoodName(String cartFoodName) {
        this.cartFoodName = cartFoodName;
    }

    public String getCartqty() {
        return cartqty;
    }

    public void setCartqty(String cartqty) {
        this.cartqty = cartqty;
    }

    public String getCarttPrice() {
        return carttPrice;
    }

    public void setCarttPrice(String carttPrice) {
        this.carttPrice = carttPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public String getTypeservice() {
        return typeservice;
    }

    public void setTypeservice(String typeservice) {
        this.typeservice = typeservice;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }
}
