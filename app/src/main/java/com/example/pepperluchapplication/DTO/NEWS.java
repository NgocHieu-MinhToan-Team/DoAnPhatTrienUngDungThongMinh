package com.example.pepperluchapplication.DTO;

import java.util.HashMap;

public class NEWS {
    String ID_NEWS, ID_PROMOTION, TITLE, CONTENT, USERNAME_STAFF;
    String DATE_VISIBLE, DATE_HIDDEN;
    HashMap<String, IMAGE_NEWS> Image;

    public NEWS() {
    }

    public NEWS(String ID_NEWS, String ID_PROMOTION, String TITLE, String CONTENT, String USERNAME_STAFF, String DATE_VISIBLE, String DATE_HIDDEN, HashMap<String, IMAGE_NEWS> image) {
        this.ID_NEWS = ID_NEWS;
        this.ID_PROMOTION = ID_PROMOTION;
        this.TITLE = TITLE;
        this.CONTENT = CONTENT;
        this.USERNAME_STAFF = USERNAME_STAFF;
        this.DATE_VISIBLE = DATE_VISIBLE;
        this.DATE_HIDDEN = DATE_HIDDEN;
        Image = image;
    }

    public String getID_NEWS() {
        return ID_NEWS;
    }

    public void setID_NEWS(String ID_NEWS) {
        this.ID_NEWS = ID_NEWS;
    }

    public String getID_PROMOTION() {
        return ID_PROMOTION;
    }

    public void setID_PROMOTION(String ID_PROMOTION) {
        this.ID_PROMOTION = ID_PROMOTION;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getUSERNAME_STAFF() {
        return USERNAME_STAFF;
    }

    public void setUSERNAME_STAFF(String USERNAME_STAFF) {
        this.USERNAME_STAFF = USERNAME_STAFF;
    }

    public String getDATE_VISIBLE() {
        return DATE_VISIBLE;
    }

    public void setDATE_VISIBLE(String DATE_VISIBLE) {
        this.DATE_VISIBLE = DATE_VISIBLE;
    }

    public String getDATE_HIDDEN() {
        return DATE_HIDDEN;
    }

    public void setDATE_HIDDEN(String DATE_HIDDEN) {
        this.DATE_HIDDEN = DATE_HIDDEN;
    }

    public HashMap<String, IMAGE_NEWS> getImage() {
        return Image;
    }

    public void setImage(HashMap<String, IMAGE_NEWS> image) {
        Image = image;
    }
}
