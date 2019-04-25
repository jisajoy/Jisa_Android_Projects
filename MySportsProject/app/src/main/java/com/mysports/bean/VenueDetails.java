package com.mysports.bean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 12/18/2017.
 */

public class VenueDetails {
    private String mainHeading;
    private String smallTitle;
    private String venueImage;
    ArrayList<VenueIconBean> mCardIconList;

    public String getMainHeading() {
        return mainHeading;
    }

    public void setMainHeading(String mainHeading) {
        this.mainHeading = mainHeading;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
    }

    public String getVenueImage() {
        return venueImage;
    }

    public void setVenueImage(String venueImage) {
        this.venueImage = venueImage;
    }

    public ArrayList<VenueIconBean> getmCardIconList() {
        return mCardIconList;
    }

    public void setmCardIconList(ArrayList<VenueIconBean> mCardIconList) {
        this.mCardIconList = mCardIconList;
    }
}
