package com.mysports.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.mysports.R;
import com.mysports.bean.Sports;
import com.mysports.bean.SportsItems;

import java.util.List;

/**
 * Created by Jaison Joy on 3/8/2018.
 */

public class StoreFilterAdapter extends ExpandableRecyclerAdapter<Sports, SportsItems, SportsViewHolder, SportsItemsViewHolder> {
    Context activity;
    List<Sports> parentList;
    LayoutInflater layoutInflater;
    public static SportsClickListener sportsClickListener;
    public static SportsItemClickListener sportsItemClickListener;

    public StoreFilterAdapter(Context activity, @NonNull List<Sports> parentList) {
        super(parentList);
        this.activity = activity;
        this.parentList = parentList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View parentView = layoutInflater.inflate(R.layout.expandable_view, parentViewGroup, false);
        return new SportsViewHolder(parentView);
    }

    @NonNull
    @Override
    public SportsItemsViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View childView = layoutInflater.inflate(R.layout.expandable_view, childViewGroup, false);
        return new SportsItemsViewHolder(childView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull SportsViewHolder parentViewHolder, final int parentPosition, @NonNull Sports parent) {
        parent = parentList.get(parentPosition);
        parentViewHolder.parentBindView(parent.getSportsName());
        parentViewHolder.mExpandViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsClickListener.sportsOnItemClickListener(v, parentPosition);
            }
        });
    }

    @Override
    public void onBindChildViewHolder(@NonNull SportsItemsViewHolder childViewHolder, int parentPosition, final int childPosition, @NonNull SportsItems child) {
        childViewHolder.childBindView(child);
        childViewHolder.mExpandViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsItemClickListener.sportsItemOnItemClickListener(v, childPosition);
            }
        });
    }


    public interface SportsClickListener {
        public void sportsOnItemClickListener(View view, int position);
    }

    public void SetOnSportsClickListener(SportsClickListener sportsClickListener) {
        this.sportsClickListener = sportsClickListener;
    }

    public interface SportsItemClickListener {
        public void sportsItemOnItemClickListener(View view, int position);
    }

    public void SetOnSportsItemClickListener(SportsItemClickListener sportsItemClickListener){
        this.sportsItemClickListener = sportsItemClickListener;
    }
}
