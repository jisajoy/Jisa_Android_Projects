package com.mysports.bean;

/**
 * Created by Jaison Joy on 3/15/2018.
 */

public class ShippingDetails {
    private String personName;
    private String personMobileNo;
    private String personAddress;
    private String personCity;
    private String personArea;
    private String personCountry;
    private String addressNo;
private Boolean addressActive;
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMobileNo() {
        return personMobileNo;
    }

    public void setPersonMobileNo(String personMobileNo) {
        this.personMobileNo = personMobileNo;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonCity() {
        return personCity;
    }

    public void setPersonCity(String personCity) {
        this.personCity = personCity;
    }

    public String getPersonArea() {
        return personArea;
    }

    public void setPersonArea(String personArea) {
        this.personArea = personArea;
    }

    public String getPersonCountry() {
        return personCountry;
    }

    public void setPersonCountry(String personCountry) {
        this.personCountry = personCountry;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public Boolean getAddressActive() {
        return addressActive;
    }

    public void setAddressActive(Boolean addressActive) {
        this.addressActive = addressActive;
    }
}
