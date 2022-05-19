package com.example.pepperluchapplication.DTO;

public class Category {
    String ID_CATEGORY,GROUP_CATEGORY,NAME_CATEGORY;

    public Category(){}
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

    public Category(String ID_CATEGORY, String GROUP_CATEGORY, String NAME_CATEGORY) {
        this.ID_CATEGORY = ID_CATEGORY;
        this.GROUP_CATEGORY = GROUP_CATEGORY;
        this.NAME_CATEGORY = NAME_CATEGORY;
    }
}
