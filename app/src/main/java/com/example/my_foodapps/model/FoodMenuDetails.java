package com.example.my_foodapps.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "FoodMenuDetails" , indices = {@Index(value = {"item_id"}, unique = true)},foreignKeys = @ForeignKey(entity = Store.class,
        parentColumns = "store_id",childColumns = "storeid", onDelete = CASCADE))

public class FoodMenuDetails {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    public int item_id;

    @ColumnInfo(name = "itemname")
    private String itemname;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "rating")
    private String rating;
    @ColumnInfo(name = "imageUrl")
    public byte[] imageUrl;

    @ColumnInfo(name = "storeid")
    public int storeid;


    public FoodMenuDetails(String itemname, String price, String rating, byte[] imageUrl, int storeid) {
        this.itemname = itemname;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.storeid = storeid;
    }

    @Ignore
    public FoodMenuDetails() {

    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public byte[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodMenuDetails foodMenuDetails = (FoodMenuDetails) o;
        return item_id == foodMenuDetails.item_id
                && storeid == foodMenuDetails.storeid
                &&imageUrl.equals(foodMenuDetails.imageUrl)
                && itemname.equals(foodMenuDetails.itemname)
                && price.equals(foodMenuDetails.price)
                && rating.equals(foodMenuDetails.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id,itemname,imageUrl,price, rating, storeid);
    }


}