package com.example.pepperluchapplication.DTO;

import java.io.Serializable;

public class CART implements Serializable {

    private PRODUCT product;
    private int soluong;
    private String note;
    private String spicy;
    public CART()
    {
        setProduct(new PRODUCT());
        setSoluong(0);
    }
    public  CART(PRODUCT product, int soluong)
    {
        this.setProduct(product);
        this.setSoluong(soluong);
    }

    public PRODUCT getProduct() {
        return product;
    }

    public void setProduct(PRODUCT product) {
        this.product = product;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


}
