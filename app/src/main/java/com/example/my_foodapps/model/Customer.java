package com.example.my_foodapps.model;


import android.util.Patterns;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;




@Entity(tableName = "customers" , indices = {@Index(value = {"cust_id"}, unique = true)})
    public  class Customer{
        // Variables
        @PrimaryKey(autoGenerate = true)
        public int cust_id ;


    public   String fullname ;


    public String address ;


    public String email ;


    public   String phoneno ;




        // 3- Constructors


    public Customer( String fullname, String address, String email, String phoneno) {

        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.phoneno = phoneno;

    }

    @Ignore
        public Customer() {
        }




        public int getCust_id() {
            return cust_id;
        }

        public void setCust_id(int cust_id) {
            this.cust_id = cust_id;


        }



        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;


        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;


        }



        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;


        }


        public String getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;

        }




       }

