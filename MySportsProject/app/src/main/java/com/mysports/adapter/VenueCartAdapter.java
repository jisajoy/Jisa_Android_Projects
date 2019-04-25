package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mysports.R;
import com.mysports.bean.VenueCartBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 3/15/2018.
 */

public class VenueCartAdapter extends RecyclerView.Adapter<VenueCartAdapter.VenueCartHolder> {
    Context activity;
    ArrayList<VenueCartBean> mVenueCartList;

    public VenueCartAdapter(Context activity, ArrayList<VenueCartBean> mVenueCartList) {
        this.activity = activity;
        this.mVenueCartList = mVenueCartList;
    }

    @Override
    public VenueCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.venue_cart, parent, false);
        VenueCartHolder venueCartHolder = new VenueCartHolder(view);
        return venueCartHolder;
    }

    @Override
    public void onBindViewHolder(VenueCartHolder holder, final int position) {
        VenueCartBean cartBean = mVenueCartList.get(position);
        holder.mVenueName.setText(cartBean.getmVenueName());
        holder.mVenueDate.setText(cartBean.getmVenueDate());
        holder.mVenueTime.setText(cartBean.getmVenueTime());
        holder.mVenueSession.setText(cartBean.getmSession());
        holder.mVenueRate.setText(cartBean.getmVenuePrice());
        holder.mVenueDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVenueCartList.remove(position);
                notifyItemRemoved(position);
                notifyItemChanged(position, mVenueCartList.size());
                Toast.makeText(activity, "Item Remove", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVenueCartList.size();
    }

    public class VenueCartHolder extends RecyclerView.ViewHolder {
        TextView mVenueName;
        TextView mVenueDate;
        TextView mVenueTime;
        ImageView mVenueDelete;
        TextView mVenueSession;
        TextView mVenueRate;

        public VenueCartHolder(View itemView) {
            super(itemView);
            mVenueName = itemView.findViewById(R.id.venue_name);
            mVenueDate = itemView.findViewById(R.id.venue_date);
            mVenueTime = itemView.findViewById(R.id.venue_time);
            mVenueDelete = itemView.findViewById(R.id.delete_venue);
            mVenueSession = itemView.findViewById(R.id.session_time);
            mVenueRate = itemView.findViewById(R.id.venue_price);
        }
    }
}
