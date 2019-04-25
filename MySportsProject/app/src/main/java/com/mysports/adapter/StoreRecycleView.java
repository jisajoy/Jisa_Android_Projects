package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mysports.R;
import com.mysports.bean.Store;
import com.mysports.bean.VenueDetails;

import java.util.ArrayList;

public class StoreRecycleView extends RecyclerView.Adapter<StoreRecycleView.StoreViewHolder> {
    Context mContext;
    ArrayList<Store> mStoreList;
    static OnItemClickListener clickListener;
    public StoreRecycleView(Context mContext, ArrayList<Store> mStoreList) {
        this.mContext = mContext;
        this.mStoreList = mStoreList;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardiew_store, parent, false);
        StoreViewHolder storeViewHolder = new StoreViewHolder(view);
        return storeViewHolder;
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        Store store = mStoreList.get(position);
        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Share", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Like", Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(mContext).load(store.getmStoreImage()).into(holder.mStoreImage);
        holder.mProductDetail.setText(store.getmFeatures());
        holder.mProductRate.setText(store.getmRate());
    }

    @Override
    public int getItemCount() {
        return mStoreList.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mStoreImage;
        ImageView mShare;
        ImageView mLike;
        TextView mProductDetail;
        TextView mProductRate;
        LinearLayout mViewMore;

        public StoreViewHolder(View itemView) {
            super(itemView);
            mStoreImage = itemView.findViewById(R.id.store_image);
            mShare = itemView.findViewById(R.id.store_share);
            mLike = itemView.findViewById(R.id.store_like);
            mProductDetail = itemView.findViewById(R.id.about_store_product);
            mProductRate = itemView.findViewById(R.id.store_rate);
            mViewMore = itemView.findViewById(R.id.view_more);
            mViewMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
