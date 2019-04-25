package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mysports.R;
import com.mysports.bean.GalleryBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 3/9/2018.
 */

public class ImageGalleryRecycleView extends RecyclerView.Adapter<ImageGalleryRecycleView.ImageGalleryHolder> {
    Context activity;
    ArrayList<GalleryBean> mGalleryList;
    OnItemClickListener onItemClickListener;
    public ImageGalleryRecycleView(Context activity, ArrayList<GalleryBean> mGalleryList) {
        this.activity = activity;
        this.mGalleryList = mGalleryList;
    }

    @Override
    public ImageGalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.image_gallery, parent, false);
        ImageGalleryHolder imageGalleryHolder = new ImageGalleryHolder(view);
        return imageGalleryHolder;
    }

    @Override
    public void onBindViewHolder(ImageGalleryHolder holder, int position) {
        GalleryBean galleryBean = mGalleryList.get(position);
        Picasso.with(activity).load(galleryBean.getImageURL()).placeholder(R.drawable.placeholder).into(holder.mGalleryimage);
    }

    @Override
    public int getItemCount() {
        return mGalleryList.size();
    }

    public class ImageGalleryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mGalleryimage;
        public ImageGalleryHolder(View itemView) {
            super(itemView);
            mGalleryimage = itemView.findViewById(R.id.gallery_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }

    public void SetOnClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClickListener(View view, int position);
    }
}
