package com.example.pepperluchapplication.Service;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.CUSTOMER;

import java.util.ArrayList;

public class MyApplication extends Application {

    static ArrayList<CART> carts=new ArrayList<CART>();
    static String ID_VOUCHER=null,ID_METHOD=null;
    static float PERCENT_DISCOUNT,AMOUNT_DISCOUNT;
    static CUSTOMER customer;

    public static CUSTOMER getCustomer() {
        return customer;
    }

    public static void setCustomer(CUSTOMER customer) {
        MyApplication.customer = customer;
    }

    public static float getPercentDiscount() {
        return PERCENT_DISCOUNT;
    }

    public static void setPercentDiscount(int percentDiscount) {
        PERCENT_DISCOUNT = percentDiscount;
    }

    public static float getAmountDiscount() {
        return AMOUNT_DISCOUNT;
    }

    public static void setAmountDiscount(int amountDiscount) {
        AMOUNT_DISCOUNT = amountDiscount;
    }

    public static String getIdVoucher() {
        return ID_VOUCHER;
    }

    public static void setIdVoucher(String idVoucher) {
        ID_VOUCHER = idVoucher;
    }

    public static String getIdMethod() {
        return ID_METHOD;
    }

    public static void setIdMethod(String idMethod) {
        ID_METHOD = idMethod;
    }

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
            if(t.getProduct().getID_PRODUCT()==cart.getProduct().getID_PRODUCT()) {
                t.setSoluong(cart.getSoluong() + t.getSoluong());
                return;
            }

        MyApplication.carts.add(cart);
    }

    public static double getTotalPriceOfCart(){
        double count=0;
        for(CART t : MyApplication.carts){
            count+=t.getProduct().getPRICE_PRODUCT()* t.getSoluong();
        }
        return count;
    }

    public static void clearCart(){
        MyApplication.carts.clear();
    }

    // make a foreground service

    public static  final  String CHANNEL_ID="CHANNEL_ORDER";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
    }

    private void createNotification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel Service", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            // toi uu code
            if(manager!=null){
                manager.createNotificationChannel(channel);
            }
        }
    }
}
