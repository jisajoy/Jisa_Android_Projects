package com.mysports.bean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/2/2018.
 */

public class Academics {
    private String mainHeading;
    private String subHeading;
    private String sportTypeImage;
    private int mAcademicsImage;
    private ArrayList<AcademicsGameListBean> mGameList;

    public String getMainHeading() {
        return mainHeading;
    }

    public void setMainHeading(String mainHeading) {
        this.mainHeading = mainHeading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getSportTypeImage() {
        return sportTypeImage;
    }

    public void setSportTypeImage(String sportTypeImage) {
        this.sportTypeImage = sportTypeImage;
    }

    public ArrayList<AcademicsGameListBean> getmGameList() {
        return mGameList;
    }

    public void setmGameList(ArrayList<AcademicsGameListBean> mGameList) {
        this.mGameList = mGameList;
    }

    public int getmAcademicsImage() {
        return mAcademicsImage;
    }

    public void setmAcademicsImage(int mAcademicsImage) {
        this.mAcademicsImage = mAcademicsImage;
    }
}
