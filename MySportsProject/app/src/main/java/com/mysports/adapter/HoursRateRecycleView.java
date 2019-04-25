package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.bean.HourRateBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 2/28/2018.
 */

public class HoursRateRecycleView extends RecyclerView.Adapter<HoursRateRecycleView.HoursRateHolder> {
    Context activity;
    ArrayList<HourRateBean> mHourList;
    public static OnClickListener onClickListener;

    public HoursRateRecycleView(Context activity, ArrayList<HourRateBean> mHourList) {
        this.activity = activity;
        this.mHourList = mHourList;
    }

    @Override
    public HoursRateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.hours_rate_recycleview, parent, false);
        HoursRateHolder hoursRateHolder = new HoursRateHolder(view);
        return hoursRateHolder;
    }

    @Override
    public void onBindViewHolder(HoursRateHolder holder, int position) {
        HourRateBean hourRateBean = mHourList.get(position);
        holder.mHours.setText(hourRateBean.getHourRate());
    }

    @Override
    public int getItemCount() {
        return mHourList.size();
    }

    public class HoursRateHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mHours;

        public HoursRateHolder(View itemView) {
            super(itemView);
            mHours = itemView.findViewById(R.id.hours_display);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface OnClickListener{
        public void onItemClick(View view, int position);
    }
    public void SetAdapterOnclickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
