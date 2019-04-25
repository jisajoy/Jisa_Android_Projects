package com.mysports.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.mysports.R;
import com.mysports.bean.Sports;

/**
 * Created by Jaison Joy on 3/8/2018.
 */

public class SportsViewHolder extends ParentViewHolder{
    TextView mTextData;
    ImageView mExpandView;
    RelativeLayout mExpandLayout;
    LinearLayout mExpandViewLayout;
    public SportsViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextData = itemView.findViewById(R.id.list_data);
        mTextData.setTypeface(mTextData.getTypeface(), Typeface.BOLD);
        mExpandView = itemView.findViewById(R.id.expand_rv);
        mExpandLayout = itemView.findViewById(R.id.expand_layout);
        mExpandViewLayout = itemView.findViewById(R.id.expandable_view);
        mExpandLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapseView();
                    mExpandView.setImageResource(R.drawable.ic_plus);
                } else {
                    expandView();
                    mExpandView.setImageResource(R.drawable.ic_minus);
                }
            }
        });
    }
    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;// disables the click on the adapter
    }
    public void parentBindView(String sports){
        mTextData.setText(sports);
    }
}
