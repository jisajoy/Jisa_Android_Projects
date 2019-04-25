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
import com.mysports.bean.StoreProductList;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 3/6/2018.
 */

public class StoreProductListRV extends RecyclerView.Adapter<StoreProductListRV.StoreProductHolder> {
    Context activity;
    ArrayList<StoreProductList> productLists;

    public StoreProductListRV(Context activity, ArrayList<StoreProductList> productLists) {
        this.activity = activity;
        this.productLists = productLists;
    }

    @Override
    public StoreProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.store_product_list, parent, false);
        StoreProductHolder storeProductHolder = new StoreProductHolder(view);
        return storeProductHolder;
    }

    @Override
    public void onBindViewHolder(StoreProductHolder holder, int position) {
        StoreProductList storeProductList = productLists.get(position);
        holder.mGameImage.setImageResource(storeProductList.getProductImage());
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class StoreProductHolder extends RecyclerView.ViewHolder {
        ImageView mGameImage;

        public StoreProductHolder(View itemView) {
            super(itemView);
            mGameImage = itemView.findViewById(R.id.game_image);
        }
    }
}
