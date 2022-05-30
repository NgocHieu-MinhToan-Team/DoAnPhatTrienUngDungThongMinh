package com.example.pepperluchapplication.DTO;

public class VOUCHER {
    private int AMOUNT_REDUCTION,PERCENT_REDUCTION,QUANTITY_VOUCHER;
    private String DATE_CREATE,DATE_END,DATE_START,ID_PROMOTION,
            ID_VOUCHER,TYPE_CUSTOMER,TYPE_REDUCTION,USERNAME_STAFF;

    public VOUCHER(){}

    public VOUCHER(int AMOUNT_REDUCTION, int PERCENT_REDUCTION, int QUANTITY_VOUCHER, String DATE_CREATE, String DATE_END, String DATE_START, String ID_PROMOTION, String ID_VOUCHER, String TYPE_CUSTOMER, String TYPE_REDUCTION, String USERNAME_STAFF) {
        this.AMOUNT_REDUCTION = AMOUNT_REDUCTION;
        this.PERCENT_REDUCTION = PERCENT_REDUCTION;
        this.QUANTITY_VOUCHER = QUANTITY_VOUCHER;
        this.DATE_CREATE = DATE_CREATE;
        this.DATE_END = DATE_END;
        this.DATE_START = DATE_START;
        this.ID_PROMOTION = ID_PROMOTION;
        this.ID_VOUCHER = ID_VOUCHER;
        this.TYPE_CUSTOMER = TYPE_CUSTOMER;
        this.TYPE_REDUCTION = TYPE_REDUCTION;
        this.USERNAME_STAFF = USERNAME_STAFF;
    }

    public int getAMOUNT_REDUCTION() {
        return AMOUNT_REDUCTION;
    }

    public void setAMOUNT_REDUCTION(int AMOUNT_REDUCTION) {
        this.AMOUNT_REDUCTION = AMOUNT_REDUCTION;
    }

    public int getPERCENT_REDUCTION() {
        return PERCENT_REDUCTION;
    }

    public void setPERCENT_REDUCTION(int PERCENT_REDUCTION) {
        this.PERCENT_REDUCTION = PERCENT_REDUCTION;
    }

    public int getQUANTITY_VOUCHER() {
        return QUANTITY_VOUCHER;
    }

    public void setQUANTITY_VOUCHER(int QUANTITY_VOUCHER) {
        this.QUANTITY_VOUCHER = QUANTITY_VOUCHER;
    }

    public String getDATE_CREATE() {
        return DATE_CREATE;
    }

    public void setDATE_CREATE(String DATE_CREATE) {
        this.DATE_CREATE = DATE_CREATE;
    }

    public String getDATE_END() {
        return DATE_END;
    }

    public void setDATE_END(String DATE_END) {
        this.DATE_END = DATE_END;
    }

    public String getDATE_START() {
        return DATE_START;
    }

    public void setDATE_START(String DATE_START) {
        this.DATE_START = DATE_START;
    }

    public String getID_PROMOTION() {
        return ID_PROMOTION;
    }

    public void setID_PROMOTION(String ID_PROMOTION) {
        this.ID_PROMOTION = ID_PROMOTION;
    }

    public String getID_VOUCHER() {
        return ID_VOUCHER;
    }

    public void setID_VOUCHER(String ID_VOUCHER) {
        this.ID_VOUCHER = ID_VOUCHER;
    }

    public String getTYPE_CUSTOMER() {
        return TYPE_CUSTOMER;
    }

    public void setTYPE_CUSTOMER(String TYPE_CUSTOMER) {
        this.TYPE_CUSTOMER = TYPE_CUSTOMER;
    }

    public String getTYPE_REDUCTION() {
        return TYPE_REDUCTION;
    }

    public void setTYPE_REDUCTION(String TYPE_REDUCTION) {
        this.TYPE_REDUCTION = TYPE_REDUCTION;
    }

    public String getUSERNAME_STAFF() {
        return USERNAME_STAFF;
    }

    public void setUSERNAME_STAFF(String USERNAME_STAFF) {
        this.USERNAME_STAFF = USERNAME_STAFF;
    }
}
