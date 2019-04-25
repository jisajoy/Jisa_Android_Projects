package com.mysports.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.activity.CartActivity;
import com.mysports.bean.CartBean;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/25/2018.
 */

public class CartRecycleView extends RecyclerView.Adapter<CartRecycleView.CartViewHolder> {
    Context mContext;
    ArrayList<CartBean> mCartList;

    public CartRecycleView(Context mContext, ArrayList<CartBean> mCartList) {
        this.mContext = mContext;
        this.mCartList = mCartList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_cartproduct, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, final int position) {
        CartBean cartBean = mCartList.get(position);
        holder.mCartProductImage.setImageResource(cartBean.getmImageLogo());
        holder.mCartProductName.setText(cartBean.getmCartProductName());
        holder.mCartProductType.setText(cartBean.getmProductType());
        holder.mCartProductQty.setText(cartBean.getmQuantity());
        holder.mDeleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCartList.remove(position);
                notifyItemRemoved(position);
                notifyItemChanged(position, mCartList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView mCartProductImage;
        TextView mCartProductName;
        TextView mCartProductQty;
        TextView mCartProductType;
ImageView mDeleteCartItem;
        public CartViewHolder(View itemView) {
            super(itemView);
            mCartProductImage = itemView.findViewById(R.id.cart_product_image);
            mCartProductName = itemView.findViewById(R.id.cart_product_name);
            mCartProductQty = itemView.findViewById(R.id.cart_product_qty);
            mCartProductType = itemView.findViewById(R.id.cart_product_type);
            mDeleteCartItem = itemView.findViewById(R.id.delete_cart);
        }
    }
}
