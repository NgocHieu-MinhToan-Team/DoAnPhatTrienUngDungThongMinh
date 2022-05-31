package com.example.pepperluchapplication.DTO;

public class METHOD {
    String ID_METHOD,NAME_METHOD;
    public METHOD(){}

    public METHOD(String ID_METHOD, String NAME_METHOD) {
        this.ID_METHOD = ID_METHOD;
        this.NAME_METHOD = NAME_METHOD;
    }

    public String getID_METHOD() {
        return ID_METHOD;
    }

    public void setID_METHOD(String ID_METHOD) {
        this.ID_METHOD = ID_METHOD;
    }

    public String getNAME_METHOD() {
        return NAME_METHOD;
    }

    public void setNAME_METHOD(String NAME_METHOD) {
        this.NAME_METHOD = NAME_METHOD;
    }
}
