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
import com.mysports.bean.VenueFilterBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 3/6/2018.
 */

public class VenueFilterAdapter extends RecyclerView.Adapter<VenueFilterAdapter.VenueHolder> {
    Context mContext;
    ArrayList<VenueFilterBean> mGameArrayList;
    String TAG;

    public VenueFilterAdapter(Context mContext, ArrayList<VenueFilterBean> mGameArrayList, String TAG) {
        this.mContext = mContext;
        this.mGameArrayList = mGameArrayList;
        this.TAG = TAG;
    }

    @Override
    public VenueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.venue_games_list, parent, false);
        VenueHolder venueHolder = new VenueHolder(view);
        return venueHolder;
    }

    @Override
    public void onBindViewHolder(VenueHolder holder, int position) {
        VenueFilterBean venueFilterBean = mGameArrayList.get(position);
        holder.mGameImage.setImageResource(venueFilterBean.getGameImage());
        if (TAG.equals("Academics")){
            holder.mGameTitle.setText(venueFilterBean.getGameTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mGameArrayList.size();
    }

    public class VenueHolder extends RecyclerView.ViewHolder {
        ImageView mGameImage;
        TextView mGameTitle;

        public VenueHolder(View itemView) {
            super(itemView);
            mGameImage = itemView.findViewById(R.id.game_image);
            mGameTitle = itemView.findViewById(R.id.game_name);
            if (TAG.equals("Academics")){
                mGameTitle.setVisibility(View.GONE);
            }
        }
    }
}
