package com.example.pepperluchapplication.DTO;

public class CUSTOMER {

    public CUSTOMER() {
    }

    public CUSTOMER(String ID_CUSTOMER, String SURNAME_CUSTOMER, String NAME_CUSTOMER,
                    String DATE_OF_BIRTH, int GENDER_CUSTOMER, String PHONE_CUSTOMER, String ADDRESS_CUSTOMER, String MAIL_CUSTOMER, String PASSWORD_CUSTOMER, int POINT, String TYPE_CUSTOMER, String DATE_CREATE) {
        this.ID_CUSTOMER = ID_CUSTOMER;
        this.SURNAME_CUSTOMER = SURNAME_CUSTOMER;
        this.NAME_CUSTOMER = NAME_CUSTOMER;
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        this.PHONE_CUSTOMER = PHONE_CUSTOMER;
        this.ADDRESS_CUSTOMER = ADDRESS_CUSTOMER;
        this.MAIL_CUSTOMER = MAIL_CUSTOMER;
        this.PASSWORD_CUSTOMER = PASSWORD_CUSTOMER;
        this.TYPE_CUSTOMER = TYPE_CUSTOMER;
        this.DATE_CREATE = DATE_CREATE;
        this.POINT = POINT;
        this.GENDER_CUSTOMER = GENDER_CUSTOMER;
    }

    public String getID_CUSTOMER() {
        return ID_CUSTOMER;
    }

    public void setID_CUSTOMER(String ID_CUSTOMER) {
        this.ID_CUSTOMER = ID_CUSTOMER;
    }

    public String getSURNAME_CUSTOMER() {
        return SURNAME_CUSTOMER;
    }

    public void setSURNAME_CUSTOMER(String SURNAME_CUSTOMER) {
        this.SURNAME_CUSTOMER = SURNAME_CUSTOMER;
    }

    public String getNAME_CUSTOMER() {
        return NAME_CUSTOMER;
    }

    public void setNAME_CUSTOMER(String NAME_CUSTOMER) {
        this.NAME_CUSTOMER = NAME_CUSTOMER;
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
    }

    public String getPHONE_CUSTOMER() {
        return PHONE_CUSTOMER;
    }

    public void setPHONE_CUSTOMER(String PHONE_CUSTOMER) {
        this.PHONE_CUSTOMER = PHONE_CUSTOMER;
    }

    public String getADDRESS_CUSTOMER() {
        return ADDRESS_CUSTOMER;
    }

    public void setADDRESS_CUSTOMER(String ADDRESS_CUSTOMER) {
        this.ADDRESS_CUSTOMER = ADDRESS_CUSTOMER;
    }

    public String getMAIL_CUSTOMER() {
        return MAIL_CUSTOMER;
    }

    public void setMAIL_CUSTOMER(String MAIL_CUSTOMER) {
        this.MAIL_CUSTOMER = MAIL_CUSTOMER;
    }

    public String getPASSWORD_CUSTOMER() {
        return PASSWORD_CUSTOMER;
    }

    public void setPASSWORD_CUSTOMER(String PASSWORD_CUSTOMER) {
        this.PASSWORD_CUSTOMER = PASSWORD_CUSTOMER;
    }

    public String getTYPE_CUSTOMER() {
        return TYPE_CUSTOMER;
    }

    public void setTYPE_CUSTOMER(String TYPE_CUSTOMER) {
        this.TYPE_CUSTOMER = TYPE_CUSTOMER;
    }

    public String getDATE_CREATE() {
        return DATE_CREATE;
    }

    public void setDATE_CREATE(String DATE_CREATE) {
        this.DATE_CREATE = DATE_CREATE;
    }

    public int getPOINT() {
        return POINT;
    }

    public void setPOINT(int POINT) {
        this.POINT = POINT;
    }

    public int getGENDER_CUSTOMER() {
        return GENDER_CUSTOMER;
    }

    public void setGENDER_CUSTOMER(int GENDER_CUSTOMER) {
        this.GENDER_CUSTOMER = GENDER_CUSTOMER;
    }

    private String ID_CUSTOMER,
            SURNAME_CUSTOMER, NAME_CUSTOMER, DATE_OF_BIRTH,
            PHONE_CUSTOMER, ADDRESS_CUSTOMER, MAIL_CUSTOMER, PASSWORD_CUSTOMER,
            TYPE_CUSTOMER,
            DATE_CREATE;
    private int POINT, GENDER_CUSTOMER;
}
