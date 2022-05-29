package com.example.pepperluchapplication.Service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.CUSTOMER;

import java.util.ArrayList;

public class MyApplication extends Application {

    public static CUSTOMER getCustomer() {
        return customer;
    }

    public static void setCustomer(CUSTOMER customer) {
        MyApplication.customer = customer;
    }

    private static CUSTOMER customer;

    static ArrayList<CART> carts = new ArrayList<CART>();

    public static ArrayList<CART> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<CART> carts) {
        this.carts = carts;
    }

    public static void setItem(CART cart) {
        //duyet kiem tra trong gio hang co sn pham do chua
        for (CART t : MyApplication.carts)
            if (t.getProduct().getID_PRODUCT() == cart.getProduct().getID_PRODUCT()) {
                t.setSoluong(cart.getSoluong() + t.getSoluong());
                return;
            }

        MyApplication.carts.add(cart);
    }

    public static double getTotalPriceOfCart() {
        double count = 0;
        for (CART t : MyApplication.carts) {
            count += t.getProduct().getPRICE_PRODUCT() * t.getSoluong();
        }
        return count;
    }

    public static void clearCart() {
        MyApplication.carts.clear();
    }

    // make a foreground service

    public static final String CHANNEL_ID = "CHANNEL_ORDER";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Service", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            // toi uu code
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }
}
