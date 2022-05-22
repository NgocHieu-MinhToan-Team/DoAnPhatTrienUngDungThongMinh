package com.example.pepperluchapplication.DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CATEGORY {
    String ID_CATEGORY,GROUP_CATEGORY,NAME_CATEGORY;
    HashMap<String,PRODUCT> Dishes;

    public String getID_CATEGORY() {
        return ID_CATEGORY;
    }

    public void setID_CATEGORY(String ID_CATEGORY) {
        this.ID_CATEGORY = ID_CATEGORY;
    }

    public String getGROUP_CATEGORY() {
        return GROUP_CATEGORY;
    }

    public void setGROUP_CATEGORY(String GROUP_CATEGORY) {
        this.GROUP_CATEGORY = GROUP_CATEGORY;
    }

    public String getNAME_CATEGORY() {
        return NAME_CATEGORY;
    }

    public void setNAME_CATEGORY(String NAME_CATEGORY) {
        this.NAME_CATEGORY = NAME_CATEGORY;
    }

    public HashMap<String,PRODUCT> getDishes() {
        return Dishes;
    }

    public void setDishes(HashMap<String,PRODUCT> dishes) {
        Dishes = dishes;
    }

    public CATEGORY(){}

    public CATEGORY(String ID_CATEGORY, String GROUP_CATEGORY, String NAME_CATEGORY) {
        this.ID_CATEGORY = ID_CATEGORY;
        this.GROUP_CATEGORY = GROUP_CATEGORY;
        this.NAME_CATEGORY = NAME_CATEGORY;
    }

    public CATEGORY(String ID_CATEGORY, String GROUP_CATEGORY, String NAME_CATEGORY, HashMap<String,PRODUCT> dishes) {
        this.ID_CATEGORY = ID_CATEGORY;
        this.GROUP_CATEGORY = GROUP_CATEGORY;
        this.NAME_CATEGORY = NAME_CATEGORY;
        this.Dishes = dishes;
    }
}
