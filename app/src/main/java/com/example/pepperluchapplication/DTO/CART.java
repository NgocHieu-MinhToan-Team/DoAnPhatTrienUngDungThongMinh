package com.example.pepperluchapplication.DTO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CART implements Serializable {

    PRODUCT product;
    int soluong;
    public CART()
    {
        product=new PRODUCT();
        soluong=0;
    }
    public  CART(PRODUCT product, int soluong)
    {
        this.product=product;
        this.soluong=soluong;
    }
}
