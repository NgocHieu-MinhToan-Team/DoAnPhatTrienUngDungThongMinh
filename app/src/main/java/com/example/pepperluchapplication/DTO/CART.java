package com.example.pepperluchapplication.DTO;

import java.io.Serializable;

public class CART implements Serializable {

    PRODUCT product;
    int soluong;

    public CART() {
        product = new PRODUCT();
        soluong = 0;
    }

    public CART(PRODUCT product, int soluong) {
        this.product = product;
        this.soluong = soluong;
    }
}
