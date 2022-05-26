package com.example.pepperluchapplication.DTO;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {

    static ArrayList<CART> carts=new ArrayList<CART>();

    public static ArrayList<CART> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<CART> carts) {
        this.carts = carts;
    }

    public static void setItem(CART cart)
    {
        //duyet kiem tra trong gio hang co sn pham do chua
        for(CART t :MyApplication.carts)
            if(t.getProduct().ID_PRODUCT==cart.getProduct().ID_PRODUCT) {
                t.setSoluong(cart.getSoluong() + t.getSoluong());
                return;
            }

        MyApplication.carts.add(cart);
    }
    public static void clearCart(){
        MyApplication.carts.clear();
    }
}
