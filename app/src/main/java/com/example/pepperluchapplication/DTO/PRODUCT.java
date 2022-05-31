package com.example.pepperluchapplication.DTO;

import java.io.Serializable;

public class PRODUCT implements Serializable {
    String ID_CATEGORY,ID_PRODUCT,IMAGE_PRODUCT,NAME_PRODUCT_EN,NAME_PRODUCT_VN;
    Long PRICE_PRODUCT;

    public String getID_CATEGORY() {
        return ID_CATEGORY;
    }

    public void setID_CATEGORY(String ID_CATEGORY) {
        this.ID_CATEGORY = ID_CATEGORY;
    }

    public String getID_PRODUCT() {
        return ID_PRODUCT;
    }

    public void setID_PRODUCT(String ID_PRODUCT) {
        this.ID_PRODUCT = ID_PRODUCT;
    }

    public String getIMAGE_PRODUCT() {
        return IMAGE_PRODUCT;
    }

    public void setIMAGE_PRODUCT(String IMAGE_PRODUCT) {
        this.IMAGE_PRODUCT = IMAGE_PRODUCT;
    }

    public String getNAME_PRODUCT_EN() {
        return NAME_PRODUCT_EN;
    }

    public void setNAME_PRODUCT_EN(String NAME_PRODUCT_EN) {
        this.NAME_PRODUCT_EN = NAME_PRODUCT_EN;
    }

    public String getNAME_PRODUCT_VN() {
        return NAME_PRODUCT_VN;
    }

    public void setNAME_PRODUCT_VN(String NAME_PRODUCT_VN) {
        this.NAME_PRODUCT_VN = NAME_PRODUCT_VN;
    }

    public Long getPRICE_PRODUCT() {
        return PRICE_PRODUCT;
    }

    public void setPRICE_PRODUCT(Long PRICE_PRODUCT) {
        this.PRICE_PRODUCT = PRICE_PRODUCT;
    }

    public PRODUCT(){}

    public PRODUCT(String ID_CATEGORY, String ID_PRODUCT, String IMAGE_PRODUCT, String NAME_PRODUCT_EN, String NAME_PRODUCT_VN, Long PRICE_PRODUCT) {
        this.ID_CATEGORY = ID_CATEGORY;
        this.ID_PRODUCT = ID_PRODUCT;
        this.IMAGE_PRODUCT = IMAGE_PRODUCT;
        this.NAME_PRODUCT_EN = NAME_PRODUCT_EN;
        this.NAME_PRODUCT_VN = NAME_PRODUCT_VN;
        this.PRICE_PRODUCT = PRICE_PRODUCT;
    }
}
