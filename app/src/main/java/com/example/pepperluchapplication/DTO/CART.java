package com.example.pepperluchapplication.DTO;

import java.io.Serializable;

public class CART implements Serializable {

    private PRODUCT product;
    private int soluong;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }

    private String spicy;
    public CART()
    {
        setProduct(new PRODUCT());
        setSoluong(0);
        setNote("");
        setSpicy("");
    }
    public  CART(PRODUCT product, int soluong, String note, String spicy)
    {
        this.setProduct(product);
        this.setSoluong(soluong);
        this.setNote(note);
        this.setSpicy(spicy);
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
