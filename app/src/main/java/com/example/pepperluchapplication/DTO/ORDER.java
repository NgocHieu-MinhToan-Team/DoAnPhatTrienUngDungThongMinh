package com.example.pepperluchapplication.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ORDER implements Serializable {
    public String getID_ORDER() {
        return ID_ORDER;
    }

    public void setID_ORDER(String ID_ORDER) {
        this.ID_ORDER = ID_ORDER;
    }

    // test order
    String ID_ORDER;
    HashMap<String,CART> LIST_CART;
    String ID_CUSTOMER,ID_VOUCHER,ID_METHOD;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;
    long STATUS;
    float TOTAL_PAYMENT;
    public ORDER(){}

    public ORDER(HashMap<String, CART> LIST_CART, String ID_CUSTOMER, String ID_VOUCHER, String ID_METHOD, long STATUS, float TOTAL_PAYMENT, String address) {
        this.LIST_CART = LIST_CART;
        this.ID_CUSTOMER = ID_CUSTOMER;
        this.ID_VOUCHER = ID_VOUCHER;
        this.ID_METHOD = ID_METHOD;
        this.STATUS = STATUS;
        this.TOTAL_PAYMENT = TOTAL_PAYMENT;
        this.address=address;

    }

    public HashMap<String, CART> getLIST_CART() {
        return LIST_CART;
    }

    public void setLIST_CART(HashMap<String, CART> LIST_CART) {
        this.LIST_CART = LIST_CART;
    }

    public String getID_CUSTOMER() {
        return ID_CUSTOMER;
    }

    public void setID_CUSTOMER(String ID_CUSTOMER) {
        this.ID_CUSTOMER = ID_CUSTOMER;
    }

    public String getID_VOUCHER() {
        return ID_VOUCHER;
    }

    public void setID_VOUCHER(String ID_VOUCHER) {
        this.ID_VOUCHER = ID_VOUCHER;
    }

    public String getID_METHOD() {
        return ID_METHOD;
    }

    public void setID_METHOD(String ID_METHOD) {
        this.ID_METHOD = ID_METHOD;
    }

    public long getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(long STATUS) {
        this.STATUS = STATUS;
    }

    public float getTOTAL_PAYMENT() {
        return TOTAL_PAYMENT;
    }

    public void setTOTAL_PAYMENT(float TOTAL_PAYMENT) {
        this.TOTAL_PAYMENT = TOTAL_PAYMENT;
    }
}
