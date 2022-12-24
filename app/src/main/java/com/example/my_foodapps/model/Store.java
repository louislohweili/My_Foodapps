package com.example.my_foodapps.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;



@Entity(tableName = "store" , indices = {@Index(value = {"store_id"}, unique = true)})
public class Store  {

    @PrimaryKey(autoGenerate = true)

    public int store_id;

    @ColumnInfo(name = "storename")
    public String storename;
    @ColumnInfo(name = "storeownername")
    public String storeownername;
    @ColumnInfo(name = "storeaddress")
    public String storeaddress;
    @ColumnInfo(name = "storeemail")
    public String storeemail;

    @ColumnInfo(name = "password")
    public String pass;



    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;

    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;

    }

    public String getStoreownername() {
        return storeownername;
    }

    public void setStoreownername(String storeownername) {
        this.storeownername = storeownername;

    }

    public String getStoreaddress() {
        return storeaddress;
    }

    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress;

    }

    public String getStoreemail() {
        return storeemail;
    }

    public void setStoreemail(String storeemail) {
        this.storeemail = storeemail;

    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;

    }
    @Ignore
    public Store() {
    }

    public Store( String storename, String storeownername, String storeaddress, String storeemail, String pass) {

        this.storename = storename;
        this.storeownername = storeownername;
        this.storeaddress = storeaddress;
        this.storeemail = storeemail;
        this.pass = pass;
    }

}