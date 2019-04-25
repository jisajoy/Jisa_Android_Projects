package com.mysports.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.utilities.MyApplication;

import java.util.ArrayList;

public class SwipeDataRV extends RecyclerView.Adapter<SwipeDataRV.SwipeDataHolder> {
    Context activity;
    String[] sizeList;
    private static int lastCheckedPos = -1;
    int clickcount = 0;

    public SwipeDataRV(Context activity, String[] sizeList) {
        this.activity = activity;
        this.sizeList = sizeList;
    }

    @Override
    public SwipeDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.text_data, parent, false);
        SwipeDataHolder swipeDataHolder = new SwipeDataHolder(view);
        return swipeDataHolder;
    }

    @Override
    public void onBindViewHolder(final SwipeDataHolder holder, final int position) {
        holder.mTextdata.setText(sizeList[position]);
        if (position == lastCheckedPos) {
            holder.mTextBackground.setCardBackgroundColor(Color.parseColor("#CA2027")); //red
            holder.mTextdata.setTextColor(Color.parseColor("#FFFFFF"));//white
            MyApplication.storeSize = sizeList[position];//selected sized
        } else {
            holder.mTextBackground.setCardBackgroundColor(Color.parseColor("#FFFFFF")); //white
            holder.mTextdata.setTextColor(Color.parseColor("#2d2c2c"));//black
        }
        holder.mTextBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickcount = clickcount + 1;
                if (clickcount == 1) {
                    holder.mTextBackground.setCardBackgroundColor(Color.parseColor("#CA2027")); //red
                    holder.mTextdata.setTextColor(Color.parseColor("#FFFFFF"));//white
                    MyApplication.storeSize = sizeList[position];//selected sized
                } else {
                    holder.mTextBackground.setCardBackgroundColor(Color.parseColor("#FFFFFF")); //white
                    holder.mTextdata.setTextColor(Color.parseColor("#2d2c2c"));//black
                }
                int prevPos = lastCheckedPos;
                lastCheckedPos = position;
                notifyItemChanged(prevPos);
                notifyItemChanged(lastCheckedPos);

            }
        });
    }

    @Override
    public int getItemCount() {
        return sizeList.length;
    }

    public class SwipeDataHolder extends RecyclerView.ViewHolder {
        TextView mTextdata;
        CardView mTextBackground;

        public SwipeDataHolder(View itemView) {
            super(itemView);
            mTextdata = itemView.findViewById(R.id.store_data);
            mTextBackground = itemView.findViewById(R.id.text_data_background);
        }
    }
}
