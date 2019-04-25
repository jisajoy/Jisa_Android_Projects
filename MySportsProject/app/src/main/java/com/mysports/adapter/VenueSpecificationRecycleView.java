package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.bean.VenueSpecificationBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/31/2018.
 */

public class VenueSpecificationRecycleView extends RecyclerView.Adapter<VenueSpecificationRecycleView.VenueSpecHolder> {
    Context mContext;
    ArrayList<VenueSpecificationBean> mVenueSpecificationList;

    public VenueSpecificationRecycleView(Context mContext, ArrayList<VenueSpecificationBean> mVenueSpecificationList) {
        this.mContext = mContext;
        this.mVenueSpecificationList = mVenueSpecificationList;
    }

    @Override
    public VenueSpecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.venue_spec_layout, parent, false);
        VenueSpecHolder venueSpecHolder = new VenueSpecHolder(view);
        return venueSpecHolder;
    }

    @Override
    public void onBindViewHolder(VenueSpecHolder holder, int position) {
        VenueSpecificationBean specificationBean = mVenueSpecificationList.get(position);
        holder.mSpecTitle.setText(specificationBean.getmSpecTitle());
        holder.mSpecImageIcon.setImageResource(specificationBean.getmSpecImage());
    }

    @Override
    public int getItemCount() {
        return mVenueSpecificationList.size();
    }

    public class VenueSpecHolder extends RecyclerView.ViewHolder {
        ImageView mSpecImageIcon;
        TextView mSpecTitle;

        public VenueSpecHolder(View itemView) {
            super(itemView);
            mSpecImageIcon = itemView.findViewById(R.id.spec_icon_image);
            mSpecTitle = itemView.findViewById(R.id.spec_title);
        }
    }
}
