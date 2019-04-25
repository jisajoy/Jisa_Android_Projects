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

public class AcademicsGameListRecycleView extends RecyclerView.Adapter<AcademicsGameListRecycleView.VenueIconHolder> {
    Context mContext;
    ArrayList<AcademicsGameListBean> mGameList;

    public AcademicsGameListRecycleView(Context mContext, ArrayList<AcademicsGameListBean> mGameList) {
        this.mContext = mContext;
        this.mGameList = mGameList;
    }

    @Override
    public VenueIconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.icon_setter, parent, false);
        VenueIconHolder venueIconHolder = new VenueIconHolder(view);
        return venueIconHolder;
    }

    @Override
    public void onBindViewHolder(VenueIconHolder holder, int position) {
        AcademicsGameListBean academicsGameListBean = mGameList.get(position);
        holder.mIconImageView.setImageResource(academicsGameListBean.getmGameListImage());
    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }

    public class VenueIconHolder extends RecyclerView.ViewHolder {
        ImageView mIconImageView;

        public VenueIconHolder(View itemView) {
            super(itemView);
            mIconImageView = itemView.findViewById(R.id.venue_icon_holder);
        }
    }
}
