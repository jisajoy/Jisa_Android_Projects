package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.bean.BrandBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 3/8/2018.
 */

public class BrandRecycleView extends RecyclerView.Adapter<BrandRecycleView.BrandViewHolder> {
    Context activity;
    ArrayList<BrandBean> brandBeansList;

    public BrandRecycleView(Context activity, ArrayList<BrandBean> brandBeansList) {
        this.activity = activity;
        this.brandBeansList = brandBeansList;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.expandable_view, parent, false);
        BrandViewHolder brandViewHolder = new BrandViewHolder(view);
        return brandViewHolder;
    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {
        BrandBean brandBean = brandBeansList.get(position);
        holder.mTextData.setText(brandBean.getBrandName());
    }

    @Override
    public int getItemCount() {
        return brandBeansList.size();
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder {
        TextView mTextData;
        ImageView mExpandView;
        ImageView mselecter;
        public BrandViewHolder(View itemView) {
            super(itemView);
            mTextData = itemView.findViewById(R.id.list_data);
            mExpandView = itemView.findViewById(R.id.expand_rv);
            mExpandView.setVisibility(View.GONE);//no need in this layout
            mselecter = itemView.findViewById(R.id.selecter);
        }
    }
}
