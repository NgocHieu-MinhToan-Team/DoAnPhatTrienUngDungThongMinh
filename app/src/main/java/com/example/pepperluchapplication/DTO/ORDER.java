package com.example.pepperluchapplication.DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class ORDER {
    // test order
    HashMap<String,CART> listOfCart;
    String id_customer,id_voucher,id_method;
    public ORDER(){}

    public HashMap<String, CART> getListOfCart() {
        return listOfCart;
    }

    public void setListOfCart(HashMap<String, CART> listOfCart) {
        this.listOfCart = listOfCart;
    }

    public ORDER(HashMap<String, CART> listOfCart, String id_customer, String id_voucher, String id_method) {
        this.listOfCart = listOfCart;
        this.id_customer = id_customer;
        this.id_voucher = id_voucher;
        this.id_method = id_method;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getId_voucher() {
        return id_voucher;
    }

    public void setId_voucher(String id_voucher) {
        this.id_voucher = id_voucher;
    }

    public String getId_method() {
        return id_method;
    }

    public void setId_method(String id_method) {
        this.id_method = id_method;
    }
}
