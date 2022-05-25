package com.example.pepperluchapplication.Model;

public class ItemProfileMenu {
    private int icon;
    private String titleItem;

    public ItemProfileMenu(int icon, String titleItem) {
        this.icon = icon;
        this.titleItem = titleItem;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitleItem() {
        return titleItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }
}
