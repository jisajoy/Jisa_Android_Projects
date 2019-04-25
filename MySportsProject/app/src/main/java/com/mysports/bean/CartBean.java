package com.mysports.bean;

/**
 * Created by Jaison Joy on 1/25/2018.
 */

public class CartBean {
    private String mCartProductName;
    private int mImageLogo;
    private String mQuantity;
    private String mProductType;


    public String getmCartProductName() {
        return mCartProductName;
    }

    public void setmCartProductName(String mCartProductName) {
        this.mCartProductName = mCartProductName;
    }

    public int getmImageLogo() {
        return mImageLogo;
    }

    public void setmImageLogo(int mImageLogo) {
        this.mImageLogo = mImageLogo;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmProductType() {
        return mProductType;
    }

    public void setmProductType(String mProductType) {
        this.mProductType = mProductType;
    }
}
