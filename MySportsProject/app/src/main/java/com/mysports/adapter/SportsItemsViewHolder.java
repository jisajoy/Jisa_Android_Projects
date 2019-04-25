package com.mysports.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.mysports.R;
import com.mysports.bean.SportsItems;

/**
 * Created by Jaison Joy on 3/8/2018.
 */

public class SportsItemsViewHolder extends ChildViewHolder {
    TextView mTextData;
    ImageView mExpandView;
    LinearLayout mExpandViewLayout;
    public SportsItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextData = itemView.findViewById(R.id.list_data);
        mExpandView = itemView.findViewById(R.id.expand_rv);
        mExpandView.setVisibility(View.GONE);
        mExpandViewLayout = itemView.findViewById(R.id.expandable_view);
    }

    public void childBindView(SportsItems sportsItems) {
        mTextData.setText(sportsItems.getSportsItems());
    }
}
