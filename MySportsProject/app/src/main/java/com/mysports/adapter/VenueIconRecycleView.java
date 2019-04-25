package com.mysports.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mysports.R;
import com.mysports.bean.AcademicsGameListBean;
import com.mysports.bean.VenueIconBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/31/2018.
 */

public class VenueIconRecycleView extends RecyclerView.Adapter<VenueIconRecycleView.VenueIconHolder> {
    Context mContext;
    ArrayList<VenueIconBean> venueIconBeans;

    public VenueIconRecycleView(Context mContext, ArrayList<VenueIconBean> venueIconBeans) {
        this.mContext = mContext;
        this.venueIconBeans = venueIconBeans;
    }

    @Override
    public VenueIconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.icon_setter, parent, false);
        VenueIconHolder venueIconHolder = new VenueIconHolder(view);
        return venueIconHolder;
    }

    @Override
    public void onBindViewHolder(VenueIconHolder holder, int position) {
        VenueIconBean venueIconBean = venueIconBeans.get(position);
        holder.mIconImageView.setImageResource(venueIconBean.getIconImage());
    }

    @Override
    public int getItemCount() {
        return venueIconBeans.size();
    }

    public class VenueIconHolder extends RecyclerView.ViewHolder {
        ImageView mIconImageView;

        public VenueIconHolder(View itemView) {
            super(itemView);
            mIconImageView = itemView.findViewById(R.id.venue_icon_holder);
        }
    }
}
