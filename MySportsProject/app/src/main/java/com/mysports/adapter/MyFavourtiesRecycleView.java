package com.mysports.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mysports.R;
import com.mysports.activity.MyFavourtiesActivity;
import com.mysports.bean.MyFav;
import com.mysports.bean.Tournament;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/22/2018.
 */

public class MyFavourtiesRecycleView extends RecyclerView.Adapter<MyFavourtiesRecycleView.FavViewHolder> {
    Context mContext;
    ArrayList<MyFav> mFavList;

    public MyFavourtiesRecycleView(MyFavourtiesActivity mContext, ArrayList<MyFav> mFavList) {
        this.mContext = mContext;
        this.mFavList = mFavList;
    }

    @Override
    public FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_favourties, parent, false);
        FavViewHolder viewHolder = new FavViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavViewHolder holder, final int position) {
        MyFav myFav = mFavList.get(position);
        holder.mFavProductName.setText(myFav.getmFavProductName());
        Picasso.with(mContext).load(myFav.getmFavProductImage()).into(holder.mFavProductImage);
        holder.mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Add To Cart", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mTrashFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mFavList.size());
                Toast.makeText(mContext, "Item Removed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavList.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView mFavProductImage;
        TextView mFavProductName;
        CardView mAddToCart;
        ImageView mTrashFav;

        public FavViewHolder(View itemView) {
            super(itemView);
            mFavProductName = itemView.findViewById(R.id.fav_product_name);
            mFavProductImage = itemView.findViewById(R.id.fav_product_image);
            mAddToCart = itemView.findViewById(R.id.add_to_cart);
            mTrashFav = itemView.findViewById(R.id.trash_fav);
        }
    }
}
