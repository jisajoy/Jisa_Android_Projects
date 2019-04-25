package com.mysports.bean;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Jaison Joy on 3/8/2018.
 */

public class Sports implements Parent<SportsItems>{
    private List<SportsItems> sportsItems;
    String sportsName;

    public Sports(List<SportsItems> sportsItems, String sportsName) {
        this.sportsItems = sportsItems;
        this.sportsName = sportsName;
    }

    public String getSportsName() {
        return sportsName;
    }

    @Override
    public List<SportsItems> getChildList() {
        return sportsItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
