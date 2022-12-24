package com.example.my_foodapps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.my_foodapps.model.Cart;
import com.example.my_foodapps.model.Customer;
import com.example.my_foodapps.model.FoodMenuDetails;
import com.example.my_foodapps.model.Order;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.repository.Cartrepo;
import com.example.my_foodapps.repository.Customerrepo;
import com.example.my_foodapps.repository.FoodMenurepo;
import com.example.my_foodapps.repository.Orderrepo;
import com.example.my_foodapps.repository.Storerepo;

import java.util.List;

public class Customerviewmodel extends AndroidViewModel {


    private final Customerrepo customerrepos;
    private final Storerepo storerepos;
    private final FoodMenurepo foodMenurepo;
    private final Cartrepo cartrepo;
    private final Orderrepo orderrepo;

    private LiveData<List<Customer>> customerOfSelectedLogin;
    private LiveData<List<Cart>> CartOfSelectCatergory;
    private LiveData<List<Customer>> customerOfSelectedCategory;
    private LiveData<List<Store>> storeOfSelectedCategory;
    private LiveData<List<Store>> storeOfSelectedlogin;
    private LiveData<List<FoodMenuDetails>> foodeOfSelectedCategory;
    private  LiveData<List<Order>> orderofselectCategory;

    public Customerviewmodel(@NonNull Application application) {
        super(application);

        customerrepos = new Customerrepo(application);
        storerepos = new Storerepo(application);
        foodMenurepo = new FoodMenurepo(application);
        cartrepo =new Cartrepo(application);
        orderrepo = new Orderrepo(application);
    }

    public void insertcustomer(Customer customer) {
        customerrepos.insert(customer);
    }


    public void updatecustomer(Customer customer) {
       customerrepos.update(customer);
   }

   // private void updatecustomer(String fullname,String address ,String email ,String phoneno, String password,int cust_id){
    //    customerrepos.updatecust( fullname,address , email , phoneno, password,cust_id);
    //}

    public void deletecustomer(Customer customer) {
        customerrepos.delete(customer);
    }



    public LiveData<List<Customer>> getAllCustomerLive() {
        return customerrepos.getAllCustomer();
    }
/**
    public LiveData<List<Customer>> getCustomer (int cust_id){
        customerOfSelectedCategory = customerrepos.getCustomer(cust_id);
        return customerOfSelectedCategory;
    }
**/


    //store
    public void insertstore(Store store) {
        storerepos.add(store);
    }

    public void update(Store store) {
        storerepos.updatestore(store);
    }

    public void deletestore(Store store) {
        storerepos.delete(store);
    }


    public LiveData<List<Store>> getAllStoreLive() {
        return storerepos.getAllStore();
    }

    public LiveData<List<Store>> getstore (int store_id){
        storeOfSelectedCategory = storerepos.getstore(store_id);
        return storeOfSelectedCategory;
    }

    public LiveData<List<Store>> getLogin (String Email,String Password ){
        storeOfSelectedlogin = storerepos.getLogin(Email,Password);
        return storeOfSelectedlogin;

    }

    //FOOD items
    public void addfooditem(FoodMenuDetails foodMenuDetails){
        foodMenurepo.insertFoodmenu(foodMenuDetails);
    }
    public void updatefooditem (FoodMenuDetails foodMenuDetails){
        foodMenurepo.updatefoodmenu(foodMenuDetails);
    }

    public void deletefooditem(FoodMenuDetails foodMenuDetails){
        foodMenurepo.delete(foodMenuDetails);
    }
    public LiveData<List<FoodMenuDetails>> getAllfooditemLive() {
        return foodMenurepo.getAllfoodmenu();
    }

    public LiveData<List<FoodMenuDetails>> getstore_id (int storeid){
        foodeOfSelectedCategory = foodMenurepo.getfooditem(storeid);
        return foodeOfSelectedCategory;
    }

    public void deletebystore (int storeid){
        foodMenurepo.deletebystore(storeid);
    }

   // public LiveData<List<FoodMenuDetails>> getCoursesOfSelectedCategory(int categoryId){
        //foodeOfSelectedCategory = repository.getCourses(categoryId);
      //  return foodeOfSelectedCategory;
   // }


    // Cart

    public void AddCart(Cart cart){
        cartrepo.addCart(cart);
    }
    public void updateCart (Cart cart){
        cartrepo.updateCart(cart);

    }

    public void DelCart (Cart cart){
        cartrepo.DelCart(cart);
    }

    public LiveData<List<Cart>> getallcart() {
        return cartrepo.getAllcart();
    }
    public LiveData<List<Cart>>getCartOfSelectCatergory(int Cartid){
        CartOfSelectCatergory = cartrepo.getCart(Cartid);
        return CartOfSelectCatergory;
    }

    //Order

    public void  AddOrder(Order order){
        orderrepo.addorder(order);

    }

    public void delOrder(Order order){
        orderrepo.delorder(order);
    }
    public  void updateorder(Order order){
        orderrepo.updateorder(order);
    }

    public LiveData<List<Order>> getallOrder() {
        return orderrepo.getAllOrder();
    }
}

